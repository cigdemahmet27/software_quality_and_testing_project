"""
Step 3 (Code Generation) + Step 4 (Base Tests) -- Agentic Pipeline
===================================================================
Reads 30 selected HumanEval prompts, sends them to ChatGPT and Gemini APIs,
saves generated code and base tests to the Maven project structure.

Usage:
    pip install -r scripts/requirements.txt
    python scripts/generate_code.py
"""

import json
import os
import re
import sys
import time
import datetime
from pathlib import Path
from dotenv import load_dotenv
from openai import OpenAI
from google import genai

# Force UTF-8 output on Windows
sys.stdout.reconfigure(encoding="utf-8", errors="replace")

# ------------------------------------------
# Configuration
# ------------------------------------------
PROJECT_ROOT = Path(__file__).resolve().parent.parent
load_dotenv(PROJECT_ROOT / ".env")

OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
OPENAI_MODEL = os.getenv("OPENAI_MODEL", "gpt-4o")
GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
GEMINI_MODEL = os.getenv("GEMINI_MODEL", "gemini-2.5-pro")

PROMPTS_FILE = PROJECT_ROOT / "dataset" / "selected_prompts.json"
SRC_DIR = PROJECT_ROOT / "src" / "main" / "java" / "humaneval"
TEST_DIR = PROJECT_ROOT / "src" / "test" / "java" / "humaneval"
LOG_DIR = PROJECT_ROOT / "llm_logs"

DELAY_BETWEEN_CALLS = 3  # seconds, to respect rate limits

SYSTEM_PROMPT = (
    "You are a Java code generator. You will be given an incomplete Java class "
    "with a method signature and its documentation. Complete the implementation.\n\n"
    "Rules:\n"
    "- Return ONLY the complete, compilable Java code.\n"
    "- Do NOT include markdown code blocks (no ```), explanations, or any text outside the Java code.\n"
    "- Keep all existing imports and the class structure.\n"
    "- Use 'public class Solution' (not package-private).\n"
    "- Do NOT add a package declaration.\n"
    "- Include all necessary imports.\n"
)

# ------------------------------------------
# API Clients
# ------------------------------------------

def init_openai():
    return OpenAI(api_key=OPENAI_API_KEY)


def init_gemini():
    client = genai.Client(api_key=GEMINI_API_KEY)
    return client

# ------------------------------------------
# Code extraction & cleanup
# ------------------------------------------

def extract_java_code(response_text):
    """Extract Java code from LLM response, stripping markdown if present."""
    # Match triple-backtick code blocks (with or without 'java' language tag)
    match = re.search(r"```(?:java)?\s*\n(.*?)\n\s*```", response_text, re.DOTALL)
    if match:
        return match.group(1).strip()
    # Remove any remaining backticks (single or triple) that LLMs sometimes add
    cleaned = response_text.strip()
    cleaned = re.sub(r"^`{1,3}(?:java)?\s*\n?", "", cleaned)
    cleaned = re.sub(r"\n?`{1,3}\s*$", "", cleaned)
    return cleaned.strip()


def add_package_declaration(code, package_name):
    """Add package declaration to top of code if not already present."""
    if f"package {package_name};" in code:
        return code
    # Remove any wrong package declaration the LLM may have added
    code = re.sub(r"^\s*package\s+[\w.]+;\s*\n", "", code)
    return f"package {package_name};\n\n{code}"


def make_class_public(code):
    """Ensure 'class Solution' is 'public class Solution' for cross-package access."""
    if "public class Solution" in code:
        return code
    return code.replace("class Solution", "public class Solution", 1)

# ------------------------------------------
# Base test conversion (dataset format -> JUnit 5)
# ------------------------------------------

def extract_main_body(test_code):
    """Extract the body of the main() method from HumanEval test code."""
    match = re.search(
        r"public\s+static\s+void\s+main\s*\([^)]*\)\s*(?:throws\s+[\w.,\s]+)?\{",
        test_code,
    )
    if not match:
        return "        // Could not extract test body automatically"

    start = match.end()
    brace_count = 1
    i = start
    while i < len(test_code) and brace_count > 0:
        if test_code[i] == "{":
            brace_count += 1
        elif test_code[i] == "}":
            brace_count -= 1
        i += 1
    return test_code[start : i - 1].strip()


def convert_to_junit5(test_code, task_num, llm_name):
    """Convert HumanEval Main-style test to JUnit 5 test class."""
    package = f"humaneval.base.{llm_name}.task{task_num}"
    src_package = f"humaneval.{llm_name}.task{task_num}"
    body = extract_main_body(test_code)

    indented_body = "\n".join(
        "        " + line if line.strip() else ""
        for line in body.split("\n")
    )

    throws_clause = ""
    if "throws" in test_code.split("{")[0]:
        throws_match = re.search(r"throws\s+([\w.,\s]+)", test_code)
        if throws_match:
            throws_clause = f" throws {throws_match.group(1).strip()}"

    return f"""package {package};

import {src_package}.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task {task_num}.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task{task_num}BaseTest {{

    @Test
    void testFromDataset(){throws_clause} {{
{indented_body}
    }}
}}
"""

# ------------------------------------------
# LLM API calls
# ------------------------------------------

def call_openai(client, prompt, task_num):
    """Call OpenAI API and return (code, raw_response, usage_info)."""
    messages = [
        {"role": "system", "content": SYSTEM_PROMPT},
        {"role": "user", "content": prompt},
    ]
    response = client.chat.completions.create(
        model=OPENAI_MODEL,
        messages=messages,
        temperature=0.2,
    )
    raw = response.choices[0].message.content
    usage = {
        "prompt_tokens": response.usage.prompt_tokens,
        "completion_tokens": response.usage.completion_tokens,
    }
    return extract_java_code(raw), raw, usage


def call_gemini(client, prompt, task_num):
    """Call Gemini API and return (code, raw_response, usage_info)."""
    response = client.models.generate_content(
        model=GEMINI_MODEL,
        contents=f"{SYSTEM_PROMPT}\n\n{prompt}",
    )
    raw = response.text
    usage = {"note": "token count not directly available"}
    return extract_java_code(raw), raw, usage

# ------------------------------------------
# File I/O helpers
# ------------------------------------------

def save_java_file(code, llm_name, task_num, is_test=False):
    """Save a Java file to the correct Maven directory."""
    package = f"humaneval.{llm_name}.task{task_num}"

    if is_test:
        base = TEST_DIR / "base" / llm_name / f"task{task_num}"
        filename = f"Task{task_num}BaseTest.java"
    else:
        base = SRC_DIR / llm_name / f"task{task_num}"
        filename = "Solution.java"

    base.mkdir(parents=True, exist_ok=True)
    filepath = base / filename

    if not is_test:
        code = make_class_public(code)
        code = add_package_declaration(code, package)

    filepath.write_text(code, encoding="utf-8")
    print(f"  [OK] Saved {filepath.relative_to(PROJECT_ROOT)}")
    return filepath


def save_log(llm_name, task_num, prompt, raw_response, usage):
    """Save the full API interaction log."""
    log_dir = LOG_DIR / llm_name / "code_generation"
    log_dir.mkdir(parents=True, exist_ok=True)

    log = {
        "timestamp": datetime.datetime.now().isoformat(),
        "task_num": task_num,
        "model": OPENAI_MODEL if llm_name == "llm1" else GEMINI_MODEL,
        "system_prompt": SYSTEM_PROMPT,
        "user_prompt": prompt,
        "raw_response": raw_response,
        "usage": usage,
    }

    log_path = log_dir / f"task{task_num}.json"
    log_path.write_text(json.dumps(log, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"  [OK] Log saved to {log_path.relative_to(PROJECT_ROOT)}")

# ------------------------------------------
# Main pipeline
# ------------------------------------------

def run():
    print("=" * 60)
    print("BLG 475E -- Agentic Code Generation Pipeline")
    print(f"LLM 1: OpenAI {OPENAI_MODEL}")
    print(f"LLM 2: Google {GEMINI_MODEL}")
    print("=" * 60)

    # Validate API keys
    if not OPENAI_API_KEY or "your_" in OPENAI_API_KEY:
        print("ERROR: Set OPENAI_API_KEY in .env")
        return
    if not GEMINI_API_KEY or "your_" in GEMINI_API_KEY:
        print("ERROR: Set GEMINI_API_KEY in .env")
        return

    # Load prompts
    with open(PROMPTS_FILE, "r", encoding="utf-8") as f:
        data = json.load(f)
    prompts = data["prompts"]
    print(f"\nLoaded {len(prompts)} prompts\n")

    # Init API clients
    openai_client = init_openai()
    gemini_client = init_gemini()

    results = {"llm1": {}, "llm2": {}}
    total = len(prompts)

    for idx, (task_num_str, task) in enumerate(prompts.items(), 1):
        task_num = int(task_num_str)
        difficulty = task["difficulty"]
        prompt_text = task["prompt"]
        test_code = task["test"]

        print(f"\n[{idx}/{total}] Task {task_num} ({difficulty})")
        print("-" * 40)

        # Skip if file already exists (resumable)
        llm1_path = SRC_DIR / "llm1" / f"task{task_num}" / "Solution.java"
        llm2_path = SRC_DIR / "llm2" / f"task{task_num}" / "Solution.java"

        # -- LLM 1: ChatGPT --
        if llm1_path.exists():
            print("  [SKIP] LLM1 (ChatGPT) -- already exists")
        else:
            try:
                print("  -> Calling ChatGPT...")
                code, raw, usage = call_openai(openai_client, prompt_text, task_num)
                save_java_file(code, "llm1", task_num)
                save_log("llm1", task_num, prompt_text, raw, usage)
                results["llm1"][task_num] = "success"
                time.sleep(DELAY_BETWEEN_CALLS)
            except Exception as e:
                print(f"  [FAIL] ChatGPT error: {e}")
                results["llm1"][task_num] = f"error: {e}"

        # -- LLM 2: Gemini --
        if llm2_path.exists():
            print("  [SKIP] LLM2 (Gemini) -- already exists")
        else:
            try:
                print("  -> Calling Gemini...")
                code, raw, usage = call_gemini(gemini_client, prompt_text, task_num)
                save_java_file(code, "llm2", task_num)
                save_log("llm2", task_num, prompt_text, raw, usage)
                results["llm2"][task_num] = "success"
                time.sleep(DELAY_BETWEEN_CALLS)
            except Exception as e:
                print(f"  [FAIL] Gemini error: {e}")
                results["llm2"][task_num] = f"error: {e}"

        # -- Base tests (same for both LLMs, just different package) --
        for llm_name in ["llm1", "llm2"]:
            test_path = TEST_DIR / "base" / llm_name / f"task{task_num}" / f"Task{task_num}BaseTest.java"
            if not test_path.exists():
                junit_test = convert_to_junit5(test_code, task_num, llm_name)
                save_java_file(junit_test, llm_name, task_num, is_test=True)

    # -- Summary --
    print("\n" + "=" * 60)
    print("SUMMARY")
    print("=" * 60)
    for llm in ["llm1", "llm2"]:
        label = "ChatGPT" if llm == "llm1" else "Gemini"
        successes = sum(1 for v in results[llm].values() if v == "success")
        errors = sum(1 for v in results[llm].values() if v != "success")
        print(f"{label}: {successes} generated, {errors} errors")

    # Save summary
    summary_path = PROJECT_ROOT / "analysis" / "comparison" / "generation_summary.json"
    summary_path.parent.mkdir(parents=True, exist_ok=True)
    summary_path.write_text(json.dumps(results, indent=2), encoding="utf-8")
    print(f"\nFull summary: {summary_path.relative_to(PROJECT_ROOT)}")


if __name__ == "__main__":
    run()

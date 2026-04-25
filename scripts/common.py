"""
Shared utilities for LLM code generation pipeline.
Used by generate_code_llm1.py and generate_code_llm2.py.
"""

import json
import re
import datetime
from pathlib import Path
from dotenv import load_dotenv

PROJECT_ROOT = Path(__file__).resolve().parent.parent
load_dotenv(PROJECT_ROOT / ".env")

PROMPTS_FILE = PROJECT_ROOT / "dataset" / "selected_prompts.json"
SRC_DIR = PROJECT_ROOT / "src" / "main" / "java" / "humaneval"
TEST_DIR = PROJECT_ROOT / "src" / "test" / "java" / "humaneval"
LOG_DIR = PROJECT_ROOT / "llm_logs"

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
# Code extraction & cleanup
# ------------------------------------------

def extract_java_code(response_text):
    """Extract Java code from LLM response, stripping markdown if present."""
    match = re.search(r"```(?:java)?\s*\n(.*?)\n\s*```", response_text, re.DOTALL)
    if match:
        return match.group(1).strip()
    cleaned = response_text.strip()
    cleaned = re.sub(r"^`{1,3}(?:java)?\s*\n?", "", cleaned)
    cleaned = re.sub(r"\n?`{1,3}\s*$", "", cleaned)
    return cleaned.strip()


def add_package_declaration(code, package_name):
    """Add package declaration to top of code if not already present."""
    if f"package {package_name};" in code:
        return code
    code = re.sub(r"^\s*package\s+[\w.]+;\s*\n", "", code)
    return f"package {package_name};\n\n{code}"


def make_class_public(code):
    """Ensure 'class Solution' is 'public class Solution'."""
    if "public class Solution" in code:
        return code
    return code.replace("class Solution", "public class Solution", 1)


# ------------------------------------------
# Base test conversion
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


def save_log(llm_name, task_num, prompt, raw_response, usage, model_name):
    """Save the full API interaction log."""
    log_dir = LOG_DIR / llm_name / "code_generation"
    log_dir.mkdir(parents=True, exist_ok=True)

    log = {
        "timestamp": datetime.datetime.now().isoformat(),
        "task_num": task_num,
        "model": model_name,
        "system_prompt": SYSTEM_PROMPT,
        "user_prompt": prompt,
        "raw_response": raw_response,
        "usage": usage,
    }

    log_path = log_dir / f"task{task_num}.json"
    log_path.write_text(json.dumps(log, indent=2, ensure_ascii=False), encoding="utf-8")
    print(f"  [OK] Log saved to {log_path.relative_to(PROJECT_ROOT)}")


def load_prompts():
    """Load the 30 selected prompts."""
    with open(PROMPTS_FILE, "r", encoding="utf-8") as f:
        data = json.load(f)
    return data["prompts"]

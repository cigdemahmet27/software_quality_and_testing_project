"""
LLM 2 (ChatGPT) — Code Generation Pipeline
=============================================
Sends 30 HumanEval prompts to OpenAI ChatGPT API,
saves generated code and base tests.

Usage:
    python scripts/generate_code_llm2.py
"""

import os
import sys
import time
from openai import OpenAI

sys.path.insert(0, os.path.dirname(__file__))
from common import (
    PROJECT_ROOT, SRC_DIR, TEST_DIR, SYSTEM_PROMPT,
    extract_java_code, save_java_file, save_log,
    convert_to_junit5, load_prompts,
)

# Force UTF-8 output on Windows
sys.stdout.reconfigure(encoding="utf-8", errors="replace")

LLM_NAME = "llm2"
OPENAI_API_KEY = os.getenv("OPENAI_API_KEY")
OPENAI_MODEL = os.getenv("OPENAI_MODEL", "gpt-4o")

DELAY_BETWEEN_CALLS = 3


def call_openai(client, prompt):
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


def run():
    print("=" * 60)
    print(f"BLG 475E -- LLM 2 Code Generation (ChatGPT {OPENAI_MODEL})")
    print("=" * 60)

    if not OPENAI_API_KEY or "your_" in OPENAI_API_KEY:
        print("ERROR: Set OPENAI_API_KEY in .env")
        return

    prompts = load_prompts()
    print(f"\nLoaded {len(prompts)} prompts\n")

    client = OpenAI(api_key=OPENAI_API_KEY)

    success_count = 0
    error_count = 0
    total = len(prompts)

    for idx, (task_num_str, task) in enumerate(prompts.items(), 1):
        task_num = int(task_num_str)
        difficulty = task["difficulty"]
        prompt_text = task["prompt"]
        test_code = task["test"]

        print(f"\n[{idx}/{total}] Task {task_num} ({difficulty})")
        print("-" * 40)

        # Skip if already exists (resumable)
        src_path = SRC_DIR / LLM_NAME / f"task{task_num}" / "Solution.java"
        if src_path.exists():
            print("  [SKIP] Already exists")
            continue

        try:
            print(f"  -> Calling ChatGPT ({OPENAI_MODEL})...")
            code, raw, usage = call_openai(client, prompt_text)
            save_java_file(code, LLM_NAME, task_num)
            save_log(LLM_NAME, task_num, prompt_text, raw, usage, OPENAI_MODEL)
            success_count += 1
        except Exception as e:
            print(f"  [FAIL] Error: {e}")
            error_count += 1

        # Save base test if not exists
        test_path = TEST_DIR / "base" / LLM_NAME / f"task{task_num}" / f"Task{task_num}BaseTest.java"
        if not test_path.exists():
            junit_test = convert_to_junit5(test_code, task_num, LLM_NAME)
            save_java_file(junit_test, LLM_NAME, task_num, is_test=True)

        # Rate limit
        if idx < total:
            print(f"  [WAIT] {DELAY_BETWEEN_CALLS}s")
            time.sleep(DELAY_BETWEEN_CALLS)

    # Summary
    print("\n" + "=" * 60)
    print(f"DONE — ChatGPT ({OPENAI_MODEL})")
    print(f"  Success: {success_count}/{total}")
    print(f"  Errors:  {error_count}/{total}")
    print(f"  Skipped: {total - success_count - error_count}/{total}")
    print("=" * 60)


if __name__ == "__main__":
    run()

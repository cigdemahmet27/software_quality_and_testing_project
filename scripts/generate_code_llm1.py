"""
LLM 1 (Gemini) — Code Generation Pipeline
===========================================
Sends 30 HumanEval prompts to Google Gemini API,
saves generated code and base tests.

Usage:
    python scripts/generate_code_llm1.py
"""

import os
import sys
import time
from google import genai

sys.path.insert(0, os.path.dirname(__file__))
from common import (
    PROJECT_ROOT, SRC_DIR, TEST_DIR, SYSTEM_PROMPT,
    extract_java_code, save_java_file, save_log,
    convert_to_junit5, load_prompts,
)

# Force UTF-8 output on Windows
sys.stdout.reconfigure(encoding="utf-8", errors="replace")

LLM_NAME = "llm1"
GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
GEMINI_MODEL = os.getenv("GEMINI_MODEL", "gemini-2.5-pro")

# Rate limit: 15 requests/min => 1 request every 4 seconds, use 5s to be safe
DELAY_BETWEEN_CALLS = 5

MAX_RETRIES = 3
RETRY_WAIT = 30  # seconds, doubles each retry


def call_gemini(client, prompt):
    """Call Gemini API with automatic retry on rate limit errors."""
    for attempt in range(MAX_RETRIES + 1):
        try:
            response = client.models.generate_content(
                model=GEMINI_MODEL,
                contents=f"{SYSTEM_PROMPT}\n\n{prompt}",
            )
            raw = response.text
            usage = {"note": "token count not directly available"}
            return extract_java_code(raw), raw, usage
        except Exception as e:
            if "429" in str(e) and attempt < MAX_RETRIES:
                wait = RETRY_WAIT * (2 ** attempt)
                print(f"  [RATE LIMIT] Waiting {wait}s before retry {attempt + 1}/{MAX_RETRIES}...")
                time.sleep(wait)
            else:
                raise


def run():
    print("=" * 60)
    print(f"BLG 475E -- LLM 1 Code Generation (Gemini {GEMINI_MODEL})")
    print("=" * 60)

    if not GEMINI_API_KEY or "your_" in GEMINI_API_KEY:
        print("ERROR: Set GEMINI_API_KEY in .env")
        return

    prompts = load_prompts()
    print(f"\nLoaded {len(prompts)} prompts\n")

    client = genai.Client(api_key=GEMINI_API_KEY)

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

        # Skip if already has content (resumable)
        src_path = SRC_DIR / LLM_NAME / f"task{task_num}" / "Solution.java"
        if src_path.exists() and src_path.stat().st_size > 0:
            print("  [SKIP] Already has content")
            continue

        try:
            print(f"  -> Calling Gemini ({GEMINI_MODEL})...")
            code, raw, usage = call_gemini(client, prompt_text)
            save_java_file(code, LLM_NAME, task_num)
            save_log(LLM_NAME, task_num, prompt_text, raw, usage, GEMINI_MODEL)
            success_count += 1
        except Exception as e:
            print(f"  [FAIL] Error: {e}")
            error_count += 1

        # Save base test if not exists
        test_path = TEST_DIR / "base" / LLM_NAME / f"task{task_num}" / f"Task{task_num}BaseTest.java"
        if not test_path.exists():
            junit_test = convert_to_junit5(test_code, task_num, LLM_NAME)
            save_java_file(junit_test, LLM_NAME, task_num, is_test=True)

        # Rate limit: wait between API calls
        if idx < total:
            print(f"  [WAIT] {DELAY_BETWEEN_CALLS}s (rate limit: 15 req/min)")
            time.sleep(DELAY_BETWEEN_CALLS)

    # Summary
    print("\n" + "=" * 60)
    print(f"DONE — Gemini ({GEMINI_MODEL})")
    print(f"  Success: {success_count}/{total}")
    print(f"  Errors:  {error_count}/{total}")
    print(f"  Skipped: {total - success_count - error_count}/{total}")
    print("=" * 60)


if __name__ == "__main__":
    run()

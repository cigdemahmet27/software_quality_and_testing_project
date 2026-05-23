# Phase 2 — Integration Testing: Step-by-Step Development Plan

## Context

Phase 1 is complete. Phase 2 requires creating a `BookScan` Java class that integrates three HumanEval tasks (#18 substring, #23 string length, #27 upper-lower case), then generating integration tests for it using both LLMs.

The key deliverable unique to Phase 2: **compare code generated from unmodified&combined prompts vs edited&combined prompts**.

---

## Step-by-Step Plan

### Step 1 — Prepare Unmodified & Combined Prompt

**What:** Take the raw HumanEval prompt descriptions for tasks #18, #23, and #27 exactly as they appear in `dataset/selected_prompts.json`, combine them together, and add the BookScan class description from the project spec. Do NOT edit or improve the prompt wording.

**Goal:** This gives us the "unmodified & combined" baseline prompt that the project requires.

**Output files:**
- `llm_logs/phase_2/llm1/class_generation/unmodified_prompt.md`
- `llm_logs/phase_2/llm2/class_generation/unmodified_prompt.md`

**Commit:** `Phase 2 Step 1: Prepare unmodified combined prompt for BookScan class generation`

---

### Step 2 — Generate BookScan with Unmodified Prompt (Both LLMs)

**What:** Send the unmodified prompt from Step 1 to both LLM1 and LLM2. Save the generated `BookScan.java` code exactly as the LLM produces it — no modifications.

**Output files:**
- `src/main/java/humaneval/phase_2/llm1/BookScan.java` (from LLM1)
- `src/main/java/humaneval/phase_2/llm2/BookScan.java` (from LLM2)
- Log the full prompt + response in `llm_logs/phase_2/llm{1,2}/class_generation/unmodified_prompt.md`

**Commit:** `Phase 2 Step 2: Generate BookScan class using unmodified prompts — LLM1 and LLM2`

> [!IMPORTANT]
> The code must compile and contain methods that relate to all three tasks (#18, #23, #27). If a generated class doesn't compile, that's still a valid result — document the error.

---

### Step 3 — Prepare Edited & Combined Prompt

**What:** Now refine/edit the prompt. Improve clarity, add context about how the three methods should work together, specify return types, edge cases, and the overall BookScan purpose more precisely. This is where you show prompt engineering skills.

**Goal:** This gives us the "edited & combined" prompt that should produce better code.

**Output files:**
- `llm_logs/phase_2/llm1/class_generation/edited_prompt.md`
- `llm_logs/phase_2/llm2/class_generation/edited_prompt.md`

**Commit:** `Phase 2 Step 3: Prepare edited combined prompt for BookScan — improved clarity and context`

---

### Step 4 — Generate BookScan with Edited Prompt (Both LLMs)

**What:** Send the edited prompt to both LLMs. Save the generated code. If the unmodified version from Step 2 was the one you want to keep for testing, you can store the edited version separately or overwrite — but you need BOTH versions logged.

**Decision needed:** Do you want to keep both BookScan versions (unmodified and edited) as separate files, or only keep the better one and document the other in logs?

> [!IMPORTANT]
> **Suggested approach:** Keep the edited version as the primary `BookScan.java` and store the unmodified version in the logs. The report comparison only needs the logs and analysis, not both compiled simultaneously.

**Output files:**
- Updated `src/main/java/humaneval/phase_2/llm1/BookScan.java` (edited version)
- Updated `src/main/java/humaneval/phase_2/llm2/BookScan.java` (edited version)
- Log in `llm_logs/phase_2/llm{1,2}/class_generation/edited_prompt.md`
- Comparison notes in `llm_logs/phase_2/llm{1,2}/prompt_comparison/`

**Commit:** `Phase 2 Step 4: Generate BookScan class using edited prompts — LLM1 and LLM2`

---

### Step 5 — Compile & Fix BookScan (If Needed)

**What:** Run `mvn compile` to check if both BookScan classes compile. If there are compilation errors, go back to the LLM with the error messages and ask it to fix. Log all refactoring interactions.

**Output files:**
- Any fixes to `BookScan.java` files
- Refactoring logs in `llm_logs/phase_2/llm{1,2}/class_generation/refactoring_*.md`

**Commit:** `Phase 2 Step 5: Fix compilation errors in BookScan — [describe what was fixed]`

---

### Step 6 — Generate Integration Tests (Both LLMs)

**What:** Ask each LLM to generate JUnit 5 integration tests for their respective BookScan class. The tests should verify:
- Word counting by length works correctly
- Line number tracking is accurate
- The three methods (#18, #23, #27) work together properly
- Edge cases: empty text, no matches, single-word lines, special characters, case sensitivity

**Output files:**
- `src/test/java/humaneval/phase_2/llm1/BookScanIntegrationTest.java`
- `src/test/java/humaneval/phase_2/llm2/BookScanIntegrationTest.java`
- Log in `llm_logs/phase_2/llm{1,2}/integration_testing/initial_tests.md`

**Commit:** `Phase 2 Step 6: Generate integration tests for BookScan — LLM1 and LLM2`

---

### Step 7 — Run Tests & Analyze Results

**What:** Run the integration tests with `mvn test`. Record pass/fail results. If tests fail, decide whether the issue is in the test or in the BookScan code.

**Commands:**
```bash
mvn test -Dtest="humaneval.phase_2.llm1.BookScanIntegrationTest"
mvn test -Dtest="humaneval.phase_2.llm2.BookScanIntegrationTest"
```

**Commit:** `Phase 2 Step 7: Run integration tests — document pass/fail results`

---

### Step 8 — Refactoring Loop (If Tests Fail)

**What:** If tests fail:
1. Feed the error output back to the LLM
2. Ask it to fix the BookScan code or the tests
3. Log every interaction
4. Re-run until tests pass

**Output files:**
- Updated source/test files
- Logs in `llm_logs/phase_2/llm{1,2}/class_generation/refactoring_*.md`

**Commit:** `Phase 2 Step 8: Refactor BookScan after test failures — [describe changes]`

---

### Step 9 — Coverage Analysis (JaCoCo)

**What:** Run tests with JaCoCo to measure branch coverage of the BookScan class.

**Command:**
```bash
mvn test jacoco:report
```

**Output files:**
- Save/screenshot coverage results to `analysis/phase_2/coverage_reports/llm1/` and `llm2/`

**Commit:** `Phase 2 Step 9: Add JaCoCo coverage analysis for BookScan integration tests`

---

### Step 10 — Test Effectiveness Assessment

**What:** Use the same approaches from Phase 1 to verify test effectiveness:
- Equivalence class partitioning for BookScan methods
- Boundary value analysis
- Identify cases where LLM-generated tests are insufficient

**Output files:**
- Analysis notes in `analysis/phase_2/prompt_comparison/`

**Commit:** `Phase 2 Step 10: Assess integration test effectiveness — equivalence classes and BVA`

---

### Step 11 — Prompt Comparison Analysis

**What:** Compare the results of unmodified vs edited prompts:
- Code quality differences
- Test pass rates
- Coverage differences
- Problems unique to each approach

**Output files:**
- Comparison analysis in `analysis/phase_2/prompt_comparison/`

**Commit:** `Phase 2 Step 11: Compare unmodified vs edited prompt results`

---

### Step 12 — Update Report

**What:** Add Phase 2 findings to the final IEEE report:
- BookScan class generation process
- Integration test results
- Prompt comparison analysis
- Coverage analysis results
- Problems encountered in integration testing vs unit testing
- Final report must be ≥ 8 pages total

**Output files:**
- Updated `report/report.tex`

**Commit:** `Phase 2 Step 12: Add integration testing section to final report`

---

## Open Questions

> [!IMPORTANT]
> **Q1:** Which two LLMs are you using for LLM1 and LLM2? I need to know so I can tailor prompts correctly.

> [!IMPORTANT]
> **Q2:** For the prompt comparison, do you want to keep both BookScan versions (unmodified + edited) as separate Java files that both compile, or just keep the final (edited) version in the source tree and document the unmodified version only in logs?

> [!WARNING]
> **Q3:** The project says "Make sure the class contains three methods related with the tasks, namely #18 substring, #23 string length, #27 upper-lower case". Should the BookScan class directly contain `howManyTimes()`, `strlen()`, and `flipCase()` methods internally, or should it import/use the existing Phase 1 Solution classes? The project description says "create a Java class called BookScan", which suggests it should be a self-contained class with these methods built in.

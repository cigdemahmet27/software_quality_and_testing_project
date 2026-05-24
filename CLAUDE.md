# CLAUDE.md — Project Context & Agent Instructions

## Project Overview

**Course:** BLG 475E — Software Quality and Testing (ITU, 2025-2026 Spring)
**Project:** LLM-based Code & Test Generation Evaluation using HumanEval-X Java Dataset
**Language:** Java 17 | **Test Framework:** JUnit 5 (Jupiter) | **Coverage:** JaCoCo 0.8.13
**Build:** Maven (pom.xml) | **Test Smells:** JNose

---

## Project Phases

### Phase 1 (COMPLETED ✅)
- Selected 30 prompts from HumanEval dataset (15 Easy, 10 Moderate, 5 Hard)
- Generated Java code using 2 LLMs (llm1, llm2) — one per LLM
- Ran base tests from dataset, created improved tests (smell + coverage driven), manual tests (equivalence partitioning + BVA)
- Performed refactoring loop when tests failed
- Mandatory tasks for Phase 2: **#18** (substring/howManyTimes), **#23** (strlen), **#27** (flipCase)

### Phase 2 (IN PROGRESS 🔄) — Integration Testing
- **Goal:** Create a `BookScan` class using LLM agents that integrates tasks #18, #23, #27
- **Purpose:** Determine how many times words of a given length appear in a text and in which lines they appear
- **Key deliverables:**
  1. BookScan class (must contain methods related to #18 substring, #23 string length, #27 upper-lower case)
  2. Integration tests for BookScan
  3. Analysis comparing: unmodified&combined prompts vs edited&combined prompts
  4. Final report (min 8 pages, IEEE format)

---

## Repository Structure

```
software_quality_and_testing_project/
├── pom.xml                              # Maven build (JUnit 5 + JaCoCo)
├── CLAUDE.md                            # THIS FILE — agent instructions
├── README.md                            # Project overview
├── .gitignore
│
├── dataset/
│   ├── selected_prompts.json            # 30 selected HumanEval prompts
│   └── SELECTED_PROMPTS_SUMMARY.md      # Quick reference table
│
├── src/main/java/
│   ├── humaneval/
│   │   ├── llm1/task{N}/Solution.java   # Phase 1: LLM1-generated code (30 tasks)
│   │   └── llm2/task{N}/Solution.java   # Phase 1: LLM2-generated code (30 tasks)
│   └── phase_2/
│       ├── llm1/unmodified/BookScan.java # Phase 2: BookScan by LLM1 (unmodified prompt)
│       ├── llm1/edited/BookScan.java     # Phase 2: BookScan by LLM1 (edited prompt)
│       ├── llm2/unmodified/BookScan.java # Phase 2: BookScan by LLM2 (unmodified prompt)
│       └── llm2/edited/BookScan.java     # Phase 2: BookScan by LLM2 (edited prompt)
│
├── src/test/java/
│   ├── humaneval/
│   │   ├── base/llm{1,2}/task{N}/       # Phase 1: Base tests
│   │   ├── improved/llm{1,2}/task{N}/   # Phase 1: Improved tests
│   │   └── manual/llm{1,2}/task{N}/     # Phase 1: Manual tests
│   └── phase_2/
│       ├── llm1/unmodified/BookScanIntegrationTest.java
│       ├── llm1/edited/BookScanIntegrationTest.java
│       ├── llm2/unmodified/BookScanIntegrationTest.java
│       └── llm2/edited/BookScanIntegrationTest.java
│
├── llm_logs/
│   ├── llm1/                            # Phase 1 logs
│   ├── llm2/                            # Phase 1 logs
│   ├── antigravity/                     # Antigravity agent logs
│   └── phase_2/
│       ├── llm1/                        # Phase 2: LLM1 prompts & responses
│       │   ├── class_generation/        # BookScan generation logs
│       │   ├── integration_testing/     # Integration test generation logs
│       │   └── prompt_comparison/       # Unmodified vs edited prompt logs
│       └── llm2/                        # Phase 2: LLM2 prompts & responses
│           ├── class_generation/
│           ├── integration_testing/
│           └── prompt_comparison/
│
├── analysis/
│   ├── equivalence_classes/             # Phase 1: Black-box analysis
│   ├── coverage_reports/llm{1,2}/       # JaCoCo reports
│   ├── test_smell_reports/              # JNose analysis
│   ├── comparison/                      # LLM1 vs LLM2 comparison
│   └── manual_test_reports/
│
├── report/                              # IEEE format LaTeX report
│
├── jnose/                               # JNose tool
└── jnose-core/                          # JNose core library
```

---

## Mandatory Task Methods (Phase 2 Foundation)

### Task #18 — `howManyTimes(String string, String substring)` → int
- Counts overlapping occurrences of a substring in a string
- Example: `howManyTimes("aaaa", "aa")` → 3

### Task #23 — `strlen(String string)` → int
- Returns length of given string
- Example: `strlen("abc")` → 3

### Task #27 — `flipCase(String string)` → String
- Flips lowercase↔uppercase for each character
- Example: `flipCase("Hello")` → "hELLO"

---

## BookScan Class Requirements (Phase 2)

**Primary Purpose:** Determine how many times words of a given length appear in a text and in which lines they appear.

**Must integrate these three methods:**
1. **substring** (#18): Used to find/count word occurrences in text
2. **string length** (#23): Used to filter words by a given length
3. **upper-lower case** (#27): Used for case-insensitive processing or case transformation

**Key comparison required:**
- Generate BookScan using prompts **unmodified & combined** (raw HumanEval prompts pasted together)
- Generate BookScan using prompts **edited & combined** (refined by you)
- Compare the two approaches in the report

---

## Coding Conventions

- **Package naming:** `phase_2.llm1.unmodified`, `phase_2.llm1.edited`, `phase_2.llm2.unmodified`, `phase_2.llm2.edited`
- **Test package naming:** Same as source packages
- **File header format:**
  ```java
  /* @Authors
   * Student Names: Ahmet Enes Çiğdem, Ali Eren Çiftçi, Taha Çali 
   * Student IDs: 150220079, 150220022, 150220050
   */
  ```
- **Consistent style:** Clear indentation, meaningful variable names, comments
- **Test naming:** Descriptive method names (e.g., `testWordCountByLengthInMultiLineText`)

---

## Build & Test Commands

```bash
# Compile
mvn compile

# Run all tests
mvn test

# Run specific test class (examples)
mvn test -Dtest=phase_2.llm1.unmodified.BookScanIntegrationTest
mvn test -Dtest=phase_2.llm1.edited.BookScanIntegrationTest
mvn test -Dtest=phase_2.llm2.unmodified.BookScanIntegrationTest
mvn test -Dtest=phase_2.llm2.edited.BookScanIntegrationTest

# Run all Phase 2 tests
mvn test -Dtest="phase_2.**"

# Run tests with coverage
mvn test jacoco:report
# Report → target/site/jacoco/index.html
```

---

## Commit Message Convention

Each commit must clearly state:
- Which step/phase it belongs to
- What was changed
- Why it was changed

**Examples:**
- `Phase 2: Add BookScan class structure for LLM1 with unmodified prompts`
- `Phase 2: Add integration tests for BookScan LLM1 — word length filtering`
- `Phase 2: Improve BookScan LLM2 prompt — add case-insensitive requirement`
- `Phase 2: Add coverage analysis for BookScan integration tests`

---

## LLM Log Format

Each log file in `llm_logs/` must include:
1. **Full prompt** sent to the agent
2. **Agent's complete response**
3. **Brief note** explaining how the output was used or why it was modified

---

## Critical Reminders

- ⚠️ Do NOT modify Phase 1 code or tests — they are finalized
- ⚠️ All LLM interactions MUST be logged in `llm_logs/phase_2/`
- ⚠️ Commit history must tell the development story — no "update" or "fix" messages
- ⚠️ Final report must be minimum 8 pages (combined Phase 1 + Phase 2)
- ⚠️ Both unmodified and edited prompt approaches must be compared in analysis

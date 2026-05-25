# JaCoCo Test Coverage Report — LLM1: Gemini (BookScan — Unmodified Prompt)

This report documents the branch and line coverage achieved by the integration test suites for Gemini (`LLM1`) unmodified BookScan implementation.

---

## Unmodified Prompt Version
- **Class Path:** `phase_2.llm1.unmodified.BookScan`
- **Line Coverage:** **91.3%** (33/36 lines covered, 3 missed)
- **Branch Coverage:** **83.3%** (25/30 branches covered, 5 missed)
- **Method Coverage:** **100%** (5/5 methods covered)

### Coverage Breakdown:

| Metric | Covered | Missed | Total | Percentage |
| :--- | :---: | :---: | :---: | :---: |
| **Branches** | 25 | 5 | 30 | **83.3%** |
| **Lines** | 33 | 3 | 36 | **91.3%** |
| **Methods** | 5 | 0 | 5 | **100%** |

> **Note:** Package-level coverage (including `BookScan.WordStats` inner class) is lower:
> 85% instruction, 83% branch. The `WordStats.toString()` method and constructor contribute to the package-level miss.

### Missed Elements Detail:
1. **Null check in `howManyTimes` — Line 105:**
   - *Code:* `if (string == null || substring == null || substring.isEmpty())`
   - *Reason:* 3 of 6 branches missed — tests never pass null or empty substring.
2. **Null check in `strlen` — Line 125:**
   - *Code:* `if (string == null)`
   - *Reason:* 1 of 2 branches missed — tests never pass null to strlen.
3. **Null check in `flipCase` — Line 139:**
   - *Code:* `if (string == null)`
   - *Reason:* 1 of 2 branches missed — tests never pass null to flipCase.
4. **`WordStats.toString()` — Line 218:**
   - *Reason:* The `toString()` method is never called during test execution.

---

## Author Context
- **Report Compiled By:** Ali Eren Çiftçi (Student ID: 150220022)
- **Coverage Data Source:** JaCoCo 0.8.13 via `mvn test -Dtest="phase_2.**" jacoco:report`
- **Status:** Complete & Verified

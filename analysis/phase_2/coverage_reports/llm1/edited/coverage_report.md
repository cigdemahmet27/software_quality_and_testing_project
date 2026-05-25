# JaCoCo Test Coverage Report — LLM1: Gemini (BookScan — Edited Prompt)

This report documents the branch and line coverage achieved by the integration test suites for Gemini (`LLM1`) edited BookScan implementation.

---

## Edited Prompt Version
- **Class Path:** `phase_2.llm1.edited.BookScan`
- **Line Coverage:** **96.5%** (55/57 lines covered, 2 missed)
- **Branch Coverage:** **95.0%** (38/40 branches covered, 2 missed)
- **Method Coverage:** **83.3%** (5/6 methods covered, 1 missed)

### Coverage Breakdown:

| Metric | Covered | Missed | Total | Percentage |
| :--- | :---: | :---: | :---: | :---: |
| **Instructions** | 265 | 5 | 270 | **98.1%** |
| **Branches** | 38 | 2 | 40 | **95.0%** |
| **Lines** | 55 | 2 | 57 | **96.5%** |
| **Methods** | 5 | 1 | 6 | **83.3%** |

### Missed Elements Detail:
1. **Unused Constructor (`BookScan()`):**
   - *Code:* Line 15 (Implicit default constructor)
   - *Reason:* All methods in `phase_2.llm1.edited.BookScan` are `public static`. Thus, the class is never instantiated (`new BookScan()`), leaving the constructor uncovered. This is a standard and expected result for static utility classes.
2. **Null check inside `normalizeToLowerCase`:**
   - *Code:* Line 148 `if (text == null) return null;`
   - *Reason:* The `scanByWordLength` method already guards against `null` text at the very beginning of execution (Line 29), ensuring `normalizeToLowerCase` is never invoked with a `null` argument.

---

## Author Context
- **Report Compiled By:** Ali Eren Çiftçi (Student ID: 150220022)
- **Coverage Data Source:** JaCoCo 0.8.13 via `mvn test -Dtest="phase_2.**" jacoco:report`
- **Status:** Complete & Verified

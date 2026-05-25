# JaCoCo Test Coverage Report — LLM2: Claude (BookScan — Unmodified Prompt)

This report documents the branch and line coverage achieved by the integration test suites for Claude (`LLM2`) unmodified BookScan implementation.

---

## Unmodified Prompt Version
- **Class Path:** `phase_2.llm2.unmodified.BookScan`
- **Line Coverage:** **97.4%** (39/40 lines covered, 1 missed)
- **Branch Coverage:** **100%** (24/24 branches covered)
- **Method Coverage:** **83.3%** (5/6 methods covered — `BookScan` class only)

### Coverage Breakdown (BookScan class only):

| Metric | Covered | Missed | Total | Percentage |
| :--- | :---: | :---: | :---: | :---: |
| **Branches** | 24 | 0 | 24 | **100%** |
| **Lines** | 34 | 0 | 34 | **100%** |
| **Methods** | 5 | 0 | 5 | **100%** |

> **Note:** Package-level coverage includes the `BookScan.WordResult` inner class. The `WordResult.toString()` method (1 line, 1 method) is unused during tests, bringing package-level line coverage to 97.4% and method coverage to 85.7% (6/7).

### Analysis:
The BookScan class itself has full branch and line coverage. The only uncovered element is the `toString` method of the `WordResult` helper class (Line 136), which is not called during scanning operations or tests.

---

## Author Context
- **Report Compiled By:** Ali Eren Çiftçi (Student ID: 150220022)
- **Coverage Data Source:** JaCoCo 0.8.13 via `mvn test -Dtest="phase_2.**" jacoco:report`
- **Status:** Complete & Verified

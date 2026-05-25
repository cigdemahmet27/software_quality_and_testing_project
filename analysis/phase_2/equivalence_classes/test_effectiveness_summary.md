# Phase 2 — Test Effectiveness Summary

> Author: Taha Çali (Student ID: 150220050)
> Date: 2026-05-25

---

## 1. Overall Test Effectiveness Comparison

| Metric | LLM1 Unmod (Gemini) | LLM1 Edit (Gemini) | LLM2 Unmod (Claude) | LLM2 Edit (Claude) |
|--------|---------------------|--------------------|--------------------|-------------------|
| **Total Tests** | 7 | 7 | 7 | 7 |
| **Tests Passed** | 7/7 (100%) | 7/7 (100%) | 7/7 (100%) | 7/7 (100%) |
| **Branch Coverage** | 100% (12/12) | 95% (38/40) | 100% (24/24) | 95% (38/40) |
| **Unit-level tests for utility methods** | 0 | 3 | 0 | 3 |
| **Integration-level tests** | 7 | 4 | 7 | 4 |
| **EQ classes covered (howManyTimes)** | 1/10 | 5/10 | 1/10 | 5/10 |
| **EQ classes covered (strlen)** | 0/6 | 3/6 | 0/6 | 3/6 |
| **EQ classes covered (flipCase)** | 3/8 (indirect) | 4/8 | 0/8 | 4/8 |
| **EQ classes covered (main method)** | 7/16 | 7/16 | 7/16 | 7/16 |
| **Boundary values tested (all methods)** | 3/26 | 1/26 | 3/26 | 2/26 |
| **Null input coverage** | Partial | Full | None | Full |

---

## 2. Strengths by Variant

### LLM1 Unmodified (Gemini — Unmodified Prompt)
- ✅ Tests overlapping substring behavior in integration context
- ✅ Verifies `flipCase` indirectly through `flippedWord` field
- ✅ Tests line number tracking with leading empty lines (BV5)
- ✅ Tests special character handling in word extraction
- ✅ 100% branch coverage

### LLM1 Edited (Gemini — Edited Prompt)
- ✅ Dedicated unit tests for all three utility methods
- ✅ Tests null/empty guards comprehensively
- ✅ Tests zero and negative target length (I3, I4)
- ✅ Tests punctuation stripping and case insensitivity
- ✅ Tests substring-inside-word scenario (V6)

### LLM2 Unmodified (Claude — Unmodified Prompt)
- ✅ Tests line number tracking accurately
- ✅ Tests overlapping substring counting
- ✅ Documents known behavioral bug (duplicate counting) in test assertions
- ✅ 100% branch coverage

### LLM2 Edited (Claude — Edited Prompt)
- ✅ Uses `@BeforeEach` for cleaner test structure
- ✅ Dedicated unit tests for all three utility methods
- ✅ Tests deduplication fix specifically (BV10)
- ✅ Tests null/empty/zero/negative edge cases

---

## 3. Weaknesses by Variant

### LLM1 Unmodified (Gemini — Unmodified Prompt)
- ❌ No isolated unit tests for `howManyTimes`, `strlen`, or `flipCase`
- ❌ Doesn't test null/empty inputs for utility methods
- ❌ Doesn't test zero/negative target length
- ❌ Case-sensitive behavior is tested as correct, but it's actually a design limitation

### LLM1 Edited (Gemini — Edited Prompt)
- ❌ No test for line numbering with empty lines (BV5)
- ❌ No test for overlapping substring behavior
- ❌ No test for multiple different words of same length
- ❌ Misses boundary value tests across the board

### LLM2 Unmodified (Claude — Unmodified Prompt)
- ❌ **Critical:** No null safety in `strlen` and `flipCase` — would crash on null input
- ❌ No isolated unit tests for utility methods
- ❌ Documents a known bug but doesn't fix it (count inflation)
- ❌ No punctuation handling — "Hello!" is treated as length 6

### LLM2 Edited (Claude — Edited Prompt)
- ❌ No test for line numbering with empty lines
- ❌ No test for overlapping substring scenarios
- ❌ No test for substring-inside-word behavior
- ❌ Misses most boundary value tests

---

## 4. Comparative Analysis: Unmodified vs Edited Test Quality

| Dimension | Unmodified Tests | Edited Tests | Winner |
|-----------|-----------------|--------------|--------|
| **Test structure** | All integration-level | Mix of unit + integration | **Edited** — better isolation |
| **Null safety coverage** | Partial (LLM1) / None (LLM2) | Full for both | **Edited** |
| **Boundary testing** | 3/26 covered | 1-2/26 covered | **Unmodified** (slightly) |
| **Integration scenarios** | Strong (overlapping, line tracking) | Weak (focus on edge cases) | **Unmodified** |
| **Bug detection ability** | Found real bugs | Tests pass-by-design | **Unmodified** |
| **Code quality** | Basic assertions | Cleaner structure | **Edited** |

### Key Insight
Unmodified and edited tests have **complementary strengths**. Unmodified tests are better at testing real integration scenarios (overlapping substrings, line tracking, multi-word interactions) while edited tests are better at isolated correctness and edge-case guards. Neither achieves comprehensive boundary value coverage.

---

## 5. Recommendations for Test Improvement

### High Priority (Missing Critical Coverage)
1. **Add null-safety tests for LLM2 unmodified utility methods** — `strlen(null)` and `flipCase(null)` would crash
2. **Add targetLength=0 and targetLength=-1 tests for unmodified versions** — no guards exist
3. **Add whitespace/newline-only input tests** — untested across all variants

### Medium Priority (Missing Boundary Coverage)
4. **Add trailing newline test** — verifies line count doesn't include phantom empty line
5. **Add targetLength=1 test** — single-character word matching
6. **Add single-occurrence substring test for `howManyTimes`** — the simplest valid case is untested in isolation
7. **Add substring-longer-than-text test** — verifies graceful handling

### Low Priority (Defensive/Robustness)
8. **Add very large text test** — performance and correctness at scale
9. **Add Unicode character tests** — `flipCase` behavior with non-ASCII letters
10. **Add `flipCase` boundary character tests** — characters adjacent to letter ranges

---

## 6. Summary Statistics

| Category | Total Defined | Covered by ≥1 Variant | Coverage Rate |
|----------|:------------:|:---------------------:|:------------:|
| **Equivalence Classes** | 40 | 21 | **52.5%** |
| **Boundary Values** | 26 | 5 | **19.2%** |
| **Total Test Conditions** | 66 | 26 | **39.4%** |

**Conclusion:** The LLM-generated integration tests achieve good branch coverage (95-100%) but only cover **39.4%** of systematically identified equivalence classes and boundary values. This demonstrates that **high branch coverage does not guarantee comprehensive test effectiveness** — a key finding for the report.

# Phase 2 — Prompt Comparison Analysis: Unmodified vs Edited

> Author: Taha Çali (Student ID: 150220050)
> Date: 2026-05-25

---

## 1. Overall Comparison Table

| Metric | LLM1 Gemini (Unmodified) | LLM1 Gemini (Edited) | LLM2 Claude (Unmodified) | LLM2 Claude (Edited) |
|--------|:------------------------:|:--------------------:|:------------------------:|:--------------------:|
| **Compiles?** | ✅ Yes | ✅ Yes | ✅ Yes | ✅ Yes |
| **Lines of code** | 140 | 161 | 141 | 244 |
| **Methods generated** | 5 (3 utility + 1 main + 1 helper toString) | 6 (3 utility + 1 main + 1 helper + 1 implicit ctor) | 7 (3 utility + 1 main + 1 toString + 1 inner ctor + 1 scan) | 7 (3 utility + 1 main + 1 helper + 1 inner ctor + 1 implicit ctor) |
| **Main method name** | `analyzeText` | `scanByWordLength` | `scan` | `scanByWordLength` |
| **Return type** | `Map<String, WordStats>` | `Map<String, Map<String, Object>>` | `Map<String, WordResult>` | `Map<String, Map<String, Object>>` |
| **Static vs Instance** | Instance | Static | Instance | Instance |
| **Case sensitive?** | ✅ Yes (bug) | ❌ No (normalized) | ❌ No (normalized) | ❌ No (normalized) |
| **Strips punctuation?** | ✅ Yes (`\\W+` split) | ✅ Yes (regex strip) | ❌ No | ✅ Yes (regex strip) |
| **Null safety** | ✅ Full | ✅ Full | ❌ Partial | ✅ Full |
| **Handles targetLength ≤ 0?** | ❌ No guard | ✅ Yes | ❌ No guard | ✅ Yes |
| **Deduplicates per-line?** | ✅ Yes (first seen) | ✅ Yes (HashSet) | ❌ No (bug) | ✅ Yes (HashSet) |
| **Uses flipCase in integration?** | ✅ For display | ✅ For normalization | ✅ For normalization | ✅ For normalization |
| **Tests passed** | 7/7 | 7/7 | 7/7 | 7/7 |
| **Branch coverage** | 100% (12/12) | 95% (38/40) | 100% (24/24) | 95% (38/40) |
| **Known bugs** | Substring false positives, case-sensitive | None | Duplicate counting, no null safety | None |
| **JavaDoc quality** | Basic comments | Full JavaDoc | HumanEval-style docstrings | Full JavaDoc with `@param`, `@return`, `{@link}` |

---

## 2. Detailed Analysis by Dimension

### 2.1 Code Compilability

All four variants compiled successfully on the first attempt. Neither the unmodified nor the edited prompts produced code that required compilation fixes. This suggests that both Gemini and Claude are highly capable of producing syntactically correct Java code regardless of prompt quality.

**Verdict:** Tie — no difference.

### 2.2 API Design

| Aspect | Unmodified (Both LLMs) | Edited (Both LLMs) |
|--------|----------------------|-------------------|
| **Method naming** | LLM1: `analyzeText`, LLM2: `scan` — each chose independently | Both: `scanByWordLength` — followed prompt suggestion |
| **Return type** | Custom inner class (`WordStats` / `WordResult`) | `Map<String, Map<String, Object>>` — followed prompt spec |
| **Inner class** | Rich data classes with fields + toString | No inner class; uses generic Map structure |

**Analysis:** The edited prompt produced consistent APIs across both LLMs because it explicitly specified the method name and return type. The unmodified prompt gave each LLM freedom, resulting in divergent designs. The unmodified versions' custom inner classes (`WordStats`, `WordResult`) are arguably better OOP design than the generic `Map<String, Object>` of the edited versions, but the edited approach is more standardized and testable.

**Verdict:** Edited wins for consistency; unmodified wins for type-safety.

### 2.3 Null and Edge-Case Handling

| Guard | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|-------|:----------:|:---------:|:----------:|:---------:|
| `howManyTimes(null, ...)` | ✅ Returns 0 | ✅ Returns 0 | ❌ NPE | ✅ Returns 0 |
| `strlen(null)` | ✅ Returns 0 | ✅ Returns 0 | ❌ NPE | ✅ Returns 0 |
| `flipCase(null)` | ✅ Returns null | ✅ Returns null | ❌ NPE | ✅ Returns null |
| `main(null, n)` | ✅ Returns empty | ✅ Returns empty | ✅ Returns empty | ✅ Returns empty |
| `main("text", 0)` | ❌ May match empty tokens | ✅ Returns empty | ❌ No guard | ✅ Returns empty |
| `main("text", -1)` | ❌ No guard | ✅ Returns empty | ❌ No guard | ✅ Returns empty |

**Analysis:** The edited prompt's explicit requirement to "handle null inputs gracefully" directly produced null-safe code in both LLMs. The unmodified prompt left this unspecified — Gemini (LLM1) added null guards proactively, while Claude (LLM2) did not. This is the **single most impactful improvement** from prompt editing.

**Verdict:** Edited wins decisively — especially for Claude/LLM2.

### 2.4 Case Sensitivity

- **LLM1 Unmodified:** Case-sensitive. `"Cat"`, `"cat"`, and `"CAT"` are three separate entries. This is arguably a bug for a text scanner, since the project spec says the class should find "how many times words of a given length appear" — implying case-insensitive matching.
- **LLM1 Edited:** Case-insensitive via `normalizeToLowerCase()` helper that delegates to `flipCase`.
- **LLM2 Unmodified:** Case-insensitive via `flipCase(word).toLowerCase()` — works but somewhat redundant approach.
- **LLM2 Edited:** Case-insensitive via `toLowerUsingFlipCase()` helper — cleaner approach.

**Analysis:** The edited prompt's explicit requirement for "case-insensitive matching by using case transformation" fixed the case-sensitivity issue in LLM1's output. LLM2 independently chose case-insensitive matching even with the unmodified prompt, suggesting Claude inferred the requirement better from context.

**Verdict:** Edited wins for LLM1; tie for LLM2.

### 2.5 Punctuation Handling

| Approach | Behavior | Example: `"Hello!"` with targetLength=5 |
|----------|----------|--------------------------------------|
| LLM1 Unmod (`\\W+` split) | Splits on non-word chars — strips punctuation implicitly | ✅ "Hello" extracted (length 5) |
| LLM1 Edit (regex strip) | `replaceAll("[^a-zA-Z0-9]", "")` then measures | ✅ "Hello" extracted (length 5) |
| LLM2 Unmod (`\\s+` split) | Splits on whitespace only — keeps punctuation | ❌ "Hello!" kept (length 6, no match) |
| LLM2 Edit (regex strip) | `replaceAll("[^a-zA-Z0-9]", "")` then measures | ✅ "Hello" extracted (length 5) |

**Analysis:** This is a significant behavioral difference. LLM2 unmodified's failure to strip punctuation means it will miss words that appear with trailing punctuation (very common in real text). LLM1 unmodified handled this indirectly through its `\\W+` word-splitting regex. The edited prompt's mention of "punctuation attached to words" prompted both LLMs to add explicit stripping.

**Verdict:** Edited wins, especially for LLM2.

### 2.6 Bug Presence

| Bug | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|-----|:----------:|:---------:|:----------:|:---------:|
| **Case-sensitive word grouping** | ✅ Present | ❌ Fixed | ❌ Absent | ❌ Absent |
| **Substring false positives** | ✅ Present | ✅ Present* | ❌ Absent | ✅ Present* |
| **Duplicate token counting** | ❌ Absent | ❌ Absent | ✅ Present | ❌ Fixed |
| **Null pointer crashes** | ❌ Absent | ❌ Absent | ✅ Present | ❌ Fixed |
| **No targetLength validation** | ✅ Present | ❌ Fixed | ✅ Present | ❌ Fixed |

*Substring false positives: `howManyTimes` counts "java" inside "javascript" when searching the normalized line. This is by-design behavior of `howManyTimes` (it counts overlapping substrings) but produces inflated counts when words are substrings of others in the same line.

**Analysis:** The edited versions eliminated 4 out of 5 bug categories. The only remaining issue (substring false positives) is inherent to the `howManyTimes` design and would require a fundamentally different counting approach (word-boundary-aware matching) to fix.

**Verdict:** Edited wins — eliminated most bugs.

### 2.7 Code Quality and Documentation

| Aspect | Unmodified | Edited |
|--------|-----------|--------|
| **Comments** | LLM1: Section headers + brief Javadoc; LLM2: HumanEval-style docstrings | Both: Full JavaDoc with `@param`, `@return`, examples |
| **Code organization** | Section separators with `====` | Section separators + class-level Javadoc |
| **Naming** | LLM1: `analyzeText`, `WordStats`; LLM2: `scan`, `WordResult` | Both: `scanByWordLength`, generic maps |
| **Helper methods** | None (LLM1 unmod); `toString()` override (both) | `normalizeToLowerCase` / `toLowerUsingFlipCase` |
| **Builder capacity** | LLM1: no capacity hint; LLM2: no capacity hint | Both: `new StringBuilder(string.length())` |

**Verdict:** Edited wins — significantly better documentation and code structure.

### 2.8 Test Quality (from Step 10 Analysis)

| Metric | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|--------|:----------:|:---------:|:----------:|:---------:|
| **EQ classes covered** | 11/40 (28%) | 19/40 (48%) | 8/40 (20%) | 17/40 (43%) |
| **Boundary values covered** | 3/26 (12%) | 1/26 (4%) | 3/26 (12%) | 2/26 (8%) |
| **Unit tests for utilities** | 0 | 3 | 0 | 3 |
| **Strongest area** | Integration scenarios | Edge cases + isolation | Integration scenarios | Edge cases + isolation |

**Verdict:** Edited versions test more equivalence classes overall but both miss boundary values. Unmodified versions are better at integration-level scenarios.

---

## 3. Summary Scorecard

| Dimension | Winner |
|-----------|--------|
| Compilability | Tie |
| API consistency | **Edited** |
| Type safety | **Unmodified** |
| Null safety | **Edited** ⭐ |
| Case handling | **Edited** |
| Punctuation handling | **Edited** |
| Bug-free code | **Edited** ⭐ |
| Code documentation | **Edited** |
| Test equivalence coverage | **Edited** |
| Test boundary coverage | **Unmodified** (slight) |
| Integration test depth | **Unmodified** |

**Overall Winner: Edited prompts** — The edited prompts produced superior code in 8 out of 11 dimensions, with the most critical improvements in null safety and bug prevention. The unmodified prompts only won in type safety (custom inner classes vs generic maps) and integration test depth (testing more realistic scenarios).

---

## 4. LLM-Specific Observations

### Gemini (LLM1)
- **Unmodified:** Proactively added null guards even though not requested. Chose `\\W+` split which coincidentally handled punctuation correctly. However, failed to implement case normalization, producing a case-sensitive scanner.
- **Edited:** Made all methods `static` (neither requested nor harmful). Followed the suggested method name and return type exactly. Added `normalizeToLowerCase` helper that explicitly uses `flipCase`.
- **Takeaway:** Gemini responds well to structural guidance but makes safe-but-incomplete decisions when given minimal instructions.

### Claude (LLM2)
- **Unmodified:** Did NOT add null guards for utility methods (following the HumanEval pattern literally). Used `flipCase` + `toLowerCase()` for normalization — slightly redundant. Had a duplicate counting bug. Did not strip punctuation.
- **Edited:** Added comprehensive null guards, JavaDoc, deduplication, punctuation stripping, and a clean `toLowerUsingFlipCase` helper. Produced the most well-documented and cleanly structured code of all four variants.
- **Takeaway:** Claude follows instructions literally — when given minimal prompts, it produces minimal code. When given detailed prompts, it produces the highest-quality output.

### Key Insight
The delta between unmodified and edited was **larger for Claude (LLM2)** than for Gemini (LLM1). This suggests that Claude is more sensitive to prompt quality — it follows instructions precisely but doesn't proactively add missing requirements. Gemini is more "opinionated" and adds safety measures independently, making prompt quality slightly less critical.

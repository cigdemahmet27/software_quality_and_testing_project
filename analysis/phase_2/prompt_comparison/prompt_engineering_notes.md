# Phase 2 — Prompt Engineering Notes

> Author: Taha Çali (Student ID: 150220050)
> Date: 2026-05-25

---

## 1. Unmodified Prompt Design

The unmodified prompt was constructed by:
1. Taking the raw HumanEval prompt descriptions for tasks #18, #23, and #27 **exactly as they appear** in the dataset
2. Pasting them together in a single prompt
3. Adding the BookScan class description from the project specification
4. Adding the file header requirement

**Intentionally NOT included:**
- No specification of case-insensitive behavior
- No specification of return types for the main method
- No mention of null handling
- No edge case requirements
- No punctuation handling guidance
- No deduplication requirements
- No JavaDoc requirements

---

## 2. Edited Prompt Design

The edited prompt was refined with the following specific changes:

### Change 1: Explicit Class Purpose Statement
**Before (unmodified):**
> "The class should integrate these three methods to fulfill the primary purpose..."

**After (edited):**
> "`BookScan` processes multi-line text (where lines are separated by `\n`) to:
> 1. Find all words of a specified length...
> 2. Count how many times each such word appears (including overlapping)...
> 3. Track which line numbers...
> 4. Support case-insensitive matching..."

**Impact:** Both LLMs produced clearer code structure following the enumerated processing pipeline. This was especially impactful for Claude (LLM2), which followed the pipeline step-by-step in its implementation.

### Change 2: Specified Return Type
**Before (unmodified):** No return type guidance.

**After (edited):**
> "`Map<String, Map<String, Object>>` or similar structure containing: For each unique word... its total count and the list of line numbers"

**Impact:** Both LLMs used the exact suggested return type. This eliminated API divergence — unmodified versions used different return types (`WordStats` vs `WordResult`), making comparison harder.

### Change 3: Case-Insensitive Matching Requirement
**Before (unmodified):** No mention of case handling.

**After (edited):**
> "Support case-insensitive matching by using case transformation"
> "Use `flipCase` to normalize text for case-insensitive comparison"

**Impact:** This fixed the case-sensitivity bug in Gemini's (LLM1) output. Without this, LLM1 unmodified treated "Cat", "cat", "CAT" as separate words. Claude (LLM2) independently chose case-insensitive matching even without this guidance, but the edited prompt produced a cleaner normalization approach.

### Change 4: Null Handling Requirement
**Before (unmodified):** No mention of null handling.

**After (edited):**
> "Handle null inputs gracefully (return empty results or throw IllegalArgumentException)"

**Impact:** This was the **most critical change**. Claude's (LLM2) unmodified version lacked null guards on `strlen()` and `flipCase()`, which would crash on null input. The edited prompt ensured both LLMs added comprehensive null safety.

### Change 5: Edge Case Enumeration
**Before (unmodified):** No edge cases mentioned.

**After (edited):**
> "Edge Cases to Handle:
> - Empty text → return empty results
> - No words matching the target length → return empty results
> - Case variations... should be treated as the same word
> - Punctuation attached to words..."

**Impact:** Both edited versions handle zero/negative target length (returning empty results). Both strip punctuation. The unmodified versions had no guard for `targetLength ≤ 0` and Claude's unmodified version didn't strip punctuation.

### Change 6: Processing Pipeline Description
**Before (unmodified):** No processing steps described.

**After (edited):**
> "1. Split the text into lines (by `\n`)
> 2. Split each line into words (by whitespace)
> 3. Use `strlen` to filter words...
> 4. Use `flipCase` to normalize...
> 5. Use `howManyTimes` to count occurrences..."

**Impact:** Both LLMs followed this pipeline almost exactly. The explicit mention of how each utility method should be used in the integration flow ensured that all three methods played meaningful roles in the main method — rather than just being present as dead code.

### Change 7: Enhanced Examples for howManyTimes
**Before (unmodified):** Original docstring examples only.

**After (edited):**
> "howManyTimes('aaaa', 'aa') → 3 (positions 0, 1, 2)"

**Impact:** Adding the positional explanation helped both LLMs implement the overlapping logic correctly using `idx++` instead of `idx += substring.length()`.

### Change 8: JavaDoc Requirement
**Before (unmodified):** No documentation requirement.

**After (edited):**
> "Include clear JavaDoc comments for all public methods"

**Impact:** Both edited versions have comprehensive JavaDoc with `@param`, `@return`, and code examples. Claude's edited version even included `{@link}` references and HTML formatting in JavaDoc — the highest documentation quality of all four variants.

---

## 3. Impact Summary

| Prompt Change | LLM1 Gemini Impact | LLM2 Claude Impact |
|---------------|:------------------:|:------------------:|
| Class purpose statement | Moderate — clearer structure | **High** — followed pipeline exactly |
| Specified return type | **High** — changed from custom class to Map | **High** — changed from custom class to Map |
| Case-insensitive requirement | **Critical** — fixed a bug | Low — already case-insensitive |
| Null handling requirement | Low — already had null guards | **Critical** — added missing null guards |
| Edge case enumeration | Moderate — added targetLength validation | **High** — added punctuation stripping + validation |
| Processing pipeline | Moderate — cleaner flow | **High** — exact pipeline implementation |
| JavaDoc requirement | Moderate — better docs | **High** — comprehensive JavaDoc |

---

## 4. Lessons Learned

1. **Explicit requirements prevent bugs.** The two most critical bugs (LLM1's case sensitivity, LLM2's null crashes) were both fixed simply by adding one sentence to the prompt.

2. **LLMs differ in proactive behavior.** Gemini adds safety measures independently (null checks even when not asked). Claude follows instructions literally — if you don't ask for null safety, you don't get it. This means prompt quality matters **more** for Claude.

3. **Return type specification ensures consistency.** Without it, each LLM chose its own data structure. With it, both produced identical interfaces — essential for fair comparison.

4. **Processing pipeline descriptions produce cleaner code.** When you enumerate the steps, LLMs organize their code to match, producing more readable and maintainable implementations.

5. **Edge case enumeration is not enough for boundary values.** Even the edited prompt's edge case list only covers basic scenarios. Neither LLM generated tests for boundary values like `targetLength=1`, trailing newlines, or Unicode characters — suggesting that test generation needs even more specific boundary value guidance than code generation.

6. **The "prompt sensitivity gap" varies by LLM.** Claude showed a much larger quality improvement from prompt editing than Gemini, making it the LLM where prompt engineering has the highest ROI.

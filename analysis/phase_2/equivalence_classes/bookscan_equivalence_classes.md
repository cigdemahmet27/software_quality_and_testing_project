# Phase 2 — Equivalence Classes and Boundary Value Analysis for BookScan

> Author: Taha Çali (Student ID: 150220050)
> Date: 2026-05-25
> Scope: All 4 BookScan variants (LLM1/LLM2 × Unmodified/Edited)

---

## 1. `howManyTimes(String text, String substring)` — Task #18

**Specification:** Count how many times a given substring appears in the original string, including overlapping occurrences.

### 1.1 Equivalence Classes

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Substring found exactly once | `"abcdef", "cd"` | `1` |
| **Valid** | V2 | Substring found multiple times (non-overlapping) | `"abcabc", "abc"` | `2` |
| **Valid** | V3 | Overlapping occurrences | `"aaaa", "aa"` | `3` |
| **Valid** | V4 | Substring not found in text | `"abcdef", "xyz"` | `0` |
| **Valid** | V5 | Single character substring, multiple matches | `"aaa", "a"` | `3` |
| **Invalid** | I1 | Empty text with non-empty substring | `"", "a"` | `0` |
| **Invalid** | I2 | Null text | `null, "a"` | `0` |
| **Invalid** | I3 | Null substring | `"abc", null` | `0` |
| **Invalid** | I4 | Empty substring | `"abc", ""` | `0` |
| **Invalid** | I5 | Substring longer than text | `"ab", "abcde"` | `0` |

### 1.2 Boundary Values

| BV ID | Boundary | Test Value | Expected |
|-------|----------|------------|----------|
| BV1 | Substring at start of text | `"hello world", "hello"` | `1` |
| BV2 | Substring at end of text | `"hello world", "world"` | `1` |
| BV3 | Substring equals entire text | `"hello", "hello"` | `1` |
| BV4 | Single character text and substring (match) | `"a", "a"` | `1` |
| BV5 | Single character text and substring (no match) | `"a", "b"` | `0` |
| BV6 | Maximum overlap (substring length n-1 in text length n) | `"aaaa", "aaa"` | `2` |
| BV7 | Case-sensitive boundary | `"aaa", "A"` | `0` |

### 1.3 Test Coverage Mapping

| Class/BV | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|----------|-----------|-----------|-----------|-----------|
| V1 | — | — | — | — |
| V2 | — | — | — | — |
| V3 | ✅ `testAnalyzeText_overlappingSubstrings` | ✅ `testHowManyTimes` (aaaa/aa) | ✅ `testScan_overlappingSubstrings` | ✅ `testHowManyTimes` (aaaa/aa) |
| V4 | — | — | — | — |
| V5 | — | ✅ `testHowManyTimes` (aaa/a) | — | ✅ `testHowManyTimes` (aaa/a) |
| I1 | — | ✅ `testHowManyTimes` (""/a) | — | ✅ `testHowManyTimes` (""/a) |
| I2 | — | ✅ `testHowManyTimes` (null/a) | — | ✅ `testHowManyTimes` (null/a) |
| I3 | — | ✅ `testHowManyTimes` (aaa/null) | — | ✅ `testHowManyTimes` (aaa/null) |
| I4 | — | ✅ `testHowManyTimes` (aaa/"") | — | ✅ `testHowManyTimes` (aaa/"") |
| I5 | — | — | — | — |
| BV1–BV7 | — | — | — | — |

### 1.4 Gap Analysis

- **Unmodified versions (both LLMs):** Do NOT test `howManyTimes` in isolation at all — they only exercise it indirectly through the main scan method. This means invalid inputs (null, empty) are never tested for `howManyTimes` directly. LLM2 unmodified doesn't even guard against null/empty substring.
- **Edited versions:** Both test `howManyTimes` directly with null/empty guards, but **neither version tests V1 (single occurrence), V2 (non-overlapping), V4 (not found), I5 (substring longer than text), or any boundary values** directly.
- **Overall:** Boundary values BV1–BV7 are completely untested across all 4 variants.

---

## 2. `strlen(String string)` — Task #23

**Specification:** Return the length of the given string.

### 2.1 Equivalence Classes

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Non-empty string | `"hello"` | `5` |
| **Valid** | V2 | String with spaces | `"hi there"` | `8` |
| **Valid** | V3 | String with special characters | `"a!@#"` | `4` |
| **Valid** | V4 | String with Unicode characters | `"café"` | `4` |
| **Invalid** | I1 | Empty string | `""` | `0` |
| **Invalid** | I2 | Null string | `null` | `0` |

### 2.2 Boundary Values

| BV ID | Boundary | Test Value | Expected |
|-------|----------|------------|----------|
| BV1 | Single character | `"a"` | `1` |
| BV2 | Very long string | `"a".repeat(10000)` | `10000` |

### 2.3 Test Coverage Mapping

| Class/BV | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|----------|-----------|-----------|-----------|-----------|
| V1 | — (indirect) | ✅ `testStrlen` ("hello") | — (indirect) | ✅ `testStrlen` ("hello") |
| V2 | — | — | — | — |
| V3 | — | — | — | — |
| V4 | — | — | — | — |
| I1 | — | ✅ `testStrlen` ("") | — | ✅ `testStrlen` ("") |
| I2 | — | ✅ `testStrlen` (null) | — | ✅ `testStrlen` (null) |
| BV1 | — | — | — | — |
| BV2 | — | — | — | — |

### 2.4 Gap Analysis

- **Unmodified versions:** Neither LLM tests `strlen` in isolation. It is only exercised indirectly through the main scan method. Critically, **LLM2 unmodified does NOT guard null** — `strlen(null)` would throw a NullPointerException.
- **Edited versions:** Both test empty and null, plus a standard string. But strings with spaces (V2), special characters (V3), Unicode (V4), and boundary values (BV1, BV2) are never tested.
- **Critical finding for LLM2 unmodified:** The `strlen` method simply returns `string.length()` without null check. If null is passed, it crashes. This is a deficiency the unmodified prompt failed to address.

---

## 3. `flipCase(String string)` — Task #27

**Specification:** Flip lowercase characters to uppercase and uppercase to lowercase. Non-alphabetic characters remain unchanged.

### 3.1 Equivalence Classes

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Mixed case string | `"Hello"` | `"hELLO"` |
| **Valid** | V2 | All lowercase | `"abc"` | `"ABC"` |
| **Valid** | V3 | All uppercase | `"ABC"` | `"abc"` |
| **Valid** | V4 | String with digits and special chars | `"123!@#AbC"` | `"123!@#aBc"` |
| **Valid** | V5 | String with spaces | `"Hello World"` | `"hELLO wORLD"` |
| **Invalid** | I1 | Empty string | `""` | `""` |
| **Invalid** | I2 | Null string | `null` | `null` |
| **Invalid** | I3 | String with only digits/symbols | `"123!@#"` | `"123!@#"` |

### 3.2 Boundary Values

| BV ID | Boundary | Test Value | Expected |
|-------|----------|------------|----------|
| BV1 | Single lowercase character | `"a"` | `"A"` |
| BV2 | Single uppercase character | `"Z"` | `"z"` |
| BV3 | Alphabet boundary: `'z'` | `"z"` | `"Z"` |
| BV4 | Alphabet boundary: `'A'` | `"A"` | `"a"` |
| BV5 | Characters adjacent to letter range: `'@'`, `'['` | `"@["` | `"@["` (unchanged) |

### 3.3 Test Coverage Mapping

| Class/BV | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|----------|-----------|-----------|-----------|-----------|
| V1 | ✅ `testAnalyzeText_caseSensitivityAndFlipCase` (indirect) | ✅ `testFlipCase` ("Hello") | — (indirect) | ✅ `testFlipCase` ("Hello") |
| V2 | ✅ (indirect via "world"→"WORLD") | — | — | — |
| V3 | ✅ (indirect via "CAT"→"cat") | — | — | — |
| V4 | — | ✅ `testFlipCase` ("123!@#AbC") | — | ✅ `testFlipCase` ("123!@#AbC") |
| V5 | — | — | — | — |
| I1 | — | ✅ `testFlipCase` ("") | — | ✅ `testFlipCase` ("") |
| I2 | — | ✅ `testFlipCase` (null) | — | ✅ `testFlipCase` (null) |
| I3 | ✅ (indirect via "1234"→"1234") | — | — | — |
| BV1–BV5 | — | — | — | — |

### 3.4 Gap Analysis

- **Unmodified versions:** Only test `flipCase` indirectly through the main method's `flippedWord` field. LLM2 unmodified does NOT guard null — `flipCase(null)` would throw NPE.
- **Edited versions:** Test null, empty, mixed case, and digits/special chars. But V2 (all lowercase), V3 (all uppercase), V5 (spaces), and all boundary values are untested.
- **Neither version** tests boundary characters adjacent to the letter range (BV5), which could reveal issues if character comparison used `<` / `>` instead of `isUpperCase` / `isLowerCase`.

---

## 4. Main Integration Method — `scanByWordLength` / `analyzeText` / `scan`

This is the primary integration method. Each variant has a different name, return type, and behavior.

### 4.0 Variant Summary

| Variant | Method Name | Return Type | Case Sensitive? | Strips Punctuation? | Deduplicates? |
|---------|------------|-------------|-----------------|---------------------|---------------|
| LLM1 Unmodified | `analyzeText` | `Map<String, WordStats>` | ✅ Yes | ✅ Yes (`\\W+` split) | ✅ Yes (first seen) |
| LLM1 Edited | `scanByWordLength` (static) | `Map<String, Map<String, Object>>` | ❌ No (normalized) | ✅ Yes (regex strip) | ✅ Yes (`processedWordsInLine`) |
| LLM2 Unmodified | `scan` | `Map<String, WordResult>` | ❌ No (normalized) | ❌ No | ❌ No (bug: duplicate counting) |
| LLM2 Edited | `scanByWordLength` | `Map<String, Map<String, Object>>` | ❌ No (normalized) | ✅ Yes (regex strip) | ✅ Yes (`processedOnLine`) |

### 4.1 Equivalence Classes (Applicable to All Variants)

| Type | ID | Class Description | Input Example | Expected |
|------|----|-------------------|---------------|----------|
| **Valid** | V1 | Single line, single matching word | `"Hello", 5` | 1 word found |
| **Valid** | V2 | Multi-line, same word on different lines | `"Java is fun\nJava is great", 4` | "Java" on lines 1, 2 |
| **Valid** | V3 | Multiple different words of same length | `"cat dog bat", 3` | 3 distinct words |
| **Valid** | V4 | No words match target length | `"hi me", 5` | empty result |
| **Valid** | V5 | Mixed word lengths in multi-line text | `"Hello world\nThis is a test\nHello again", 5` | "Hello", "world", "again" |
| **Valid** | V6 | Word appears as substring of another word | `"Java is inside Javascript", 4` | depends on variant behavior |
| **Valid** | V7 | Same word in different cases | `"Cat cat CAT", 3` | depends on case-sensitivity |
| **Valid** | V8 | Words with punctuation attached | `"Hello! hello, HELLO...", 5` | depends on punctuation handling |
| **Invalid** | I1 | Null text | `null, 5` | empty map |
| **Invalid** | I2 | Empty text | `"", 5` | empty map |
| **Invalid** | I3 | Target length = 0 | `"Hello", 0` | empty map (edited) or no matches (unmod) |
| **Invalid** | I4 | Target length negative | `"Hello", -1` | empty map (edited) or no matches (unmod) |
| **Invalid** | I5 | Text with only whitespace | `"   \n  ", 3` | empty map |
| **Invalid** | I6 | Text with only newlines | `"\n\n\n", 3` | empty map |

### 4.2 Boundary Values

| BV ID | Boundary | Test Value | Expected |
|-------|----------|------------|----------|
| BV1 | Target length = 1 | `"I am a cat", 1` | words "I", "a" found |
| BV2 | Single word on single line | `"Java", 4` | 1 word, line 1 |
| BV3 | Word at very first position | `"Hello world", 5` | "Hello" at line 1 |
| BV4 | Word at very last position | `"world Hello", 5` | both on line 1 |
| BV5 | Line numbering with leading empty lines | `"\n\nJava\n\n\nJava", 4` | lines 3 and 6 |
| BV6 | Line numbering with trailing newline | `"Java\n", 4` | line 1 |
| BV7 | Text with only one line (no newlines) | `"hello world", 5` | "hello", "world" on line 1 |
| BV8 | Very long text (many lines) | 100+ lines | correct line tracking |
| BV9 | Overlapping word as substring | `"ana loves banana", 3` | "ana" count depends on variant |
| BV10 | Duplicate word on same line | `"Java Java", 4` | depends on deduplication |
| BV11 | Word exactly at target length vs target±1 | `"abc abcd ab", 3` | only "abc" matches |
| BV12 | Target length exceeds all words | `"hi me", 100` | empty map |

### 4.3 Test Coverage Mapping

| Class/BV | LLM1 Unmod | LLM1 Edit | LLM2 Unmod | LLM2 Edit |
|----------|-----------|-----------|-----------|-----------|
| V1 | — | — | — | — |
| V2 | — | ✅ `testScanByWordLengthBasic` | — | ✅ `testScanByWordLengthBasic` |
| V3 | ✅ `testAnalyzeText_standardMultiLineText` | — | ✅ `testScan_standardMultiLineText` | — |
| V4 | ✅ `testAnalyzeText_noMatches` | — | ✅ `testScan_noMatches` | — |
| V5 | ✅ `testAnalyzeText_standardMultiLineText` | — | ✅ `testScan_standardMultiLineText` | — |
| V6 | — | ✅ `testScanByWordLength_OverlappingAndSubstringIssues` | — | — |
| V7 | ✅ `testAnalyzeText_caseSensitivityAndFlipCase` | ✅ `testScanByWordLength_PunctuationAndCaseInsensitivity` | ✅ `testScan_caseSensitivityAndFlipCase` | ✅ `testScanByWordLength_PunctuationAndCaseInsensitivity` |
| V8 | ✅ `testAnalyzeText_specialCharactersAndSingleWordLines` | ✅ `testScanByWordLength_PunctuationAndCaseInsensitivity` | ✅ `testScan_specialCharactersAndSingleWordLines` | ✅ `testScanByWordLength_PunctuationAndCaseInsensitivity` |
| I1 | ✅ `testAnalyzeText_emptyAndNullText` | ✅ `testScanByWordLengthEdgeCases` | ✅ `testScan_emptyAndNullText` | ✅ `testScanByWordLengthEdgeCases` |
| I2 | ✅ `testAnalyzeText_emptyAndNullText` | ✅ `testScanByWordLengthEdgeCases` | ✅ `testScan_emptyAndNullText` | ✅ `testScanByWordLengthEdgeCases` |
| I3 | — | ✅ `testScanByWordLengthEdgeCases` | — | ✅ `testScanByWordLengthEdgeCases` |
| I4 | — | ✅ `testScanByWordLengthEdgeCases` | — | ✅ `testScanByWordLengthEdgeCases` |
| I5 | — | — | — | — |
| I6 | — | — | — | — |
| BV1 | — | — | — | — |
| BV2 | — | — | — | — |
| BV3 | ✅ (part of V5 test) | — | ✅ (part of V5 test) | — |
| BV4 | — | — | — | — |
| BV5 | ✅ `testAnalyzeText_accurateLineNumbers` | — | ✅ `testScan_accurateLineNumbers` | — |
| BV6 | — | — | — | — |
| BV7 | — | ✅ (implicit in basic test, single line) | — | ✅ (implicit in edge cases) |
| BV8 | — | — | — | — |
| BV9 | ✅ `testAnalyzeText_overlappingSubstrings` | — | ✅ `testScan_overlappingSubstrings` | — |
| BV10 | — | — | — | ✅ `testScanByWordLength_DuplicateOnSameLineFixed` |
| BV11 | — | — | — | — |
| BV12 | ✅ `testAnalyzeText_noMatches` | — | ✅ `testScan_noMatches` | — |

### 4.4 Gap Analysis

#### Common Gaps Across All Variants:
- **I5 (whitespace-only text):** No variant tests behavior with `"   \n  "`.
- **I6 (newline-only text):** No variant tests `"\n\n\n"`.
- **BV1 (targetLength=1):** No variant tests single-character word matching.
- **BV2 (single word, single line):** No variant tests the simplest case in isolation.
- **BV6 (trailing newline):** No variant tests how trailing newlines affect line counting.
- **BV8 (large text):** No variant tests with >10 lines — line tracking at scale untested.
- **BV11 (target length precision):** No variant verifies that only exact-length words match (not ±1).

#### LLM1 Unmodified Gaps:
- Doesn't test I3/I4 (zero/negative target length) — the method has no guard, so `targetLength=0` would match empty tokens from split.
- No direct unit tests for `howManyTimes`, `strlen`, or `flipCase` — all tested only indirectly.
- Case-sensitive behavior means `"Cat"` and `"cat"` are separate entries — tests verify this, but it's a design flaw for a text scanner.

#### LLM1 Edited Gaps:
- Doesn't test BV5 (empty-line line numbering) or BV9 (overlapping substrings) directly.
- V3 (multiple different words same length) is not tested.
- No test for V1 (single word, single match).

#### LLM2 Unmodified Gaps:
- **Known bug (duplicate counting):** `scan()` counts the same word multiple times per line because it doesn't deduplicate. The test documents this but doesn't fix it (count=9 for "Cat cat CAT").
- **No null safety:** `strlen(null)` and `flipCase(null)` would crash. Tests avoid calling these with null.
- Doesn't test I3/I4 (zero/negative target length).
- Punctuation is not stripped — `"Hello!"` is a 6-character word, not 5.

#### LLM2 Edited Gaps:
- V6 (substring of another word) is not tested.
- BV5 (empty-line line numbering) is not tested.
- BV9 (overlapping substrings inside longer words) is not tested.
- V1 (single word) is not tested.

---

## 5. Integration-Specific Concerns

### 5.1 Method Interaction Issues

The integration method `scanByWordLength`/`analyzeText`/`scan` chains all three utility methods. The following interaction concerns arise:

| Concern | Description | Impact |
|---------|-------------|--------|
| **Substring false positives** | `howManyTimes` counts substring matches inside other words (e.g., "Java" in "Javascript") | Inflated occurrence counts — affects LLM1 unmodified and any variant using `howManyTimes` on full text |
| **Case normalization chain** | Edited versions normalize via `flipCase` → lowercase conversion. If `flipCase` is buggy, all word matching breaks | Single point of failure for case-insensitive grouping |
| **Punctuation interaction with strlen** | If punctuation is not stripped before `strlen`, word lengths are measured incorrectly | LLM2 unmodified: `"Hello!"` has strlen=6, so it doesn't match targetLength=5 |
| **Empty token handling** | `split("\\s+")` can produce empty tokens for leading whitespace | LLM2 edited guards this; LLM1 edited's `replaceAll` handles it; LLM2 unmodified skips empties; LLM1 unmodified uses `\\W+` split |

### 5.2 Classes Where LLM-Generated Tests Are Insufficient

| Area | Classes Not Covered | Risk Level |
|------|---------------------|------------|
| `howManyTimes` isolation | V1, V2, V4, I5, BV1-BV7 | **Medium** — indirect testing provides some coverage |
| `strlen` with special inputs | V2, V3, V4, BV1, BV2 | **Low** — `strlen` is trivial |
| `flipCase` boundary chars | BV3, BV4, BV5 | **Low** — standard library usage |
| Main method with targetLength=0/-1 | I3, I4 | **High for unmodified** — no guard exists |
| Whitespace/newline-only text | I5, I6 | **Medium** — could produce unexpected tokens |
| Single-char word matching | BV1 | **Medium** — untested behavior path |
| Line counting edge cases | BV6 (trailing newline) | **Medium** — off-by-one risk |

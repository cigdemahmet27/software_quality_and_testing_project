# Equivalence Class Tables - Taha's Assigned Tasks
## Phase 1.1: Manual Black-Box Testing

> Author: Taha
> Tasks: #0, #2, #8, #10, #11, #14, #15, #16, #18 (Zorunlu), #39

---

## Task #0 — hasCloseElements (Orta)
**Specification:** Check if in given list of numbers, are any two numbers closer to each other than given threshold.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | List with a pair closer than threshold | `[1.0, 1.2], 0.5` | `true` |
| **Valid** | V2 | List with no pair closer than threshold | `[1.0, 2.0, 3.0], 0.5` | `false` |
| **Valid** | V3 | Identical elements with positive threshold | `[5.0, 5.0], 0.1` | `true` |
| **Valid** | V4 | Large list with close pair hidden within | `[1,3,5,7,9,9.05], 0.1` | `true` |
| **Valid** | V5 | List with negative numbers | `[-1.0, -1.1], 0.2` | `true` |
| **Invalid** | I1 | Empty list (no pairs possible) | `[], 0.5` | `false` |
| **Invalid** | I2 | Single element list (no pairs possible) | `[1.0], 0.5` | `false` |
| Boundary | BC1 | Distance exactly equals threshold (strict `<`) | `[1.0, 2.0], 1.0` | `false` |
| Boundary | BC2 | Distance just below threshold | `[1.0, 1.9], 1.0` | `true` |
| Boundary | BC3 | Threshold = 0 with distinct elements | `[1.0, 2.0], 0.0` | `false` |
| Boundary | BC4 | Threshold = 0 with identical elements | `[5.0, 5.0], 0.0` | `false` |
| Boundary | BC5 | Very large threshold (all pairs qualify) | `[1.0, 100.0], 200.0` | `true` |

---

## Task #2 — truncateNumber (Kolay)
**Specification:** Given a positive floating point number, return the decimal part.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Number with small decimal part | `3.1` | `0.1` |
| **Valid** | V2 | Number with large decimal part (close to 1) | `2.999` | `0.999` |
| **Valid** | V3 | Number with exactly 0.5 decimal | `3.5` | `0.5` |
| **Valid** | V4 | Very large number | `123456.789` | `~0.789` |
| **Valid** | V5 | Number between 0 and 1 | `0.25` | `0.25` |
| **Invalid** | I1 | Integer number (zero decimal) | `5.0` | `0.0` |
| **Invalid** | I2 | Zero input (boundary of "positive") | `0.0` | `0.0` |
| Boundary | BC1 | Very close to integer from above | `1.0000001` | `~0.0000001` |
| Boundary | BC2 | Very close to next integer from below | `1.9999999` | `~0.9999999` |

---

## Task #8 — sumProduct (Kolay)
**Specification:** For a given list of integers, return a tuple of sum and product. Empty sum=0, empty product=1.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Multiple positive integers | `[1, 2, 3, 4]` | `[10, 24]` |
| **Valid** | V2 | Single element list | `[5]` | `[5, 5]` |
| **Valid** | V3 | List with negative integers | `[-1, -2, -3]` | `[-6, -6]` |
| **Valid** | V4 | Mixed positive and negative | `[1, -2, 3]` | `[2, -6]` |
| **Invalid** | I1 | Empty list (special case from spec) | `[]` | `[0, 1]` |
| **Invalid** | I2 | List containing zero (product → 0) | `[1, 2, 0, 4]` | `[7, 0]` |
| Boundary | BC1 | Single element = 0 | `[0]` | `[0, 0]` |
| Boundary | BC2 | Single element = 1 (multiplicative identity) | `[1]` | `[1, 1]` |
| Boundary | BC3 | All elements are 1 | `[1, 1, 1, 1]` | `[4, 1]` |

---

## Task #10 — makePalindrome (Zor)
**Specification:** Find the shortest palindrome that begins with a supplied string.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | String already a palindrome | `"aba"` | `"aba"` |
| **Valid** | V2 | String with palindromic suffix | `"cat"` | `"catac"` |
| **Valid** | V3 | String with no palindromic suffix (last char only) | `"abcd"` | `"abcdcba"` |
| **Valid** | V4 | Palindromic suffix of length > 1 | `"aab"` | `"aabaa"` |
| **Invalid** | I1 | Empty string | `""` | `""` |
| Boundary | BC1 | Single character | `"a"` | `"a"` |
| Boundary | BC2 | Two same characters | `"bb"` | `"bb"` |
| Boundary | BC3 | Two different characters | `"ab"` | `"aba"` |
| Boundary | BC4 | All same characters | `"aaaa"` | `"aaaa"` |
| Boundary | BC5 | Result is a palindrome (verification) | `"hello"` | palindrome ✓ |
| Boundary | BC6 | Result starts with original string | `"testing"` | starts with input ✓ |

---

## Task #11 — stringXor (Orta)
**Specification:** Input are two binary strings. Perform XOR and return result as string.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Same bits → all 0s | `"010", "010"` | `"000"` |
| **Valid** | V2 | All different bits → all 1s | `"010", "101"` | `"111"` |
| **Valid** | V3 | Mixed bits → mixed result | `"010", "110"` | `"100"` |
| **Valid** | V4 | Longer binary strings | `"10101010", "01010101"` | `"11111111"` |
| **Invalid** | I1 | Empty strings | `"", ""` | `""` |
| Boundary | BC1 | `"0" XOR "0"` | `"0", "0"` | `"0"` |
| Boundary | BC2 | `"1" XOR "1"` | `"1", "1"` | `"0"` |
| Boundary | BC3 | `"0" XOR "1"` | `"0", "1"` | `"1"` |
| Boundary | BC4 | `"1" XOR "0"` | `"1", "0"` | `"1"` |
| Boundary | BC5 | Self-inverse (a XOR a = 0) | `"10110", "10110"` | `"00000"` |

---

## Task #14 — allPrefixes (Kolay)
**Specification:** Return list of all prefixes from shortest to longest of the input string.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Multi-character string | `"abc"` | `["a", "ab", "abc"]` |
| **Valid** | V2 | String with repeated characters | `"aaa"` | `["a", "aa", "aaa"]` |
| **Valid** | V3 | String with special characters | `"a!b"` | `["a", "a!", "a!b"]` |
| **Invalid** | I1 | Empty string | `""` | `[]` |
| Boundary | BC1 | Single character | `"a"` | `["a"]` |
| Boundary | BC2 | Two characters | `"ab"` | `["a", "ab"]` |
| Boundary | BC3 | String with spaces | `"a b"` | `["a", "a ", "a b"]` |

---

## Task #15 — stringSequence (Kolay)
**Specification:** Return a string containing space-delimited numbers from 0 up to n inclusive.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Small positive n | `5` | `"0 1 2 3 4 5"` |
| **Valid** | V2 | Larger n with double-digit numbers | `10` | `"0 1 2 ... 10"` |
| **Invalid** | I1 | n = 0 (minimum/edge of domain) | `0` | `"0"` |
| Boundary | BC1 | n = 0 → no spaces | `0` | `"0"` (no spaces) |
| Boundary | BC2 | n = 1 (first sequence with space) | `1` | `"0 1"` |
| Boundary | BC3 | n = 2 | `2` | `"0 1 2"` |
| Boundary | BC4 | No leading/trailing spaces | `5` | trimmed result |

---

## Task #16 — countDistinctCharacters (Kolay)
**Specification:** Given a string, find how many distinct characters (regardless of case) it consists of.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Mixed case same letters (case-insensitive) | `"xyzXYZ"` | `3` |
| **Valid** | V2 | All distinct characters | `"abcdef"` | `6` |
| **Valid** | V3 | All same character repeated | `"aaaa"` | `1` |
| **Valid** | V4 | String with digits and special chars | `"a1b2c3"` | `6` |
| **Invalid** | I1 | Empty string | `""` | `0` |
| Boundary | BC1 | Single character (lowercase or uppercase) | `"a"` / `"A"` | `1` |
| Boundary | BC2 | Two same chars different case | `"aA"` | `1` |
| Boundary | BC3 | String with only spaces | `"   "` | `1` |

---

## Task #18 — howManyTimes (Orta) [ZORUNLU GÖREV]
**Specification:** Find how many times a given substring can be found in the original string. Count overlapping cases.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Substring found exactly once | `"xyzabcdef", "abc"` | `1` |
| **Valid** | V2 | Substring found multiple times | `"ababab", "ab"` | `3` |
| **Valid** | V3 | Overlapping occurrences | `"aaaa", "aa"` | `3` |
| **Valid** | V4 | Substring not found | `"abcdef", "xyz"` | `0` |
| **Invalid** | I1 | Empty string with non-empty substring | `"", "a"` | `0` |
| **Invalid** | I2 | Substring longer than string | `"abc", "abcdef"` | `0` |
| Boundary | BC1 | Single char match | `"a", "a"` | `1` |
| Boundary | BC2 | Single char no match | `"a", "b"` | `0` |
| Boundary | BC3 | Substring at the very beginning | `"hello", "he"` | `1` |
| Boundary | BC4 | Substring at the very end | `"hello", "lo"` | `1` |
| Boundary | BC5 | Maximum overlapping | `"aaaa", "aaa"` | `2` |
| Boundary | BC6 | Substring equals entire string | `"hello", "hello"` | `1` |
| Boundary | BC7 | Case-sensitive search | `"aaa", "A"` | `0` |

---

## Task #39 — primeFib (Zor)
**Specification:** primeFib returns the n-th number that is a Fibonacci number and it's also prime.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Small n values (docstring examples) | `1, 2, 3, 4, 5` | `2, 3, 5, 13, 89` |
| **Valid** | V2 | Larger n values | `6, 7` | `233, 1597` |
| **Valid** | V3 | Result is both prime AND Fibonacci | `primeFib(5)` | prime ✓ & fib ✓ |
| **Invalid** | I1 | Non-prime Fibonacci numbers skipped | `8, 21, 34, 55` | never returned |
| Boundary | BC1 | n=1 (minimum valid input) | `1` | `2` |
| Boundary | BC2 | Results are monotonically increasing | `1..6` | strictly increasing |

# Equivalence Class Tables - Ahmet Enes's Assigned Tasks
## Phase 1.1: Manual Black-Box Testing

> Author: Ahmet Enes Çiğdem
> Tasks: #3, #4, #5, #7, #9, #26, #27, #28, #29, #30

---

## Task #3 — belowZero (Kolay)
**Specification:** Given a list of deposit and withdrawal operations on a bank account starting at zero balance, detect if at any point the balance falls below zero. Return true if it does, false otherwise.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list (no operations) | `[]` | `false` |
| **Valid** | V2 | Only positive operations (deposits) | `[10, 20, 30]` | `false` |
| **Valid** | V3 | Balance drops exactly to zero but not below | `[10, 5, -15, 20]` | `false` |
| **Valid** | V4 | Balance drops below zero mid-sequence | `[1, 2, -4, 5]` | `true` |
| Boundary | BC1 | Balance drops below zero immediately (first op) | `[-5, 10, 20]` | `true` |

---

## Task #4 — meanAbsoluteDeviation (Orta)
**Specification:** For a given list of input numbers, calculate Mean Absolute Deviation around the mean. MAD = average |x - x_mean|.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Standard distribution (nominal case) | `[1.0, 2.0, 3.0, 4.0]` | `1.0` |
| **Valid** | V2 | All identical elements (zero deviation) | `[5.0, 5.0, 5.0, 5.0]` | `0.0` |
| **Valid** | V3 | List with negative numbers | `[-5.0, 5.0]` | `5.0` |
| Boundary | BC1 | Single element list (minimum non-empty size) | `[42.0]` | `0.0` |

---

## Task #5 — intersperse (Kolay)
**Specification:** Insert a number 'delimiter' between every two consecutive elements of input list.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[], 4` | `[]` |
| **Valid** | V2 | Multiple element list (nominal case) | `[1, 2, 3], 4` | `[1, 4, 2, 4, 3]` |
| **Valid** | V3 | Negative/zero delimiter (edge-case value) | `[5, 10, 15], -99` | `[5, -99, 10, -99, 15]` |
| Boundary | BC1 | Single element list (no consecutive pairs) | `[10], 5` | `[10]` |

---

## Task #7 — filterBySubstring (Kolay)
**Specification:** Filter an input list of strings only for ones that contain the given substring.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[], "a"` | `[]` |
| **Valid** | V2 | Mixed matches and non-matches (nominal) | `["abc", "bacd", "cde", "array"], "a"` | `["abc", "bacd", "array"]` |
| **Valid** | V3 | No matches found | `["hello", "world", "java"], "z"` | `[]` |
| **Valid** | V4 | Exact match (substring equals full string) | `["javascript", "java", "python"], "java"` | `["javascript", "java"]` |
| Boundary | BC1 | Empty substring (matches everything) | `["apple", "banana", "cherry"], ""` | `["apple", "banana", "cherry"]` |

---

## Task #9 — rollingMax (Orta)
**Specification:** From a given list of integers, generate a list of the rolling maximum element found until the given moment in the sequence.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[]` | `[]` |
| **Valid** | V2 | Strictly increasing sequence (max updates every step) | `[1, 2, 3, 4, 5]` | `[1, 2, 3, 4, 5]` |
| **Valid** | V3 | Strictly decreasing sequence (max never updates) | `[5, 4, 3, 2, 1]` | `[5, 5, 5, 5, 5]` |
| **Valid** | V4 | Mixed/fluctuating values (nominal case) | `[1, 2, 3, 2, 3, 4, 2]` | `[1, 2, 3, 3, 3, 4, 4]` |
| **Valid** | V5 | Sequence with negative numbers only | `[-10, -5, -20, -2, -3]` | `[-10, -5, -5, -2, -2]` |
| Boundary | BC1 | Single element list | `[42]` | `[42]` |

---

## Task #26 — removeDuplicates (Orta)
**Specification:** From a list of integers, remove all elements that occur more than once. Keep order of elements left the same as in the input.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[]` | `[]` |
| **Valid** | V2 | All unique elements (no duplicates) | `[1, 2, 3, 4, 5]` | `[1, 2, 3, 4, 5]` |
| **Valid** | V3 | All elements are duplicates | `[1, 1, 2, 2, 3, 3, 3]` | `[]` |
| **Valid** | V4 | Mixed unique and duplicate elements (nominal) | `[1, 2, 3, 2, 4]` | `[1, 3, 4]` |
| **Valid** | V5 | Negative numbers with duplicates | `[-1, -5, -1, 10, -5, 42]` | `[10, 42]` |

---

## Task #27 — flipCase (Kolay)
**Specification:** For a given string, flip lowercase characters to uppercase and uppercase to lowercase.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | All lowercase letters | `"java"` | `"JAVA"` |
| **Valid** | V2 | All uppercase letters | `"PYTHON"` | `"python"` |
| **Valid** | V3 | Mixed case letters (nominal case) | `"Hello"` | `"hELLO"` |
| **Valid** | V4 | Non-alphabetical characters (numbers, symbols) | `"123 !@#"` | `"123 !@#"` |
| Boundary | BC1 | Empty string | `""` | `""` |

---

## Task #28 — concatenate (Kolay)
**Specification:** Concatenate list of strings into a single string.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[]` | `""` |
| **Valid** | V2 | Multiple elements (nominal case) | `["a", "b", "c"]` | `"abc"` |
| **Valid** | V3 | List with empty strings within | `["java", "", "script"]` | `"javascript"` |
| **Valid** | V4 | Strings containing spaces | `["Hello ", "World", "!"]` | `"Hello World!"` |
| Boundary | BC1 | Single element list | `["hello"]` | `"hello"` |

---

## Task #29 — filterByPrefix (Kolay)
**Specification:** Filter an input list of strings only for ones that start with a given prefix.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[], "a"` | `[]` |
| **Valid** | V2 | Mixed matches and non-matches (nominal) | `["abc", "bcd", "cde", "array"], "a"` | `["abc", "array"]` |
| **Valid** | V3 | No matches found | `["apple", "banana", "cherry"], "z"` | `[]` |
| **Valid** | V4 | Substring present but not at start (startsWith vs contains) | `["banana", "analytics", "cabana"], "ana"` | `["analytics"]` |
| Boundary | BC1 | Empty prefix (matches everything) | `["hello", "world"], ""` | `["hello", "world"]` |

---

## Task #30 — getPositive (Kolay)
**Specification:** Return only positive numbers in the list.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Empty list | `[]` | `[]` |
| **Valid** | V2 | Only negative numbers | `[-1, -2, -3]` | `[]` |
| **Valid** | V3 | Mixed positive and negative numbers (nominal) | `[-1, 2, -4, 5, 6]` | `[2, 5, 6]` |
| **Valid** | V4 | All positive numbers | `[10, 20, 30]` | `[10, 20, 30]` |
| Boundary | BC1 | Zero included (zero is not positive) | `[0, 1, 2]` | `[1, 2]` |

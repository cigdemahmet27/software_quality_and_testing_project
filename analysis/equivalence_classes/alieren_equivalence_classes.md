# Equivalence Class Tables - Ali Eren's Assigned Tasks
## Phase 1.1: Manual Black-Box Testing

> Author: Ali Eren Çiftçi
> Tasks: #13, #23, #25, #31, #34, #35, #42, #43, #45, #49

---

## Task #13 — greatestCommonDivisor (Kolay)
**Specification:** Return a greatest common divisor of two integers a and b.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Positive coprime integers | `3, 5` | `1` |
| **Valid** | V2 | One is a multiple of the other | `25, 5` | `5` |
| **Valid** | V3 | Positive integers with a common divisor | `144, 60` | `12` |
| **Valid** | V4 | One input is zero, other is non-zero | `0, 15` | `15` |
| **Valid** | V5 | Negative inputs (GCD must be positive) | `-25, 15` | `5` |
| **Valid** | V6 | Both inputs are zero | `0, 0` | `0` |
| Boundary | BC1 | Minimal positive boundary | `1, 1` | `1` |
| Boundary | BC2 | Integer.MAX_VALUE with a prime | `2147483647, 7` | `1` |
| Boundary | BC3 | Integer.MIN_VALUE handling (overflow/abs) | `-2147483648, 2` | `2` |
| Boundary | BC4 | Euclidean algorithm performance (large primes) | `1000000007, 999999937` | `1` |

---

## Task #23 — strlen (Kolay)
**Specification:** Return length of given string.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Standard alphabetical string | `"abc"` | `3` |
| **Valid** | V2 | Empty string | `""` | `0` |
| **Valid** | V3 | String with only whitespaces | `"   "` | `3` |
| **Valid** | V4 | String with escape characters | `"a\nb"` | `3` |
| **Invalid** | I1 | Null reference (unhandled by base code) | `null` | `NullPointerException` |
| Boundary | BC1 | Minimum length boundary (empty) | `""` | `0` |
| Boundary | BC2 | Off-by-one boundary (single character) | `"a"` | `1` |
| Boundary | BC3 | Very large string (stress test) | `"a".repeat(10000)` | `10000` |

---

## Task #25 — factorize (Orta)
**Specification:** Return prime factors of n from smallest to largest.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | n is a prime number | `7` | `[7]` |
| **Valid** | V2 | n is a power of a single prime | `8` | `[2, 2, 2]` |
| **Valid** | V3 | n is a composite with multiple distinct factors | `70` | `[2, 5, 7]` |
| **Valid** | V4 | n is 1 (boundary of factorization) | `1` | `[]` |
| Boundary | BC1 | Smallest prime | `2` | `[2]` |
| Boundary | BC2 | Perfect square | `25` | `[5, 5]` |
| Boundary | BC3 | Large prime (O(n) vs O(√n) performance) | `2147483629` | `[2147483629]` |

---

## Task #31 — isPrime (Orta)
**Specification:** Return true if a given number is prime, and false otherwise.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Small prime number | `3` | `true` |
| **Valid** | V2 | Large prime number | `13441` | `true` |
| **Valid** | V3 | Even composite number > 2 | `6` | `false` |
| **Valid** | V4 | Odd composite number | `9` | `false` |
| **Invalid** | I1 | Number 1 (not prime by definition) | `1` | `false` |
| **Invalid** | I2 | Negative number | `-7` | `false` |
| **Invalid** | I3 | Zero | `0` | `false` |
| Boundary | BC1 | Smallest prime (lower bound of primes) | `2` | `true` |
| Boundary | BC4 | Perfect square (sqrt boundary) | `4` | `false` |
| Boundary | BC5 | Integer.MAX_VALUE (Mersenne prime, performance) | `2147483647` | `true` |

---

## Task #34 — unique (Kolay)
**Specification:** Return sorted unique elements in a list.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Standard list with duplicates, unsorted | `[5, 3, 5, 2, 3]` | `[2, 3, 5]` |
| **Valid** | V2 | List with no duplicates, unsorted | `[3, 1, 2]` | `[1, 2, 3]` |
| **Valid** | V3 | List with negative and positive numbers | `[-5, 0, 5, -5]` | `[-5, 0, 5]` |
| **Valid** | V4 | Empty list | `[]` | `[]` |
| **Invalid** | I1 | Null list reference | `null` | `NullPointerException` |
| **Invalid** | I2 | List containing null elements | `[1, null, 2]` | `NullPointerException` |
| Boundary | BC1 | Single element list | `[42]` | `[42]` |
| Boundary | BC2 | All elements are identical | `[7, 7, 7, 7]` | `[7]` |
| Boundary | BC3 | Large list with many duplicates (stress test) | `10000 elements` | fast execution |

---

## Task #35 — maxElement (Kolay)
**Specification:** Return maximum element in the list.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | All positive numbers | `[1, 5, 3]` | `5` |
| **Valid** | V2 | All negative numbers | `[-10, -5, -20]` | `-5` |
| **Valid** | V3 | Mixed positive, negative, and zero | `[5, -5, 0, 9]` | `9` |
| **Valid** | V4 | Duplicate maximum values | `[7, 2, 7, 1]` | `7` |
| **Invalid** | I1 | Empty list | `[]` | `NoSuchElementException` |
| **Invalid** | I2 | Null list reference | `null` | `NullPointerException` |
| **Invalid** | I3 | List containing null elements | `[1, null, 2]` | `NullPointerException` |
| Boundary | BC1 | Single element list | `[42]` | `42` |
| Boundary | BC2 | Integer limit boundaries | `[MIN_VALUE, 0, MAX_VALUE]` | `Integer.MAX_VALUE` |
| Boundary | BC3 | Large list performance (100,000 elements) | `100000 elements` | fast execution |

---

## Task #42 — incrList (Kolay)
**Specification:** Return list with elements incremented by 1.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | List of positive numbers | `[1, 2, 3]` | `[2, 3, 4]` |
| **Valid** | V2 | List of negative numbers | `[-5, -3, -1]` | `[-4, -2, 0]` |
| **Valid** | V3 | List containing zeros | `[0, 0]` | `[1, 1]` |
| **Valid** | V4 | Empty list | `[]` | `[]` |
| **Invalid** | I1 | Null list reference | `null` | `NullPointerException` |
| **Invalid** | I2 | List containing null elements (unboxing) | `[1, null, 2]` | `NullPointerException` |
| Boundary | BC1 | Single element list | `[42]` | `[43]` |
| Boundary | BC2 | Integer.MAX_VALUE → overflow to MIN_VALUE | `[2147483647]` | `[-2147483648]` |
| Boundary | BC3 | Integer.MIN_VALUE → MIN_VALUE + 1 | `[-2147483648]` | `[-2147483647]` |
| Boundary | BC4 | Large list performance (100,000 elements) | `100000 elements` | fast execution |

---

## Task #43 — pairsSumToZero (Orta)
**Specification:** Returns True if there are two distinct elements in the list that sum to zero, and False otherwise.

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Positive/Negative pair exists | `[2, 4, -5, 5]` | `true` |
| **Valid** | V2 | No pairs sum to zero | `[1, 2, 3]` | `false` |
| **Valid** | V3 | Only one zero element (distinctness check) | `[0, 1, 2]` | `false` |
| **Valid** | V4 | Two zero elements (0 + 0 = 0) | `[1, 0, 3, 0]` | `true` |
| **Valid** | V5 | Single element list | `[1]` | `false` |
| **Valid** | V6 | Empty list | `[]` | `false` |
| **Invalid** | I1 | Null list reference | `null` | `NullPointerException` |
| **Invalid** | I2 | List containing null elements | `[1, null]` | `NullPointerException` |
| Boundary | BC1 | Integer limits (MAX_VALUE and -MAX_VALUE) | `[2147483647, -2147483647]` | `true` |
| Boundary | BC2 | Performance stress test (O(n²) check) | `5000 positive elements` | `false` (fast) |
| Boundary | BC3 | Pair at boundaries of the list | `[5, 1, 2, -5]` | `true` |

---

## Task #45 — triangleArea (Kolay)
**Specification:** Given length of a side and height, return the area of the triangle. Formula: (a * h) / 2.0

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | Positive integers | `5, 3` | `7.5` |
| **Valid** | V2 | Positive floating point numbers | `2.5, 4.2` | `5.25` |
| **Valid** | V3 | Large positive values | `1000, 2000` | `1000000.0` |
| **Invalid** | I1 | Negative side (formula returns negative) | `-5, 3` | `< 0` |
| **Invalid** | I2 | Negative height (physical constraint) | `5, -3` | `< 0` |
| **Invalid** | I3 | Side or height is zero | `0, 5` | `0.0` |
| Boundary | BC1 | Very small positive values (precision test) | `0.0001, 0.0002` | `~0.00000001` |
| Boundary | BC2 | Overflow boundary with Double.MAX_VALUE | `MAX_VALUE, 2.0` | `Double.MAX_VALUE` |
| Boundary | BC3 | Minimal positive boundary (Double.MIN_VALUE) | `MIN_VALUE, 2.0` | `Double.MIN_VALUE` |

---

## Task #49 — modp (Kolay)
**Specification:** Return 2^n modulo p. Formula: (2^n) % p

| Type | ID | Class Description | Input Example | Expected Output |
|------|----|-------------------|---------------|-----------------|
| **Valid** | V1 | n < p (standard case) | `3, 5` | `3` |
| **Valid** | V2 | n > p (larger exponent) | `1101, 101` | `2` |
| **Valid** | V3 | n is 0 (2⁰ = 1) | `0, 101` | `1` |
| **Invalid** | I1 | p is 1 (any number mod 1 = 0) | `5, 1` | `0` |
| **Invalid** | I2 | Negative p handling (Java % behavior) | `3, -5` | `3` |
| Boundary | BC1 | Minimal n and p (0, 1) | `0, 1` | `0` |
| Boundary | BC3 | Fermat's Little Theorem (p-1, p) | `100, 101` | `1` |
| Boundary | BC4 | Large n performance test (O(n) loop) | `1000000, 7` | `0 ≤ result < 7` |

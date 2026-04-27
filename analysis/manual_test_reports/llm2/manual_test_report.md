# Manual Black-Box Test Report — LLM2

> **Author:** Ali Eren Çiftçi (150220022)
> **Date:** 2026-04-27
> **Test Framework:** JUnit 5 (Maven Surefire)
> **Command:** `mvn test -Dtest="humaneval.manual.llm2.**"`

---

## Summary

| Metric | Value |
|--------|-------|
| Total Tests Run | 273 |
| Passed | 269 |
| Failed | 3 |
| Errors (Timeout) | 1 |
| Skipped | 0 |
| Total Time | 3.271 s |

**Result: 4 defects detected in LLM2-generated code — all successfully caught by manual black-box tests.**

---

## Test Results by Task

| Task | Test Class | Tests | Result |
|------|-----------|-------|--------|
| #0  | `task0.Task0ManualTest` | — | ✅ PASS |
| #2  | `task2.Task2ManualTest` | 13 | ✅ PASS |
| #3  | `task3.Task3ManualTest` | 5 | ✅ PASS |
| #4  | `task4.Task4ManualTest` | 4 | ✅ PASS |
| #5  | `task5.Task5ManualTest` | 4 | ✅ PASS |
| #8  | `task8.Task8ManualTest` | — | ✅ PASS |
| #10 | `task10.Task10ManualTest` | 16 | ✅ PASS |
| #11 | `task11.Task11ManualTest` | 13 | ✅ PASS |
| #13 | `task13.Task13ManualTest` | 10 | ❌ 1 FAILURE |
| #14 | `task14.Task14ManualTest` | 10 | ✅ PASS |
| #15 | `task15.Task15ManualTest` | 10 | ✅ PASS |
| #16 | `task16.Task16ManualTest` | 13 | ✅ PASS |
| #18 | `task18.Task18ManualTest` | 17 | ✅ PASS |
| #23 | `task23.Task23ManualTest` | 8 | ✅ PASS |
| #25 | `task25.Task25ManualTest` | 7 | ❌ 1 TIMEOUT |
| #26 | `task26.Task26ManualTest` | 5 | ✅ PASS |
| #27 | `task27.Task27ManualTest` | 5 | ✅ PASS |
| #28 | `task28.Task28ManualTest` | 5 | ✅ PASS |
| #29 | `task29.Task29ManualTest` | 5 | ✅ PASS |
| #30 | `task30.Task30ManualTest` | 5 | ✅ PASS |
| #31 | `task31.Task31ManualTest` | 10 | ✅ PASS |
| #34 | `task34.Task34ManualTest` | — | ✅ PASS |
| #35 | `task35.Task35ManualTest` | — | ✅ PASS |
| #39 | `task39.Task39ManualTest` | 14 | ✅ PASS |
| #42 | `task42.Task42ManualTest` | 10 | ✅ PASS |
| #43 | `task43.Task43ManualTest` | 11 | ✅ PASS |
| #45 | `task45.Task45ManualTest` | 9 | ❌ 1 FAILURE |
| #49 | `task49.Task49ManualTest` | 8 | ❌ 1 FAILURE |

---

## Failure Analysis

### Failure 1: Task #13 — `greatestCommonDivisor` (V5: Negative Inputs)

| Field | Detail |
|-------|--------|
| **Test** | `testV5_negativeInputs` |
| **Input** | `greatestCommonDivisor(-25, 15)` |
| **Expected** | `5` (GCD is always non-negative) |
| **Actual** | `-5` |
| **Category** | Valid Equivalence Class (V5) |

**Root Cause:** The LLM2 implementation uses the standard Euclidean algorithm but does **not** take the absolute value of inputs. When `a` is negative, the recursive `a % b` propagates the sign, causing the result to be negative. The mathematical convention is that GCD is always non-negative.

**Buggy code:**

```java
public int greatestCommonDivisor(int a, int b) {
    if (b == 0) {
        return a;  // returns negative 'a' when input is negative
    }
    return greatestCommonDivisor(b, a % b);
}
```

**Fix Suggestion:** Add `a = Math.abs(a); b = Math.abs(b);` at the beginning of the method.

---

### Failure 2: Task #45 — `triangleArea` (BC2: Double.MAX_VALUE Overflow)

| Field | Detail |
|-------|--------|
| **Test** | `testBC2_overflowBoundary` |
| **Input** | `triangleArea(Double.MAX_VALUE, 2.0)` |
| **Expected** | `Double.MAX_VALUE` (≈1.7976931348623157E308) |
| **Actual** | `Infinity` |
| **Category** | Boundary Condition (BC2) |

**Root Cause:** Same issue as LLM1. The expression `a * h / 2.0` computes `Double.MAX_VALUE * 2.0` first, which overflows to `Infinity`. Since division is left-to-right, dividing `Infinity / 2.0` still yields `Infinity`.

**Buggy code:**

```java
public double triangleArea(double a, double h) {
    return a * h / 2.0;
}
```

**Fix Suggestion:** Reorder to `a * (h / 2.0)` to avoid intermediate overflow.

---

### Failure 3: Task #49 — `modp` (BC1: Minimal Inputs n=0, p=1)

| Field | Detail |
|-------|--------|
| **Test** | `testBC1_minimalInputs` |
| **Input** | `modp(0, 1)` |
| **Expected** | `0` (any number mod 1 = 0) |
| **Actual** | `1` |
| **Category** | Boundary Condition (BC1) |

**Root Cause:** When `n = 0`, the loop does not execute at all, so the method returns the initial value `result = 1`. However, `2^0 mod 1 = 1 mod 1 = 0`. The code does not apply the final modulo operation to the initial value when the loop is skipped.

**Buggy code:**

```java
public int modp(int n, int p) {
    int result = 1;
    for (int i = 0; i < n; i++) {
        result = (result * 2) % p;
    }
    return result;  // returns 1 instead of 1 % p when n=0
}
```

**Fix Suggestion:** Change the initial value to `int result = 1 % p;` or add `return result % p;` at the end.

---

### Error 1: Task #25 — `factorize` (BC3: Large Prime Performance Timeout)

| Field | Detail |
|-------|--------|
| **Test** | `testBC3_largePrimePerformance` |
| **Input** | `factorize(2147483629)` |
| **Expected** | `[2147483629]` (within 1 second) |
| **Actual** | `TimeoutException` (exceeded 1 second) |
| **Category** | Boundary Condition (BC3) — Performance |

**Root Cause:** The LLM2 implementation uses a naive `O(n)` trial division algorithm that increments the divisor by 1 each time. For a large prime like `2147483629`, the loop must iterate ~2.1 billion times, which far exceeds the 1-second timeout. An efficient algorithm would only iterate up to `√n ≈ 46,340` times.

**Buggy code:**

```java
public List<Integer> factorize(int n) {
    List<Integer> factors = new ArrayList<>();
    int divisor = 2;
    while (n > 1) {
        if (n % divisor == 0) {
            factors.add(divisor);
            n /= divisor;
        } else {
            divisor++;  // O(n) — too slow for large primes
        }
    }
    return factors;
}
```

**Fix Suggestion:** Add a `√n` check: after the loop reaches `divisor * divisor > n`, add `n` directly as the remaining prime factor and break.

---

## Conclusion

The manual black-box tests successfully identified **4 distinct bugs** in LLM2-generated code:

| # | Task | Bug Type | Category |
|---|------|----------|----------|
| 1 | #13 | Missing absolute value for negative inputs | Functional (V5) |
| 2 | #45 | Double overflow in intermediate multiplication | Boundary (BC2) |
| 3 | #49 | Missing modulo on initial value when n=0 | Boundary (BC1) |
| 4 | #25 | O(n) algorithm too slow for large primes | Performance (BC3) |

LLM2 has **more defects (4)** than LLM1 (2), indicating lower code quality for boundary conditions and edge cases. Notably, LLM2's Task #13 lacks basic input normalization, and Task #25 uses an inefficient algorithm — issues that LLM1 handled correctly.

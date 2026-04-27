# Manual Black-Box Test Report — LLM1

> **Author:** Ali Eren Çiftçi (150220022)
> **Date:** 2026-04-27
> **Test Framework:** JUnit 5 (Maven Surefire)
> **Command:** `mvn test -Dtest="humaneval.manual.llm1.**"`

---

## Summary

| Metric | Value |
|--------|-------|
| Total Tests Run | 273 |
| Passed | 271 |
| Failed | 2 |
| Errors | 0 |
| Skipped | 0 |
| Total Time | 4.189 s |

**Result: 2 failures detected — both are genuine bugs in LLM1-generated code, successfully caught by boundary condition tests.**

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
| #13 | `task13.Task13ManualTest` | 10 | ✅ PASS |
| #14 | `task14.Task14ManualTest` | 10 | ✅ PASS |
| #15 | `task15.Task15ManualTest` | 10 | ✅ PASS |
| #16 | `task16.Task16ManualTest` | 13 | ✅ PASS |
| #18 | `task18.Task18ManualTest` | 17 | ✅ PASS |
| #23 | `task23.Task23ManualTest` | 8 | ✅ PASS |
| #25 | `task25.Task25ManualTest` | 7 | ✅ PASS |
| #26 | `task26.Task26ManualTest` | 5 | ✅ PASS |
| #27 | `task27.Task27ManualTest` | 5 | ✅ PASS |
| #28 | `task28.Task28ManualTest` | 5 | ✅ PASS |
| #29 | `task29.Task29ManualTest` | 5 | ✅ PASS |
| #30 | `task30.Task30ManualTest` | 5 | ✅ PASS |
| #31 | `task31.Task31ManualTest` | 10 | ❌ 1 FAILURE |
| #34 | `task34.Task34ManualTest` | — | ✅ PASS |
| #35 | `task35.Task35ManualTest` | — | ✅ PASS |
| #39 | `task39.Task39ManualTest` | 14 | ✅ PASS |
| #42 | `task42.Task42ManualTest` | 10 | ✅ PASS |
| #43 | `task43.Task43ManualTest` | 11 | ✅ PASS |
| #45 | `task45.Task45ManualTest` | 9 | ❌ 1 FAILURE |
| #49 | `task49.Task49ManualTest` | 8 | ✅ PASS |

---

## Failure Analysis

### Failure 1: Task #31 — `isPrime` (BC5: Integer.MAX_VALUE)

| Field | Detail |
|-------|--------|
| **Test** | `testBC5_maxIntPrime` |
| **Input** | `isPrime(2147483647)` |
| **Expected** | `true` (2147483647 = 2³¹ − 1 is a Mersenne prime) |
| **Actual** | `false` |
| **Category** | Boundary Condition (BC5) |

**Root Cause:** The LLM1 implementation uses the loop condition `i * i <= n` where `i` is an `int`. When `n = Integer.MAX_VALUE = 2147483647`, the expression `i * i` overflows the 32-bit integer range and wraps around to a negative value. This causes the loop to terminate prematurely and incorrectly report the number as non-prime.

**Fix Suggestion:** Cast `i` to `long` before multiplication: `(long) i * i <= n`.

---

### Failure 2: Task #45 — `triangleArea` (BC2: Double.MAX_VALUE Overflow)

| Field | Detail |
|-------|--------|
| **Test** | `testBC2_overflowBoundary` |
| **Input** | `triangleArea(Double.MAX_VALUE, 2.0)` |
| **Expected** | `Double.MAX_VALUE` (≈1.7976931348623157E308) |
| **Actual** | `Infinity` |
| **Category** | Boundary Condition (BC2) |

**Root Cause:** The LLM1 implementation computes `(a * h) / 2.0`. When `a = Double.MAX_VALUE` and `h = 2.0`, the intermediate multiplication `Double.MAX_VALUE * 2.0` exceeds the double-precision floating point range and evaluates to `Infinity`. Dividing `Infinity / 2.0` still yields `Infinity`. Mathematically the result should be `Double.MAX_VALUE`, but the order of operations causes an overflow in the intermediate step.

**Fix Suggestion:** Reorder operations to avoid intermediate overflow: `a * (h / 2.0)` or `(a / 2.0) * h`.

---

## Conclusion

Both failures are **genuine bugs in the LLM1-generated code**, not test defects. The manual black-box tests successfully identified:

1. **Integer overflow** in the primality check loop condition (Task #31)
2. **Double overflow** due to unsafe order of arithmetic operations (Task #45)

These results demonstrate the effectiveness of boundary value analysis in detecting subtle edge-case bugs that typical functional tests would miss.

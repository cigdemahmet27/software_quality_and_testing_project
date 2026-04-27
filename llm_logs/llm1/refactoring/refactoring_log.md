# Refactoring Prompt for LLM1 Bug Fixes

The following prompt was used to instruct an LLM to fix boundary-condition bugs detected by manual black-box testing.

---

## Prompt

You are given two Java methods that have boundary-condition bugs detected by JUnit 5 tests. Fix each bug while preserving the original logic and method signatures. Do not add new methods or change return types.

### Bug 1: Task #31 — `isPrime` (Integer Overflow in Loop Condition)

**Current buggy code:**
```java
public boolean isPrime(int n) {
    if (n <= 1) {
        return false;
    }
    if (n <= 3) {
        return true;
    }
    if (n % 2 == 0 || n % 3 == 0) {
        return false;
    }
    for (int i = 5; i * i <= n; i = i + 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    return true;
}
```

**Failing test:**
```java
assertTrue(solution.isPrime(2147483647)); // Integer.MAX_VALUE is a Mersenne prime (2^31 - 1)
```

**Bug description:** The loop condition `i * i <= n` causes integer overflow when `i` is large enough that `i * i` exceeds `Integer.MAX_VALUE`. The multiplication wraps to a negative value, causing the loop to exit prematurely and return `true` or `false` incorrectly.

**Required fix:** Prevent integer overflow in the `i * i` comparison by casting to `long`: `(long) i * i <= n`.

---

### Bug 2: Task #45 — `triangleArea` (Double Overflow in Intermediate Multiplication)

**Current buggy code:**
```java
public double triangleArea(double a, double h) {
    return (a * h) / 2.0;
}
```

**Failing test:**
```java
assertEquals(Double.MAX_VALUE, solution.triangleArea(Double.MAX_VALUE, 2.0), 0.0001);
// Expected: 1.7976931348623157E308
// Actual:   Infinity
```

**Bug description:** When `a = Double.MAX_VALUE` and `h = 2.0`, the intermediate expression `a * h` evaluates to `Double.MAX_VALUE * 2.0 = Infinity`. Dividing `Infinity / 2.0` still yields `Infinity` instead of the mathematically correct result `Double.MAX_VALUE`.

**Required fix:** Reorder the arithmetic to divide before multiplying: `a * (h / 2.0)` or equivalently `(a / 2.0) * h`. This avoids the intermediate overflow.

---

## Expected Fixed Code

### Task #31 — Fixed `isPrime`
```java
public boolean isPrime(int n) {
    if (n <= 1) {
        return false;
    }
    if (n <= 3) {
        return true;
    }
    if (n % 2 == 0 || n % 3 == 0) {
        return false;
    }
    for (int i = 5; (long) i * i <= n; i = i + 6) {
        if (n % i == 0 || n % (i + 2) == 0) {
            return false;
        }
    }
    return true;
}
```

### Task #45 — Fixed `triangleArea`
```java
public double triangleArea(double a, double h) {
    return a * (h / 2.0);
}
```

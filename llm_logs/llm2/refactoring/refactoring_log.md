# Refactoring Prompt for LLM2 Bug Fixes

The following prompt was used to instruct an LLM to fix bugs detected by manual black-box testing.

---

## Prompt

You are given four Java methods that have bugs detected by JUnit 5 tests. Fix each bug while preserving the original logic and method signatures. Do not add new methods or change return types.

### Bug 1: Task #13 — `greatestCommonDivisor` (Missing Absolute Value for Negative Inputs)

**Current buggy code:**
```java
public int greatestCommonDivisor(int a, int b) {
    if (b == 0) {
        return a;
    }
    return greatestCommonDivisor(b, a % b);
}
```

**Failing test:**
```java
assertEquals(5, solution.greatestCommonDivisor(-25, 15), "GCD must be positive even if inputs are negative");
// Expected: 5
// Actual: -5
```

**Bug description:** The Euclidean algorithm does not normalize negative inputs. When `a` is negative, the result inherits the negative sign. By mathematical convention, GCD is always non-negative.

**Required fix:** Add `a = Math.abs(a); b = Math.abs(b);` at the beginning of the method to normalize inputs before computation.

---

### Bug 2: Task #25 — `factorize` (O(n) Algorithm Too Slow for Large Primes)

**Current buggy code:**
```java
public List<Integer> factorize(int n) {
    List<Integer> factors = new ArrayList<>();
    int divisor = 2;
    while (n > 1) {
        if (n % divisor == 0) {
            factors.add(divisor);
            n /= divisor;
        } else {
            divisor++;
        }
    }
    return factors;
}
```

**Failing test:**
```java
@Timeout(1)
void testBC3_largePrimePerformance() {
    int largePrime = 2147483629;
    assertEquals(Arrays.asList(largePrime), solution.factorize(largePrime));
}
// Result: TimeoutException — timed out after 1 second
```

**Bug description:** The naive trial division iterates up to `n` in the worst case (when `n` is prime). For `n = 2147483629`, this means ~2.1 billion iterations, far exceeding the 1-second timeout. An efficient algorithm should only iterate up to `√n`.

**Required fix:** Add a square root check. Once `divisor * divisor > n` and `n > 1`, the remaining `n` is a prime factor — add it directly and break.

---

### Bug 3: Task #45 — `triangleArea` (Double Overflow in Intermediate Multiplication)

**Current buggy code:**
```java
public double triangleArea(double a, double h) {
    return a * h / 2.0;
}
```

**Failing test:**
```java
assertEquals(Double.MAX_VALUE, solution.triangleArea(Double.MAX_VALUE, 2.0), 0.0001);
// Expected: 1.7976931348623157E308
// Actual: Infinity
```

**Bug description:** The expression `a * h` evaluates `Double.MAX_VALUE * 2.0 = Infinity` before dividing by 2.0. Dividing `Infinity / 2.0` still yields `Infinity`.

**Required fix:** Reorder the arithmetic to divide before multiplying: `a * (h / 2.0)`.

---

### Bug 4: Task #49 — `modp` (Missing Modulo on Initial Value When n=0)

**Current buggy code:**
```java
public int modp(int n, int p) {
    int result = 1;
    for (int i = 0; i < n; i++) {
        result = (result * 2) % p;
    }
    return result;
}
```

**Failing test:**
```java
assertEquals(0, solution.modp(0, 1));
// Expected: 0  (2^0 mod 1 = 1 mod 1 = 0)
// Actual: 1
```

**Bug description:** When `n = 0`, the loop body never executes, so `result` stays as the initial value `1`. However, `2^0 mod 1 = 1 mod 1 = 0`. The initial value is not subjected to the modulo operation.

**Required fix:** Initialize `result` as `1 % p` instead of `1`.

---

## Expected Fixed Code

### Task #13 — Fixed `greatestCommonDivisor`
```java
public int greatestCommonDivisor(int a, int b) {
    a = Math.abs(a);
    b = Math.abs(b);
    if (b == 0) {
        return a;
    }
    return greatestCommonDivisor(b, a % b);
}
```

### Task #25 — Fixed `factorize`
```java
public List<Integer> factorize(int n) {
    List<Integer> factors = new ArrayList<>();
    int divisor = 2;
    while (divisor * divisor <= n) {
        if (n % divisor == 0) {
            factors.add(divisor);
            n /= divisor;
        } else {
            divisor++;
        }
    }
    if (n > 1) {
        factors.add(n);
    }
    return factors;
}
```

### Task #45 — Fixed `triangleArea`
```java
public double triangleArea(double a, double h) {
    return a * (h / 2.0);
}
```

### Task #49 — Fixed `modp`
```java
public int modp(int n, int p) {
    int result = 1 % p;
    for (int i = 0; i < n; i++) {
        result = (result * 2) % p;
    }
    return result;
}
```

/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #39 - primeFib (LLM2)
 */
package humaneval.manual.llm2.task39;

import humaneval.llm2.task39.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for primeFib
 *
 * Specification: primeFib returns the n-th number that is a Fibonacci number
 * and it's also prime. Prime Fibonacci numbers: 2, 3, 5, 13, 89, 233, 1597, ...
 *
 * VALID Equivalence Classes (n >= 1):
 * V1: Small n values (n=1..5) → matches docstring examples
 * V2: Larger n values → 6th, 7th prime Fibonacci
 * V3: Result verification → result is both prime and Fibonacci
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Non-prime Fibonacci numbers should be skipped (8, 21, 34, 55)
 *
 * Boundary Conditions:
 * BC1: n=1 → minimum valid input → 2
 * BC2: Results are monotonically increasing
 */
class Task39ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: primeFib(1) → 2 (docstring)")
    void testV1_first() {
        assertEquals(2, solution.primeFib(1));
    }

    @Test
    @DisplayName("V1: primeFib(2) → 3 (docstring)")
    void testV1_second() {
        assertEquals(3, solution.primeFib(2));
    }

    @Test
    @DisplayName("V1: primeFib(3) → 5 (docstring)")
    void testV1_third() {
        assertEquals(5, solution.primeFib(3));
    }

    @Test
    @DisplayName("V1: primeFib(4) → 13 (docstring, skips 8)")
    void testV1_fourth() {
        assertEquals(13, solution.primeFib(4));
    }

    @Test
    @DisplayName("V1: primeFib(5) → 89 (docstring, skips 21,34,55)")
    void testV1_fifth() {
        assertEquals(89, solution.primeFib(5));
    }

    @Test
    @DisplayName("V1: First 5 values match docstring exactly")
    void testV1_allDocstringExamples() {
        int[] expected = {2, 3, 5, 13, 89};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], solution.primeFib(i + 1),
                    "primeFib(" + (i + 1) + ") mismatch");
        }
    }

    @Test
    @DisplayName("V2: primeFib(6) → 233")
    void testV2_sixth() {
        assertEquals(233, solution.primeFib(6));
    }

    @Test
    @DisplayName("V2: primeFib(7) → 1597")
    void testV2_seventh() {
        assertEquals(1597, solution.primeFib(7));
    }

    @Test
    @DisplayName("V3: primeFib(5) result is actually prime")
    void testV3_resultIsPrime() {
        int result = solution.primeFib(5);
        assertTrue(isPrime(result), result + " should be prime");
    }

    @Test
    @DisplayName("V3: primeFib(4) result is actually a Fibonacci number")
    void testV3_resultIsFibonacci() {
        int result = solution.primeFib(4);
        assertTrue(isFibonacci(result), result + " should be Fibonacci");
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: 8 is Fibonacci but not prime - should be skipped")
    void testI1_eightSkipped() {
        assertNotEquals(8, solution.primeFib(3));
        assertNotEquals(8, solution.primeFib(4));
    }

    @Test
    @DisplayName("I1: 21 is Fibonacci but not prime - should be skipped")
    void testI1_twentyOneSkipped() {
        assertNotEquals(21, solution.primeFib(4));
        assertNotEquals(21, solution.primeFib(5));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: n=1 is minimum valid input, result is positive")
    void testBC1_minimumInput() {
        int result = solution.primeFib(1);
        assertTrue(result > 0, "Result should be positive");
        assertEquals(2, result);
    }

    @Test
    @DisplayName("BC2: Results are monotonically increasing")
    void testBC2_monotonicallyIncreasing() {
        int prev = 0;
        for (int i = 1; i <= 6; i++) {
            int current = solution.primeFib(i);
            assertTrue(current > prev, "primeFib(" + i + ") should be > previous");
            prev = current;
        }
    }

    // Helper methods
    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private boolean isFibonacci(int n) {
        int a = 0, b = 1;
        while (b < n) { int t = b; b = a + b; a = t; }
        return b == n;
    }
}

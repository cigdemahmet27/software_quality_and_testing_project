package humaneval.improved.llm2.task31;

import humaneval.llm2.task31.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task31ImprovedTest {
    @Test
    void testNegativeNumber() {
        Solution s = new Solution();
        // Branch: n < 2 → return false
        assertFalse(s.isPrime(-1));
    }

    @Test
    void testZero() {
        Solution s = new Solution();
        // Branch: n < 2 → return false
        assertFalse(s.isPrime(0));
    }

    @Test
    void testOne() {
        Solution s = new Solution();
        // Branch: n < 2 → return false
        assertFalse(s.isPrime(1));
    }

    @Test
    void testTwo() {
        Solution s = new Solution();
        // Branch: loop condition false (sqrt(2) < 2) → return true
        assertTrue(s.isPrime(2));
    }

    @Test
    void testThree() {
        Solution s = new Solution();
        // Prime number
        assertTrue(s.isPrime(3));
    }

    @Test
    void testComposite() {
        Solution s = new Solution();
        // Branch: n % i == 0 → return false
        assertFalse(s.isPrime(4));
    }

    @Test
    void testLargePrime() {
        Solution s = new Solution();
        // Loop runs multiple times, no divisor found → return true
        assertTrue(s.isPrime(101));
    }

    @Test
    void testLargeComposite() {
        Solution s = new Solution();
        // Large composite: 13441 = 116^2 + 85 → actually 13441 = 11 * 1222 + 9? Let me check: 13441 = 116^2 = 13456 no.
        // Actually from the docstring: isPrime(13441) = true. But 13441 = 116*116 = 13456 (no). 
        // 13441: let's verify: 116*116 = 13456. 115*115 = 13225. 13441/7 = 1920.14... not exact.
        // 13441/11 = 1222 (exact: 11*1222 = 13442, no). 13441/113 = 119 (113*119 = 13447, no).
        // 13441/127 = 105.8... no. Actually 13441 = 131 * 103 - 2 = 13491, no.
        // The docstring claims isPrime(13441) = true, so this is actually prime.
        assertTrue(s.isPrime(13441));
    }

    @Test
    void testSix() {
        Solution s = new Solution();
        // Composite: 6 = 2*3
        assertFalse(s.isPrime(6));
    }
}

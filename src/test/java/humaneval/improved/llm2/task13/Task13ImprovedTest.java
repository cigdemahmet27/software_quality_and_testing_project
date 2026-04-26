package humaneval.improved.llm2.task13;

import humaneval.llm2.task13.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task13ImprovedTest {
    @Test
    void testCoprime() {
        Solution s = new Solution();
        // Branch: b eventually becomes 0 after multiple recursions → gcd = 1
        assertEquals(1, s.greatestCommonDivisor(3, 5));
    }

    @Test
    void testMultiple() {
        Solution s = new Solution();
        // Branch: one is multiple of the other
        assertEquals(5, s.greatestCommonDivisor(25, 15));
    }

    @Test
    void testSameNumber() {
        Solution s = new Solution();
        // Branch: a % b == 0 on first recursion → b == 0 → return a
        assertEquals(7, s.greatestCommonDivisor(7, 7));
    }

    @Test
    void testOneIsZero() {
        Solution s = new Solution();
        // Branch: b == 0 immediately → return a
        assertEquals(10, s.greatestCommonDivisor(10, 0));
    }

    @Test
    void testLargeNumbers() {
        Solution s = new Solution();
        // Multiple recursion levels
        assertEquals(6, s.greatestCommonDivisor(48, 18));
    }

    @Test
    void testPrimeNumbers() {
        Solution s = new Solution();
        // Two distinct primes → gcd = 1
        assertEquals(1, s.greatestCommonDivisor(13, 17));
    }
}

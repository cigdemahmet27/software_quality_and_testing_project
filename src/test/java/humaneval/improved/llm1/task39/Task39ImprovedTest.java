package humaneval.improved.llm1.task39;

import humaneval.llm1.task39.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task39ImprovedTest {
    @Test
    void testPrimeFib() {
        Solution s = new Solution();
        
        // Loop coverage and branches
        assertEquals(2, s.primeFib(1));
        assertEquals(3, s.primeFib(2));
        assertEquals(5, s.primeFib(3));
        assertEquals(13, s.primeFib(4));
        assertEquals(89, s.primeFib(5));
        
        // To hit all isPrime conditions we just need primeFib loop to evaluate various numbers.
        // b = 2 (prime)
        // b = 3 (prime)
        // b = 5 (prime)
        // b = 8 (not prime, 8 % 2 == 0)
        // b = 13 (prime)
        // b = 21 (not prime, 21 % 3 == 0)
        // b = 34 (not prime, 34 % 2 == 0)
        // b = 55 (not prime, 55 % 5 == 0 -> goes into i loop and 55 % 5 == 0)
    }

    @Test
    void testIsPrime() {
        Solution s = new Solution();
        assertFalse(s.isPrime(1));
        assertFalse(s.isPrime(0));
        assertFalse(s.isPrime(-5));
        assertTrue(s.isPrime(2));
        assertTrue(s.isPrime(3));
        assertFalse(s.isPrime(4));
        assertFalse(s.isPrime(9));
        assertTrue(s.isPrime(5));
        assertTrue(s.isPrime(7));
        assertFalse(s.isPrime(25)); // 25 % 5 == 0
        assertFalse(s.isPrime(49)); // 49 % 7 == 0, hits n % (i + 2) == 0
    }
}

package humaneval.improved.llm1.task31;

import humaneval.llm1.task31.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task31ImprovedTest {
    @Test
    void testIsPrime() {
        Solution s = new Solution();
        
        // Branch: n <= 1
        assertFalse(s.isPrime(1));
        assertFalse(s.isPrime(0));
        assertFalse(s.isPrime(-5));
        
        // Branch: n <= 3
        assertTrue(s.isPrime(2));
        assertTrue(s.isPrime(3));
        
        // Branch: n % 2 == 0 || n % 3 == 0
        assertFalse(s.isPrime(4));
        assertFalse(s.isPrime(6));
        assertFalse(s.isPrime(9));
        
        // Loop and branches inside loop
        // n % i == 0
        assertFalse(s.isPrime(25)); // i = 5
        
        // n % (i + 2) == 0 -> i + 2 = 7
        assertFalse(s.isPrime(49)); // i = 5, i+2 = 7
        
        // Prime greater than 3
        assertTrue(s.isPrime(5));
        assertTrue(s.isPrime(11));
        assertTrue(s.isPrime(101));
    }
}

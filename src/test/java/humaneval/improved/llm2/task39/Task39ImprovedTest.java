package humaneval.improved.llm2.task39;

import humaneval.llm2.task39.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task39ImprovedTest {
    @Test
    void testFirstPrimeFib() {
        Solution s = new Solution();
        // 1st prime Fibonacci: fib seq 0,1,1,2,3,5,8,13... → first prime fib is 2
        assertEquals(2, s.primeFib(1));
    }

    @Test
    void testSecondPrimeFib() {
        Solution s = new Solution();
        // 2nd prime Fibonacci: 3
        assertEquals(3, s.primeFib(2));
    }

    @Test
    void testThirdPrimeFib() {
        Solution s = new Solution();
        // 3rd prime Fibonacci: 5
        assertEquals(5, s.primeFib(3));
    }

    @Test
    void testFourthPrimeFib() {
        Solution s = new Solution();
        // 4th: 13 (8 is not prime, 13 is)
        // Branch: isPrime returns false for 8, true for 13
        assertEquals(13, s.primeFib(4));
    }

    @Test
    void testFifthPrimeFib() {
        Solution s = new Solution();
        // 5th: 89 (21=3*7 not prime, 34=2*17 not prime, 55=5*11 not prime, 89 is prime)
        // Branch: isPrime returns false multiple times before finding 89
        assertEquals(89, s.primeFib(5));
    }
}

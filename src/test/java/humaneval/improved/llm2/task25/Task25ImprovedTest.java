package humaneval.improved.llm2.task25;

import humaneval.llm2.task25.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task25ImprovedTest {
    @Test
    void testPrimeNumber() {
        Solution s = new Solution();
        // Branch: n % divisor != 0 many times, then n==divisor → single factor
        assertEquals(List.of(7), s.factorize(7));
    }

    @Test
    void testPowerOfTwo() {
        Solution s = new Solution();
        // Branch: n % divisor == 0 repeatedly with divisor=2
        assertEquals(Arrays.asList(2, 2, 2), s.factorize(8));
    }

    @Test
    void testPerfectSquare() {
        Solution s = new Solution();
        // Branch: same factor repeated
        assertEquals(Arrays.asList(5, 5), s.factorize(25));
    }

    @Test
    void testMultipleDistinctFactors() {
        Solution s = new Solution();
        // Branch: divisor incremented multiple times
        assertEquals(Arrays.asList(2, 5, 7), s.factorize(70));
    }

    @Test
    void testTwo() {
        Solution s = new Solution();
        // Smallest prime
        assertEquals(List.of(2), s.factorize(2));
    }

    @Test
    void testLargeComposite() {
        Solution s = new Solution();
        // Multiple factors: 100 = 2*2*5*5
        assertEquals(Arrays.asList(2, 2, 5, 5), s.factorize(100));
    }
}

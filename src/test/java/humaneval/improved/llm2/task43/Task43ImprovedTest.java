package humaneval.improved.llm2.task43;

import humaneval.llm2.task43.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task43ImprovedTest {
    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Branch: inner loop never entered (j starts at 1, but size is 1)
        assertFalse(s.pairsSumToZero(List.of(1)));
    }

    @Test
    void testNoPairSumsToZero() {
        Solution s = new Solution();
        // Branch: sum != 0 for all pairs
        assertFalse(s.pairsSumToZero(Arrays.asList(1, 3, 5, 0)));
    }

    @Test
    void testPairFound() {
        Solution s = new Solution();
        // Branch: l.get(i) + l.get(j) == 0 → return true
        assertTrue(s.pairsSumToZero(Arrays.asList(2, 4, -5, 3, 5, 7)));
    }

    @Test
    void testNoPairWithNegatives() {
        Solution s = new Solution();
        // Negatives present but no pair sums to zero
        assertFalse(s.pairsSumToZero(Arrays.asList(1, 3, -2, 1)));
    }

    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: outer loop condition fails → return false
        assertFalse(s.pairsSumToZero(List.of()));
    }

    @Test
    void testTwoElements() {
        Solution s = new Solution();
        // Minimal pair summing to zero
        assertTrue(s.pairsSumToZero(Arrays.asList(5, -5)));
    }

    @Test
    void testZeroPair() {
        Solution s = new Solution();
        // Two zeros: 0 + 0 = 0 → true
        assertTrue(s.pairsSumToZero(Arrays.asList(0, 0)));
    }

    @Test
    void testSingleZero() {
        Solution s = new Solution();
        // Single zero → no pair
        assertFalse(s.pairsSumToZero(List.of(0)));
    }
}

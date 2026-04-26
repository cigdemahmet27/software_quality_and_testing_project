package humaneval.improved.llm2.task30;

import humaneval.llm2.task30.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task30ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: stream empty → empty result
        assertEquals(List.of(), s.getPositive(List.of()));
    }

    @Test
    void testAllPositive() {
        Solution s = new Solution();
        // Branch: filter x > 0 true for all
        assertEquals(Arrays.asList(1, 2, 3), s.getPositive(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testAllNegative() {
        Solution s = new Solution();
        // Branch: filter x > 0 false for all
        assertEquals(List.of(), s.getPositive(Arrays.asList(-1, -2, -3)));
    }

    @Test
    void testMixed() {
        Solution s = new Solution();
        // Branch: mixed positives and negatives
        assertEquals(Arrays.asList(2, 5, 6), s.getPositive(Arrays.asList(-1, 2, -4, 5, 6)));
    }

    @Test
    void testWithZero() {
        Solution s = new Solution();
        // Boundary: zero is NOT positive (x > 0 is false for 0)
        assertEquals(Arrays.asList(1, 2), s.getPositive(Arrays.asList(0, 1, 2)));
    }

    @Test
    void testAllZeros() {
        Solution s = new Solution();
        // All zeros → none pass filter
        assertEquals(List.of(), s.getPositive(Arrays.asList(0, 0, 0)));
    }
}

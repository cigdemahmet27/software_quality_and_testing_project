package humaneval.improved.llm2.task3;

import humaneval.llm2.task3.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task3ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: loop body never executed → return false
        assertFalse(s.belowZero(Collections.emptyList()));
    }

    @Test
    void testNeverBelowZero() {
        Solution s = new Solution();
        // Branch: balance never < 0 → return false
        assertFalse(s.belowZero(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testGoesBelowZero() {
        Solution s = new Solution();
        // Branch: balance < 0 → return true
        assertTrue(s.belowZero(Arrays.asList(1, 2, -4, 5)));
    }

    @Test
    void testExactlyZero() {
        Solution s = new Solution();
        // Boundary: balance == 0 but not < 0 → return false
        assertFalse(s.belowZero(Arrays.asList(1, -1)));
    }

    @Test
    void testImmediatelyBelowZero() {
        Solution s = new Solution();
        // Branch: first operation goes below zero
        assertTrue(s.belowZero(Arrays.asList(-1)));
    }

    @Test
    void testAllNegatives() {
        Solution s = new Solution();
        // Branch: balance goes below on first element
        assertTrue(s.belowZero(Arrays.asList(-5, -3, -1)));
    }
}

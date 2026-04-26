package humaneval.improved.llm2.task9;

import humaneval.llm2.task9.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task9ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: numbers.size() == 0 → return empty
        assertEquals(List.of(), s.rollingMax(List.of()));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Branch: only first element added, loop not entered
        assertEquals(List.of(5), s.rollingMax(List.of(5)));
    }

    @Test
    void testIncreasingSequence() {
        Solution s = new Solution();
        // Branch: numbers.get(i) > currentMax is always true
        assertEquals(Arrays.asList(1, 2, 3, 4), s.rollingMax(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    void testDecreasingSequence() {
        Solution s = new Solution();
        // Branch: numbers.get(i) > currentMax is always false
        assertEquals(Arrays.asList(4, 4, 4, 4), s.rollingMax(Arrays.asList(4, 3, 2, 1)));
    }

    @Test
    void testMixedSequence() {
        Solution s = new Solution();
        // Branch: alternating true/false for max update
        assertEquals(Arrays.asList(1, 2, 3, 3, 3, 4, 4),
                s.rollingMax(Arrays.asList(1, 2, 3, 2, 3, 4, 2)));
    }

    @Test
    void testAllSame() {
        Solution s = new Solution();
        // Branch: numbers.get(i) > currentMax is always false (equal)
        assertEquals(Arrays.asList(3, 3, 3), s.rollingMax(Arrays.asList(3, 3, 3)));
    }
}

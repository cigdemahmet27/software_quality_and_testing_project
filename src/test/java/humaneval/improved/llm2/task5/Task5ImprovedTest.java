package humaneval.improved.llm2.task5;

import humaneval.llm2.task5.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task5ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: numbers.size() == 0 → return empty list
        assertEquals(List.of(), s.intersperse(List.of(), 4));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Branch: loop body not entered (size-1==0), only last element added
        assertEquals(List.of(1), s.intersperse(List.of(1), 4));
    }

    @Test
    void testMultipleElements() {
        Solution s = new Solution();
        // Branch: loop runs, delimiter inserted between each pair
        assertEquals(Arrays.asList(1, 4, 2, 4, 3), s.intersperse(Arrays.asList(1, 2, 3), 4));
    }

    @Test
    void testTwoElements() {
        Solution s = new Solution();
        // Minimal case with delimiter insertion
        assertEquals(Arrays.asList(5, 0, 6), s.intersperse(Arrays.asList(5, 6), 0));
    }

    @Test
    void testNegativeDelimiter() {
        Solution s = new Solution();
        // Edge: negative delimiter value
        assertEquals(Arrays.asList(1, -1, 2, -1, 3), s.intersperse(Arrays.asList(1, 2, 3), -1));
    }
}

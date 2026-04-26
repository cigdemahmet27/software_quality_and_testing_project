package humaneval.improved.llm2.task35;

import humaneval.llm2.task35.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task35ImprovedTest {
    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Collections.max on single element → returns it
        assertEquals(5, s.maxElement(List.of(5)));
    }

    @Test
    void testMultipleElements() {
        Solution s = new Solution();
        // Max is last element
        assertEquals(3, s.maxElement(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testMaxInMiddle() {
        Solution s = new Solution();
        // Max is in the middle
        assertEquals(9, s.maxElement(Arrays.asList(1, 9, 3)));
    }

    @Test
    void testNegativeNumbers() {
        Solution s = new Solution();
        // All negatives → max is least negative
        assertEquals(-1, s.maxElement(Arrays.asList(-5, -3, -1)));
    }

    @Test
    void testLargerList() {
        Solution s = new Solution();
        assertEquals(123, s.maxElement(Arrays.asList(5, 3, -5, 2, -3, 3, 9, 0, 123, 1, -10)));
    }

    @Test
    void testAllSame() {
        Solution s = new Solution();
        // All same → returns that value
        assertEquals(7, s.maxElement(Arrays.asList(7, 7, 7)));
    }
}

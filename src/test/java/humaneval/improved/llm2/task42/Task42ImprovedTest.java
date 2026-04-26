package humaneval.improved.llm2.task42;

import humaneval.llm2.task42.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task42ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Stream on empty → empty result
        assertEquals(List.of(), s.incrList(List.of()));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        assertEquals(List.of(2), s.incrList(List.of(1)));
    }

    @Test
    void testMultipleElements() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(2, 3, 4), s.incrList(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testWithZero() {
        Solution s = new Solution();
        // 0 + 1 = 1
        assertEquals(Arrays.asList(1, 2), s.incrList(Arrays.asList(0, 1)));
    }

    @Test
    void testNegativeNumbers() {
        Solution s = new Solution();
        // -1 + 1 = 0
        assertEquals(Arrays.asList(0, -1), s.incrList(Arrays.asList(-1, -2)));
    }

    @Test
    void testLargerList() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(6, 4, 6, 3, 4, 4, 10, 1, 124),
                s.incrList(Arrays.asList(5, 3, 5, 2, 3, 3, 9, 0, 123)));
    }
}

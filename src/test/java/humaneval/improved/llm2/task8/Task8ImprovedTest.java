package humaneval.improved.llm2.task8;

import humaneval.llm2.task8.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task8ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: loop body never entered → sum=0, product=1
        assertEquals(Arrays.asList(0, 1), s.sumProduct(List.of()));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // One iteration only
        assertEquals(Arrays.asList(5, 5), s.sumProduct(List.of(5)));
    }

    @Test
    void testMultipleElements() {
        Solution s = new Solution();
        // Multiple iterations
        assertEquals(Arrays.asList(10, 24), s.sumProduct(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    void testWithZero() {
        Solution s = new Solution();
        // Product becomes 0 when list contains 0
        assertEquals(Arrays.asList(3, 0), s.sumProduct(Arrays.asList(1, 0, 2)));
    }

    @Test
    void testNegativeNumbers() {
        Solution s = new Solution();
        // Negative numbers: sum=-6, product=-6
        assertEquals(Arrays.asList(-6, -6), s.sumProduct(Arrays.asList(-1, -2, -3)));
    }
}

package humaneval.improved.llm2.task34;

import humaneval.llm2.task34.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task34ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // HashSet and sort on empty → empty result
        assertEquals(List.of(), s.unique(List.of()));
    }

    @Test
    void testNoDuplicates() {
        Solution s = new Solution();
        // All unique → sorted
        assertEquals(Arrays.asList(1, 2, 3), s.unique(Arrays.asList(3, 1, 2)));
    }

    @Test
    void testWithDuplicates() {
        Solution s = new Solution();
        // Duplicates removed, sorted
        assertEquals(Arrays.asList(0, 2, 3, 5, 9, 123),
                s.unique(Arrays.asList(5, 3, 5, 2, 3, 3, 9, 0, 123)));
    }

    @Test
    void testAllSame() {
        Solution s = new Solution();
        // All same → single element
        assertEquals(List.of(7), s.unique(Arrays.asList(7, 7, 7)));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        assertEquals(List.of(42), s.unique(List.of(42)));
    }

    @Test
    void testNegativeNumbers() {
        Solution s = new Solution();
        // Negative numbers sorted correctly
        assertEquals(Arrays.asList(-3, -1, 0, 2), s.unique(Arrays.asList(2, -1, 0, -3, 2)));
    }
}

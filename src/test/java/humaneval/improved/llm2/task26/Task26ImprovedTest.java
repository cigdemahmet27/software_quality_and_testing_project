package humaneval.improved.llm2.task26;

import humaneval.llm2.task26.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task26ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: loop and stream produce empty result
        assertEquals(List.of(), s.removeDuplicates(List.of()));
    }

    @Test
    void testNoDuplicates() {
        Solution s = new Solution();
        // Branch: all counts == 1 → all kept
        assertEquals(Arrays.asList(1, 2, 3), s.removeDuplicates(Arrays.asList(1, 2, 3)));
    }

    @Test
    void testAllDuplicates() {
        Solution s = new Solution();
        // Branch: all counts > 1 → all removed
        assertEquals(List.of(), s.removeDuplicates(Arrays.asList(1, 1, 2, 2)));
    }

    @Test
    void testMixed() {
        Solution s = new Solution();
        // Branch: filter keeps count==1, removes count>1
        assertEquals(Arrays.asList(1, 3, 4), s.removeDuplicates(Arrays.asList(1, 2, 3, 2, 4)));
    }

    @Test
    void testTripleDuplicate() {
        Solution s = new Solution();
        // Element appearing 3 times → removed
        assertEquals(Arrays.asList(2), s.removeDuplicates(Arrays.asList(1, 1, 2, 1)));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Single element → count==1 → kept
        assertEquals(List.of(5), s.removeDuplicates(List.of(5)));
    }
}

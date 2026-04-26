package humaneval.improved.llm2.task0;

import humaneval.llm2.task0.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task0ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: numbers.size()==0 → outer loop never entered
        assertFalse(s.hasCloseElements(Collections.emptyList(), 0.5));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Branch: size==1 → inner loop never entered
        assertFalse(s.hasCloseElements(Collections.singletonList(1.0), 0.5));
    }

    @Test
    void testCloseElementsFound() {
        Solution s = new Solution();
        // Branch: distance < threshold → return true
        assertTrue(s.hasCloseElements(Arrays.asList(1.0, 2.8, 3.0, 4.0, 5.0, 2.0), 0.3));
    }

    @Test
    void testNoCloseElements() {
        Solution s = new Solution();
        // Branch: distance >= threshold for all pairs → return false
        assertFalse(s.hasCloseElements(Arrays.asList(1.0, 3.0, 5.0), 0.5));
    }

    @Test
    void testExactThreshold() {
        Solution s = new Solution();
        // Boundary: distance == threshold (not strictly less) → false
        assertFalse(s.hasCloseElements(Arrays.asList(1.0, 1.5), 0.5));
    }

    @Test
    void testJustBelowThreshold() {
        Solution s = new Solution();
        // Boundary: distance just below threshold → true
        assertTrue(s.hasCloseElements(Arrays.asList(1.0, 1.49), 0.5));
    }

    @Test
    void testTwoIdenticalElements() {
        Solution s = new Solution();
        // Branch: distance == 0 < any positive threshold → true
        assertTrue(s.hasCloseElements(Arrays.asList(2.0, 2.0), 0.1));
    }
}

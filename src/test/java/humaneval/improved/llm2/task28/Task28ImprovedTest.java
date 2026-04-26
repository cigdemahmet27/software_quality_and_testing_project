package humaneval.improved.llm2.task28;

import humaneval.llm2.task28.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task28ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Empty list → empty string
        assertEquals("", s.concatenate(List.of()));
    }

    @Test
    void testSingleElement() {
        Solution s = new Solution();
        // Single string → returned as-is
        assertEquals("hello", s.concatenate(List.of("hello")));
    }

    @Test
    void testMultipleElements() {
        Solution s = new Solution();
        // Multiple strings concatenated
        assertEquals("abc", s.concatenate(Arrays.asList("a", "b", "c")));
    }

    @Test
    void testWithEmptyStrings() {
        Solution s = new Solution();
        // Empty strings in list → no effect
        assertEquals("ac", s.concatenate(Arrays.asList("a", "", "c")));
    }

    @Test
    void testAllEmpty() {
        Solution s = new Solution();
        // All empty strings → empty result
        assertEquals("", s.concatenate(Arrays.asList("", "", "")));
    }
}

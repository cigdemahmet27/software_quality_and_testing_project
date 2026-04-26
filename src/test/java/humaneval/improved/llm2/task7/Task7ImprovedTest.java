package humaneval.improved.llm2.task7;

import humaneval.llm2.task7.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task7ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: loop body never entered → empty result
        assertEquals(List.of(), s.filterBySubstring(List.of(), "a"));
    }

    @Test
    void testAllMatch() {
        Solution s = new Solution();
        // Branch: s.contains(substring) is true for all
        assertEquals(Arrays.asList("abc", "ba"), s.filterBySubstring(Arrays.asList("abc", "ba"), "a"));
    }

    @Test
    void testNoneMatch() {
        Solution s = new Solution();
        // Branch: s.contains(substring) is false for all
        assertEquals(List.of(), s.filterBySubstring(Arrays.asList("xyz", "pqr"), "a"));
    }

    @Test
    void testPartialMatch() {
        Solution s = new Solution();
        // Branch: mixed — some contain, some don't
        assertEquals(Arrays.asList("abc", "bacd", "array"),
                s.filterBySubstring(Arrays.asList("abc", "bacd", "cde", "array"), "a"));
    }

    @Test
    void testSubstringAtEnd() {
        Solution s = new Solution();
        // Branch: substring found at end of string
        assertEquals(Arrays.asList("cola"), s.filterBySubstring(Arrays.asList("cola", "pep"), "la"));
    }

    @Test
    void testEmptySubstring() {
        Solution s = new Solution();
        // Edge: empty substring matches everything
        assertEquals(Arrays.asList("abc", "def"),
                s.filterBySubstring(Arrays.asList("abc", "def"), ""));
    }
}

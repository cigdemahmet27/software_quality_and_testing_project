package humaneval.improved.llm2.task29;

import humaneval.llm2.task29.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task29ImprovedTest {
    @Test
    void testEmptyList() {
        Solution s = new Solution();
        // Branch: stream is empty → empty result
        assertEquals(List.of(), s.filterByPrefix(List.of(), "a"));
    }

    @Test
    void testAllMatch() {
        Solution s = new Solution();
        // Branch: startsWith true for all
        assertEquals(Arrays.asList("abc", "array"),
                s.filterByPrefix(Arrays.asList("abc", "array"), "a"));
    }

    @Test
    void testNoneMatch() {
        Solution s = new Solution();
        // Branch: startsWith false for all
        assertEquals(List.of(), s.filterByPrefix(Arrays.asList("bcd", "cde"), "a"));
    }

    @Test
    void testPartialMatch() {
        Solution s = new Solution();
        // Mixed: some start with prefix, some don't
        assertEquals(Arrays.asList("abc", "array"),
                s.filterByPrefix(Arrays.asList("abc", "bcd", "cde", "array"), "a"));
    }

    @Test
    void testEmptyPrefix() {
        Solution s = new Solution();
        // Edge: empty prefix matches everything
        assertEquals(Arrays.asList("abc", "def"),
                s.filterByPrefix(Arrays.asList("abc", "def"), ""));
    }

    @Test
    void testPrefixLongerThanString() {
        Solution s = new Solution();
        // Prefix longer than string → no match
        assertEquals(List.of(), s.filterByPrefix(List.of("ab"), "abc"));
    }
}

package humaneval.improved.llm2.task18;

import humaneval.llm2.task18.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task18ImprovedTest {
    @Test
    void testEmptyString() {
        Solution s = new Solution();
        // Branch: string.length() - substring.length() < 0 → loop skipped
        assertEquals(0, s.howManyTimes("", "a"));
    }

    @Test
    void testNoOccurrence() {
        Solution s = new Solution();
        // Branch: equals is always false
        assertEquals(0, s.howManyTimes("abc", "z"));
    }

    @Test
    void testSingleOccurrence() {
        Solution s = new Solution();
        // One match found
        assertEquals(1, s.howManyTimes("abc", "b"));
    }

    @Test
    void testNonOverlapping() {
        Solution s = new Solution();
        // Multiple non-overlapping occurrences
        assertEquals(3, s.howManyTimes("aaa", "a"));
    }

    @Test
    void testOverlapping() {
        Solution s = new Solution();
        // Branch: overlapping matches counted
        assertEquals(3, s.howManyTimes("aaaa", "aa"));
    }

    @Test
    void testSubstringEqualsString() {
        Solution s = new Solution();
        // Exact match → 1 occurrence
        assertEquals(1, s.howManyTimes("hello", "hello"));
    }

    @Test
    void testSubstringLongerThanString() {
        Solution s = new Solution();
        // Branch: loop condition fails immediately → 0
        assertEquals(0, s.howManyTimes("hi", "hello"));
    }
}

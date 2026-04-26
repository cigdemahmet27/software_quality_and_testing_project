package humaneval.improved.llm2.task16;

import humaneval.llm2.task16.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task16ImprovedTest {
    @Test
    void testEmptyString() {
        Solution s = new Solution();
        // Branch: loop never entered → set is empty → size 0
        assertEquals(0, s.countDistinctCharacters(""));
    }

    @Test
    void testMixedCase() {
        Solution s = new Solution();
        // Branch: toLowerCase collapses case → 3 distinct chars
        assertEquals(3, s.countDistinctCharacters("xyzXYZ"));
    }

    @Test
    void testAllSame() {
        Solution s = new Solution();
        // Branch: all chars are same → 1 distinct
        assertEquals(1, s.countDistinctCharacters("aaaa"));
    }

    @Test
    void testWithSpecialChars() {
        Solution s = new Solution();
        // Non-letter characters also counted
        assertEquals(4, s.countDistinctCharacters("a1b!"));
    }

    @Test
    void testJerry() {
        Solution s = new Solution();
        // "Jerry" → j, e, r, y = 4 distinct
        assertEquals(4, s.countDistinctCharacters("Jerry"));
    }

    @Test
    void testSingleChar() {
        Solution s = new Solution();
        // One char → 1 distinct
        assertEquals(1, s.countDistinctCharacters("Z"));
    }
}

package humaneval.improved.llm2.task23;

import humaneval.llm2.task23.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task23ImprovedTest {
    @Test
    void testEmptyString() {
        Solution s = new Solution();
        // Branch: string.length() returns 0
        assertEquals(0, s.strlen(""));
    }

    @Test
    void testSingleChar() {
        Solution s = new Solution();
        assertEquals(1, s.strlen("a"));
    }

    @Test
    void testMultipleChars() {
        Solution s = new Solution();
        assertEquals(3, s.strlen("abc"));
    }

    @Test
    void testWithSpaces() {
        Solution s = new Solution();
        // Spaces count as characters
        assertEquals(5, s.strlen("ab cd"));
    }

    @Test
    void testLongerString() {
        Solution s = new Solution();
        assertEquals(11, s.strlen("hello world"));
    }
}

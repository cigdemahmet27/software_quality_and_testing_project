package humaneval.improved.llm2.task27;

import humaneval.llm2.task27.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task27ImprovedTest {
    @Test
    void testEmptyString() {
        Solution s = new Solution();
        // Branch: loop never entered
        assertEquals("", s.flipCase(""));
    }

    @Test
    void testAllLowercase() {
        Solution s = new Solution();
        // Branch: isLowerCase true for all → toUpperCase
        assertEquals("HELLO", s.flipCase("hello"));
    }

    @Test
    void testAllUppercase() {
        Solution s = new Solution();
        // Branch: isLowerCase false for all → toLowerCase
        assertEquals("hello", s.flipCase("HELLO"));
    }

    @Test
    void testMixedCase() {
        Solution s = new Solution();
        // Both branches exercised
        assertEquals("hELLO", s.flipCase("Hello"));
    }

    @Test
    void testWithDigits() {
        Solution s = new Solution();
        // Digits are not lowercase → go to else → toLowerCase (no-op)
        assertEquals("ABC123", s.flipCase("abc123"));
    }

    @Test
    void testSingleChar() {
        Solution s = new Solution();
        assertEquals("A", s.flipCase("a"));
        assertEquals("a", s.flipCase("A"));
    }
}

package humaneval.improved.llm2.task10;

import humaneval.llm2.task10.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task10ImprovedTest {
    @Test
    void testEmptyString() {
        Solution s = new Solution();
        // Branch: string.length() == 0 → return ""
        assertEquals("", s.makePalindrome(""));
    }

    @Test
    void testAlreadyPalindrome() {
        Solution s = new Solution();
        // Branch: entire string is palindrome → no suffix appended
        assertEquals("aba", s.makePalindrome("aba"));
    }

    @Test
    void testSingleChar() {
        Solution s = new Solution();
        // Branch: single char is palindrome → return itself
        assertEquals("a", s.makePalindrome("a"));
    }

    @Test
    void testNonPalindrome() {
        Solution s = new Solution();
        // Branch: loop increments beginningOfSuffix, appends reversed prefix
        assertEquals("catac", s.makePalindrome("cat"));
    }

    @Test
    void testPartialPalindromeSuffix() {
        Solution s = new Solution();
        // Branch: suffix "ata" is palindrome, only "c" reversed appended
        assertEquals("catac", s.makePalindrome("cata"));
    }

    @Test
    void testIsPalindromeTrue() {
        Solution s = new Solution();
        // Direct test of isPalindrome helper
        assertTrue(s.isPalindrome("racecar"));
    }

    @Test
    void testIsPalindromeFalse() {
        Solution s = new Solution();
        // Branch: charAt(i) != charAt(j) → return false
        assertFalse(s.isPalindrome("hello"));
    }

    @Test
    void testIsPalindromeEvenLength() {
        Solution s = new Solution();
        // Even-length palindrome
        assertTrue(s.isPalindrome("abba"));
    }
}

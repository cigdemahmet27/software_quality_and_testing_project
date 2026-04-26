package humaneval.improved.llm1.task10;

import humaneval.llm1.task10.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task10ImprovedTest {
    @Test
    void testIsPalindrome() {
        Solution s = new Solution();
        
        // True palindrome
        assertTrue(s.isPalindrome("racecar"));
        // Empty is palindrome
        assertTrue(s.isPalindrome(""));
        // Single char is palindrome
        assertTrue(s.isPalindrome("a"));
        // False palindrome
        assertFalse(s.isPalindrome("hello"));
    }

    @Test
    void testMakePalindrome() {
        Solution s = new Solution();
        
        // Branch: n == 0
        assertEquals("", s.makePalindrome(""));
        
        // Loop coverage for makePalindrome
        assertEquals("catac", s.makePalindrome("cat"));
        assertEquals("catac", s.makePalindrome("cata"));
        
        // Palindrome already
        assertEquals("racecar", s.makePalindrome("racecar"));
        
        // Entire string needs to be reversed except last char
        assertEquals("abcba", s.makePalindrome("abc"));
    }
}

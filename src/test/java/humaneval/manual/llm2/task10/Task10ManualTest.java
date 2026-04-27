/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #10 - makePalindrome (LLM2)
 */
package humaneval.manual.llm2.task10;

import humaneval.llm2.task10.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for makePalindrome
 *
 * Specification: Find the shortest palindrome that begins with a supplied string.
 * Algorithm: find the longest postfix of the string that is a palindrome,
 * then append to the end the reverse of the string prefix that comes before it.
 *
 * VALID Equivalence Classes (non-empty strings):
 * V1: String already a palindrome → returns itself
 * V2: String with palindromic suffix → appends reversed prefix
 * V3: String with no palindromic suffix (only last char) → appends most of reversed string
 * V4: String with palindromic suffix of length > 1
 *
 * INVALID Equivalence Classes (degenerate inputs):
 * I1: Empty string → ""
 *
 * Boundary Conditions:
 * BC1: Single character → returns itself (trivially palindrome)
 * BC2: Two same characters → already palindrome
 * BC3: Two different characters → appends 1 char
 * BC4: All same characters → already palindrome
 * BC5: Result is indeed a palindrome (verification)
 * BC6: Result starts with original string (verification)
 */
class Task10ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: 'aba' already palindrome → 'aba'")
    void testV1_alreadyPalindrome() {
        assertEquals("aba", solution.makePalindrome("aba"));
    }

    @Test
    @DisplayName("V1: 'racecar' already palindrome → 'racecar'")
    void testV1_racecar() {
        assertEquals("racecar", solution.makePalindrome("racecar"));
    }

    @Test
    @DisplayName("V1: 'aa' already palindrome → 'aa'")
    void testV1_doubleA() {
        assertEquals("aa", solution.makePalindrome("aa"));
    }

    @Test
    @DisplayName("V2: 'cat' → 'catac' (docstring example)")
    void testV2_catDocstring() {
        assertEquals("catac", solution.makePalindrome("cat"));
    }

    @Test
    @DisplayName("V2: 'cata' → 'catac' (docstring example)")
    void testV2_cataDocstring() {
        assertEquals("catac", solution.makePalindrome("cata"));
    }

    @Test
    @DisplayName("V3: 'abcd' → 'abcdcba' (no palindromic suffix)")
    void testV3_noPalindromicSuffix() {
        assertEquals("abcdcba", solution.makePalindrome("abcd"));
    }

    @Test
    @DisplayName("V3: 'xyz' → 'xyzyx'")
    void testV3_xyz() {
        assertEquals("xyzyx", solution.makePalindrome("xyz"));
    }

    @Test
    @DisplayName("V4: 'aab' → 'aabaa' (palindromic suffix > 1 char)")
    void testV4_palindromicSuffix() {
        assertEquals("aabaa", solution.makePalindrome("aab"));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Empty string → ''")
    void testI1_emptyString() {
        assertEquals("", solution.makePalindrome(""));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: Single char 'a' → 'a'")
    void testBC1_singleChar() {
        assertEquals("a", solution.makePalindrome("a"));
    }

    @Test
    @DisplayName("BC1: Single char 'z' → 'z'")
    void testBC1_singleCharZ() {
        assertEquals("z", solution.makePalindrome("z"));
    }

    @Test
    @DisplayName("BC2: 'bb' → 'bb' (two same chars)")
    void testBC2_twoSameChars() {
        assertEquals("bb", solution.makePalindrome("bb"));
    }

    @Test
    @DisplayName("BC3: 'ab' → 'aba' (two different chars)")
    void testBC3_twoDifferentChars() {
        assertEquals("aba", solution.makePalindrome("ab"));
    }

    @Test
    @DisplayName("BC4: 'aaaa' → 'aaaa' (all same chars)")
    void testBC4_allSameChars() {
        assertEquals("aaaa", solution.makePalindrome("aaaa"));
    }

    @Test
    @DisplayName("BC5: Result of 'hello' is a palindrome")
    void testBC5_resultIsPalindrome() {
        String result = solution.makePalindrome("hello");
        assertEquals(result, new StringBuilder(result).reverse().toString());
    }

    @Test
    @DisplayName("BC6: Result starts with original input 'testing'")
    void testBC6_resultStartsWithOriginal() {
        String input = "testing";
        assertTrue(solution.makePalindrome(input).startsWith(input));
    }
}

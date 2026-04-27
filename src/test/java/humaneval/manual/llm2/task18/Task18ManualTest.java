/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #18 - howManyTimes (LLM2) [ZORUNLU GÖREV]
 */
package humaneval.manual.llm2.task18;

import humaneval.llm2.task18.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for howManyTimes [MANDATORY TASK]
 *
 * Specification: Find how many times a given substring can be found in the
 * original string. Count overlapping cases.
 *
 * VALID Equivalence Classes (non-empty string and substring):
 * V1: Substring found exactly once → 1
 * V2: Substring found multiple times (non-overlapping) → count
 * V3: Overlapping occurrences → count with overlaps
 * V4: Substring not found → 0
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Empty string with non-empty substring → 0
 * I2: Substring longer than string → 0
 *
 * Boundary Conditions:
 * BC1: Single char match → 1
 * BC2: Single char no match → 0
 * BC3: Substring at the very beginning → 1
 * BC4: Substring at the very end → 1
 * BC5: Maximum overlapping (e.g., "aaa" in "aaaa" → 2)
 * BC6: Substring equals entire string → 1
 * BC7: Case-sensitive check
 */
class Task18ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: 'abc' found once in 'xyzabcdef' → 1")
    void testV1_foundOnce() {
        assertEquals(1, solution.howManyTimes("xyzabcdef", "abc"));
    }

    @Test
    @DisplayName("V2: 'ab' in 'ababab' → 3")
    void testV2_multipleOccurrences() {
        assertEquals(3, solution.howManyTimes("ababab", "ab"));
    }

    @Test
    @DisplayName("V3: 'aa' in 'aaaa' → 3 (docstring, overlapping)")
    void testV3_overlappingDocstring() {
        assertEquals(3, solution.howManyTimes("aaaa", "aa"));
    }

    @Test
    @DisplayName("V3: 'a' in 'aaa' → 3 (docstring)")
    void testV3_singleCharOverlapping() {
        assertEquals(3, solution.howManyTimes("aaa", "a"));
    }

    @Test
    @DisplayName("V3: 'aba' in 'ababa' → 2 (overlapping)")
    void testV3_overlappingAba() {
        assertEquals(2, solution.howManyTimes("ababa", "aba"));
    }

    @Test
    @DisplayName("V3: 'abab' in 'ababab' → 2 (overlapping)")
    void testV3_overlappingLongerPattern() {
        assertEquals(2, solution.howManyTimes("ababab", "abab"));
    }

    @Test
    @DisplayName("V4: 'xyz' not in 'abcdef' → 0")
    void testV4_notFound() {
        assertEquals(0, solution.howManyTimes("abcdef", "xyz"));
    }

    @Test
    @DisplayName("V4: 'ab' not in 'cd' → 0")
    void testV4_notFoundShort() {
        assertEquals(0, solution.howManyTimes("cd", "ab"));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Empty string with substring 'a' → 0")
    void testI1_emptyString() {
        assertEquals(0, solution.howManyTimes("", "a"));
    }

    @Test
    @DisplayName("I2: Substring 'abcdef' longer than string 'abc' → 0")
    void testI2_substringLongerThanString() {
        assertEquals(0, solution.howManyTimes("abc", "abcdef"));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: Single char 'a' in 'a' → 1")
    void testBC1_singleCharMatch() {
        assertEquals(1, solution.howManyTimes("a", "a"));
    }

    @Test
    @DisplayName("BC2: Single char 'b' in 'a' → 0")
    void testBC2_singleCharNoMatch() {
        assertEquals(0, solution.howManyTimes("a", "b"));
    }

    @Test
    @DisplayName("BC3: 'he' at start of 'hello' → 1")
    void testBC3_substringAtBeginning() {
        assertEquals(1, solution.howManyTimes("hello", "he"));
    }

    @Test
    @DisplayName("BC4: 'lo' at end of 'hello' → 1")
    void testBC4_substringAtEnd() {
        assertEquals(1, solution.howManyTimes("hello", "lo"));
    }

    @Test
    @DisplayName("BC5: 'aaa' in 'aaaa' → 2 (max overlapping)")
    void testBC5_maximumOverlapping() {
        assertEquals(2, solution.howManyTimes("aaaa", "aaa"));
    }

    @Test
    @DisplayName("BC6: 'hello' in 'hello' → 1 (exact match)")
    void testBC6_substringEqualsString() {
        assertEquals(1, solution.howManyTimes("hello", "hello"));
    }

    @Test
    @DisplayName("BC7: 'A' in 'aaa' → 0 (case-sensitive)")
    void testBC7_caseSensitive() {
        assertEquals(0, solution.howManyTimes("aaa", "A"));
    }
}

/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #16 - countDistinctCharacters (LLM1)
 */
package humaneval.manual.llm1.task16;

import humaneval.llm1.task16.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for countDistinctCharacters
 *
 * Specification: Given a string, find out how many distinct characters
 * (regardless of case) does it consist of.
 *
 * VALID Equivalence Classes (non-empty strings):
 * V1: Mixed case same letters → counted as one (e.g., "xyzXYZ" → 3)
 * V2: All distinct characters → count equals unique lowercase count
 * V3: All same character repeated → 1
 * V4: String with digits and special characters
 *
 * INVALID Equivalence Classes (degenerate inputs):
 * I1: Empty string → 0
 *
 * Boundary Conditions:
 * BC1: Single character (lowercase or uppercase) → 1
 * BC2: Two same characters different case → 1
 * BC3: String with only spaces → 1
 */
class Task16ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: 'xyzXYZ' → 3 (docstring example, case-insensitive)")
    void testV1_mixedCaseDocstring() {
        assertEquals(3, solution.countDistinctCharacters("xyzXYZ"));
    }

    @Test
    @DisplayName("V1: 'Jerry' → 4 (docstring example)")
    void testV1_jerry() {
        assertEquals(4, solution.countDistinctCharacters("Jerry"));
    }

    @Test
    @DisplayName("V1: 'aAbBcCaAbBcC' → 3 (long mixed case)")
    void testV1_longMixedCase() {
        assertEquals(3, solution.countDistinctCharacters("aAbBcCaAbBcC"));
    }

    @Test
    @DisplayName("V2: 'abcdef' → 6 (all distinct)")
    void testV2_allDistinct() {
        assertEquals(6, solution.countDistinctCharacters("abcdef"));
    }

    @Test
    @DisplayName("V3: 'aaaa' → 1 (all same)")
    void testV3_allSame() {
        assertEquals(1, solution.countDistinctCharacters("aaaa"));
    }

    @Test
    @DisplayName("V3: 'AaAa' → 1 (same char mixed case)")
    void testV3_allSameMixedCase() {
        assertEquals(1, solution.countDistinctCharacters("AaAa"));
    }

    @Test
    @DisplayName("V4: 'a1b2c3' → 6 (with digits)")
    void testV4_withDigits() {
        assertEquals(6, solution.countDistinctCharacters("a1b2c3"));
    }

    @Test
    @DisplayName("V4: 'a!a@a' → 3 (with special chars: a, !, @)")
    void testV4_withSpecialChars() {
        assertEquals(3, solution.countDistinctCharacters("a!a@a"));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Empty string → 0")
    void testI1_emptyString() {
        assertEquals(0, solution.countDistinctCharacters(""));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: Single char 'a' → 1")
    void testBC1_singleLowercase() {
        assertEquals(1, solution.countDistinctCharacters("a"));
    }

    @Test
    @DisplayName("BC1: Single char 'A' → 1")
    void testBC1_singleUppercase() {
        assertEquals(1, solution.countDistinctCharacters("A"));
    }

    @Test
    @DisplayName("BC2: 'aA' → 1 (same char different case)")
    void testBC2_twoSameDifferentCase() {
        assertEquals(1, solution.countDistinctCharacters("aA"));
    }

    @Test
    @DisplayName("BC3: '   ' (3 spaces) → 1")
    void testBC3_onlySpaces() {
        assertEquals(1, solution.countDistinctCharacters("   "));
    }
}

/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #14 - allPrefixes (LLM2)
 */
package humaneval.manual.llm2.task14;

import humaneval.llm2.task14.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Manual Black-Box Tests for allPrefixes
 *
 * Specification: Return list of all prefixes from shortest to longest of the input string.
 *
 * VALID Equivalence Classes (non-empty strings):
 * V1: Multi-character string → list of incremental prefixes
 * V2: String with repeated characters → distinct prefix strings
 * V3: String with special characters → handles non-alphanumeric
 *
 * INVALID Equivalence Classes (degenerate inputs):
 * I1: Empty string → empty list
 *
 * Boundary Conditions:
 * BC1: Single character → list with one element
 * BC2: Two characters → list with two elements
 * BC3: String with spaces → spaces preserved in prefixes
 */
class Task14ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: 'abc' → ['a', 'ab', 'abc']")
    void testV1_multiCharString() {
        assertEquals(Arrays.asList("a", "ab", "abc"), solution.allPrefixes("abc"));
    }

    @Test
    @DisplayName("V1: 'hello' → 5 incremental prefixes")
    void testV1_helloString() {
        assertEquals(Arrays.asList("h", "he", "hel", "hell", "hello"), solution.allPrefixes("hello"));
    }

    @Test
    @DisplayName("V1: Result size equals input string length")
    void testV1_resultSizeMatchesLength() {
        assertEquals(7, solution.allPrefixes("testing").size());
    }

    @Test
    @DisplayName("V2: 'aaa' → ['a', 'aa', 'aaa'] (repeated chars)")
    void testV2_repeatedChars() {
        assertEquals(Arrays.asList("a", "aa", "aaa"), solution.allPrefixes("aaa"));
    }

    @Test
    @DisplayName("V3: 'a!b' → ['a', 'a!', 'a!b'] (special chars)")
    void testV3_specialChars() {
        assertEquals(Arrays.asList("a", "a!", "a!b"), solution.allPrefixes("a!b"));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Empty string → empty list")
    void testI1_emptyString() {
        assertEquals(List.of(), solution.allPrefixes(""));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: Single char 'a' → ['a']")
    void testBC1_singleChar() {
        assertEquals(Arrays.asList("a"), solution.allPrefixes("a"));
    }

    @Test
    @DisplayName("BC1: Single char 'z' → ['z']")
    void testBC1_singleCharZ() {
        assertEquals(Arrays.asList("z"), solution.allPrefixes("z"));
    }

    @Test
    @DisplayName("BC2: 'ab' → ['a', 'ab']")
    void testBC2_twoChars() {
        assertEquals(Arrays.asList("a", "ab"), solution.allPrefixes("ab"));
    }

    @Test
    @DisplayName("BC3: 'a b' → ['a', 'a ', 'a b'] (spaces preserved)")
    void testBC3_withSpaces() {
        assertEquals(Arrays.asList("a", "a ", "a b"), solution.allPrefixes("a b"));
    }
}

/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #15 - stringSequence (LLM2)
 */
package humaneval.manual.llm2.task15;

import humaneval.llm2.task15.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for stringSequence
 *
 * Specification: Return a string containing space-delimited numbers from 0 up to n inclusive.
 *
 * VALID Equivalence Classes (n >= 0):
 * V1: Small positive n → correct short sequence
 * V2: Larger positive n → sequence with double-digit numbers
 *
 * INVALID Equivalence Classes (degenerate inputs):
 * I1: n = 0 → returns only "0" (minimum valid, edge of domain)
 *
 * Boundary Conditions:
 * BC1: n = 0 → "0" (no spaces)
 * BC2: n = 1 → "0 1" (first sequence with space)
 * BC3: n = 2 → "0 1 2"
 * BC4: No leading/trailing spaces in result
 */
class Task15ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: n=5 → '0 1 2 3 4 5'")
    void testV1_smallPositive() {
        assertEquals("0 1 2 3 4 5", solution.stringSequence(5));
    }

    @Test
    @DisplayName("V1: n=3 → '0 1 2 3'")
    void testV1_three() {
        assertEquals("0 1 2 3", solution.stringSequence(3));
    }

    @Test
    @DisplayName("V1: n=7 → each number correct in sequence")
    void testV1_sequenceCorrectness() {
        String[] parts = solution.stringSequence(7).split(" ");
        for (int i = 0; i <= 7; i++) {
            assertEquals(String.valueOf(i), parts[i]);
        }
    }

    @Test
    @DisplayName("V2: n=10 → '0 1 2 3 4 5 6 7 8 9 10'")
    void testV2_doubleDigit() {
        assertEquals("0 1 2 3 4 5 6 7 8 9 10", solution.stringSequence(10));
    }

    @Test
    @DisplayName("V2: n=15 → 16 space-separated numbers")
    void testV2_fifteen() {
        String[] parts = solution.stringSequence(15).split(" ");
        assertEquals(16, parts.length);
        assertEquals("0", parts[0]);
        assertEquals("15", parts[15]);
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: n=0 → '0' (minimum input, edge of domain)")
    void testI1_zero() {
        assertEquals("0", solution.stringSequence(0));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: n=0 → no spaces in result")
    void testBC1_zeroNoSpaces() {
        assertFalse(solution.stringSequence(0).contains(" "));
    }

    @Test
    @DisplayName("BC2: n=1 → '0 1'")
    void testBC2_one() {
        assertEquals("0 1", solution.stringSequence(1));
    }

    @Test
    @DisplayName("BC3: n=2 → '0 1 2'")
    void testBC3_two() {
        assertEquals("0 1 2", solution.stringSequence(2));
    }

    @Test
    @DisplayName("BC4: No leading or trailing spaces")
    void testBC4_noLeadingTrailingSpaces() {
        String result = solution.stringSequence(5);
        assertEquals(result.trim(), result);
    }
}

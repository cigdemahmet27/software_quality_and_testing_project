/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #11 - stringXor (LLM2)
 */
package humaneval.manual.llm2.task11;

import humaneval.llm2.task11.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for stringXor
 *
 * Specification: Input are two strings a and b consisting only of 1s and 0s.
 * Perform binary XOR on these inputs and return result also as a string.
 *
 * VALID Equivalence Classes (equal-length binary strings):
 * V1: Same bits → all 0s result
 * V2: All different bits → all 1s result
 * V3: Mixed bits → mixed XOR result
 * V4: Longer binary strings → correct XOR
 *
 * INVALID Equivalence Classes (degenerate inputs):
 * I1: Empty strings → empty string result
 *
 * Boundary Conditions:
 * BC1: Single bit "0" XOR "0" → "0"
 * BC2: Single bit "1" XOR "1" → "0"
 * BC3: Single bit "0" XOR "1" → "1"
 * BC4: Single bit "1" XOR "0" → "1"
 * BC5: Self-inverse property (a XOR a = all zeros)
 */
class Task11ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: '010' XOR '010' → '000' (same bits)")
    void testV1_sameBits() {
        assertEquals("000", solution.stringXor("010", "010"));
    }

    @Test
    @DisplayName("V1: '111' XOR '111' → '000'")
    void testV1_allOnesXorAllOnes() {
        assertEquals("000", solution.stringXor("111", "111"));
    }

    @Test
    @DisplayName("V2: '010' XOR '101' → '111' (all different)")
    void testV2_allDifferent() {
        assertEquals("111", solution.stringXor("010", "101"));
    }

    @Test
    @DisplayName("V2: '000' XOR '111' → '111'")
    void testV2_zerosXorOnes() {
        assertEquals("111", solution.stringXor("000", "111"));
    }

    @Test
    @DisplayName("V3: '010' XOR '110' → '100' (docstring example)")
    void testV3_mixedDocstring() {
        assertEquals("100", solution.stringXor("010", "110"));
    }

    @Test
    @DisplayName("V3: '1010' XOR '0110' → '1100'")
    void testV3_mixedFourDigit() {
        assertEquals("1100", solution.stringXor("1010", "0110"));
    }

    @Test
    @DisplayName("V4: '10101010' XOR '01010101' → '11111111'")
    void testV4_longerStrings() {
        assertEquals("11111111", solution.stringXor("10101010", "01010101"));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: '' XOR '' → ''")
    void testI1_emptyStrings() {
        assertEquals("", solution.stringXor("", ""));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: '0' XOR '0' → '0'")
    void testBC1_zeroXorZero() {
        assertEquals("0", solution.stringXor("0", "0"));
    }

    @Test
    @DisplayName("BC2: '1' XOR '1' → '0'")
    void testBC2_oneXorOne() {
        assertEquals("0", solution.stringXor("1", "1"));
    }

    @Test
    @DisplayName("BC3: '0' XOR '1' → '1'")
    void testBC3_zeroXorOne() {
        assertEquals("1", solution.stringXor("0", "1"));
    }

    @Test
    @DisplayName("BC4: '1' XOR '0' → '1'")
    void testBC4_oneXorZero() {
        assertEquals("1", solution.stringXor("1", "0"));
    }

    @Test
    @DisplayName("BC5: Self-inverse '10110' XOR '10110' → '00000'")
    void testBC5_selfInverse() {
        assertEquals("00000", solution.stringXor("10110", "10110"));
    }
}

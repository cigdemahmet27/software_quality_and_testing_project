/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #0 - hasCloseElements (LLM2)
 * Equivalence Class Partitioning with Boundary Conditions
 */
package humaneval.manual.llm2.task0;

import humaneval.llm2.task0.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Manual Black-Box Tests for hasCloseElements
 *
 * Specification: Check if in given list of numbers, are any two numbers
 * closer to each other than given threshold.
 *
 * VALID Equivalence Classes (normal input domain, list with 2+ elements):
 * V1: List with a pair closer than threshold → true
 * V2: List with no pair closer than threshold → false
 * V3: Identical elements (distance=0) with positive threshold → true
 * V4: Large list with close pair hidden within → true
 * V5: List with negative numbers → valid computation
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Empty list → false (no pairs to compare)
 * I2: Single element list → false (no pairs to compare)
 *
 * Boundary Conditions:
 * BC1: Distance exactly equals threshold → false (strict < comparison)
 * BC2: Distance just below threshold → true
 * BC3: Threshold = 0 with distinct elements → false
 * BC4: Threshold = 0 with identical elements → false (0 < 0 is false)
 * BC5: Very large threshold → true (all pairs qualify)
 */
class Task0ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: List with close pair [1.0, 1.2] threshold=0.5 → true")
    void testV1_closePairExists() {
        assertTrue(solution.hasCloseElements(Arrays.asList(1.0, 1.2), 0.5));
    }

    @Test
    @DisplayName("V1: Docstring example - close pair exists → true")
    void testV1_docstringExample() {
        assertTrue(solution.hasCloseElements(
                Arrays.asList(1.0, 2.8, 3.0, 4.0, 5.0, 2.0), 0.3));
    }

    @Test
    @DisplayName("V2: No close pair [1.0, 2.0, 3.0] threshold=0.5 → false")
    void testV2_noClosePair() {
        assertFalse(solution.hasCloseElements(Arrays.asList(1.0, 2.0, 3.0), 0.5));
    }

    @Test
    @DisplayName("V3: Identical elements [5.0, 5.0] threshold=0.1 → true")
    void testV3_identicalElements() {
        assertTrue(solution.hasCloseElements(Arrays.asList(5.0, 5.0), 0.1));
    }

    @Test
    @DisplayName("V4: Close pair hidden at end of large list → true")
    void testV4_largeListCloseAtEnd() {
        assertTrue(solution.hasCloseElements(
                Arrays.asList(1.0, 3.0, 5.0, 7.0, 9.0, 9.05), 0.1));
    }

    @Test
    @DisplayName("V5: Negative numbers [-1.0, -1.1] threshold=0.2 → true")
    void testV5_negativeNumbers() {
        assertTrue(solution.hasCloseElements(Arrays.asList(-1.0, -1.1), 0.2));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Empty list → false")
    void testI1_emptyList() {
        assertFalse(solution.hasCloseElements(new ArrayList<>(), 0.5));
    }

    @Test
    @DisplayName("I2: Single element list → false")
    void testI2_singleElement() {
        assertFalse(solution.hasCloseElements(Arrays.asList(1.0), 0.5));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: Distance exactly equals threshold (1.0 < 1.0 = false)")
    void testBC1_distanceEqualsThreshold() {
        assertFalse(solution.hasCloseElements(Arrays.asList(1.0, 2.0), 1.0));
    }

    @Test
    @DisplayName("BC2: Distance just below threshold (0.9 < 1.0 = true)")
    void testBC2_distanceJustBelowThreshold() {
        assertTrue(solution.hasCloseElements(Arrays.asList(1.0, 1.9), 1.0));
    }

    @Test
    @DisplayName("BC3: Threshold=0 with distinct elements → false")
    void testBC3_zeroThresholdDistinct() {
        assertFalse(solution.hasCloseElements(Arrays.asList(1.0, 2.0), 0.0));
    }

    @Test
    @DisplayName("BC4: Threshold=0 with identical elements (0 < 0 = false)")
    void testBC4_zeroThresholdIdentical() {
        assertFalse(solution.hasCloseElements(Arrays.asList(5.0, 5.0), 0.0));
    }

    @Test
    @DisplayName("BC5: Very large threshold → all pairs close → true")
    void testBC5_largeThreshold() {
        assertTrue(solution.hasCloseElements(Arrays.asList(1.0, 100.0), 200.0));
    }
}

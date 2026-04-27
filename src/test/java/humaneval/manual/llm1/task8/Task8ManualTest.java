/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #8 - sumProduct (LLM1)
 * Equivalence Class Partitioning with Boundary Conditions
 */
package humaneval.manual.llm1.task8;

import humaneval.llm1.task8.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Manual Black-Box Tests for sumProduct
 *
 * Specification: For a given list of integers, return a tuple consisting
 * of a sum and a product of all the integers in a list.
 * Empty sum should be equal to 0 and empty product should be equal to 1.
 *
 * VALID Equivalence Classes (non-empty lists):
 * V1: Multiple positive integers → correct sum and product
 * V2: Single element list → [elem, elem]
 * V3: List with negative integers → handles sign correctly
 * V4: Mixed positive and negative → correct sign for product
 *
 * INVALID Equivalence Classes (degenerate inputs):
 * I1: Empty list → [0, 1] (special case from spec)
 * I2: List containing zero → product becomes 0
 *
 * Boundary Conditions:
 * BC1: Single element = 0 → [0, 0]
 * BC2: Single element = 1 (multiplicative identity) → [1, 1]
 * BC3: All elements are 1 → [n, 1]
 */
class Task8ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: [1,2,3,4] → [10, 24]")
    void testV1_multiplePositive() {
        assertEquals(Arrays.asList(10, 24), solution.sumProduct(Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    @DisplayName("V1: [2,3] → [5, 6]")
    void testV1_twoPositive() {
        assertEquals(Arrays.asList(5, 6), solution.sumProduct(Arrays.asList(2, 3)));
    }

    @Test
    @DisplayName("V2: Single element [5] → [5, 5]")
    void testV2_singleElement() {
        assertEquals(Arrays.asList(5, 5), solution.sumProduct(Arrays.asList(5)));
    }

    @Test
    @DisplayName("V2: Single element [7] → [7, 7]")
    void testV2_singleElementSeven() {
        assertEquals(Arrays.asList(7, 7), solution.sumProduct(Arrays.asList(7)));
    }

    @Test
    @DisplayName("V3: [-1,-2,-3] → [-6, -6] (odd count negatives)")
    void testV3_allNegative() {
        assertEquals(Arrays.asList(-6, -6), solution.sumProduct(Arrays.asList(-1, -2, -3)));
    }

    @Test
    @DisplayName("V3: [-2,-3] → [-5, 6] (even count, positive product)")
    void testV3_twoNegative() {
        assertEquals(Arrays.asList(-5, 6), solution.sumProduct(Arrays.asList(-2, -3)));
    }

    @Test
    @DisplayName("V4: [1,-2,3] → [2, -6] (mixed signs)")
    void testV4_mixedSigns() {
        assertEquals(Arrays.asList(2, -6), solution.sumProduct(Arrays.asList(1, -2, 3)));
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Empty list → [0, 1] (from spec)")
    void testI1_emptyList() {
        assertEquals(Arrays.asList(0, 1), solution.sumProduct(List.of()));
    }

    @Test
    @DisplayName("I2: [1,2,0,4] → [7, 0] (zero makes product 0)")
    void testI2_containsZero() {
        assertEquals(Arrays.asList(7, 0), solution.sumProduct(Arrays.asList(1, 2, 0, 4)));
    }

    @Test
    @DisplayName("I2: [0,0,0] → [0, 0] (all zeros)")
    void testI2_allZeros() {
        assertEquals(Arrays.asList(0, 0), solution.sumProduct(Arrays.asList(0, 0, 0)));
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: [0] → [0, 0]")
    void testBC1_singleZero() {
        assertEquals(Arrays.asList(0, 0), solution.sumProduct(Arrays.asList(0)));
    }

    @Test
    @DisplayName("BC2: [1] → [1, 1] (multiplicative identity)")
    void testBC2_singleOne() {
        assertEquals(Arrays.asList(1, 1), solution.sumProduct(Arrays.asList(1)));
    }

    @Test
    @DisplayName("BC3: [1,1,1,1] → [4, 1] (product stays 1)")
    void testBC3_allOnes() {
        assertEquals(Arrays.asList(4, 1), solution.sumProduct(Arrays.asList(1, 1, 1, 1)));
    }
}

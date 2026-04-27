/* @Authors
 * Student Names: Taha
 * Manual black-box tests for Task #2 - truncateNumber (LLM1)
 * Equivalence Class Partitioning with Boundary Conditions
 */
package humaneval.manual.llm1.task2;

import humaneval.llm1.task2.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for truncateNumber
 *
 * Specification: Given a positive floating point number, return the decimal part.
 *
 * VALID Equivalence Classes (positive floating point numbers):
 * V1: Number with small decimal part (e.g., 3.1 → 0.1)
 * V2: Number with large decimal part close to 1 (e.g., 2.999 → 0.999)
 * V3: Number with exactly 0.5 decimal (e.g., 3.5 → 0.5)
 * V4: Very large number (e.g., 123456.789 → 0.789)
 * V5: Number between 0 and 1 (e.g., 0.25 → 0.25)
 *
 * INVALID Equivalence Classes (edge/degenerate inputs):
 * I1: Integer number with zero decimal part (e.g., 5.0 → 0.0)
 * I2: Zero input (0.0 → 0.0, boundary of "positive")
 *
 * Boundary Conditions:
 * BC1: Number very close to integer from above (e.g., 1.0000001)
 * BC2: Number very close to next integer from below (e.g., 1.9999999)
 */
class Task2ManualTest {

    private final Solution solution = new Solution();

    // ==================== VALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("V1: Number 3.1 → decimal part 0.1")
    void testV1_smallDecimalPart() {
        assertEquals(0.1, solution.truncateNumber(3.1), 1e-9);
    }

    @Test
    @DisplayName("V1: Number 7.25 → decimal part 0.25")
    void testV1_quarterDecimal() {
        assertEquals(0.25, solution.truncateNumber(7.25), 1e-9);
    }

    @Test
    @DisplayName("V2: Number 2.999 → decimal part 0.999")
    void testV2_largeDecimalPart() {
        assertEquals(0.999, solution.truncateNumber(2.999), 1e-9);
    }

    @Test
    @DisplayName("V2: Number 4.95 → decimal part 0.95")
    void testV2_decimalClose95() {
        assertEquals(0.95, solution.truncateNumber(4.95), 1e-9);
    }

    @Test
    @DisplayName("V3: Number 3.5 → decimal part 0.5 (docstring example)")
    void testV3_exactlyHalf() {
        assertEquals(0.5, solution.truncateNumber(3.5), 1e-9);
    }

    @Test
    @DisplayName("V4: Large number 123456.789 → decimal part ~0.789")
    void testV4_veryLargeNumber() {
        assertEquals(0.789, solution.truncateNumber(123456.789), 1e-6);
    }

    @Test
    @DisplayName("V5: Number 0.25 (between 0 and 1) → 0.25")
    void testV5_smallPositiveNumber() {
        assertEquals(0.25, solution.truncateNumber(0.25), 1e-9);
    }

    @Test
    @DisplayName("V5: Number 0.99 (between 0 and 1) → 0.99")
    void testV5_smallPositiveCloseToOne() {
        assertEquals(0.99, solution.truncateNumber(0.99), 1e-9);
    }

    // ==================== INVALID EQUIVALENCE CLASSES ====================

    @Test
    @DisplayName("I1: Integer number 5.0 → decimal part 0.0")
    void testI1_integerNumber() {
        assertEquals(0.0, solution.truncateNumber(5.0), 1e-9);
    }

    @Test
    @DisplayName("I1: Integer number 1.0 → decimal part 0.0")
    void testI1_integerNumberOne() {
        assertEquals(0.0, solution.truncateNumber(1.0), 1e-9);
    }

    @Test
    @DisplayName("I2: Zero input 0.0 → 0.0")
    void testI2_zero() {
        assertEquals(0.0, solution.truncateNumber(0.0), 1e-9);
    }

    // ==================== BOUNDARY CONDITIONS ====================

    @Test
    @DisplayName("BC1: Number 1.0000001 → very small positive decimal")
    void testBC1_closeToIntegerAbove() {
        double result = solution.truncateNumber(1.0000001);
        assertTrue(result > 0 && result < 0.001, "Should be very small positive number");
    }

    @Test
    @DisplayName("BC2: Number 1.9999999 → very close to 1.0 but less")
    void testBC2_closeToNextIntegerBelow() {
        double result = solution.truncateNumber(1.9999999);
        assertTrue(result > 0.999 && result < 1.0, "Should be very close to 1.0 but less");
    }
}

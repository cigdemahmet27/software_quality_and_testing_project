/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm2.task45;

import humaneval.llm2.task45.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for triangleArea
 *
 * Specification: Given length of a side and height, return the area of the
 * triangle.
 * Formula: (a * h) / 2.0
 *
 * VALID Equivalence Classes:
 * V1: Both a and h are positive integers → correct area
 * V2: Both a and h are positive floating point numbers → correct area
 * V3: Large positive values → correct large area
 *
 * INVALID Equivalence Classes (Physical/Mathematical constraints):
 * I1: Side 'a' is negative → Should ideally handle as error or return 0
 * I2: Height 'h' is negative → Should ideally handle as error or return 0
 * I3: Side 'a' or height 'h' is zero → Area should be 0.0
 *
 * Boundary Conditions:
 * BC1: Very small positive values (Precision test) → Correct double precision
 * area
 * BC2: a or h equals Double.MAX_VALUE → Check for infinity/overflow handling
 * BC3: Minimal positive boundary (Double.MIN_VALUE) → Correct calculation
 */
class Task45ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Positive integers '5, 3' → 7.5")
  void testV1_positiveIntegers() {
    assertEquals(7.5, solution.triangleArea(5, 3), 0.0001);
  }

  @Test
  @DisplayName("V2: Positive doubles '2.5, 4.2' → 5.25")
  void testV2_positiveDoubles() {
    assertEquals(5.25, solution.triangleArea(2.5, 4.2), 0.0001);
  }

  @Test
  @DisplayName("V3: Large positive values '1000, 2000' → 1000000.0")
  void testV3_largeValues() {
    assertEquals(1000000.0, solution.triangleArea(1000, 2000), 0.0001);
  }

  // ==================== INVALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("I1: Negative side 'a' → -7.5 (Logic check: Area cannot be negative)")
  void testI1_negativeSide() {
    // Matematiksel olarak formül -7.5 döner ancak geometrik olarak bu geçersizdir.
    // Base kodun bu durumu kontrol etmediğini kanıtlıyoruz.
    assertTrue(solution.triangleArea(-5, 3) < 0, "Area should not be negative in a physical context.");
  }

  @Test
  @DisplayName("I2: Negative height 'h' → -7.5 (Physical constraint check)")
  void testI2_negativeHeight() {
    assertTrue(solution.triangleArea(5, -3) < 0);
  }

  @Test
  @DisplayName("I3: Height or side is zero → 0.0")
  void testI3_zeroInput() {
    assertEquals(0.0, solution.triangleArea(0, 5), 0.0001);
    assertEquals(0.0, solution.triangleArea(5, 0), 0.0001);
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Small precision test '0.0001, 0.0002' → 0.00000001")
  void testBC1_precisionTest() {
    assertEquals(0.00000001, solution.triangleArea(0.0001, 0.0002), 1e-12);
  }

  @Test
  @DisplayName("BC2: Overflow boundary with Double.MAX_VALUE")
  void testBC2_overflowBoundary() {
    double result = solution.triangleArea(Double.MAX_VALUE, 2.0);
    // a * h / 2.0 where h=2 should return Double.MAX_VALUE
    assertEquals(Double.MAX_VALUE, result, 0.0001);

    // This will result in Infinity
    double infinityResult = solution.triangleArea(Double.MAX_VALUE, Double.MAX_VALUE);
    assertEquals(Double.POSITIVE_INFINITY, infinityResult);
  }

  @Test
  @DisplayName("BC3: Minimal positive boundary using Double.MIN_VALUE")
  void testBC3_minimalBoundary() {
    double side = Double.MIN_VALUE;
    double height = 2.0;

    // (Double.MIN_VALUE * 2.0) / 2.0 = Double.MIN_VALUE
    double expected = Double.MIN_VALUE;
    double result = solution.triangleArea(side, height);

    assertEquals(expected, result, "Sınır: Double limitlerinde en küçük değer hesaplaması.");
  }
}
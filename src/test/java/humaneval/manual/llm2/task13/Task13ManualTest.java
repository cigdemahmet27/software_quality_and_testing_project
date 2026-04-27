/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm2.task13;

import humaneval.llm2.task13.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

/**
 * Manual Black-Box Tests for greatestCommonDivisor
 *
 * Specification: Return a greatest common divisor of two integers a and b.
 * Mathematical Property: gcd(a, b) = gcd(|a|, |b|) and result must be
 * non-negative.
 *
 * VALID Equivalence Classes:
 * V1: Positive coprime integers → 1
 * V2: Positive integers where one is a multiple of other → smaller absolute
 * value
 * V3: Positive integers with a common divisor → GCD
 * V4: One input is zero, other is non-zero → absolute value of non-zero input
 * V5: Negative integers (Should return positive GCD) → positive GCD
 * V6: Both inputs are zero → 0 (Standard convention in many libraries)
 *
 * INVALID Equivalence Classes:
 * I1: (None identified for primitive int inputs, as all integers are
 * mathematically valid for GCD)
 *
 * Boundary Conditions:
 * BC1: Minimum positive boundary (1, 1) → 1
 * BC2: Large prime number and Integer.MAX_VALUE → 1
 * BC3: Integer.MIN_VALUE with other integers → Positive result (Testing
 * overflow/abs)
 * BC4: Efficiency test (Euclidean algorithm performance)
 */
class Task13ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Positive coprimes '3, 5' → 1")
  void testV1_coprimeNumbers() {
    assertEquals(1, solution.greatestCommonDivisor(3, 5));
  }

  @Test
  @DisplayName("V2: One is multiple of other '25, 5' → 5")
  void testV2_multipleRelationship() {
    assertEquals(5, solution.greatestCommonDivisor(25, 5));
  }

  @Test
  @DisplayName("V3: Common divisor '144, 60' → 12")
  void testV3_commonDivisor() {
    assertEquals(12, solution.greatestCommonDivisor(144, 60));
  }

  @Test
  @DisplayName("V4: One input is zero '0, 15' → 15")
  void testV4_oneZeroInput() {
    assertEquals(15, solution.greatestCommonDivisor(0, 15));
    assertEquals(15, solution.greatestCommonDivisor(15, 0));
  }

  @Test
  @DisplayName("V5: Negative inputs '-25, 15' → 5 (Math property check)")
  void testV5_negativeInputs() {
    // NOTE: LLM base code might fail this by returning -5
    assertEquals(5, solution.greatestCommonDivisor(-25, 15), "GCD must be positive even if inputs are negative");
    assertEquals(5, solution.greatestCommonDivisor(25, -15), "GCD must be positive even if inputs are negative");
    assertEquals(5, solution.greatestCommonDivisor(-25, -15), "GCD must be positive even if both inputs are negative");
  }

  @Test
  @DisplayName("V6: Both inputs zero '0, 0' → 0")
  void testV6_bothZeroInputs() {
    assertEquals(0, solution.greatestCommonDivisor(0, 0));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Minimal positive boundary '1, 1' → 1")
  void testBC1_minimalPositive() {
    assertEquals(1, solution.greatestCommonDivisor(1, 1));
  }

  @Test
  @DisplayName("BC2: Integer.MAX_VALUE and prime → 1")
  void testBC2_maxValues() {
    assertEquals(1, solution.greatestCommonDivisor(Integer.MAX_VALUE, 7));
  }

  @Test
  @DisplayName("BC3: Integer.MIN_VALUE handling → Positive result")
  void testBC3_minValues() {
    // Integer.MIN_VALUE is -2147483648, which is divisible by 2. Result should be
    // 2.
    assertEquals(2, solution.greatestCommonDivisor(Integer.MIN_VALUE, 2));
  }

  @Test
  @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
  @DisplayName("BC4: Euclidean algorithm performance test")
  void testBC4_performance() {
    // Testing deep recursion or large iterations
    assertEquals(1, solution.greatestCommonDivisor(1000000007, 999999937));
  }
}
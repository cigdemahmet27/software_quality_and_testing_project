/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm1.task49;

import humaneval.llm1.task49.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for modp
 *
 * Specification: Return 2^n modulo p.
 * Formula: (2^n) % p
 *
 * VALID Equivalence Classes:
 * V1: n and p are positive, n < p → 2^n % p
 * V2: n and p are positive, n > p → 2^n % p
 * V3: n is 0 → 1 (2^0 = 1)
 * V4: n is large, p is small → correctly handles iterative modulo
 *
 * INVALID/EDGE Equivalence Classes:
 * I1: p is 1 → 0 (Any number modulo 1 is 0)
 * I2: p is negative → Result should follow modular arithmetic conventions
 * I3: n is negative → Unspecified (Mathematical definition usually requires n
 * >= 0)
 *
 * Boundary Conditions:
 * BC1: n = 0, p = any → 1
 * BC2: p = 1 (Lower bound of p) → 0
 * BC3: n = 100, p = 101 (Power equals p-1, Fermat's Little Theorem case) → 1
 * BC4: Large n (Performance test for O(n) loop)
 */
class Task49ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: n < p (3, 5) → 8 % 5 = 3")
  void testV1_nLessThanP() {
    assertEquals(3, solution.modp(3, 5));
  }

  @Test
  @DisplayName("V2: n > p (1101, 101) → 2")
  void testV2_nGreaterThanP() {
    assertEquals(2, solution.modp(1101, 101));
  }

  @Test
  @DisplayName("V3: n is 0 (0, 101) → 1")
  void testV3_nIsZero() {
    assertEquals(1, solution.modp(0, 101));
  }

  @Test
  @DisplayName("BC3: Fermat's Little Theorem case (100, 101) → 1")
  void testBC3_fermatCase() {
    // 2^100 mod 101 should be 1 because 101 is prime
    assertEquals(1, solution.modp(100, 101));
  }

  // ==================== INVALID / EDGE CLASSES ====================

  @Test
  @DisplayName("I1: p is 1 → 0")
  void testI1_pIsOne() {
    assertEquals(0, solution.modp(5, 1));
  }

  @Test
  @DisplayName("I2: Negative p handling")
  void testI2_negativeP() {
    // Result of % in Java with negative divisor can be tricky.
    // We test to see the behavior.
    int result = solution.modp(3, -5);
    // (2^3) % -5 in Java is 3.
    assertEquals(3, result);
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Minimal n and p (0, 1) → 0")
  void testBC1_minimalInputs() {
    assertEquals(0, solution.modp(0, 1));
  }

  @Test
  @Timeout(1)
  @DisplayName("BC4: Performance test for large n (1,000,000)")
  void testBC4_largeNPerformance() {
    // O(n) complexity check. 1 million iterations should pass in < 1s.
    int result = solution.modp(1000000, 7);
    assertTrue(result >= 0 && result < 7);
  }
}
/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm2.task25;

import humaneval.llm2.task25.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * Manual Black-Box Tests for factorize
 *
 * Specification: Return prime factors of n from smallest to largest.
 *
 * VALID Equivalence Classes:
 * V1: n is a prime number → [n]
 * V2: n is a power of a single prime (e.g., 8) → [2, 2, 2]
 * V3: n is a composite number with multiple distinct factors (e.g., 70) → [2,
 * 5, 7]
 * V4: n is 1 (Boundary of factorization) → []
 *
 * INVALID Equivalence Classes:
 * I1: n is 0 or negative → Unspecified (Should ideally throw exception or
 * return empty)
 *
 * Boundary Conditions:
 * BC1: Smallest prime (n=2) → [2]
 * BC2: Perfect squares (n=25) → [5, 5]
 * BC3: Large prime number (Performance Test) → [n] (Testing O(n) vs O(sqrt(n)))
 */
class Task25ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Prime number '7' → [7]")
  void testV1_primeNumber() {
    assertEquals(Arrays.asList(7), solution.factorize(7));
  }

  @Test
  @DisplayName("V2: Power of prime '8' → [2, 2, 2]")
  void testV2_powerOfPrime() {
    assertEquals(Arrays.asList(2, 2, 2), solution.factorize(8));
  }

  @Test
  @DisplayName("V3: Composite number '70' → [2, 5, 7]")
  void testV3_compositeNumber() {
    assertEquals(Arrays.asList(2, 5, 7), solution.factorize(70));
  }

  @Test
  @DisplayName("V4: Base boundary '1' → []")
  void testV4_one() {
    assertEquals(Collections.emptyList(), solution.factorize(1));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Smallest prime boundary '2' → [2]")
  void testBC1_smallestPrime() {
    assertEquals(Arrays.asList(2), solution.factorize(2));
  }

  @Test
  @DisplayName("BC2: Perfect square '25' → [5, 5]")
  void testBC2_perfectSquare() {
    assertEquals(Arrays.asList(5, 5), solution.factorize(25));
  }

  @Test
  @Timeout(1) // JUnit 5 Timeout (1 second)
  @DisplayName("BC3: Large prime performance test (2147483629) → Fast execution")
  void testBC3_largePrimePerformance() {
    // This prime is near Integer.MAX_VALUE.
    // O(n) algorithm will iterate 2 billion times and FAIL this 1s timeout.
    int largePrime = 2147483629;
    assertEquals(Arrays.asList(largePrime), solution.factorize(largePrime));
  }
}
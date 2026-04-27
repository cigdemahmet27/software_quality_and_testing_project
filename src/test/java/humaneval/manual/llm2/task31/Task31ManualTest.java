/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm2.task31;

import humaneval.llm2.task31.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for isPrime
 *
 * Specification: Return true if a given number is prime, and false otherwise.
 * Definition: A prime number is a natural number greater than 1 that has no
 * positive divisors other than 1 and itself.
 *
 * VALID Equivalence Classes:
 * V1: Small prime numbers (e.g., 2, 3, 5) → true
 * V2: Large prime numbers (e.g., 101, 13441) → true
 * V3: Even composite numbers > 2 → false
 * V4: Odd composite numbers (e.g., 9, 15, 25) → false
 *
 * INVALID/EDGE Equivalence Classes:
 * I1: Numbers less than 2 (0, 1, negatives) → false
 * I2: Smallest prime number (2) → true
 *
 * Boundary Conditions:
 * BC1: Lower bound of primes (n=2) → true
 * BC2: n=1 (Not prime by definition) → false
 * BC3: n=0 and negative numbers → false
 * BC4: Perfect squares (e.g., 4, 9, 16) → false (Testing the sqrt boundary)
 * BC5: Large integer boundary (Integer.MAX_VALUE) → true (It's a Mersenne
 * prime: 2^31 - 1)
 */
class Task31ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Small prime number '3' → true")
  void testV1_smallPrime() {
    assertTrue(solution.isPrime(3));
  }

  @Test
  @DisplayName("V2: Large prime number '13441' → true")
  void testV2_largePrime() {
    assertTrue(solution.isPrime(13441));
  }

  @Test
  @DisplayName("V3: Even composite number '6' → false")
  void testV3_evenComposite() {
    assertFalse(solution.isPrime(6));
  }

  @Test
  @DisplayName("V4: Odd composite number '9' → false")
  void testV4_oddComposite() {
    assertFalse(solution.isPrime(9));
  }

  @Test
  @DisplayName("BC4: Perfect square '4' → false")
  void testBC4_perfectSquare() {
    assertFalse(solution.isPrime(4));
  }

  // ==================== INVALID / EDGE CLASSES ====================

  @Test
  @DisplayName("I1: Number 1 → false (By definition)")
  void testI1_oneIsNotPrime() {
    assertFalse(solution.isPrime(1));
  }

  @Test
  @DisplayName("I1: Negative number '-7' → false")
  void testI1_negativeNumber() {
    assertFalse(solution.isPrime(-7));
  }

  @Test
  @DisplayName("I1: Zero → false")
  void testI1_zero() {
    assertFalse(solution.isPrime(0));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Smallest prime '2' → true")
  void testBC1_smallestPrime() {
    assertTrue(solution.isPrime(2));
  }

  @Test
  @Timeout(1)
  @DisplayName("BC5: Integer.MAX_VALUE (2147483647) → true (Performance test)")
  void testBC5_maxIntPrime() {
    // Integer.MAX_VALUE is a prime number.
    // This test ensures the loop runs efficiently for the largest possible input.
    assertTrue(solution.isPrime(Integer.MAX_VALUE));
  }
}
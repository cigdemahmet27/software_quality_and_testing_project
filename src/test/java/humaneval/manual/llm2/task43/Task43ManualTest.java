/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm2.task43;

import humaneval.llm2.task43.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Manual Black-Box Tests for pairsSumToZero
 *
 * Specification: Returns True if there are two DISTINCT elements in the list
 * that
 * sum to zero, and False otherwise.
 *
 * VALID Equivalence Classes:
 * V1: List with a pair that sums to zero (e.g., 5 and -5) → true
 * V2: List with no pairs that sum to zero → false
 * V3: List with only one zero element → false (Distinctness check)
 * V4: List with two zero elements → true (0 + 0 = 0)
 * V5: Empty list or single element list → false
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Null list reference → NullPointerException
 * I2: List containing null elements → NullPointerException during
 * unboxing/addition
 *
 * Boundary Conditions:
 * BC1: Smallest possible pair (Integer.MIN_VALUE + 1 and -(Integer.MIN_VALUE +
 * 1))
 * BC2: Large list without any pairs (Stress Test for O(n^2))
 * BC3: Pair exists at the very beginning and very end of the list
 * BC4: All elements are positive or all are negative
 */
class Task43ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Positive/Negative pair exists '2, 4, -5, 5' → true")
  void testV1_pairExists() {
    assertTrue(solution.pairsSumToZero(Arrays.asList(2, 4, -5, 5)));
  }

  @Test
  @DisplayName("V2: No pairs sum to zero '1, 2, 3' → false")
  void testV2_noPairsExist() {
    assertFalse(solution.pairsSumToZero(Arrays.asList(1, 2, 3)));
  }

  @Test
  @DisplayName("V3: Only one zero element '[0, 1, 2]' → false (Distinctness)")
  void testV3_singleZero() {
    // 0 needs another 0 to sum to zero.
    assertFalse(solution.pairsSumToZero(Arrays.asList(0, 1, 2)));
  }

  @Test
  @DisplayName("V4: Two zero elements '[1, 0, 3, 0]' → true")
  void testV4_twoZeros() {
    assertTrue(solution.pairsSumToZero(Arrays.asList(1, 0, 3, 0)));
  }

  @Test
  @DisplayName("V5: Single element list '[1]' → false")
  void testV5_singleElement() {
    assertFalse(solution.pairsSumToZero(Arrays.asList(1)));
  }

  @Test
  @DisplayName("V5: Empty list '[]' → false")
  void testV5_emptyList() {
    assertFalse(solution.pairsSumToZero(Collections.emptyList()));
  }

  // ==================== INVALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("I1: Null list reference → throws NullPointerException")
  void testI1_nullList() {
    assertThrows(NullPointerException.class, () -> solution.pairsSumToZero(null));
  }

  @Test
  @DisplayName("I2: List containing null '[1, null]' → throws NullPointerException")
  void testI2_listWithNull() {
    assertThrows(NullPointerException.class, () -> solution.pairsSumToZero(Arrays.asList(1, null)));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Integer limits check (Not Overflowing)")
  void testBC1_integerLimits() {
    // Integer.MAX_VALUE and -(Integer.MAX_VALUE)
    assertTrue(solution.pairsSumToZero(Arrays.asList(2147483647, -2147483647)));
  }

  @Test
  @DisplayName("BC3: Pair at boundaries '[5, 1, 2, -5]' → true")
  void testBC3_pairAtBoundaries() {
    assertTrue(solution.pairsSumToZero(Arrays.asList(5, 1, 2, -5)));
  }

  @Test
  @Timeout(1)
  @DisplayName("BC2: Performance stress test (O(n^2) check with 5000 elements)")
  void testBC2_largeListPerformance() {
    List<Integer> largeList = new ArrayList<>();
    for (int i = 1; i <= 5000; i++) {
      largeList.add(i); // All positive, no pairs
    }
    assertFalse(solution.pairsSumToZero(largeList));
  }
}
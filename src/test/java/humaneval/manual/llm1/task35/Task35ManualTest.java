/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm1.task35;

import humaneval.llm1.task35.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Manual Black-Box Tests for maxElement
 *
 * Specification: Return maximum element in the list.
 *
 * VALID Equivalence Classes:
 * V1: List with all positive numbers → maximum positive
 * V2: List with all negative numbers → maximum negative (closest to 0)
 * V3: List with mixed numbers (positive, negative, zero) → maximum element
 * V4: List with duplicate maximum values → maximum element
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Empty list → NoSuchElementException (Unhandled by base code)
 * I2: Null list reference → NullPointerException (Unhandled by base code)
 * I3: List containing null elements → NullPointerException during comparison
 *
 * Boundary Conditions:
 * BC1: Single element list → returns the only element
 * BC2: List containing Integer.MAX_VALUE and Integer.MIN_VALUE →
 * Integer.MAX_VALUE
 * BC3: Large list (Stress test) → Fast execution
 */
class Task35ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: All positive numbers '1, 5, 3' → 5")
  void testV1_allPositiveNumbers() {
    assertEquals(5, solution.maxElement(Arrays.asList(1, 5, 3)));
  }

  @Test
  @DisplayName("V2: All negative numbers '-10, -5, -20' → -5")
  void testV2_allNegativeNumbers() {
    assertEquals(-5, solution.maxElement(Arrays.asList(-10, -5, -20)));
  }

  @Test
  @DisplayName("V3: Mixed numbers '5, -5, 0, 9' → 9")
  void testV3_mixedNumbers() {
    assertEquals(9, solution.maxElement(Arrays.asList(5, -5, 0, 9)));
  }

  @Test
  @DisplayName("V4: Duplicate maximums '7, 2, 7, 1' → 7")
  void testV4_duplicateMaximums() {
    assertEquals(7, solution.maxElement(Arrays.asList(7, 2, 7, 1)));
  }

  // ==================== INVALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("I1: Empty list '[]' → throws NoSuchElementException")
  void testI1_emptyList() {
    // Collections.max() throws NoSuchElementException when the collection is empty
    assertThrows(NoSuchElementException.class, () -> solution.maxElement(Collections.emptyList()));
  }

  @Test
  @DisplayName("I2: Null list reference → throws NullPointerException")
  void testI2_nullList() {
    assertThrows(NullPointerException.class, () -> solution.maxElement(null));
  }

  @Test
  @DisplayName("I3: List containing null element '[1, null, 2]' → throws NullPointerException")
  void testI3_listWithNullElement() {
    assertThrows(NullPointerException.class, () -> solution.maxElement(Arrays.asList(1, null, 2)));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Single element list '[42]' → 42")
  void testBC1_singleElement() {
    assertEquals(42, solution.maxElement(Arrays.asList(42)));
  }

  @Test
  @DisplayName("BC2: Integer limit boundaries → Integer.MAX_VALUE")
  void testBC2_integerLimits() {
    List<Integer> limits = Arrays.asList(Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -1);
    assertEquals(Integer.MAX_VALUE, solution.maxElement(limits));
  }

  @Test
  @Timeout(1)
  @DisplayName("BC3: Large list performance (100,000 elements) → executes fast")
  void testBC3_largeListPerformance() {
    List<Integer> largeList = new ArrayList<>(100000);
    for (int i = 0; i < 100000; i++) {
      largeList.add(i);
    }
    assertEquals(99999, solution.maxElement(largeList));
  }
}
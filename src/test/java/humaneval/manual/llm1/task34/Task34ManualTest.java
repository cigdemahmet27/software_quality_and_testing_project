/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm1.task34;

import humaneval.llm1.task34.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Manual Black-Box Tests for unique
 *
 * Specification: Return sorted unique elements in a list.
 *
 * VALID Equivalence Classes:
 * V1: Standard list with duplicates, unsorted → sorted unique list
 * V2: List with no duplicates, unsorted → sorted list
 * V3: List with negative and positive numbers → sorted unique including
 * negatives
 * V4: Empty list → empty list
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Null list reference → NullPointerException (Unhandled by base code)
 * I2: List containing null elements → NullPointerException during
 * Collections.sort
 *
 * Boundary Conditions:
 * BC1: Single element list → same list
 * BC2: List where all elements are identical → list with one element
 * BC3: Large list with many duplicates (Performance/Stress) → Fast execution
 */
class Task34ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Standard list with duplicates '5, 3, 5, 2, 3' → '2, 3, 5'")
  void testV1_listWithDuplicates() {
    List<Integer> input = Arrays.asList(5, 3, 5, 2, 3);
    List<Integer> expected = Arrays.asList(2, 3, 5);
    assertEquals(expected, solution.unique(input));
  }

  @Test
  @DisplayName("V2: List without duplicates '3, 1, 2' → '1, 2, 3'")
  void testV2_listWithoutDuplicates() {
    List<Integer> input = Arrays.asList(3, 1, 2);
    List<Integer> expected = Arrays.asList(1, 2, 3);
    assertEquals(expected, solution.unique(input));
  }

  @Test
  @DisplayName("V3: List with negative numbers '-5, 0, 5, -5' → '-5, 0, 5'")
  void testV3_listWithNegativeNumbers() {
    List<Integer> input = Arrays.asList(-5, 0, 5, -5);
    List<Integer> expected = Arrays.asList(-5, 0, 5);
    assertEquals(expected, solution.unique(input));
  }

  @Test
  @DisplayName("V4: Empty list '[]' → '[]'")
  void testV4_emptyList() {
    List<Integer> input = Collections.emptyList();
    List<Integer> expected = Collections.emptyList();
    assertEquals(expected, solution.unique(input));
  }

  // ==================== INVALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("I1: Null list reference → throws NullPointerException")
  void testI1_nullList() {
    // HashSet constructor throws NPE when null collection is passed
    assertThrows(NullPointerException.class, () -> solution.unique(null));
  }

  @Test
  @DisplayName("I2: List containing null element '[1, null, 2]' → throws NullPointerException")
  void testI2_listWithNullElement() {
    // HashSet accepts null, but Collections.sort throws NPE when comparing null
    // integers
    List<Integer> input = Arrays.asList(1, null, 2);
    assertThrows(NullPointerException.class, () -> solution.unique(input));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Single element list '[42]' → '[42]'")
  void testBC1_singleElementList() {
    List<Integer> input = Arrays.asList(42);
    List<Integer> expected = Arrays.asList(42);
    assertEquals(expected, solution.unique(input));
  }

  @Test
  @DisplayName("BC2: All elements identical '[7, 7, 7, 7]' → '[7]'")
  void testBC2_allElementsIdentical() {
    List<Integer> input = Arrays.asList(7, 7, 7, 7);
    List<Integer> expected = Arrays.asList(7);
    assertEquals(expected, solution.unique(input));
  }

  @Test
  @Timeout(1) // JUnit 5 Timeout annotation (1 second)
  @DisplayName("BC3: Large list with duplicates (10,000 elements) → evaluates fast")
  void testBC3_largeListPerformance() {
    // Boundary performance test
    List<Integer> largeInput = new ArrayList<>();
    for (int i = 0; i < 10000; i++) {
      largeInput.add(i % 100); // 100 unique elements repeated 100 times
    }
    List<Integer> result = solution.unique(largeInput);

    assertEquals(100, result.size());
    assertEquals(0, result.get(0));
    assertEquals(99, result.get(99));
  }
}
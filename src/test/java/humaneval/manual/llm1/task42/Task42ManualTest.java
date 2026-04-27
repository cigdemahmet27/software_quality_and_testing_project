/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm1.task42;

import humaneval.llm1.task42.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Manual Black-Box Tests for incrList
 *
 * Specification: Return list with elements incremented by 1.
 *
 * VALID Equivalence Classes:
 * V1: List of positive numbers → incremented positive numbers
 * V2: List of negative numbers → incremented negative numbers
 * V3: List containing zeros → ones
 * V4: Empty list → empty list
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Null list reference → NullPointerException (Unhandled by base code)
 * I2: List containing null elements → NullPointerException during unboxing in
 * lambda
 *
 * Boundary Conditions:
 * BC1: Single element list → works normally
 * BC2: List with Integer.MAX_VALUE → Integer Overflow (Wraps around to
 * Integer.MIN_VALUE)
 * BC3: List with Integer.MIN_VALUE → Integer.MIN_VALUE + 1
 * BC4: Large list (100,000 elements) → Fast execution (Stream performance)
 */
class Task42ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Positive numbers '1, 2, 3' → '2, 3, 4'")
  void testV1_positiveNumbers() {
    List<Integer> input = Arrays.asList(1, 2, 3);
    List<Integer> expected = Arrays.asList(2, 3, 4);
    assertEquals(expected, solution.incrList(input));
  }

  @Test
  @DisplayName("V2: Negative numbers '-5, -3, -1' → '-4, -2, 0'")
  void testV2_negativeNumbers() {
    List<Integer> input = Arrays.asList(-5, -3, -1);
    List<Integer> expected = Arrays.asList(-4, -2, 0);
    assertEquals(expected, solution.incrList(input));
  }

  @Test
  @DisplayName("V3: List containing zeros '0, 0' → '1, 1'")
  void testV3_zeros() {
    List<Integer> input = Arrays.asList(0, 0);
    List<Integer> expected = Arrays.asList(1, 1);
    assertEquals(expected, solution.incrList(input));
  }

  @Test
  @DisplayName("V4: Empty list '[]' → '[]'")
  void testV4_emptyList() {
    List<Integer> input = Collections.emptyList();
    List<Integer> expected = Collections.emptyList();
    assertEquals(expected, solution.incrList(input));
  }

  // ==================== INVALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("I1: Null list reference → throws NullPointerException")
  void testI1_nullList() {
    // l.stream() will throw NPE
    assertThrows(NullPointerException.class, () -> solution.incrList(null));
  }

  @Test
  @DisplayName("I2: List containing null element '[1, null, 2]' → throws NPE (Unboxing)")
  void testI2_listWithNullElement() {
    // Lambda "x -> x + 1" implicitly unboxes the Integer 'x' to a primitive 'int'.
    // If 'x' is null, this unboxing throws a NullPointerException.
    List<Integer> input = Arrays.asList(1, null, 2);
    assertThrows(NullPointerException.class, () -> solution.incrList(input));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Single element list '[42]' → '[43]'")
  void testBC1_singleElement() {
    List<Integer> input = Arrays.asList(42);
    List<Integer> expected = Arrays.asList(43);
    assertEquals(expected, solution.incrList(input));
  }

  @Test
  @DisplayName("BC2: Integer.MAX_VALUE → Overflow to Integer.MIN_VALUE")
  void testBC2_integerMaxValueOverflow() {
    // Java arithmetic silently overflows.
    List<Integer> input = Arrays.asList(Integer.MAX_VALUE);
    List<Integer> expected = Arrays.asList(Integer.MIN_VALUE);
    assertEquals(expected, solution.incrList(input));
  }

  @Test
  @DisplayName("BC3: Integer.MIN_VALUE → Integer.MIN_VALUE + 1")
  void testBC3_integerMinValue() {
    List<Integer> input = Arrays.asList(Integer.MIN_VALUE);
    List<Integer> expected = Arrays.asList(Integer.MIN_VALUE + 1);
    assertEquals(expected, solution.incrList(input));
  }

  @Test
  @Timeout(1)
  @DisplayName("BC4: Large list performance (100,000 elements) → evaluates fast")
  void testBC4_largeListPerformance() {
    List<Integer> largeInput = new ArrayList<>(100000);
    for (int i = 0; i < 100000; i++) {
      largeInput.add(1);
    }

    List<Integer> result = solution.incrList(largeInput);

    assertEquals(100000, result.size());
    assertEquals(2, result.get(0));
    assertEquals(2, result.get(99999));
  }
}
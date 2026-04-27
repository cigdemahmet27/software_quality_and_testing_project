/* @Authors
 * Student Names: Ali Eren Çiftçi
 * Student IDs: 150220022
 */
package humaneval.manual.llm1.task23;

import humaneval.llm1.task23.Solution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Manual Black-Box Tests for strlen
 *
 * Specification: Return length of given string.
 *
 * VALID Equivalence Classes:
 * V1: Standard alphabetical string → > 0
 * V2: Empty string → 0
 * V3: String with only whitespaces → > 0
 * V4: String with escape characters (e.g., \n, \t) → > 0
 *
 * INVALID Equivalence Classes (degenerate/edge inputs):
 * I1: Null reference → NullPointerException (Unhandled exception by base code)
 *
 * Boundary Conditions:
 * BC1: Minimum length string (empty "") → 0
 * BC2: Off-by-one boundary (single character "a") → 1
 * BC3: Very large string (Stress test) → length
 */
class Task23ManualTest {

  private final Solution solution = new Solution();

  // ==================== VALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("V1: Standard string 'abc' → 3")
  void testV1_standardString() {
    assertEquals(3, solution.strlen("abc"));
  }

  @Test
  @DisplayName("V2: Empty string '' → 0")
  void testV2_emptyString() {
    assertEquals(0, solution.strlen(""));
  }

  @Test
  @DisplayName("V3: Whitespace only '   ' → 3")
  void testV3_whitespaceString() {
    assertEquals(3, solution.strlen("   "));
  }

  @Test
  @DisplayName("V4: Escape characters 'a\\nb' → 3 (\\n counts as 1)")
  void testV4_escapeCharacters() {
    assertEquals(3, solution.strlen("a\nb"));
  }

  // ==================== INVALID EQUIVALENCE CLASSES ====================

  @Test
  @DisplayName("I1: Null reference → throws NullPointerException")
  void testI1_nullString() {
    // Base LLM code does not handle nulls, so we expect an NPE.
    assertThrows(NullPointerException.class, () -> solution.strlen(null));
  }

  // ==================== BOUNDARY CONDITIONS ====================

  @Test
  @DisplayName("BC1: Minimum length boundary '' → 0")
  void testBC1_minimumLength() {
    assertEquals(0, solution.strlen(""));
  }

  @Test
  @DisplayName("BC2: Off-by-one boundary 'a' → 1")
  void testBC2_singleCharacter() {
    assertEquals(1, solution.strlen("a"));
  }

  @Test
  @DisplayName("BC3: Very large string (10000 chars) → 10000")
  void testBC3_largeString() {
    String largeString = "a".repeat(10000);
    assertEquals(10000, solution.strlen(largeString));
  }
}
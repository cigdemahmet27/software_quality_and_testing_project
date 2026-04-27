package humaneval.manual.llm1.task29;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task29.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task29ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `strings`: Empty list (Size = 0)
     * - `prefix`: Any valid string (e.g., "a")
     * - Expected Output: An empty list `[]`
     * - Rationale: Verifies the boundary case where no input strings are provided. The stream should process zero elements and return an empty result list without errors.
     */
    @Test
    public void testFilterByPrefixEmptyList() {
        List<String> input = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        List<String> actual = solution.filterByPrefix(input, "a");
        
        assertEquals(expected, actual, "Filtering an empty list should always return an empty list.");
    }

    /* * Equivalence Class 2: Nominal Case (Multiple Matches and Non-Matches)
     * - `strings`: A list containing strings that start with the prefix and strings that do not.
     * - `prefix`: A standard character or string (e.g., "a")
     * - Expected Output: A list containing only the elements that start with the specified prefix.
     * - Rationale: Tests the standard filtering logic to ensure the `startsWith` predicate is correctly applied to identify matches while preserving their relative order.
     */
    @Test
    public void testFilterByPrefixMixedMatches() {
        List<String> input = Arrays.asList("abc", "bcd", "cde", "array");
        List<String> expected = Arrays.asList("abc", "array");
        List<String> actual = solution.filterByPrefix(input, "a");
        
        assertEquals(expected, actual, "Should return only the strings that start with the given prefix.");
    }

    /* * Equivalence Class 3: No Matches Found
     * - `strings`: A list where no elements start with the given prefix.
     * - `prefix`: A prefix not present at the start of any string (e.g., "z")
     * - Expected Output: An empty list `[]`
     * - Rationale: Ensures the function correctly returns an empty list when the filter excludes all elements, rather than returning null or the original list.
     */
    @Test
    public void testFilterByPrefixNoMatches() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        List<String> expected = new ArrayList<>();
        List<String> actual = solution.filterByPrefix(input, "z");
        
        assertEquals(expected, actual, "If no strings match the prefix, an empty list should be returned.");
    }

    /* * Equivalence Class 4: Empty Prefix Edge Case
     * - `strings`: A valid list of strings.
     * - `prefix`: An empty string `""`
     * - Expected Output: The original list (all elements)
     * - Rationale: In Java, every string starts with an empty string. This tests the boundary behavior where the filter should effectively allow all elements through.
     */
    @Test
    public void testFilterByPrefixEmptyPrefix() {
        List<String> input = Arrays.asList("hello", "world");
        List<String> expected = Arrays.asList("hello", "world");
        List<String> actual = solution.filterByPrefix(input, "");
        
        assertEquals(expected, actual, "An empty prefix should match all strings in the list.");
    }

    /* * Equivalence Class 5: Substring is Present but Not at the Start
     * - `strings`: Strings that contain the prefix characters but not at index 0 (e.g., "banana" containing "ana").
     * - `prefix`: A specific substring (e.g., "ana")
     * - Expected Output: An empty list (or excluding those specific strings).
     * - Rationale: Validates that the function strictly uses `startsWith` rather than a general `contains` check.
     */
    @Test
    public void testFilterByPrefixSubstringNotAtStart() {
        List<String> input = Arrays.asList("banana", "analytics", "cabana");
        List<String> expected = Arrays.asList("analytics");
        List<String> actual = solution.filterByPrefix(input, "ana");
        
        assertEquals(expected, actual, "Should only match strings where the prefix is at the very beginning.");
    }
}
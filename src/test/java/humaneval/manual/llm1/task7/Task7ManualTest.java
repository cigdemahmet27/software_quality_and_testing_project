package humaneval.manual.llm1.task7;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task7.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task7ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `strings`: Empty list (Size = 0)
     * - `substring`: Any valid string (e.g., "a")
     * - Expected Output: An empty list `[]`
     * - Rationale: Tests the boundary condition where the input list contains no elements. The loop will be skipped, and an empty list should be returned immediately.
     */
    @Test
    public void testFilterBySubstringEmptyList() {
        List<String> input = new ArrayList<>();
        List<String> expected = new ArrayList<>();
        List<String> actual = solution.filterBySubstring(input, "a");
        
        assertEquals(expected, actual, "Filtering an empty list should return an empty list.");
    }

    /* * Equivalence Class 2: Nominal Case (Mixed Matches and Non-Matches)
     * - `strings`: A list containing some strings that include the substring and some that do not.
     * - `substring`: A standard string (e.g., "a")
     * - Expected Output: A list containing only the strings that have the substring.
     * - Rationale: This tests the "happy path" logic to ensure the function properly includes matching elements and excludes non-matching elements without altering the order.
     */
    @Test
    public void testFilterBySubstringMixedList() {
        List<String> input = Arrays.asList("abc", "bacd", "cde", "array");
        List<String> expected = Arrays.asList("abc", "bacd", "array");
        List<String> actual = solution.filterBySubstring(input, "a");
        
        assertEquals(expected, actual, "Should filter out strings that do not contain the substring.");
    }

    /* * Equivalence Class 3: No Matches Found
     * - `strings`: A list of strings where none contain the target substring.
     * - `substring`: A string not present in any element (e.g., "z")
     * - Expected Output: An empty list `[]`
     * - Rationale: Tests the scenario where the condition `s.contains(substring)` is always false. The function should safely process all elements and return an empty result.
     */
    @Test
    public void testFilterBySubstringNoMatches() {
        List<String> input = Arrays.asList("hello", "world", "java");
        List<String> expected = new ArrayList<>();
        List<String> actual = solution.filterBySubstring(input, "z");
        
        assertEquals(expected, actual, "If no strings contain the substring, it should return an empty list.");
    }

    /* * Equivalence Class 4: Empty Substring Edge Case
     * - `strings`: A valid list of strings.
     * - `substring`: An empty string `""`
     * - Expected Output: The original list (all elements)
     * - Rationale: In Java, `String.contains("")` always evaluates to true. Therefore, filtering by an empty substring should return a list identical to the input list.
     */
    @Test
    public void testFilterBySubstringEmptySubstring() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        List<String> expected = Arrays.asList("apple", "banana", "cherry");
        List<String> actual = solution.filterBySubstring(input, "");
        
        assertEquals(expected, actual, "Filtering by an empty substring should return all elements.");
    }
    
    /* * Equivalence Class 5: Exact Match
     * - `strings`: A list where an element is exactly the substring itself.
     * - `substring`: The exact string (e.g., "java")
     * - Expected Output: A list containing the exact match.
     * - Rationale: Ensures that `contains` correctly identifies when the substring is the entirety of the target string, rather than just a part of it.
     */
    @Test
    public void testFilterBySubstringExactMatch() {
        List<String> input = Arrays.asList("javascript", "java", "python");
        List<String> expected = Arrays.asList("javascript", "java");
        List<String> actual = solution.filterBySubstring(input, "java");
        
        assertEquals(expected, actual, "Should correctly match strings that are exactly the substring or contain it.");
    }
}
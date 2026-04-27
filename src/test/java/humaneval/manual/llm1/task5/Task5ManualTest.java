package humaneval.manual.llm1.task5;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task5.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task5ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `numbers`: Empty list (Size = 0)
     * - `delimiter`: Any valid integer (e.g., 4)
     * - Expected Output: An empty list `[]`
     * - Rationale: This tests the boundary condition where the input list has no elements. Based on the problem description, an empty list should simply return an empty list without applying the delimiter.
     */
    @Test
    public void testIntersperseEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = List.of();
        List<Integer> actual = solution.intersperse(input, 4);
        
        assertEquals(expected, actual, "An empty list should return an empty list.");
    }

    /* * Equivalence Class 2: Single Element List
     * - `numbers`: List with exactly one element (Size = 1)
     * - `delimiter`: Any valid integer (e.g., 5)
     * - Expected Output: A list containing only the original single element.
     * - Rationale: This is another boundary value. The requirement is to insert the delimiter "between every two consecutive elements." If there is only one element, there are no consecutive pairs, so the delimiter should not be inserted.
     */
    @Test
    public void testIntersperseSingleElementList() {
        List<Integer> input = Arrays.asList(10);
        List<Integer> expected = Arrays.asList(10);
        List<Integer> actual = solution.intersperse(input, 5);
        
        assertEquals(expected, actual, "A single element list should return unmodified without delimiters.");
    }

    /* * Equivalence Class 3: Multiple Element List (Nominal Case)
     * - `numbers`: List with multiple elements (Size > 1)
     * - `delimiter`: A standard positive integer (e.g., 4)
     * - Expected Output: A new list with the delimiter placed strictly between the elements.
     * - Rationale: This tests the standard valid input class (the "happy path") to ensure the core logic properly separates elements without appending a trailing or leading delimiter.
     */
    @Test
    public void testIntersperseMultipleElements() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 4, 2, 4, 3);
        List<Integer> actual = solution.intersperse(input, 4);
        
        assertEquals(expected, actual, "The delimiter should be inserted between all consecutive elements.");
    }

    /* * Equivalence Class 4: Multiple Element List with Edge Case Delimiters (Negative/Zero)
     * - `numbers`: List with multiple elements
     * - `delimiter`: A negative integer or zero (e.g., -99)
     * - Expected Output: A new list with the edge-case delimiter placed between elements.
     * - Rationale: Delimiters aren't restricted to positive integers. This equivalence class ensures the function handles different types of integer values for the delimiter without issue.
     */
    @Test
    public void testIntersperseNegativeDelimiter() {
        List<Integer> input = Arrays.asList(5, 10, 15);
        List<Integer> expected = Arrays.asList(5, -99, 10, -99, 15);
        List<Integer> actual = solution.intersperse(input, -99);
        
        assertEquals(expected, actual, "The function should correctly handle negative integer delimiters.");
    }
}
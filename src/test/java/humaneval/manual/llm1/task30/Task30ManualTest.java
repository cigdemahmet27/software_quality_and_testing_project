package humaneval.manual.llm1.task30;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task30.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task30ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `l`: Empty list (Size = 0)
     * - Expected Output: An empty list `[]`
     * - Rationale: Boundary condition testing the behavior when no numbers are provided. The stream filter should handle an empty stream and return an empty result list without errors.
     */
    @Test
    public void testGetPositiveEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = solution.getPositive(input);
        
        assertEquals(expected, actual, "An empty list should return an empty list.");
    }

    /* * Equivalence Class 2: Only Negative Numbers
     * - `l`: A list containing only negative integers (e.g., -1, -5, -10).
     * - Expected Output: An empty list `[]`
     * - Rationale: Tests the scenario where no elements satisfy the condition `x > 0`. The filter should remove all elements.
     */
    @Test
    public void testGetPositiveOnlyNegative() {
        List<Integer> input = Arrays.asList(-1, -2, -3);
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = solution.getPositive(input);
        
        assertEquals(expected, actual, "A list of only negative numbers should return an empty list.");
    }

    /* * Equivalence Class 3: Includes Zero
     * - `l`: A list containing zero along with other numbers.
     * - Expected Output: A list excluding zero.
     * - Rationale: The mathematical definition of "positive" excludes zero ($x > 0$). This tests the boundary to ensure the filter does not incorrectly include 0.
     */
    @Test
    public void testGetPositiveWithZero() {
        List<Integer> input = Arrays.asList(0, 1, 2);
        List<Integer> expected = Arrays.asList(1, 2);
        List<Integer> actual = solution.getPositive(input);
        
        assertEquals(expected, actual, "Zero is not a positive number and should be filtered out.");
    }

    /* * Equivalence Class 4: Mixed Positive and Negative Numbers (Nominal Case)
     * - `l`: A list with a variety of positive and negative integers.
     * - Expected Output: A list containing only the positive integers in their original order.
     * - Rationale: Standard "happy path" testing to ensure the filter logic works as described in the documentation and examples.
     */
    @Test
    public void testGetPositiveMixedNumbers() {
        List<Integer> input = Arrays.asList(-1, 2, -4, 5, 6);
        List<Integer> expected = Arrays.asList(2, 5, 6);
        List<Integer> actual = solution.getPositive(input);
        
        assertEquals(expected, actual, "Should return only positive numbers from a mixed list.");
    }

    /* * Equivalence Class 5: All Positive Numbers
     * - `l`: A list where every element is already positive.
     * - Expected Output: A list identical to the input.
     * - Rationale: Ensures that the filter does not remove any elements when the condition is universally met across the input.
     */
    @Test
    public void testGetPositiveAllPositive() {
        List<Integer> input = Arrays.asList(10, 20, 30);
        List<Integer> expected = Arrays.asList(10, 20, 30);
        List<Integer> actual = solution.getPositive(input);
        
        assertEquals(expected, actual, "A list of all positive numbers should remain unchanged.");
    }
}
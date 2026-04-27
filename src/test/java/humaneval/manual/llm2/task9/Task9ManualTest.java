package humaneval.manual.llm2.task9;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm2.task9.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task9ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `numbers`: Empty list (Size = 0)
     * - Expected Output: An empty list `[]`
     * - Rationale: Tests the boundary condition where the sequence has no elements. The loop shouldn't execute, and it should immediately return an empty result list.
     */
    @Test
    public void testRollingMaxEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = solution.rollingMax(input);
        
        assertEquals(expected, actual, "An empty list should return an empty list.");
    }

    /* * Equivalence Class 2: Single Element List
     * - `numbers`: List with exactly one element (Size = 1)
     * - Expected Output: A list containing the same single element.
     * - Rationale: Tests the minimum valid non-empty boundary. The current maximum is the only element, and the loop for subsequent elements does not run.
     */
    @Test
    public void testRollingMaxSingleElement() {
        List<Integer> input = Arrays.asList(42);
        List<Integer> expected = Arrays.asList(42);
        List<Integer> actual = solution.rollingMax(input);
        
        assertEquals(expected, actual, "A single-element list should return a list with that same element.");
    }

    /* * Equivalence Class 3: Strictly Increasing Sequence
     * - `numbers`: List where each subsequent element is larger than the previous (e.g., 1, 2, 3, 4)
     * - Expected Output: The output list should be identical to the input list.
     * - Rationale: Tests the scenario where the maximum value is updated at every single step of the iteration.
     */
    @Test
    public void testRollingMaxStrictlyIncreasing() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> actual = solution.rollingMax(input);
        
        assertEquals(expected, actual, "For a strictly increasing list, the rolling max should match the input list.");
    }

    /* * Equivalence Class 4: Strictly Decreasing Sequence
     * - `numbers`: List where each subsequent element is smaller than the previous (e.g., 5, 4, 3, 2)
     * - Expected Output: A list where every element is equal to the first element.
     * - Rationale: Tests the scenario where the maximum value is set at the first index and never updated, as no subsequent value exceeds it.
     */
    @Test
    public void testRollingMaxStrictlyDecreasing() {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(5, 5, 5, 5, 5);
        List<Integer> actual = solution.rollingMax(input);
        
        assertEquals(expected, actual, "For a strictly decreasing list, the rolling max should constantly be the first element.");
    }

    /* * Equivalence Class 5: Mixed Sequence with Fluctuating Values (Nominal Case)
     * - `numbers`: List containing a mix of increases, decreases, and duplicates.
     * - Expected Output: A correctly computed rolling maximum sequence reflecting the highest value seen up to each index.
     * - Rationale: Tests standard application logic to ensure it can retain a previous maximum and update it correctly when a new peak is reached later in the sequence.
     */
    @Test
    public void testRollingMaxMixedValues() {
        List<Integer> input = Arrays.asList(1, 2, 3, 2, 3, 4, 2);
        List<Integer> expected = Arrays.asList(1, 2, 3, 3, 3, 4, 4);
        List<Integer> actual = solution.rollingMax(input);
        
        assertEquals(expected, actual, "The rolling max should correctly track the highest value seen so far amidst fluctuating numbers.");
    }

    /* * Equivalence Class 6: Sequence with Negative Numbers
     * - `numbers`: List containing entirely or partially negative numbers.
     * - Expected Output: A correctly computed sequence of maximums based on negative integer comparisons.
     * - Rationale: Edge case to ensure the initialization and comparison logic doesn't wrongly assume a baseline of 0 (e.g., comparing -5 and -2 correctly identifies -2 as the new max).
     */
    @Test
    public void testRollingMaxNegativeNumbers() {
        List<Integer> input = Arrays.asList(-10, -5, -20, -2, -3);
        List<Integer> expected = Arrays.asList(-10, -5, -5, -2, -2);
        List<Integer> actual = solution.rollingMax(input);
        
        assertEquals(expected, actual, "The function should correctly evaluate rolling maximums for negative numbers.");
    }
}
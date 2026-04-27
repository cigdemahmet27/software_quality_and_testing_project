package humaneval.manual.llm1.task26;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task26.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task26ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `numbers`: Empty list (Size = 0)
     * - Expected Output: An empty list `[]`
     * - Rationale: Tests the boundary condition where the input list has no elements. The frequency map will be empty, and the stream will naturally return an empty list without throwing exceptions.
     */
    @Test
    public void testRemoveDuplicatesEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = solution.removeDuplicates(input);
        
        assertEquals(expected, actual, "An empty list should return an empty list.");
    }

    /* * Equivalence Class 2: List with No Duplicates (All Unique)
     * - `numbers`: A list where every element appears exactly once.
     * - Expected Output: A list identical to the input list.
     * - Rationale: Tests the scenario where all elements have a frequency of 1. None should be filtered out, and the original order must be preserved.
     */
    @Test
    public void testRemoveDuplicatesAllUnique() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> actual = solution.removeDuplicates(input);
        
        assertEquals(expected, actual, "A list with no duplicates should remain unchanged.");
    }

    /* * Equivalence Class 3: List with All Duplicates (No Unique Elements)
     * - `numbers`: A list where every element appears at least twice.
     * - Expected Output: An empty list `[]`
     * - Rationale: Tests the inverse extreme where no element has a frequency of 1. The filter should catch every element and return a completely empty list.
     */
    @Test
    public void testRemoveDuplicatesAllDuplicates() {
        List<Integer> input = Arrays.asList(1, 1, 2, 2, 3, 3, 3);
        List<Integer> expected = new ArrayList<>();
        List<Integer> actual = solution.removeDuplicates(input);
        
        assertEquals(expected, actual, "A list consisting entirely of duplicate elements should result in an empty list.");
    }

    /* * Equivalence Class 4: Mixed Unique and Duplicate Elements (Nominal Case)
     * - `numbers`: A list containing a mix of unique elements and elements that appear multiple times.
     * - Expected Output: A list containing only the elements that appeared exactly once, in their original order.
     * - Rationale: Tests the primary "happy path" logic described in the problem statement to ensure duplicates are completely removed while leaving single-occurrence elements intact and ordered correctly.
     */
    @Test
    public void testRemoveDuplicatesMixedElements() {
        List<Integer> input = Arrays.asList(1, 2, 3, 2, 4);
        List<Integer> expected = Arrays.asList(1, 3, 4);
        List<Integer> actual = solution.removeDuplicates(input);
        
        assertEquals(expected, actual, "Should remove elements that occur more than once while preserving the order of the remaining elements.");
    }

    /* * Equivalence Class 5: List with Negative Numbers and Duplicates
     * - `numbers`: A list containing negative integers, some duplicated and some unique.
     * - Expected Output: A list containing only the unique negative/positive integers.
     * - Rationale: Ensures that the Map and Stream logic correctly hashes and filters negative integer values just as it does positive ones.
     */
    @Test
    public void testRemoveDuplicatesNegativeNumbers() {
        List<Integer> input = Arrays.asList(-1, -5, -1, 10, -5, 42);
        List<Integer> expected = Arrays.asList(10, 42);
        List<Integer> actual = solution.removeDuplicates(input);
        
        assertEquals(expected, actual, "Should correctly process and remove duplicate negative numbers.");
    }
}
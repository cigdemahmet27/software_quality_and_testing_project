package humaneval.manual.llm1.task3;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task3.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task3ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `operations`: Empty list (Size = 0)
     * - Expected Output: false
     * - Rationale: An empty sequence of operations means the balance remains at the initial value of 0. Since it never drops below 0, the function should return false.
     */
    @Test
    public void testBelowZeroEmptyList() {
        List<Integer> input = new ArrayList<>();
        boolean actual = solution.belowZero(input);
        
        assertFalse(actual, "An empty list of operations should never cause the balance to fall below zero.");
    }

    /* * Equivalence Class 2: Only Positive Operations (Deposits)
     * - `operations`: List containing only positive integers.
     * - Expected Output: false
     * - Rationale: With only deposits being made, the balance will continuously increase and will never drop below the initial zero balance.
     */
    @Test
    public void testBelowZeroOnlyPositive() {
        List<Integer> input = Arrays.asList(10, 20, 30);
        boolean actual = solution.belowZero(input);
        
        assertFalse(actual, "A sequence of purely positive deposits should return false.");
    }

    /* * Equivalence Class 3: Balance Drops Exactly to Zero
     * - `operations`: A mix of positive and negative integers where the cumulative sum drops exactly to 0, but not below.
     * - Expected Output: false
     * - Rationale: The problem strictly states the balance must fall *below* zero (< 0). Dropping exactly back to zero should not trigger a true condition.
     */
    @Test
    public void testBelowZeroExactlyZero() {
        List<Integer> input = Arrays.asList(10, 5, -15, 20);
        boolean actual = solution.belowZero(input);
        
        assertFalse(actual, "A balance hitting exactly zero is not below zero, should return false.");
    }

    /* * Equivalence Class 4: Balance Drops Below Zero Mid-Sequence
     * - `operations`: A sequence where a withdrawal exceeds the current accumulated positive balance.
     * - Expected Output: true
     * - Rationale: This represents the core "happy path" for a true condition, testing whether the function properly calculates cumulative sums and catches the negative drop.
     */
    @Test
    public void testBelowZeroDropsBelow() {
        List<Integer> input = Arrays.asList(1, 2, -4, 5);
        boolean actual = solution.belowZero(input);
        
        assertTrue(actual, "The balance drops to -1, so the function should return true.");
    }

    /* * Equivalence Class 5: Balance Drops Below Zero Immediately
     * - `operations`: A sequence where the very first operation is a withdrawal (negative number).
     * - Expected Output: true
     * - Rationale: Boundary condition testing if the function correctly catches a negative balance on the very first iteration before any positive balances are accumulated.
     */
    @Test
    public void testBelowZeroImmediateDrop() {
        List<Integer> input = Arrays.asList(-5, 10, 20);
        boolean actual = solution.belowZero(input);
        
        assertTrue(actual, "An immediate withdrawal from a zero balance should immediately return true.");
    }
}
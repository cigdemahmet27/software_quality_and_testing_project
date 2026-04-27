package humaneval.manual.llm2.task4;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm2.task4.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class Task4ManualTest {

    Solution solution = new Solution();
    private static final double DELTA = 1e-6; // Tolerance for double comparisons

    /* * Equivalence Class 1: Nominal Case (Standard Distribution)
     * - `numbers`: A standard list of differing positive numbers.
     * - Expected Output: The correctly calculated Mean Absolute Deviation.
     * - Rationale: Tests the standard "happy path" given in the problem description to ensure the basic mathematical logic (mean and absolute deviation) is correct.
     */
    @Test
    public void testMeanAbsoluteDeviationStandard() {
        List<Double> input = Arrays.asList(1.0, 2.0, 3.0, 4.0);
        double expected = 1.0; 
        // Mean is 2.5. Deviations: 1.5, 0.5, 0.5, 1.5. Sum = 4.0. MAD = 4.0 / 4 = 1.0
        double actual = solution.meanAbsoluteDeviation(input);
        
        assertEquals(expected, actual, DELTA, "Standard valid list should compute correct MAD.");
    }

    /* * Equivalence Class 2: All Identical Elements
     * - `numbers`: A list where every element is exactly the same.
     * - Expected Output: 0.0
     * - Rationale: If all elements are the same, the mean is equal to the elements, and the difference between each element and the mean is 0. Therefore, the MAD must be exactly 0.0.
     */
    @Test
    public void testMeanAbsoluteDeviationIdenticalElements() {
        List<Double> input = Arrays.asList(5.0, 5.0, 5.0, 5.0);
        double expected = 0.0;
        double actual = solution.meanAbsoluteDeviation(input);
        
        assertEquals(expected, actual, DELTA, "List with identical elements should have a MAD of 0.0.");
    }

    /* * Equivalence Class 3: Single Element List
     * - `numbers`: A list containing exactly one element.
     * - Expected Output: 0.0
     * - Rationale: Tests the boundary condition for the minimum size required to not divide by zero. The mean of one element is the element itself, resulting in a 0.0 deviation.
     */
    @Test
    public void testMeanAbsoluteDeviationSingleElement() {
        List<Double> input = Arrays.asList(42.0);
        double expected = 0.0;
        double actual = solution.meanAbsoluteDeviation(input);
        
        assertEquals(expected, actual, DELTA, "A single element list should have a MAD of 0.0.");
    }

    /* * Equivalence Class 4: List with Negative Numbers
     * - `numbers`: A list containing negative floating-point numbers.
     * - Expected Output: Correctly calculated MAD.
     * - Rationale: Tests whether the `Math.abs` function and the sum logic properly handle negative values when computing the distance from the mean.
     */
    @Test
    public void testMeanAbsoluteDeviationNegativeNumbers() {
        List<Double> input = Arrays.asList(-5.0, 5.0);
        double expected = 5.0; 
        // Mean is 0.0. Deviations: |-5.0 - 0.0| = 5.0, |5.0 - 0.0| = 5.0. Sum = 10.0. MAD = 10.0 / 2 = 5.0
        double actual = solution.meanAbsoluteDeviation(input);
        
        assertEquals(expected, actual, DELTA, "List with negative numbers should correctly compute positive MAD.");
    }
}
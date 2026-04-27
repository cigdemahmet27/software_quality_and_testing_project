package humaneval.manual.llm1.task27;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task27.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task27ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty String
     * - `string`: "" (Length = 0)
     * - Expected Output: ""
     * - Rationale: Tests the boundary condition where the input string is empty. The loop should not execute, and it should immediately return an empty string.
     */
    @Test
    public void testFlipCaseEmptyString() {
        String input = "";
        String expected = "";
        String actual = solution.flipCase(input);
        
        assertEquals(expected, actual, "An empty string should return an empty string.");
    }

    /* * Equivalence Class 2: All Lowercase Letters
     * - `string`: A string containing only lowercase alphabetical characters.
     * - Expected Output: The same string in all uppercase.
     * - Rationale: Tests the `if (Character.isLowerCase(c))` branch exclusively to ensure all lowercase letters are properly converted to uppercase.
     */
    @Test
    public void testFlipCaseAllLowercase() {
        String input = "java";
        String expected = "JAVA";
        String actual = solution.flipCase(input);
        
        assertEquals(expected, actual, "All lowercase letters should be converted to uppercase.");
    }

    /* * Equivalence Class 3: All Uppercase Letters
     * - `string`: A string containing only uppercase alphabetical characters.
     * - Expected Output: The same string in all lowercase.
     * - Rationale: Tests the `else` branch for letters to ensure all uppercase letters are properly recognized and converted to lowercase.
     */
    @Test
    public void testFlipCaseAllUppercase() {
        String input = "PYTHON";
        String expected = "python";
        String actual = solution.flipCase(input);
        
        assertEquals(expected, actual, "All uppercase letters should be converted to lowercase.");
    }

    /* * Equivalence Class 4: Mixed Case Letters (Nominal Case)
     * - `string`: A string containing a mix of uppercase and lowercase letters.
     * - Expected Output: A string where every letter's case is inverted.
     * - Rationale: Tests the standard behavior described in the problem statement where the loop dynamically switches between the `if` and `else` branches based on the character.
     */
    @Test
    public void testFlipCaseMixedCase() {
        String input = "Hello";
        String expected = "hELLO";
        String actual = solution.flipCase(input);
        
        assertEquals(expected, actual, "Mixed case letters should be perfectly flipped.");
    }

    /* * Equivalence Class 5: Non-Alphabetical Characters
     * - `string`: A string containing spaces, numbers, and symbols.
     * - Expected Output: The exact same string.
     * - Rationale: Non-alphabetical characters do not have an uppercase or lowercase state. `Character.isLowerCase` evaluates to false, and the `else` block `Character.toLowerCase` safely returns the original character without corruption.
     */
    @Test
    public void testFlipCaseNonAlphabetical() {
        String input = "123 !@#";
        String expected = "123 !@#";
        String actual = solution.flipCase(input);
        
        assertEquals(expected, actual, "Numbers, spaces, and symbols should remain completely unchanged.");
    }
}
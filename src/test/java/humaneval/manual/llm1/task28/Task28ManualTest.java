package humaneval.manual.llm1.task28;

/* @Authors
* Student Names: Ahmet Enes Çiğdem
* Student IDs: 150220079
*/

import humaneval.llm1.task28.Solution;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Task28ManualTest {

    Solution solution = new Solution();

    /* * Equivalence Class 1: Empty List
     * - `strings`: Empty list (Size = 0)
     * - Expected Output: "" (Empty string)
     * - Rationale: Boundary condition testing the behavior when no strings are provided. According to the method documentation and the behavior of `String.join`, an empty collection should result in an empty string.
     */
    @Test
    public void testConcatenateEmptyList() {
        List<String> input = new ArrayList<>();
        String expected = "";
        String actual = solution.concatenate(input);
        
        assertEquals(expected, actual, "Concatenating an empty list should return an empty string.");
    }

    /* * Equivalence Class 2: Single Element List
     * - `strings`: A list containing exactly one string.
     * - Expected Output: The same single string.
     * - Rationale: Boundary condition to ensure that the concatenation logic handles a single element correctly without adding unnecessary characters or failing.
     */
    @Test
    public void testConcatenateSingleElement() {
        List<String> input = Arrays.asList("hello");
        String expected = "hello";
        String actual = solution.concatenate(input);
        
        assertEquals(expected, actual, "A single-element list should return the element itself.");
    }

    /* * Equivalence Class 3: Multiple Elements (Nominal Case)
     * - `strings`: A list containing multiple short strings.
     * - Expected Output: A single string containing all elements joined back-to-back.
     * - Rationale: Tests the primary functionality of the method to ensure that elements are concatenated in the correct order without any delimiters.
     */
    @Test
    public void testConcatenateMultipleElements() {
        List<String> input = Arrays.asList("a", "b", "c");
        String expected = "abc";
        String actual = solution.concatenate(input);
        
        assertEquals(expected, actual, "Multiple strings should be joined into a single string in order.");
    }

    /* * Equivalence Class 4: List with Empty Strings
     * - `strings`: A list that contains strings of zero length (e.g., ["a", "", "b"]).
     * - Expected Output: The concatenation of the elements, effectively ignoring the empty strings in the final character count.
     * - Rationale: Ensures that empty string elements are handled correctly by the join logic and do not cause errors or unexpected spacing.
     */
    @Test
    public void testConcatenateWithEmptyStrings() {
        List<String> input = Arrays.asList("java", "", "script");
        String expected = "javascript";
        String actual = solution.concatenate(input);
        
        assertEquals(expected, actual, "Empty strings within the list should not affect the concatenation of other elements.");
    }

    /* * Equivalence Class 5: Strings with Spaces
     * - `strings`: A list where elements contain whitespace.
     * - Expected Output: A single string preserving those internal spaces.
     * - Rationale: Validates that the function does not trim or modify the content of the individual strings during the concatenation process.
     */
    @Test
    public void testConcatenateWithSpaces() {
        List<String> input = Arrays.asList("Hello ", "World", "!");
        String expected = "Hello World!";
        String actual = solution.concatenate(input);
        
        assertEquals(expected, actual, "Strings containing spaces should be concatenated while preserving the spaces.");
    }
}
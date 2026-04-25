package humaneval.base.llm1.task10;

import humaneval.llm1.task10.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 10.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task10BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        Objects.equals(s.makePalindrome(""), ""),
                        Objects.equals(s.makePalindrome("x"), "x"),
                        Objects.equals(s.makePalindrome("xyz"), "xyzyx"),
                        Objects.equals(s.makePalindrome("xyx"), "xyx"),
                        Objects.equals(s.makePalindrome("jerry"), "jerryrrej")
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

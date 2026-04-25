package humaneval.base.llm2.task27;

import humaneval.llm2.task27.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 27.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task27BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        Objects.equals(s.flipCase(""), ""),
                        Objects.equals(s.flipCase("Hello!"), "hELLO!"),
                        Objects.equals(s.flipCase("These violent delights have violent ends"), "tHESE VIOLENT DELIGHTS HAVE VIOLENT ENDS")
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

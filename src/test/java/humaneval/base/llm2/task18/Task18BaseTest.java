package humaneval.base.llm2.task18;

import humaneval.llm2.task18.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 18.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task18BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.howManyTimes("", "x") == 0,
                        s.howManyTimes("xyxyxyx", "x") == 4,
                        s.howManyTimes("cacacacac", "cac") == 4,
                        s.howManyTimes("john doe", "john") == 1
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

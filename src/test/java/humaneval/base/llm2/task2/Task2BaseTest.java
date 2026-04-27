package humaneval.base.llm2.task2;

import humaneval.llm2.task2.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 2.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task2BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                    s.truncateNumber(3.5) == 0.5,
                    Math.abs(s.truncateNumber(1.33) - 0.33) < 1e-6,
                    Math.abs(s.truncateNumber(123.456) - 0.456) < 1e-6
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

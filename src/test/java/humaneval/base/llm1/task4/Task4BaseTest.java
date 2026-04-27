package humaneval.base.llm1.task4;

import humaneval.llm1.task4.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 4.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task4BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        Math.abs(s.meanAbsoluteDeviation(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0))) - 2.0/3.0) < 1e-6,
                        Math.abs(s.meanAbsoluteDeviation(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0))) - 1.0) < 1e-6,
                        Math.abs(s.meanAbsoluteDeviation(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0))) - 6.0/5.0) < 1e-6
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

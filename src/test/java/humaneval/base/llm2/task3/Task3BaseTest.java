package humaneval.base.llm2.task3;

import humaneval.llm2.task3.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 3.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task3BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        !s.belowZero(new ArrayList<>(Arrays.asList())),
                        !s.belowZero(new ArrayList<>(Arrays.asList(1, 2, -3, 1, 2, -3))),
                        s.belowZero(new ArrayList<>(Arrays.asList(1, 2, -4, 5, 6))),
                        !s.belowZero(new ArrayList<>(Arrays.asList(1, -1, 2, -2, 5, -5, 4, -4))),
                        s.belowZero(new ArrayList<>(Arrays.asList(1, -1, 2, -2, 5, -5, 4, -5))),
                        s.belowZero(new ArrayList<>(Arrays.asList(1, -2, 2, -2, 5, -5, 4, -4)))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

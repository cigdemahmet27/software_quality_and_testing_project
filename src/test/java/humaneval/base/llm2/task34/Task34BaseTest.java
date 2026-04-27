package humaneval.base.llm2.task34;

import humaneval.llm2.task34.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 34.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task34BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.unique(new ArrayList<>(Arrays.asList(5, 3, 5, 2, 3, 3, 9, 0, 123))).equals(Arrays.asList(0, 2, 3, 5, 9, 123))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

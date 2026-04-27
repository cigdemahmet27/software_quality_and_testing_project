package humaneval.base.llm2.task42;

import humaneval.llm2.task42.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 42.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task42BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.incrList(new ArrayList<>(Arrays.asList())).equals(List.of()),
                        s.incrList(new ArrayList<>(Arrays.asList(3, 2, 1))).equals(Arrays.asList(4, 3, 2)),
                        s.incrList(new ArrayList<>(Arrays.asList(5, 2, 5, 2, 3, 3, 9, 0, 123))).equals(Arrays.asList(6, 3, 6, 3, 4, 4, 10, 1, 124))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

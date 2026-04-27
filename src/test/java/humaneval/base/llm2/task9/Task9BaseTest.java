package humaneval.base.llm2.task9;

import humaneval.llm2.task9.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 9.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task9BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.rollingMax(new ArrayList<>(List.of())).equals(List.of()),
                        s.rollingMax(new ArrayList<>(Arrays.asList(1, 2, 3, 4))).equals(Arrays.asList(1, 2, 3, 4)),
                        s.rollingMax(new ArrayList<>(Arrays.asList(4, 3, 2, 1))).equals(Arrays.asList(4, 4, 4, 4)),
                        s.rollingMax(new ArrayList<>(Arrays.asList(3, 2, 3, 100, 3))).equals(Arrays.asList(3, 3, 3, 100, 100))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

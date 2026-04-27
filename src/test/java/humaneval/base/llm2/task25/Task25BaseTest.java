package humaneval.base.llm2.task25;

import humaneval.llm2.task25.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 25.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task25BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.factorize(2).equals(List.of(2)),
                        s.factorize(4).equals(Arrays.asList(2, 2)),
                        s.factorize(8).equals(Arrays.asList(2, 2, 2)),
                        s.factorize(3 * 19).equals(Arrays.asList(3, 19)),
                        s.factorize(3 * 19 * 3 * 19).equals(Arrays.asList(3, 3, 19, 19)),
                        s.factorize(3 * 19 * 3 * 19 * 3 * 19).equals(Arrays.asList(3, 3, 3, 19, 19, 19)),
                        s.factorize(3 * 19 * 19 * 19).equals(Arrays.asList(3, 19, 19, 19)),
                        s.factorize(3 * 2 * 3).equals(Arrays.asList(2, 3, 3))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

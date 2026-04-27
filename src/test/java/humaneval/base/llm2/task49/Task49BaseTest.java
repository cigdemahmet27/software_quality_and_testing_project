package humaneval.base.llm2.task49;

import humaneval.llm2.task49.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 49.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task49BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.modp(3, 5) == 3,
                        s.modp(1101, 101) == 2,
                        s.modp(0, 101) == 1,
                        s.modp(3, 11) == 8,
                        s.modp(100, 101) == 1,
                        s.modp(30, 5) == 4,
                        s.modp(31, 5) == 3
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

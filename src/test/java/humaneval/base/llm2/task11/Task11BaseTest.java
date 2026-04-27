package humaneval.base.llm2.task11;

import humaneval.llm2.task11.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 11.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task11BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        Objects.equals(s.stringXor("111000", "101010"), "010010"),
                        Objects.equals(s.stringXor("1", "1"), "0"),
                        Objects.equals(s.stringXor("0101", "0000"), "0101")
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

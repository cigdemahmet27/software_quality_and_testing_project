package humaneval.base.llm2.task13;

import humaneval.llm2.task13.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 13.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task13BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.greatestCommonDivisor(3, 7) == 1,
                        s.greatestCommonDivisor(10, 15) == 5,
                        s.greatestCommonDivisor(49, 14) == 7,
                        s.greatestCommonDivisor(144, 60) == 12
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

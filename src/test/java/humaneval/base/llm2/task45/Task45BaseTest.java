package humaneval.base.llm2.task45;

import humaneval.llm2.task45.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 45.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task45BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.triangleArea(5, 3) == 7.5,
                        s.triangleArea(2, 2) == 2.0,
                        s.triangleArea(10, 8) == 40.0
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

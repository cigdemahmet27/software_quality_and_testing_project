package humaneval.base.llm2.task35;

import humaneval.llm2.task35.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 35.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task35BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.maxElement(new ArrayList<>(Arrays.asList(1, 2, 3))) == 3,
                        s.maxElement(new ArrayList<>(Arrays.asList(5, 3, -5, 2, -3, 3, 9, 0, 124, 1, -10))) == 124
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

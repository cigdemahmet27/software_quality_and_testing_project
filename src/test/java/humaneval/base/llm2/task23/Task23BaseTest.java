package humaneval.base.llm2.task23;

import humaneval.llm2.task23.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 23.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task23BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.strlen("") == 0,
                        s.strlen("x") == 1,
                        s.strlen("asdasnakj") == 9
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

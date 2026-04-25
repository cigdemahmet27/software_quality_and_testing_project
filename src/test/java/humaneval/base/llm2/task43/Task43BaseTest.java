package humaneval.base.llm2.task43;

import humaneval.llm2.task43.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 43.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task43BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        !s.pairsSumToZero(new ArrayList<>(Arrays.asList(1, 3, 5, 0))),
                        !s.pairsSumToZero(new ArrayList<>(Arrays.asList(1, 3, -2, 1))),
                        !s.pairsSumToZero(new ArrayList<>(Arrays.asList(1, 2, 3, 7))),
                        s.pairsSumToZero(new ArrayList<>(Arrays.asList(2, 4, -5, 3, 5, 7))),
                        !s.pairsSumToZero(new ArrayList<>(List.of(1))),
                        s.pairsSumToZero(new ArrayList<>(Arrays.asList(-3, 9, -1, 3, 2, 30))),
                        s.pairsSumToZero(new ArrayList<>(Arrays.asList(-3, 9, -1, 3, 2, 31))),
                        !s.pairsSumToZero(new ArrayList<>(Arrays.asList(-3, 9, -1, 4, 2, 30))),
                        !s.pairsSumToZero(new ArrayList<>(Arrays.asList(-3, 9, -1, 4, 2, 31)))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

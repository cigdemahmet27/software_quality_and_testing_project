package humaneval.base.llm2.task30;

import humaneval.llm2.task30.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 30.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task30BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.getPositive(new ArrayList<>(Arrays.asList(-1, -2, 4, 5, 6))).equals(Arrays.asList(4, 5, 6)),
                        s.getPositive(new ArrayList<>(Arrays.asList(5, 3, -5, 2, 3, 3, 9, 0, 123, 1, -10))).equals(Arrays.asList(5, 3, 2, 3, 3, 9, 123, 1)),
                        s.getPositive(new ArrayList<>(Arrays.asList(-1, -2))).equals(List.of()),
                        s.getPositive(List.of()).equals(List.of())
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

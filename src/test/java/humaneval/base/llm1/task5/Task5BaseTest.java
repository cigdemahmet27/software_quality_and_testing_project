package humaneval.base.llm1.task5;

import humaneval.llm1.task5.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 5.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task5BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.intersperse(new ArrayList<>(List.of()), 7).equals(List.of()),
                        s.intersperse(new ArrayList<>(Arrays.asList(5, 6, 3, 2)), 8).equals(Arrays.asList(5, 8, 6, 8, 3, 8, 2)),
                        s.intersperse(new ArrayList<>(Arrays.asList(2, 2, 2)), 2).equals(Arrays.asList(2, 2, 2, 2, 2))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

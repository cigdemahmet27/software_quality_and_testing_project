package humaneval.base.llm2.task7;

import humaneval.llm2.task7.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 7.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task7BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.filterBySubstring(new ArrayList<>(List.of()), "john").equals(List.of()),
                        s.filterBySubstring(new ArrayList<>(Arrays.asList("xxx", "asd", "xxy", "john doe", "xxxAAA", "xxx")), "xxx").equals(Arrays.asList("xxx", "xxxAAA", "xxx")),
                        s.filterBySubstring(new ArrayList<>(Arrays.asList("xxx", "asd", "aaaxxy", "john doe", "xxxAAA", "xxx")), "xx").equals(Arrays.asList("xxx", "aaaxxy", "xxxAAA", "xxx")),
                        s.filterBySubstring(new ArrayList<>(Arrays.asList("grunt", "trumpet", "prune", "gruesome")), "run").equals(Arrays.asList("grunt", "prune"))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

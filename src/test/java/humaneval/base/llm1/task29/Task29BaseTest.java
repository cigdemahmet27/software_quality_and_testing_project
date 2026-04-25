package humaneval.base.llm1.task29;

import humaneval.llm1.task29.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 29.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task29BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.filterByPrefix(new ArrayList<>(List.of()), "john").equals(List.of()),
                        s.filterByPrefix(new ArrayList<>(Arrays.asList("xxx", "asd", "xxy", "john doe", "xxxAAA", "xxx")), "xxx").equals(Arrays.asList("xxx", "xxxAAA", "xxx"))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

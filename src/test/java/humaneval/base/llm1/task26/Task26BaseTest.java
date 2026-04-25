package humaneval.base.llm1.task26;

import humaneval.llm1.task26.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 26.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task26BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.removeDuplicates(new ArrayList<>(List.of())).equals(List.of()),
                        s.removeDuplicates(new ArrayList<>(Arrays.asList(1, 2, 3, 4))).equals(Arrays.asList(1, 2, 3, 4)),
                        s.removeDuplicates(new ArrayList<>(Arrays.asList(1, 2, 3, 2, 4, 3, 5))).equals(Arrays.asList(1, 4, 5))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

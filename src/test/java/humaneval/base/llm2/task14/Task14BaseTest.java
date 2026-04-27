package humaneval.base.llm2.task14;

import humaneval.llm2.task14.Solution;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * Base tests from HumanEval dataset for Task 14.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task14BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.allPrefixes("").equals(List.of()),
                        s.allPrefixes("asdfgh").equals(Arrays.asList("a", "as", "asd", "asdf", "asdfg", "asdfgh")),
                        s.allPrefixes("WWW").equals(Arrays.asList("W", "WW", "WWW"))
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

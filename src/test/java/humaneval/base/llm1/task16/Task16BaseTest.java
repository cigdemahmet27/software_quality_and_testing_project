package humaneval.base.llm1.task16;

import humaneval.llm1.task16.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 16.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task16BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        s.countDistinctCharacters("") == 0,
                        s.countDistinctCharacters("abcde") == 5,
                        s.countDistinctCharacters("abcde" + "cade" + "CADE") == 5,
                        s.countDistinctCharacters("aaaaAAAAaaaa") == 1,
                        s.countDistinctCharacters("Jerry jERRY JeRRRY") == 5
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

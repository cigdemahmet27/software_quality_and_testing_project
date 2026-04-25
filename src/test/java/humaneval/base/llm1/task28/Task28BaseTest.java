package humaneval.base.llm1.task28;

import humaneval.llm1.task28.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 28.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task28BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        Objects.equals(s.concatenate(new ArrayList<>(List.of())), ""),
                        Objects.equals(s.concatenate(new ArrayList<>(Arrays.asList("x", "y", "z"))), "xyz"),
                        Objects.equals(s.concatenate(new ArrayList<>(Arrays.asList("x", "y", "z", "w", "k"))), "xyzwk")
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

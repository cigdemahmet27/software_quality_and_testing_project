package humaneval.base.llm2.task31;

import humaneval.llm2.task31.Solution;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.lang.*;
import java.util.stream.*;

/**
 * Base tests from HumanEval dataset for Task 31.
 * Adapted to JUnit 5 format with minimal modifications.
 */
class Task31BaseTest {

    @Test
    void testFromDataset() {
        Solution s = new Solution();
                List<Boolean> correct = Arrays.asList(
                        !s.isPrime(6),
                        s.isPrime(101),
                        s.isPrime(11),
                        s.isPrime(13441),
                        s.isPrime(61),
                        !s.isPrime(4),
                        !s.isPrime(1),
                        s.isPrime(5),
                        s.isPrime(11),
                        s.isPrime(17),
                        !s.isPrime(5 * 17),
                        !s.isPrime(11 * 7),
                        !s.isPrime(13441 * 19)
                );
                if (correct.contains(false)) {
                    throw new AssertionError();
                }
    }
}

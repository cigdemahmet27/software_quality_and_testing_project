package humaneval.improved.llm2.task25;

import humaneval.llm2.task25.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/* @Authors
 * Student Names: Taha Çali
 * Student IDs: <student_id>
 */
public class Task25ImprovedTest {

    // Branch: while loop entered + if(n % divisor == 0) TRUE repeatedly
    // + if(n > 1) FALSE → n becomes 1 after full factorization
    // 8 = 2*2*2, after dividing out all 2s, n becomes 1 → last if NOT entered
    @Test
    void testPowerOfTwo_noLeftoverPrime() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(2, 2, 2), s.factorize(8));
    }

    // Branch: while loop entered + if(n % divisor == 0) FALSE (divisor incremented)
    // + if(n > 1) TRUE → leftover prime added
    // 70 = 2*5*7: divisor increments past 2→5→7; after loop n=7>1
    @Test
    void testMultipleDistinctFactors_leftoverPrime() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(2, 5, 7), s.factorize(70));
    }

    // Branch: while loop NEVER entered (divisor*divisor > n from the start)
    // + if(n > 1) TRUE → the number itself added as sole factor
    // n=2: divisor=2, 2*2=4 > 2 → loop skipped entirely; n=2>1 → [2]
    @Test
    void testWhileLoopNeverEntered_smallestPrime() {
        Solution s = new Solution();
        assertEquals(List.of(2), s.factorize(2));
    }

    // Branch: while loop entered + if(n % divisor == 0) TRUE (same factor twice)
    // + if(n > 1) FALSE → n becomes 1
    // 4 = 2*2: after dividing, n=1 → last if NOT entered
    @Test
    void testPerfectSquareSmall_noLeftoverPrime() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(2, 2), s.factorize(4));
    }

    // Branch: while loop entered + both TRUE and FALSE branches of inner if
    // + if(n > 1) TRUE
    // 100 = 2*2*5*5: divisor stays 2 (true branch), then increments to 3 (false),
    // then 4 (false), then 5 (true); n becomes 1 → last if FALSE
    @Test
    void testLargeComposite_mixedBranches_noLeftover() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(2, 2, 5, 5), s.factorize(100));
    }

    // Branch: while loop entered + if(n % divisor == 0) FALSE many times
    // + if(n > 1) TRUE → single large prime leftover
    // 21 = 3*7: divisor=2 → false (increment), divisor=3 → true, n=7;
    // divisor=3, 3*3=9>7 → exit loop; n=7>1 → [3,7]
    @Test
    void testCompositeWithLeftoverPrime() {
        Solution s = new Solution();
        assertEquals(Arrays.asList(3, 7), s.factorize(21));
    }

    // NEW: Branch: while loop NEVER entered + if(n > 1) FALSE directly
    // This explicitly tests the false branch of the final condition from the start
    @Test
    void testOne_returnsEmptyList() {
        Solution s = new Solution();
        assertEquals(Collections.emptyList(), s.factorize(1));
    }
    
    // NEW: Boundary Condition check for edge case <= 0
    @Test
    void testZero_returnsEmptyList() {
        Solution s = new Solution();
        assertEquals(Collections.emptyList(), s.factorize(0));
    }
}
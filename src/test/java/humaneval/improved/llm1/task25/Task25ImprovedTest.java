package humaneval.improved.llm1.task25;

import humaneval.llm1.task25.Solution;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

public class Task25ImprovedTest {
    @Test
    void testFactorize() {
        Solution s = new Solution();
        
        // Loop n % 2 == 0 (executes multiple times)
        assertEquals(Arrays.asList(2, 2, 2), s.factorize(8));
        
        // Loop for odd factors
        assertEquals(Arrays.asList(5, 5), s.factorize(25));
        assertEquals(Arrays.asList(2, 5, 7), s.factorize(70));
        
        // n > 1 branch at end (prime number)
        assertEquals(Arrays.asList(13), s.factorize(13));
        
        // 1 case
        assertEquals(Collections.emptyList(), s.factorize(1));
    }
}

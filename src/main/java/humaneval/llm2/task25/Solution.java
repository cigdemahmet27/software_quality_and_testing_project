package humaneval.llm2.task25;

import java.util.*;

public class Solution {
    /**
    Return list of prime factors of given integer in the order from smallest to largest.
    Each of the factors should be listed number of times corresponding to how many times it appears in factorization.
    Input number should be equal to the product of all factors
    >>> factorize(8)
    [2, 2, 2]
    >>> factorize(25)
    [5, 5]
    >>> factorize(70)
    [2, 5, 7]
     */
    public List<Integer> factorize(int n) {
        List<Integer> factors = new ArrayList<>();
        int divisor = 2;
        while (divisor * divisor <= n) {
            if (n % divisor == 0) {
                factors.add(divisor);
                n /= divisor;
            } else {
                divisor++;
            }
        }
        if (n > 1) {
            factors.add(n);
        }
        return factors;
    }
}

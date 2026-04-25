package humaneval.llm1.task25;

import java.util.*;
import java.lang.*;

public class Solution {
    /**
    Return list of prime factors of given integer in the order from smallest to largest.
    Each of the factors should be listed number of times corresponding to how many times it appeares in factorization.
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

        // Handle factor 2
        while (n % 2 == 0) {
            factors.add(2);
            n /= 2;
        }

        // Handle odd factors
        // We only need to check up to sqrt(n) because if n has a prime factor greater than sqrt(n),
        // it must also have a prime factor smaller than sqrt(n) (or be prime itself).
        // The loop condition i*i <= n is more efficient than i <= Math.sqrt(n) as it avoids floating-point operations.
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // If n is still greater than 1, it means the remaining n is a prime factor itself
        // (this covers cases where n was initially prime or had a large prime factor remaining)
        if (n > 1) {
            factors.add(n);
        }

        return factors;
    }
}
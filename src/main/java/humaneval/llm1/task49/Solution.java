package humaneval.llm1.task49;


public class Solution {
    /**
    Return 2^n modulo p (be aware of numerics).
    >>> modp(3, 5)
    3
    >>> modp(1101, 101)
    2
    >>> modp(0, 101)
    1
    >>> modp(3, 11)
    8
    >>> modp(100, 101)
    1
     */
    public int modp(int n, int p) {
        // Handle the base case where n is 0.
        // 2^0 = 1. The result should be 1 mod p.
        // If p is 1, 1 mod 1 is 0.
        // If p > 1, 1 mod p is 1.
        if (n == 0) {
            return 1 % p;
        }

        // Initialize result to 1, as x^0 = 1.
        long result = 1;
        // The base for exponentiation is 2.
        long base = 2;
        // Use a long for the exponent to safely perform operations within the loop,
        // although 'n' is int, it's good practice for modular exponentiation.
        long exponent = n; 

        // Binary exponentiation (also known as exponentiation by squaring)
        // This algorithm efficiently computes (base^exponent) % p.
        while (exponent > 0) {
            // If the current bit of the exponent is 1 (i.e., exponent is odd),
            // we multiply this power of the base into our result.
            // We take modulo p at each step to prevent overflow.
            if (exponent % 2 == 1) {
                result = (result * base) % p;
            }
            
            // Square the base for the next iteration.
            // This effectively moves to the next power of 2 for the exponent.
            // Again, take modulo p to keep the numbers manageable.
            base = (base * base) % p;
            
            // Halve the exponent (equivalent to a right shift, moving to the next bit).
            exponent /= 2;
        }

        // The final result will be an integer between 0 and p-1 (inclusive),
        // so it fits within an int.
        return (int) result;
    }
}
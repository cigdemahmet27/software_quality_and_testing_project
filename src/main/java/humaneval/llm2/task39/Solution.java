package humaneval.llm2.task39;


public class Solution {
    /**
    primeFib returns n-th number that is a Fibonacci number and it's also prime.
    >>> primeFib(1)
    2
    >>> primeFib(2)
    3
    >>> primeFib(3)
    5
    >>> primeFib(4)
    13
    >>> primeFib(5)
    89
     */
    public int primeFib(int n) {
        int f0 = 0, f1 = 1;
        int count = 0;
        while (true) {
            int fib = f0 + f1;
            if (isPrime(fib)) {
                count++;
                if (count == n) {
                    return fib;
                }
            }
            f0 = f1;
            f1 = fib;
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}

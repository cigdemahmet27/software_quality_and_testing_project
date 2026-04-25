package humaneval.llm1.task15;

import java.util.*;
import java.lang.*;

public class Solution {
    /**
    Return a string containing space-delimited numbers starting from 0 upto n inclusive.
    >>> stringSequence(0)
    "0"
    >>> stringSequence(5)
    "0 1 2 3 4 5"
     */
    public String stringSequence(int n) {
        if (n < 0) {
            // According to examples, n is non-negative. If negative input is possible,
            // we might want to throw an IllegalArgumentException or return an empty string.
            // Sticking to the examples, assume n >= 0.
            return ""; // Or handle as an error case, but example suggests non-negative n.
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= n; i++) {
            sb.append(i);
            if (i < n) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
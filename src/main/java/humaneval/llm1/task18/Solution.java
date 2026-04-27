package humaneval.llm1.task18;


public class Solution {
    /**
    Find how many times a given substring can be found in the original string. Count overlaping cases.
    >>> howManyTimes("", "a")
    0
    >>> howManyTimes("aaa", "a")
    3
    >>> howManyTimes("aaaa", "aa")
    3
     */
    public int howManyTimes(String string, String substring) {
        if (string == null || substring == null || string.isEmpty() && !substring.isEmpty()) {
            return 0;
        }
        if (substring.isEmpty()) {
            return 0; // An empty substring is often considered to not appear, or infinitely. Sticking to 0 for practical counting.
        }
        if (substring.length() > string.length()) {
            return 0;
        }

        int count = 0;
        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = string.indexOf(substring, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex++; // Move search start to the next character to allow overlapping
            }
        }
        return count;
    }
}
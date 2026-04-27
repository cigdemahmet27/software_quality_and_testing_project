package humaneval.llm1.task11;


public class Solution {
    /**
    Input are two strings a and b consisting only of 1s and 0s.
    Perform binary XOR on these inputs and return result also as a string.
    >>> stringXor("010", "110")
    "100"
     */
    public String stringXor(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        int maxLength = Math.max(lenA, lenB);

        // Pad strings with leading zeros to make them equal length
        StringBuilder paddedA = new StringBuilder();
        for (int i = 0; i < maxLength - lenA; i++) {
            paddedA.append('0');
        }
        paddedA.append(a);

        StringBuilder paddedB = new StringBuilder();
        for (int i = 0; i < maxLength - lenB; i++) {
            paddedB.append('0');
        }
        paddedB.append(b);

        // Now perform character-by-character XOR
        StringBuilder result = new StringBuilder(maxLength);
        for (int i = 0; i < maxLength; i++) {
            char charA = paddedA.charAt(i);
            char charB = paddedB.charAt(i);

            // XOR logic: result is '1' if characters are different, '0' if they are the same
            if (charA == charB) {
                result.append('0');
            } else {
                result.append('1');
            }
        }

        return result.toString();
    }
}
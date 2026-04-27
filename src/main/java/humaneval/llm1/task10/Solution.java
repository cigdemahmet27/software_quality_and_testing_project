package humaneval.llm1.task10;


public class Solution {
    /**
    Test if given string is a palindrome
     */
    public boolean isPalindrome(String string) {
        int i = 0;
        int j = string.length() - 1;
        while (i < j) {
            if (string.charAt(i)!= string.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    /**
    Find the shortest palindrome that begins with a supplied string.
    Algorithm idea is simple:
    - Find the longest postfix of supplied string that is a palindrome.
    - Append to the end of the string reverse of a string prefix that comes before the palindromic suffix.
    >>> makePalindrome("")
    ""
    >>> makePalindrome("cat")
    "catac"
    >>> makePalindrome("cata")
    "catac"
     */
    public String makePalindrome(String string) {
        int n = string.length();

        // Find the starting index of the longest palindromic suffix.
        // We iterate `i` from 0 to n-1.
        // `string.substring(i)` gives suffixes of decreasing length.
        // The first one we find that is a palindrome will be the longest.
        int longestPalindromicSuffixStartIndex = n; 

        for (int i = 0; i < n; i++) {
            String suffix = string.substring(i);
            if (isPalindrome(suffix)) {
                longestPalindromicSuffixStartIndex = i;
                break; // Found the longest palindromic suffix
            }
        }

        // The prefix of the original string that comes before the longest palindromic suffix.
        // This is the part that needs to be reversed and prepended to form the full palindrome.
        String prefixToReverse = string.substring(0, longestPalindromicSuffixStartIndex);

        // Reverse this prefix.
        String reversedPrefix = new StringBuilder(prefixToReverse).reverse().toString();

        // Append the reversed prefix to the original string to form the shortest palindrome.
        return string + reversedPrefix;
    }
}
/*
 * @lc app=leetcode id=680 lang=java
 *
 * [680] Valid Palindrome II
 */

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.
 */ 
// @lc code=start

/**
 * O(n), 100% runtime
 * find one, try two ways
 * 
 * This solution fix a variation of k diff.
 */
class Solution {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        return validPalindrome(s, 0, j, 0);
    }

    boolean validPalindrome(String s, int i, int j, int diff) {
        while(i < j) {
            char chari = s.charAt(i);
            char charj = s.charAt(j);

            if(chari != charj) {
                if(diff == 1) {
                    return false;
                }
                return validPalindrome(s, i+1, j, 1) || validPalindrome(s, i, j-1, 1);
            }
            i++;
            j--;
        }
        return true;

    }
}
// @lc code=end


/**
 * another solution, 97%
 * idea
 */
class Solution {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while(i < j) {
            char chari = s.charAt(i);
            char charj = s.charAt(j);
            if(chari != charj) {
                return validPalindrome(s, i+1, j) || validPalindrome(s, i, j-1);
            }
            i++;
            j--;
        }
        return true;
    }

    boolean validPalindrome(String s, int i, int j) {
        while(i < j) {
            char chari = s.charAt(i);
            char charj = s.charAt(j);

            if(chari != charj) {
                return false;
            }
            i++;
            j--;
        }
        return true;

    }
}
/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 */

// @lc code=start

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */

/**
 * easy question. 
 * while{if() continue;}
 * 
 * */ 
class Solution {
    public boolean isPalindrome(String s) {
        if(s==null) {
            return false;
        }
        if(s.length() == 0) {
            return true;
        }
        String t = s.toLowerCase();
        int i = 0, j = t.length()-1;
        while(i<j) {
            if(!isAlphabet(t.charAt(i))) {
                i++;
                continue;
            }
            if(!isAlphabet(t.charAt(j))) {
                j--;
                continue;
            }
            if(t.charAt(i++) != t.charAt(j--)) {
                // System.out.println(t.charAt(i-1) + ", " + t.charAt(j+1));
                return false;
            }
        }
        return true;
    }
    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c>='A' && c<='Z') || (c>='0' && c<='9');
    }
}
// @lc code=end

/**
 * another try
 *  * while{while()...; while()....; logic}

 */
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
       int i = 0, j = s.length() - 1;
       while(i < j) {
           while(i < j && !isAlphabet(s.charAt(i))) i++;
           while(i < j && !isAlphabet(s.charAt(j))) j--;
           if(s.charAt(i) != s.charAt(j)) {
               return false;
           }
           i++;
           j--;
       }
       return true;
    }
    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c>='A' && c<='Z') || (c>='0' && c<='9');
    }
}
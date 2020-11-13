/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

/**
 * 
 Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

 

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input: s = "adceb", p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input: s = "acdcb", p = "a*c?b"
Output: false
 */ 


/**
 * the intuitive way, readlly matching, and recursive
 * TLE
 */ 
// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0) {
            return true;
        } else if(p.length() == 0){
            return false;
        } else if(s.length() == 0) {
            for(int i = 0; i < p.length(); i++) {
                if(p.charAt(i) != '*') {
                    return false;
                }
            }
            return true;
        }
        char c = p.charAt(0);
        boolean result = false;
        if(c >= 'a' && c <= 'z') {
            if(s.charAt(0) != c) {
                return false;
            } else {
                result |= isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
            }
        } else if(c == '?') {
            if(s.length() > 0) {
                result |= isMatch(s.substring(1, s.length()), p.substring(1, p.length()));
            }
        } else if(c == '*') {
            result |= isMatch(s, p.substring(1, p.length()));
            for(int i = 0; i < s.length(); i++) {
                result |= isMatch(s.substring(i, s.length()), p.substring(1, p.length()));
            }
            result |= isMatch("", p.substring(1, p.length()));
        } else {
            return false;
        }
        return result;
    }
}
// @lc code=end


/**
 * dp[i][j] matching? when ending at s[i-1] and p[i-1]
 * - base case:
 *      + origin: dp[0][0]: they do match, so dp[0][0] = true
 *      + first row: dp[0][j]: except for String p starts with *, otherwise all false
 *      + first col: dp[i][0]: can't match when p is empty. All false.
 *      + dp[0][0] = true empty matching empty string
 *      + dp[i][0] 1 <= i <= sLen, false, s not matching empty
 *      + dp[0][j] i <= j <= pLen, is p[j-1] == '*', then dp[0][j] == dp[0][j-1]
 * - status transition
 *      + dp[i][j] 
 *          - s[i-1] == p[i-1] || p[i-1] =='?' then dp[i][j] = dp[i-1][j-1]
 *          - p[i-1] == '*', dp[i][j] = dp[i][j-1] || dp[i-1][j], 
 *              + s[0..i] matching p[0..j-1], p just move to '*', 
 *                  - * matches no character in S
 *              + sp[0..i-1] matching p[0..j], s continue to match '*', 
 *                  - * matches a 1 character (most recent) in S and we can continue to use * to match more previous characters
 * 
 * then, the result is dp[sLen][pLen];
 * 
 * The missing part is base part '*' and transition part, '*'
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen+1][pLen+1];
        dp[0][0] = true;
        // for(int i = 1; i <= sLen; i++) {
        //     dp[i][0] = false;
        // }
        for(int i = 1; i <= pLen; i++) {
            if(p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-1];
            }
        }

        for(int i = 1; i <= sLen; i++) {
            for(int j = 1; j <= pLen; j++) {
                if((s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?' )&&dp[i-1][j-1]) {
                    dp[i][j] = true;
                } else if(p.charAt(j-1) == '*' && (dp[i-1][j] || dp[i][j-1])) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[sLen][pLen];
    }
}


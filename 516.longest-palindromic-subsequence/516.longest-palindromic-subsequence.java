/*
 * @lc app=leetcode id=516 lang=java
 *
 * [516] Longest Palindromic Subsequence
 */

/**
 *
 algorithms	Medium (54.32%)	2963	215
Tags
Companies
Given a string s, find the longest palindromic subsequence's length in s.

A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

 

Example 1:

Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".
Example 2:

Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
 

Constraints:

1 <= s.length <= 1000
s consists only of lowercase English letters.
 */

/**
 * 双序列的动态规划
 * state: dp[i][j]表示array[i...j]满足条件关系的答案
 * function: f[i][j] = 研究第i个和第j个的匹配关系  
 * initialize: f[i][i]
 * answer: dp[0][n]
 */ 
// @lc code=start
class Solution {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int dp[][]= new int[len][len];
        for(int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for(int i = len-1; i >= 0; i--) {
            for(int j = i+1; j < len; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][len-1];
    }
}
// @lc code=end


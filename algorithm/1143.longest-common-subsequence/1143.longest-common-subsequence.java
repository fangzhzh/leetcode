/*
 * @lc app=leetcode id=1143 lang=java
 *
 * [1143] Longest Common Subsequence
 */

/**
Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

 

Example 1:

Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.
Example 2:

Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.
Example 3:

Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
 

Constraints:

1 <= text1.length, text2.length <= 1000
text1 and text2 consist of only lowercase English characters.
 */

 /**
  * Solution 1: TOP Down recursive 
  */
// @lc code=start
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
       return longestCommonSubsequence(text1, 0, text2, 0);
    }
    public int longestCommonSubsequence(String text1, int idx1, String text2, int idx2) {
        if(idx1 == text1.length() || idx2 == text2.length()) {
            return 0;
        }
        if(text1.charAt(idx1) == text2.charAt(idx2)) {
            return 1+longestCommonSubsequence(text1, idx1+1, text2, idx2+1);
        } else {
            return Math.max(longestCommonSubsequence(text1, idx1+1, text2, idx2), 
            longestCommonSubsequence(text1, idx1, text2, idx2+1)
            );
        }
    }

}


/**
 * Solution 2: Bottom Up recursive
 */


/**
 * Solution 3: double dimention dp
 */
// @lc code=start
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 1; i <= m; i++) {
            for(int j = i; j <= n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
// @lc code=end



/**
 * Solution 3: one dimention dp
 */
// @lc code=start
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] dp = new int[n+1];
        for(int i = 1; i <= m; i++) {
            int prev = dp[0];
            for(int j = 1; j <= n; j++) {
                int temp = dp[j];
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[j] = prev + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j-1]);
                }
                prev = temp;
            }
        }
        return dp[n];
    }
}
// @lc code=end


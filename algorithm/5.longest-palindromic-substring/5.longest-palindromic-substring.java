/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
        // Time O(n^2)
        // Space O(n^2)

        // f(i,j) = f(i+1,j-1) && (s[i] == s[j])
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i<n; i++) {
                int j = i + len - 1;
                if(j >= n) {
                    break;
                }
                if(s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if(len <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if(dp[i][j] && len > maxLen) {
                    start = i;
                    maxLen = len;
                }
            }

        }
        return s.substring(start, start + maxLen);
    }
}
// @lc code=end


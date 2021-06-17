/*
 * @lc app=leetcode id=583 lang=java
 *
 * [583] Delete Operation for Two Strings
 */

/**
Category	Difficulty	Likes	Dislikes
algorithms	Medium (49.26%)	1443	34
Tags
Companies
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

 

Example 1:

Input: word1 = "sea", word2 = "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Example 2:

Input: word1 = "leetcode", word2 = "etco"
Output: 4
 

Constraints:

1 <= word1.length, word2.length <= 500
word1 and word2 consist of only lowercase English letters.

 */


/**
 * Solution1: recursive, the loop invariant is
 * if word1[i] == word2[j], minD(i, j) = minD(i-1, j-1)
 * else either remove word1[i], or remove word2[j], or remove both
 * so it's min(minD(i-1, j) + 1, minD(i, j-1)+1, minD(i-1, j-1)+2)
 */
// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        return minDistance(word1, 0, word2, 0);
    }
    private int minDistance(String word1, int i, String word2, int j) {
        if(i == word1.length() && j == word2.length()) {
            return 0;
        }
        if(i == word1.length()) {
            return word2.length() - j;
        }
        if(j == word2.length()) {
            return word1.length() - i;
        }
        if(word1.charAt(i) == word2.charAt(j)) {
            return minDistance(word1, i+1, word2, j+1);
        } else {
            return Math.min(
                Math.min(minDistance(word1, i+1, word2, j) + 1,
                        minDistance(word1, i, word2, j+1) + 1),
                minDistance(word1, i+1, word2, j+1) + 2
            );
        }
    }
}
// @lc code=end

/**
 * Solution2: Dynamic programming
 * The same mathmatics applies to DP as well
 * if word1[i] == word2[j], minD(i, j) = minD(i-1, j-1)
 * else either remove word1[i], or remove word2[j], or remove both
 * so it's min(minD(i-1, j) + 1, minD(i, j-1)+1, minD(i-1, j-1)+2)
 * 
 */
// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][]dp = new int[m+1][n+1];
        for(int i = 0;i <= m; i++) {
            dp[i][0] = i;
        }
        for(int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        for(int i = 1; i <=m; i++) {
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j]+1, dp[i][j-1]+1),
                    dp[i-1][j-1]+2);
                }
            }
        }
        return dp[m][n];
    }
}
// @lc code=end


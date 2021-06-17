/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */

/**
Category	Difficulty	Likes	Dislikes
algorithms	Hard (45.83%)	5316	66
Tags
Companies
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
 

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 

Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
 */



/**
 * | | | 
 * |--| -- |
 * | dp[i-1][j-1] | dp[i-1][j] |
 * | dp[i][j-1] | target |
 *
 * | | | 
 * |--| -- |
 * | 替换，跳过 | 删除 |
 * | 删除 | target |
 */ 
// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[] dp = new int[len2+1];
        for(int i = 1; i <= len1; i++) {
            dp[i][0] = i;
        }
        for(int j = 1; j <= len2; j++) {
            dp[0][j] = j;
        }
        for(int i = 1; i < len1+1; i++) {
            for(int j = 1; j < len2+1; j++) {
                char c1 = word1.charAt(i-1);
                char c2 = word2.charAt(j-1);
                if(c1 == c2) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(
                        dp[i-1][j] + 1,
                        Math.min(
                        dp[i][j-1] + 1,
                        dp[i-1][j-1] + 1)
                    );
                }
            }

        }
        return dp[len1][len2];
    }
}
// @lc code=end


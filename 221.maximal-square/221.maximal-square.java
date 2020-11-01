/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 */

 /**
  * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
  */


/**
 * DP
 * dp[i][j] = Math.min(Math.min(dp[i][j-1] , dp[i-1][j-1]), dp[i-1][j]) + 1;
 * This formula is genious
 * 
 * What I did at first is level  = dp[i][j] = dp[i-1][j-1] + 1 
 * scan matrix[i-1level][j-1]...max[i-1][j-1] == '1'
 * scan matrix[i-1][j-level]...max[i-1][j-1] == '1'
 * */  
// @lc code=start
class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0) return 0;
        int len = matrix.length, size = matrix[0].length;
        int max = 0;
        int[][] dp = new int[len+1][size+1];
        for(int i = 1; i <= len; i++) {
            for(int j = 1; j <= size; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j-1] , dp[i-1][j-1]), dp[i-1][j]) + 1;
                    if(dp[i][j] >  max) {
                        max = dp[i][j];
                    }
                }
            }
        }
        return max * max;
    }
}
// @lc code=end


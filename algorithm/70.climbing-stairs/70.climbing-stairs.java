/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 */

// @lc code=start
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return climbStairsTopdown(n, dp);
    }
    // bottom up, iterative, dp
    // start from solving base cases, and incrementally, iteratively build toward final solution
    private int climbStairsBottomup(int n) {
         int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // top down, dfs + memo
    // start from original problem, then break it down to sub problems, solve them as needed.
    private int climbStairsTopdown(int n, int[] dp) {
        if(n == 0 || n == 1) {
            return 1;
        }
        if(dp[n] != 0) {
            return dp[n];
        }
        dp[n] = climbStairsTopdown(n-1, dp) + climbStairsTopdown(n-2, dp);;
        return dp[n];
    }
}
// @lc code=end


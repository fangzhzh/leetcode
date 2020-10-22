/*
 * @lc app=leetcode id=714 lang=java
 *
 * [714] Best Time to Buy and Sell Stock with Transaction Fee
 */

// @lc code=start

/**
 * This question is not hard to understand, but not easy to get right.
 * My first thought was dp[i][0] as buy, dp[i][1] as sell, but dp[i][0] can be buy or not buy, the state goes to 4 dimention.
 *
 * But looking at the formula, Math.max(dp[i-1][1], dp[i-1][0]+prices[i-1] - fee) did cover the case, ith day sell or not sell.
 *
 */
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len+1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = -prices[0];
        dp[1][1] = 0;
        for(int i = 2; i <= len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i-1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]+prices[i-1] - fee);
        }

        return dp[len][1];
    }
}
// @lc code=end



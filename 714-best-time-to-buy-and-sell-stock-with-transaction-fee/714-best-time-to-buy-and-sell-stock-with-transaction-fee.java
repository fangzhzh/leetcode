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
 * Every day, we have three options, buy, sell, rest,
 * - dp[i][2]
 *      + dp[i][0] means ith day, max profit no stock at hand
 *      + dp[i][1] means ith day, max profit one stock at hand
 * 
 * 
 * one optimisation: we noticed that ith is only depend on (i-1)th, so we get get ride of the dp array,
 * one variable to carray (i-1)th days max profit
 */
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len+1][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]-prices[i-1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]+prices[i-1] - fee);
        }

        return dp[len][1];
    }
}
// @lc code=end

/**
 * the fact that the ith day is only depends on (i-1)th day
 * 
 */
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int profit0 = 0, profit1 = Integer.MIN_VALUE;
        
        for(int i = 1; i <= len; i++) {
            profit0 = Math.max(profit0, profit1 + prices[i-1] );
            profit1 = Math.max(profit1, profit0 - prices[i-1] - fee);
        }
        return profit0;
    }
}



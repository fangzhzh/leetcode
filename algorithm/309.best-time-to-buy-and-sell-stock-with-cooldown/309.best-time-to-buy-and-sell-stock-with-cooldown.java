/*
 * @lc app=leetcode id=309 lang=java
 *
 * [309] Best Time to Buy and Sell Stock with Cooldown
 */

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]

 */ 

/**
 * same as other stock problem.
 * for each day, we have at most three choices: buy, sell, rest(cooldown)
 */
// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len <= 1) {
            return 0;
        }
        int[][] dp = new int[len+1][2];
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[1][0] = 0;
        dp[1][1] = -prices[0];
        for(int i = 2; i <= prices.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i-1]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i-1]);
        }
        return dp[len][0];
    }
}
// @lc code=end


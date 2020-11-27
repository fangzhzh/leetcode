/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */

// @lc code=start

/**
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
Example 4:

Input: prices = [1]
Output: 0
 

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
 * 
 */


/**
 * dp[i][k][0]
 * dp[i][k][1]
 * 
 * max profit at ith day, at most k transaction, has 0 or 1 stock at hand
 * 
 * the k is the Stock in hand after current transaction, so that it means a buy is a transaction
 */

/**
 * The question fits dynamic programming
 * - optimal substructure: 
 *      + max/min/longest XXX at ith
 * - overlapping sub problem
 *      + ith can be obtained by 0...(i-1)th
 */
 
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int [][][]dp = new int[len+1][3][2];
        dp[0][2][0] = 0;
        dp[0][2][1] = Integer.MIN_VALUE;
        dp[0][1][0] = 0;
        dp[0][1][1] = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++) {
            dp[i][2][0] = Math.max(dp[i-1][2][0], dp[i-1][2][1]+prices[i-1]);
            dp[i][2][1] = Math.max(dp[i-1][2][1], dp[i-1][1][0]-prices[i-1]);
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1]+prices[i-1]);
            dp[i][1][1] = Math.max(dp[i-1][1][1], -prices[i-1]);
        }
        return dp[len][2][0];
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */

// @lc code=start
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int profit0 = 0, profit1 = Integer.MIN_VALUE;
        for(int p : prices) {
            profit0 = Math.max(profit0, profit1+p);
            profit1 = Math.max(profit1, profit0-p);
        }
        return profit0;
    }
}
// @lc code=end


class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < len; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            if(prices[i] - min > result) {
                result = prices[i] - min;
            }
        }
        return result;
    }
}

// wrong version is min/max
// the test case for it: [2,4,1]
public int maxProfit(int[] prices) {
    int len = prices.length;
    if(len == 0) {
        return 0;
    }
    int max = 0;
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < len; i++) {
        if(prices[i] < min) {
            min = prices[i];
            max = 0;
        }
        if(prices[i] > max) {
            max = prices[i];
        }
    }
    return max - min;
}

/**
 * compacter version, but the first version should have better runtime since comparation is fater than write value
 * Though the first version don't have to check the zero case because the loop will handle it.
 */
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < prices.length; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            profit = Math.max(profit, prices[i] - min);
        }
        return profit;
    }
}


/**
 * check the post:
 * Most consistent ways of dealing with the series of stock problems
 * DP:
 * base: dp[i][0][0] = 0, dp[i][0][1] = -Inf
 * Formula: 
 * dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1]+prices[i])
 * dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) where dp[i-1][0][0] = 0
 * dp[i][1][1] = Math.max(dp[i-1][1][1], -prices[i])
 * 
 */
class Solution {
    public int maxProfit(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        for(int i = 0; i < prices.length; i++) {
            dp_i10 = Math.max(dp_i10, dp_i11 + prices[i]);
            dp_i11 = Math.max(dp_i11, -prices[i]);
        }
        return dp_i10;
    }
}

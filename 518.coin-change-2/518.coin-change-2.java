/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change 2
 */

/**
Coin Change 2
Category	Difficulty	Likes	Dislikes
algorithms	Medium (50.93%)	2984	76
Tags
Companies
You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

 

Example 1:

Input: amount = 5, coins = [1, 2, 5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10] 
Output: 1
 

Note:

You can assume that

0 <= amount <= 5000
1 <= coin <= 5000
the number of coins is less than 500
the answer is guaranteed to fit into signed 32-bit integer
 */


 /**
  * Solution 1: Recursive 
  * change(amount) = Sum_0<i<coins.length(change(amount-coins[i]))
  * The only tricky part is that when for i, you can't go back to pick up i-1 
  * because the combination is already selected by when processing i-1
  */
// @lc code=start
class Solution {
    public int change(int amount, int[] coins) {
        int res = 0;
        for(int i = 0; i < coins.length; i++) {
            res += change(amount-coins[i], coins, i);
        }
        return res;

    }
    public int change(int amount, int[] coins, int idx) {

        if(amount < 0 || idx >= coins.length) {
            return 0;
        }
        if(amount==0) {
            return 1;
        }
        int res = 0;
        for(int i = idx; i < coins.length; i++) {
            res += change(amount-coins[i], coins, i);
        }
        return res;

    }
}
// @lc code=end

/**
 * dp[i] is the number of combination which sums up to i with all coins, the coins is the hidden state here
 * dp[i] is a sum of dp[i-coin[j]]
 */
/**
 * Analysis for solution 2, and solutoin 3: Example 1 2
 * Solution 2, m-th round of outer loop fill m-th dp[m] using all possible coins, so you get
 * 1 1 2, 1 2 1, 2 1 1
 *
 * Solution 3, m-th round of outer loop dp[] array filled with combination of first m coins only
 * rest of the coins are not used yet.
 * 1 1 2, it will never go back to 2 1 1
 */
/**
 * Solution 2: Wrong one
 */
 class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(i >= coins[j]) {
                    dp[i] = dp[i] + dp[i-coins[j]];
                }
            }
        }
        return dp[amount];
    }
}

/**
 * SOlution 3: Correct one
 */
 class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for(int j = 0; j < coins.length; j++) {
            for(int i = 1; i <= amount; i++) {
                if(i >= coins[j]) {
                    dp[i] = dp[i] + dp[i-coins[j]];
                }
            }
        }
        return dp[amount];
    }
}

/**
 * Solution4: two dimentional state
 * dp[i][j] 若只使用coins中的前i个硬币的面值，若想凑出金额j，有dp[i][j]种凑法。
 * 1. 不取coins[i], dp[i][j] = dp[i-1][j]
 * 1. 取. dp[i][j] = dp[i][j-coins[i]], 硬币可用无数个,此处的i指前i个硬币面值
 */
 class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        for(int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for(int i = 1; i <= coins.length; i++) {
            for(int j = 1; j <= amount; j++) {
                if(j >= coins[i-1]) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }
}
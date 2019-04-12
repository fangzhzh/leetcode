class Solution {
    fun coinChange(coins: IntArray, amount: Int): Int {
        if(coins.size == 0) {
            return -1
        }
        // combination to make up the amount 
        val dp = IntArray(amount+1, {-1})
        dp[0] = 0
        var result = -1
        for(i in 1..amount) {
            //todo
            var min = Int.MAX_VALUE
            for(coin in coins) {
                if(i >= coin && dp[i-coin] != -1 && min > dp[i-coin]) {
                     min = dp[i-coin]+1
                }
            }
            if(min != Int.MAX_VALUE) {
	            dp[i] = min
            }
        }
        return dp[amount]
    }
}

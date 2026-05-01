class Solution {
    fun maxProfit(prices: IntArray): Int {
        val len = prices.size
        if(len == 0) {
            return 0
        }
        val dp = IntArray(len)
        dp[0] = 0
        var minPrice = prices[0]
        
        for(i in 1..len-1) {
            dp[i] = Math.max(dp[i-1], prices[i] - minPrice)
            if(minPrice > prices[i]) {
                minPrice = prices[i]
            }
        }
        return dp[len-1]
    }
}

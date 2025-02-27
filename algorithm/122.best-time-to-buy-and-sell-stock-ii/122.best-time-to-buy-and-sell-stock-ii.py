class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        n = len(prices)
        # dp[i][0] not holding the stack
        # dp[i][1] holding the stock
        dp = [[0] * 2 for _ in range(n + 1)]
        dp[0][1] = float('-inf') # Integer min, max
        for i in range(1, n+1):
            price = prices[i-1]
            dp[i][0] = max(dp[i-1][1] + price, dp[i-1][0])
            dp[i][1] = max(dp[i-1][0] - price, dp[i-1][1])
        
        return dp[n][0]

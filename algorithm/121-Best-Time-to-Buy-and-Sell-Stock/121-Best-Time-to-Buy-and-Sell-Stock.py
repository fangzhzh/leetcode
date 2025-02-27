class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        # focus on one transaction
        def maxProfitOneTransaction() -> int:
            low, high, profit = prices[0], prices[0], 0
            for num in prices:
                if num < low:
                    low = num
                    high = num
                elif num > high:
                        high = num
                        profit = max(profit, high - low)
            return profit
        # maxPrice is not needed, this just udpate maxProfit every loop
        # maxProfit = max(maxProfit, profit) just looks clean, it's at best as good as if(profie>maxProfit) maxProfit = profit
        def maxProfitGlobalMinMax() -> int:
            maxProfit, minNum = 0, prices[0]
            for num in prices:
                minNum = min(minNum, num)
                profit = num-minNum
                maxProfit = max(maxProfit, profit)
            return maxProfit
        return maxProfitGlobalMinMax()



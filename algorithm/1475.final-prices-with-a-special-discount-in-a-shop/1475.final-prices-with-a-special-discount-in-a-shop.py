#
# @lc app=leetcode id=1475 lang=python3
#
# [1475] Final Prices With a Special Discount in a Shop
#

# @lc code=start
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        # monoton stack
        # next samller is the discount pricess
        n = len(prices)
        if n == 1:
            return prices
        ans = [0]*n
        stack = []
        for i in range(0, n):
            price = prices[i]
            while stack and prices[stack[-1]] >= price:
                j = stack.pop()
                ans[j] = prices[j] - price
            stack.append(i)
        while stack:
            idx = stack.pop()
            ans[idx] = prices[idx]
        return ans

        
# @lc code=end


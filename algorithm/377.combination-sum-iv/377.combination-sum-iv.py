#
# @lc app=leetcode id=377 lang=python3
#
# [377] Combination Sum IV
#

# @lc code=start
class Solution:
    def combinationSum4(self, nums: List[int], target: int) -> int:
        def dp_()->int:
            # dp[i], how many way to sum to i with nums
            n = len(nums)
            dp = [0] * (target+1)
            dp[0] = 1
            for i in range(1, target+1):
                for num in nums:
                    if i - num >= 0:
                        dp[i] += dp[i-num]
            return dp[target]
        def dfs()->int:
            n = len(nums)
            self.ans = 0
            nums.sort()
            def helper(nums: List[int], idx: int, sum: int) -> None:
                if nums[idx] + sum == target:
                    self.ans += 1
                elif nums[idx] + sum < target:
                    for i in range(0, n):
                        helper(nums, i, sum+nums[idx])
            for i in range(0, n):
                helper(nums, i, 0)
            return self.ans
        return dp()
# @lc code=end



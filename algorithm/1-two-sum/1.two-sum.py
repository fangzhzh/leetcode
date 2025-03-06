#
# @lc app=leetcode id=1 lang=python3
#
# [1] Two Sum
#

# @lc code=start
class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        cache = {}
        for i in range(len(nums)):
            num = nums[i]
            if target - num in cache and cache[target-num] != i:
                return [cache[target-num], i]
            cache[num] = i
        return []        
# @lc code=end


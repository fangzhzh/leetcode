#
# @lc app=leetcode id=169 lang=python3
#
# [169] Majority Element
#

# @lc code=start
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        def majorElement1() -> int:
            major, occur = nums[0], 0
            
            for num in nums:
                if num == major:
                    occur += 1
                else:
                    occur -= 1
                    if occur < 0:
                        major = num
                        occur += 1
            return major
        ## A cleaner solution, not better just cleaner
        def majorElementCleaner() -> int:
            ans, cnt = -1, 0
            for num in nums:
                if cnt == 0:
                    ans = num
                    cnt += 1
                elif num == ans:
                    cnt += 1
                else:
                    cnt -= 1
            return ans

        return majorElementCleaner()
# @lc code=end


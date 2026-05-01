class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        n = len(nums)
        if(n < = 2) return n
        i = 0

        for j in range(0, len(nums)):
            # there are different way to check
            # 1. nums[j] == nums[j+1] and nums[j] == nums[j+2] => at most twice
            # 2. nums[j] > nums[i-2] => at most twice and sorted array
            if j+2 < len(nums) and nums[j] == nums[j+1] and nums[j] == nums[j+2]:
                continue
            else:
                nums[i] = nums[j]
                i += 1
        return i


        
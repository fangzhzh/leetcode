#
# @lc app=leetcode id=912 lang=python3
#
# [912] Sort an Array
#

# @lc code=start
class Solution:
    def sortArray(self, nums: List[int]) -> List[int]:
        def partition(nums: List[int], left: int, right: int)->int:
            mid = nums[(left+right)//2]
            while left <= right:
                while nums[left] < mid:
                    left += 1
                while nums[right] > mid:
                    right -= 1
                if left <= right:
                    nums[left], nums[right] = nums[right], nums[left]
                    left += 1
                    right -= 1
            return left
        def sortArrayIter(nums: List[int], left: int, right: int) -> List[int]:
            while left < right:
                pivot = partition(nums, left, right)
                
        # sort array [left, right)
        def sortArray(nums: List[int], left: int, right: int)->List[int]:
            pivot = partition(nums, left, right)
            if left < pivot-1:
                sortArray(nums, left, pivot-1)
            if pivot < right:
                sortArray(nums, pivot, right)
            return nums
        return sortArray(nums, 0, len(nums)-1)
# @lc code=end


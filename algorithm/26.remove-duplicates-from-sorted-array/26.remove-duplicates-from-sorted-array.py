#
# @lc app=leetcode id=26 lang=python3
#
# [26] Remove Duplicates from Sorted Array
#

"""
Remove Duplicates from Sorted Array
Category	Difficulty	Likes	Dislikes
algorithms	Easy (59.33%)	16177	19537
Tags
Companies
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
nums is sorted in non-decreasing order.
"""

# first try
# tried to define the [-∞, i], [i, j], [j,∞] and failed for this problems
# the solution is so simple and intuitive that I never thought about
# why?
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        #[-∞, i] removed dup [i,j) dup to be handled, [unknow]
        i, j, n = 1, 1, len(nums)+1
        while i < n and j < n:
            while i < n and nums[i] != nums[i-1]:
                i += 1
            while j < n and nums[j] == nums[j-1]:
                j += 1
            nums[i] = nums[j]
        return i


# @lc code=start
class Solution:
    def removeDuplicates(self, nums: List[int]) -> int:
        #[-∞, i] removed dup [i,j) dup to be handled, [unknow]
        """
        i, j, n = 1, 1, len(nums)+1
        while i < n and j < n:
            while i < n and nums[i] != nums[i-1]:
                i += 1
            while j < n and nums[j] == nums[j-1]:
                j += 1
            nums[i] = nums[j]
        return i
        """
        j = 1
        for i in range(1, len(nums)):
            if nums[i] != nums[i-1]:
                nums[j] = nums[i]
                j += 1
        return j

        
# @lc code=end


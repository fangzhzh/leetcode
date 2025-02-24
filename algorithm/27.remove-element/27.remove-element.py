#
# @lc app=leetcode id=27 lang=python3
#
# [27] Remove Element
#
"""
Remove Element
Category	Difficulty	Likes	Dislikes
algorithms	Easy (59.36%)	3517	4534
Tags
Companies
Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:

Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
Return k.
Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int val = ...; // Value to remove
int[] expectedNums = [...]; // The expected answer with correct length.
                            // It is sorted with no values equaling val.

int k = removeElement(nums, val); // Calls your implementation

assert k == expectedNums.length;
sort(nums, 0, k); // Sort the first k elements of nums
for (int i = 0; i < actualLength; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

 

Example 1:

Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,1,2,2,3,0,4,2], val = 2
Output: 5, nums = [0,1,4,0,3,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
Note that the five elements can be returned in any order.
It does not matter what you leave beyond the returned k (hence they are underscores).
 

Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 50
0 <= val <= 100
"""

# two pointers, closing to the miidle
# [-∞, i), non-val
# [i, j], non handled
# (j, ∞], val
# i, meaning the first val
# j, meaning the last non-val
# so i or j+1 is the count of non-val

# @lc code=start
# recursive, clear
# TC O(n), SC O(n)
class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        def helper(i: int, j: int)-> int:
            if i > j:
                return j+1
            if nums[i] != val:
                return helper(i+1, j)
            if nums[j] == val:
                return helper(i, j-1)
            nums[i], nums[j] = nums[j], nums[i]
            return helper(i+1, j-1)
        return helper(0, len(nums)-1)

        
# @lc code=end

# iterative version
# TC O(n), SC O(1)

class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        # two pointers chasing
        i, j = 0, len(nums)-1
        
        while i <= j:
            while i <= j and nums[i] != val:
                i += 1
            while i <= j and nums[j] == val:
                j -= 1
            if i < j:
                nums[i], nums[j] = nums[j], nums[i]
                i += 1
                j -= 1
        return i



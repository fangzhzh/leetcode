/*
 * @lc app=leetcode id=80 lang=
 * 
 * java
 *
 * [80] Remove Duplicates from Sorted Array II
 */

// @lc code=start


/**
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
 */


/**
 * first solution, edge case's as pivot = 2, and i starts from 2
 * but the idea is the same, if nums[i] > nums[pivot] -2, can't make sense the algorithm
 * 
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null) {
            return 0;
        }
        int len = nums.length;
        if(len < 3) {
            return len;
        }
        int pivot = 2;
        for(int i = 2; i < len; i++) {
            if(nums[i] > nums[pivot-2]) {
                nums[pivot++] = nums[i];
            }
        }
        return pivot;
    }
}

/**
 * if first two elements, we copy them.
 * slow makes sure no more than twice element exist in before it, not include slow.
 * After that, if the nums[i] == nums[slow-2], means nums[slow-2] == nums[slow-1] == nums[i], 
 * we will ignore the nums[i], continue;
 * otherwise, num[i] > nums[slow-2], at most twice the nums[slow-2], 
 * then nums[slow] = nums[i], move slow forward
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null) {
            return 0;
        }
        int len = nums.length;
        int slow = 0;
        for(int i = 0; i < len; i++) {
            if(i < 2 || nums[i] > nums[slow-2]) {
                nums[slow] = nums[i];
                slow++;
            }
        }
        return slow;
    }
}
// @lc code=end

/**
 * one step furthuer, this is the algorithm for at most k
 */
class Solution {
    int removeDuplicates(vector<int>& nums, int k) {
        int i = 0;
        for (int n : nums)
            if (i < k || n > nums[i-k])
                nums[i++] = n;
        return i;
    }
}


/*
 * @lc  id=283 lang=java
 *
 * [283] Move Zeroes
 */

 /**
  * 
  Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
  */
// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int right = nums.length-1;
        for(int i = nums.length-1; i >= 0; i--) {
            if(nums[i]!=0) {
                continue;
            }
            for(int j = i; j < right; j++) {
                nums[j] = nums[j+1];
            }
            nums[right--] = 0;
        }
    }
}
// @lc code=end


/**
 * Analysis:
 * - O(n), might be duplicate operations.
 * 
 * Refer Solution2.java, edit: Solution2 is not correct since it's not keeping the order.
 * - the same idea of Remove Duplicates from sorted Array
 * - find the left, find the right,  swap
 */

/**
 * Another two pointers,
 * moving all non-zero to left by help of slow pointer, then fill the right with 0.
 * */ 
class Solution {
    public void moveZeroes(int[] nums) {
        // two pointers
        int left = 0;
        for(int v : nums) {
            if(v != 0) {
                nums[left] = v;
                left++;
            }
        }
        for(;left<nums.length;left++) nums[left] = 0;

    }
}
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
 * Refer Solution2.java
 */

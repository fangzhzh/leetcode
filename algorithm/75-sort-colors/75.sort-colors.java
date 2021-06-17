/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 */

/** 
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Follow up:

Could you solve this problem without using the library's sort function?
Could you come up with a one-pass algorithm using only O(1) constant space?
 

Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
*/

/**
 * brutal force, sort algorithm
 * 
 * count and fill
 * 
 */
// @lc code=start
class Solution {
    public void sortColors(int[] nums) {
        int r = 0, w = 0, b = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                r++;
            } else if(nums[i] == 1) {
                w++;
            } else {
                b++;
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(r > 0) {
                nums[i] = 0;
                r--;
            } else if(w > 0) {
                nums[i] = 1;
                w--;
            } else if(b > 0){
                nums[i] = 2;
                b--;
            } else {
                break;
            }
        }

    }
}
// @lc code=end

/**
 * find and swap, don't think this algor is better than others except it's one pass
 */
class Solution {
    public void sortColors(int[] nums) {
        int low = 0, high = nums.length - 1;
        for(int i = 0; i < nums.length; ) {
            if(i < high && nums[i]==2) {
                swap(nums, i, high);
                high--;
            } else if(i >= low && nums[i]==0) {
                swap(nums, i, low);
                low++;
                i++;
            } else {
                i++;
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}


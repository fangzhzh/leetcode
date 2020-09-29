/*
 * @lc  id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

 /**
  * 
  Given a sorted array nums, remove the duplicates in-place such that each element appears only once and returns the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
  */

/**
 * ## Solution intuitive:
 * - find the first unmatch, move following
 * - if cur equal to previous, move following
 * 
 * ## a big problem is too many unnecessary move
 * - need to maintain counter, idx which are error-prone
 */
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int offset = 0;
        int idx = 0; 
        while(idx+offset < len -1) {
            if(nums[idx] == nums[idx+1]) {
                int left = idx;
                int right = idx + 1;
                while(right<len) {
                    nums[left++] = nums[right++];
                }
                offset++;
            } else {
                idx++;
            }
        }
        return len - offset ;
    }
}
// @lc code=end




/*
 * @lc id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */
/**
 * Solution clever:
 * - two pointers
 * - one for pivot, one for scanning
 */
// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if(len < 2) {
            return len;
        }

        int idx = 1;
        for(int i = 1; i<len; i++) {
            if(nums[i] != nums[i-1]) {
                nums[idx++] = nums[i];
            }
        }
        return idx;

    }
}
// @lc code=end



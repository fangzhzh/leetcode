/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 */

// @lc code=start
class Solution {
    public int searchInsert(int[] nums, int target) {
        // 找到最小的k,k >= target，所以是左边界问题
        // Time O(logN) n is the length of nums
        // space O(1)

        int left = 0, right = nums.length;
        while(left < right) { // [left, right)
            int mid = left + (right - left) / 2;
            if(nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
// @lc code=end


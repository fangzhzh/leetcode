/*
 * @lc app=leetcode id=704 lang=java
 *
 * [704] Binary Search
 */

// @lc code=start
class Solution {
    // time O(logN) n is the nums.length
    // space O(1)
    public int search(int[] nums, int target) {
        // find the target binary search
        int left = 0, right = nums.length - 1; // [0, nums.length-1]
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < target) {
                left = mid + 1; //[mid+1, right]
            } else if(nums[mid] > target) {
                right = mid - 1; // [left, mid -1]
            }
        }
        return -1;
    }
}
// @lc code=end


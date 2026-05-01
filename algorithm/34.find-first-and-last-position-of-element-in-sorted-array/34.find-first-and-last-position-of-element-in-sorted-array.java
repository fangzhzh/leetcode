/*
 * @lc app=leetcode id=34 lang=java
 * @lcpr version=30403
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */

// @lc code=start
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
                return new int[]{-1, -1};
        }
        int left = lower(nums, target);
        int right = upper(nums,target);
        int[] res = new int[2];
        res[0] = left;
        res[1] = right;
        return res;
    }
    private int lower (int[] nums, int target){
        int l = 0, r = nums.length-1;
        while(l<r) {
            int m = (l+r)/2;
            if(nums[m] >= target) {
                r = m;
            } else {
                l = m +1;
            }
        }
        if(nums[l] == target) {
            return l;
        } else {
            return -1;
        }
    }
    private int upper (int[] nums, int target){
        int l = 0, r = nums.length-1;
        while(l<r) {
            int m = (1+l+r)/2;
            if(nums[m] > target) {
                r = m-1;
            } else {
                l = m;
            }
        }
        if(nums[l] == target) {
            return l;
        } else {
            return -1;
        }
    }
}
// @lc code=end



/*
// @lcpr case=start
// [5,7,7,8,8,10]\n8\n
// @lcpr case=end

// @lcpr case=start
// [5,7,7,8,8,10]\n6\n
// @lcpr case=end

// @lcpr case=start
// []\n0\n
// @lcpr case=end

 */


/*
 * @lc app=leetcode id=153 lang=java
 * @lcpr version=30403
 *
 * [153] Find Minimum in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l < r) {
            int mid = (l+r)/2;
            if(nums[mid] > nums[r]) {
                l = mid+1;
            } else if(nums[mid] < nums[r]) {
                r = mid;
            } else {
                return nums[mid];
            }
        }    
        return nums[l];
    }
}
// @lc code=end



/*
// @lcpr case=start
// [3,4,5,1,2]\n
// @lcpr case=end

// @lcpr case=start
// [4,5,6,7,0,1,2]\n
// @lcpr case=end

// @lcpr case=start
// [11,13,15,17]\n
// @lcpr case=end

 */


/*
 * @lc app=leetcode id=162 lang=java
 * @lcpr version=30403
 *
 * [162] Find Peak Element
 */

// @lc code=start
class Solution {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l < r) {
            int mid = (l+r)/2;
            if(nums[mid] < nums[mid+1]) {
                l = mid+1;
            } else {
                r = mid;
            }
        }    
        return l;
    }
}
// @lc code=end



class Solution {
    public int findBottomElement(int[] nums) {
        int l = 0, r = nums.length-1;
        while(l<r) {
            int m = (l+r)/2;
            if(nums[m] > nums[m+1]) {
                l = m+1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
/*
// @lcpr case=start
// [1,2,3,1]\n
// @lcpr case=end

// @lcpr case=start
// [1,2,1,3,5,6,4]\n
// @lcpr case=end

 */


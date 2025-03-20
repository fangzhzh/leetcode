/*
 * @lc app=leetcode id=55 lang=java
 *
 * [55] Jump Game
 */

// @lc code=start
// TC O(n)
class Solution {
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i+nums[i]);
        }
        return true;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=213 lang=java
 * @lcpr version=30403
 *
 * [213] House Robber II
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[2];
        int ans = 0;
        return Math.max(
            rob(nums, 0, n-2),
            rob(nums, 1, n-1)
        );

   }

   private int rob(int[] nums, int s, int e) {
    int prevRob = 0, maxRob = 0;
    for(int i = s; i <= e; i++) {
        int tmp = Math.max(maxRob, prevRob+nums[i]);
        prevRob = maxRob;
        maxRob = tmp;
    }
    return maxRob;
   }
}
// @lc code=end



/*
// @lcpr case=start
// [2,3,2]\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,1]\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3]\n
// @lcpr case=end

 */


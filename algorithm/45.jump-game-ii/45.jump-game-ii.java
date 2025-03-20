/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */

// @lc code=start

// TC O(n * num) => worst case TC O(n^2)
class Solution {
    public int jump(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < nums.length; i++) {
            if(dp[i] == Integer.MAX_VALUE) continue;
            for(int j = 1; j <= nums[i]; j++) {
                if(i+j < nums.length) {
                    dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length-1];
    }
}
// @lc code=end


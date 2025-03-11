/*
 * @lc app=leetcode id=377 lang=java
 *
 * [377] Combination Sum IV
 */

// @lc code=start
/**
 * comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i]
 * Top down 
 */
class Solution {
    // TC O(nlogn)
    public int combinationSum4(int[] nums, int target) {
        int len = nums.length;
        int []dp = new int[target+1];
        dp[0] = 1;
        Arrays.sort(nums);
        for(int i = 1; i <= target; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(nums[j] > i) {
                    break;
                }
                dp[i] += dp[i-nums[j]];
            }
        }
        return dp[target];
    }
}

// 2025-03-11
class Solution {
    // TC O(k*n)
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1]; // how many combination sum at i
        dp[0] = 1; 
        for(int i = 1; i <= target; i++) {
            for( int num : nums) {
                int ni = i-num;
                if(ni >= 0 && ni <= target) {
                    dp[i] += dp[ni];
                }
            }
        }
        return dp[target];
    }
}
// @lc code=end


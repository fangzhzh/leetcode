/*
 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 */

 /**
  * 
  ou are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
  */

/**
 * recursive, not a backtrack
 * 
 * find a way to get target == 0 when idx == nums.length
 * 
 * sum = sum + sum(target+nums[i]) + sum[target-nums[i]];
 */  
// @lc code=start
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        return helper(0, nums, 0, S);
    }
    int helper(
        int sum,
        int[] nums,
        int idx, 
        int target
    ) {
        if(target == 0 && idx == nums.length) {
            sum++;
        }
        if(idx >= nums.length) {
            return sum;
        }
        return sum 
        + helper(sum, nums, idx+1, target-nums[idx]) 
        + helper(sum, nums, idx+1, target+nums[idx]);
    }
}
// @lc code=end


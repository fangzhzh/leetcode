/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 */

/**
 * Partition Equal Subset Sum
Category	Difficulty	Likes	Dislikes
algorithms	Medium (47.18%)	12742	262
Tags
Companies
Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
// @lc code=start
class Solution {
    // TC O(n * target)
    // SC O(target)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        if(sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[] dp = new boolean[target+1];
        dp[0] = true;
        for(int num : nums) {
            for(int j = target; j>=num; j--) {
                dp[j] = dp[j] || dp[j-num];
            }
        }
        return dp[target];
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 */

/**
 * 
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 

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
 *  */ 



/**
 * deductive the math 
 * 
 * sum(a') == sum(a'') and a'+a'' = A
 * 
 * so that sum(a') == sum(a) / 2; so that sum(a') % 2 == 0
 * 
 * This is a valuable understanding.
 * 
 * after that, an array, find some subset equal to the target
 * 
 * Of course solutions include: 
 * - backtracking 
 * - knapsack
 * 
 * 
 * The set idea is also good, it includes all possible sum, combination, permutation
 * for(int n : nums) {
 *  for(s : set) {
 *      set.add(s+n);
 *  }
 *  
 * }
 *  */ 
// @lc code=start
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for(int n : nums) {
            Set<Integer> newSet = new HashSet<>();
            newSet.add(n);
            for(int s : set) {
                if(n + s == target) return true;
                newSet.add(n + s);
            }
            set.addAll(newSet);
        }
        return false;

    }
}
// @lc code=end

/**
 * backpacking 
 * 
 * the sum analysis is as same as before
 * 
 * dp[i][j]
 * 
 * whether nums[0...i] sum to j
 * 
 * nums[0..n][0] <= true, take nothing from array
 * 
 * dp[i][j] = dp[i-1][j](not taken) || dp[i-i][j-nums[i]]
 * 
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[nums.length+1][target+1];
        dp[0][0] = true;
        for(int i = 1; i <= nums.length; i++) {
            dp[i][0] = true;
        }
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                if(nums[i-1] <= j) {
                    dp[i][j] = (dp[i][j] || dp[i-1][j-nums[i-1]]);
                }
            }
        }
        return dp[nums.length][target];

    }
}

/**
 * backtracking
 * 
 * Though TLE
 * 
 * but the idea is we either take it or not take it, recursive check next number
 */

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        return helper(nums, target, 0, 0);
    }
    boolean helper(int[] nums, int target, int idx, int sum) {
        if(sum == target && idx == nums.length) {
            return true;
        }
        if(idx >= nums.length) {
            return false;
        }
        return helper(nums, target, idx+1, sum + nums[idx]) || helper(nums, target, idx+1, sum);
    }
}
/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 */


/**
 * 
 * 
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

 

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Example 2:

Input: nums = [1]
Output: 1
Example 3:

Input: nums = [0]
Output: 0
Example 4:

Input: nums = [-1]
Output: -1
Example 5:

Input: nums = [-2147483647]
Output: -2147483647
 

Constraints:

1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1

*/ 


// @lc code=start

/**
 * get from leetcode discuss
 * https://leetcode.com/problems/maximum-subarray/discuss/20210/O(n)-Java-solution
 * 
 * It's so easy to understand and so hard to think of
 */
class Solution {
    public int maxSubArray(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            if(sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
// @lc code=end


/**
 * and dp version
 * 
 * dp[i] mean the max value ending in ith
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i-1]+nums[i-1], nums[i-1]);
            if(max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
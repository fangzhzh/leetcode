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
 * Solution 1: recursive
 * 
 * 
 */
class Solution {
    int max = 0;
    public int maxSubArray(int[] nums) {
        maxSubArray(nums, nums.length-1);
        return max;
    }
    private int maxSubArray(int[] nums, int idx) {
        if(idx == 0) return nums[idx];
        int sum = maxSubArray(nums, idx-1);
        int result;
        if(sum > 0) {
            result = sum + nums[idx];
        } else {
            result = nums[idx];
        }
        max = Math.max(max, result);
        return result;
    }
}

/**
 * Solution 2: dp version
 * one dimension state array
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

/**
 * Solution 2.1: DP with one dimension state array
 */
 class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int max = nums[0];
        int[] dp = new int[len];
        for(int i = 0; i < len; i++) {
            dp[i] = nums[i];
        }

        for(int i = 1; i < len; i++) {
            if(dp[i-1] > 0) {
                dp[i] = dp[i-1] + nums[i];
            } 
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

/**
 * Solution 3: DP with state compression 
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
 * Solution not 4: why slicing window not working
 * spent some time to reasoning why this question is not fix slicing window
 * At last, it turns out it can be a slicing window problem, but only different is
 * It doesnt really need the left part of index to keep the valid condition like the 
 * sum < 0 part, the left sudently jump to the rightO52
 */

 class Solution {
    public int maxSubArray(int[] nums) {
        int left = 0, right =0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        while(right < nums.length) {
            if(sum < 0) {
                sum = nums[right];
                left = right;
            } else {
                sum += nums[right];
            }
            if(max < sum) {
                max = sum;
            }
            right++;
        }
        return max;
    }
}
/*
 * @lc app=leetcode id=674 lang=java
 *
 * [674] Longest Continuous Increasing Subsequence
 */


/**
 * 
 Given an unsorted array of integers nums, return the length of the longest continuous increasing subsequence (i.e. subarray). The subsequence must be strictly increasing.

A continuous increasing subsequence is defined by two indices l and r (l < r) such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each l <= i < r, nums[i] < nums[i + 1].

Example 1:

Input: nums = [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
Even though [1,3,5,7] is an increasing subsequence, it is not continuous as elements 5 and 7 are separated by element
4.
 */

/**
 * relatively simple question.
 * 100% runtime
 */ 
// @lc code=start
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int cnt = 0;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for(int value : nums) {
            if(value > max) {
                max = value;
                cur++;
                if(cnt < cur) {
                    cnt = cur;
                }
            } else {
                max = value;
                cur = 1;
            }
        }
        return cnt;
        
    }
}
// @lc code=end

/**
 * a further thinking, the max is redudant
 * 99.06% runtime
 */
public int findLengthOfLCIS(int[] nums) {
    int cnt = 0, cur = 1;
    for(int i = 0; i < nums.length; i++) {
        if(i == 0 || nums[i] <= nums[i-1]) {
            cur = 1;
        } else {
            cur++;
        }
        if(cnt < cur) {
            cnt = cur;
        }
    }
    return cnt;
 }
    
}

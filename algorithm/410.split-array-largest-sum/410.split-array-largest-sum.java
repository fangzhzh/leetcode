/*
 * @lc app=leetcode id=410 lang=java
 *
 * [410] Split Array Largest Sum
 */

/**
 * 
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m subarrays.

 

Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.
Example 2:

Input: nums = [1,2,3,4,5], m = 2
Output: 9
Example 3:

Input: nums = [1,4,4], m = 3
Output: 4
 

Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 106
1 <= m <= min(50, nums.length)
 *  */ 
// @lc code=start
/**
 * 
 * https://leetcode.com/problems/split-array-largest-sum/discuss/161143/Logical-Thinking-with-Code-Beats-99.89
 * 
 * Given searching range and a target value, it is natural to apply Binary Search.
Searching range is influenced by given m:
minVal - maximum element of nums
maxVal - sum of elements of nums (when m = 1)
Target: min(largest subarray sum), the minimum possible value that satisfies the requirement.


 */
class Solution {
    public int splitArray(int[] nums, int m) {
        int max = 0, sum = 0;
        for(int n : nums) {
            max = Math.max(n, max);
            sum += n;
        }

        int left = max, right = sum;
        while(left+1 < right) {
            int mid = (right - left)/2 + left;
            if(canSplit(mid, nums, m)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (canSplit(left, nums, m)) {
            return left;
        }
        return right;
    }
    private boolean canSplit(int max, int[] nums, int m) {
        int curSum = 0, curSplit = 1;
        for(int n : nums) {
            curSum += n;
            if(curSum > max) {
                curSum = n;
                curSplit++;
                if(curSplit > m) {
                    return false;
                }
            }
        }
        return true;
        
    }
}
// @lc code=end


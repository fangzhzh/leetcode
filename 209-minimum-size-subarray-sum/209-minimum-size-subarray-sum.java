/*
 * @lc id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

 /**
  * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
  */
// @lc code=start
class Solution {
    //O(n^2)
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int[]sums = new int[nums.length];
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i] = sum;
            for(int j = i; j >= 0; j--) {
                if(sums[i] - sums[j] >= s) {
                    if(i - j  < min) {
                        min = i - j ;
                    } 
                    break;
                }
            }
            if(sums[i]>=s) {
                if(i < min) {
                    min = i+1;
                }
            }
        }

        return min==Integer.MAX_VALUE?0:min;
    }
}
// @lc code=end

/**
 * ## analysis
 * O(n^2)
 * ### Improvement:
 * - two pointers
 * - binary search
 * 
 */

class Solution {
    // two pointer
    // O(n^2)
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int j = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= s) {
                ans = Math.min(ans, i-j+1);
                sum -= nums[j++];
            }
        }

        return ans==Integer.MAX_VALUE?0:ans;
    }
}



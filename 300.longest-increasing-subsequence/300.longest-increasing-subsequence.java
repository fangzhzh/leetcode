/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */

// @lc code=start

/**
 * Category	Difficulty	Likes	Dislikes
algorithms	Medium (42.92%)	6714	153
Tags
Companies
Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up:

Could you come up with the O(n2) solution?
Could you improve it to O(n log(n)) time complexity?

 */


/**
 * O(N^2)
 *  Top down
\\[ 
fn = 
\begin{cases}
 0	&\text{if $length=0$} \\
 \max_{1 \leq j \leq i}  \begin{cases}
 LIS(j)+1	&\text{if $num[i]> num[j]$} \\
 LIS(j) &\text{if $nums[i] <= num[j]$ }
\end{cases} &\text{$length>0$}
\end{cases}
\\]

https://www.HostMath.com/Show.aspx?Code=fn%20%3D%20%0A%5Cbegin%7Bcases%7D%0A%200%09%26%5Ctext%7Bif%20%24length%3D0%24%7D%20%5C%5C%0A%20%5Cmax_%7B1%20%5Cleq%20j%20%5Cleq%20i%7D%20%20%5Cbegin%7Bcases%7D%0A%20LIS(j)%2B1%09%26%5Ctext%7Bif%20%24num%5Bi%5D%3E%20num%5Bj%5D%24%7D%20%5C%5C%0A%20LIS(j)%20%26%5Ctext%7Bif%20%24nums%5Bi%5D%20%3C%3D%20num%5Bj%5D%24%20%7D%0A%5Cend%7Bcases%7D%20%26%5Ctext%7B%24length%3E0%24%7D%0A%5Cend%7Bcases%7D%0A
 */ 

class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
       return lengthOfLIS(nums, nums.length-1);
    }
    private int lengthOfLIS(int[]nums, int end) {
        int max = 1;
        for(int i = end-1; i > 0; i--) {
            if(nums[end] <= nums[i]) {
                max = Math.max(max, lengthOfLIS(nums, i));
            } else {
                max = Math.max(max, lengthOfLIS(nums, i) + 1);
            }
        }
        return max;
    }
}


/**
 * O(N^2)
 * why better than O(N^2) recursive version, it cut unneccesary calculation.
 *
 * Soln 2: Dynamic programming: DP
 * state: dp[i] LIS till nums[i]
 * init: dp[i] = 1
 * transiton: dp[i] = if(nums[i] > num[j]) dp[j] + 1; else dp[j];
 */
class Solution {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int max = 0;
        for(int i = 1; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = dp[j] + 1;
                } else {
                    dp[i] = dp[j];
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}// @lc code=end


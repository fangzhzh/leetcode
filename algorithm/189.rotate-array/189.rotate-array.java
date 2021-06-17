/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array
 */


/**
 * 
 * Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 

Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 */ 
// @lc code=start
/**
 * 3 reverse 
 * The nums can be split to two arrays
 * [1,2,3,4,5,6,7], k = 3
 * [0,n-k], [n-k, n], XY
 * X = [1,2,3,4], Y = [5,6,7]
 * If we reverse every part, X^T = [4,3,2,1], Y^T=[7,6,5]
 * (X^TY^T)^T = YX
 * (X^TY^T)^T = ([4,3,2,1], [7,6,5])^T = [5,6,7,1,2,3,4]
 * 
 * Magic of mathmatics.
 * 
 * (X^T)^T = X
 * 
 * 
 * Related question:
 * - ReverseWords "I am a student."" => “student. a am I” 
 * https://leetcode.com/problems/reverse-words-in-a-string/
 */
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length-1;
        k = k % nums.length;
        swap(nums, n-k+1, n);
        swap(nums, 0, n-k);
        swap(nums, 0, n);
    }
    void swap(int[] nums, int left, int right) {
        while(left < right) {
            int tmp = nums[right];
            nums[right] = nums[left];
            nums[left] = tmp;
            left++;
            right--;
        }
    }
}
// @lc code=end


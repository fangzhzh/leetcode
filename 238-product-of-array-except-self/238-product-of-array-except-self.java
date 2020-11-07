/*
 * @lc id=238 lang=java
 *
 * [238] Product of Array Except Self
 */

// @lc code=start
/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

 /**
  * O(n)
  * ##analysis
  - migrate two dementional loop to two loops downsize the O(n^2) to O(n)
  - why it works?
    + with an extra memory, the intermediate value can be memoried and reused to get ride of some unneeded calculation.
    + sounds like the double loop always a waste in this scenerio
  */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ret = new int[len];
        int sum = 1;
        for(int i = 0; i < len; i++) {
            ret[i] = sum;
            sum *= nums[i];
        }
        sum = 1;
        for(int i = len-1; i>=0; i--) {
            ret[i] *= sum;
            sum *= nums[i];
        }
        return ret;
    }
}
// @lc code=end

/**
 * listen, example, brutal force, optimal
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int []ans = new int[len];
        int prod = 1;
        for(int i = 0; i < len; i++) {
            ans[i] = prod;
            prod *= nums[i];
        }
        prod = 1;
        for(int i = len-1; i >= 0; i--) {
            ans[i] *= prod;
            prod *= nums[i];
        }
        return ans;
    }
}



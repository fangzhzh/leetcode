/*
 * @lc app=leetcode id=66 lang=java
 *
 * [66] Plus One
 */

/**
 * Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

 

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Example 3:

Input: digits = [0]
Output: [1]
 *  */ 
// @lc code=start


/**
 * 1 ms, faster than 8.11% 
 * 
 * lol, the answer by other people are crazy simple and elegant.
 * like
 * if(digits[i] < 9) {digits[i]++, return digits;}
 */
class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        StringBuilder sb = new StringBuilder();
        for(int i = digits.length-1; i>=0; i--) {
            int tmp = digits[i] + carry;
            // if(tmp > 10) {
            carry = tmp / 10;
            tmp = tmp % 10;
            // }
            sb.insert(0, tmp);
        }
        
        if(carry > 0) {
            sb.insert(0, carry);
        }
        int[] ans = new int[sb.length()];
        for(int i = 0; i < sb.length(); i++) {
            ans[i] = sb.charAt(i)-'0';
        }
        return ans;
    }
}
// @lc code=end


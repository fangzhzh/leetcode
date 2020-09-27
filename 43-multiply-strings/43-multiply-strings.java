/*
 * @lc id=43 lang=java
 *
 * [43] Multiply Strings
 */

 /**
  Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"

  */
// @lc code=start
class Solution {
    public String multiply(String num1, String num2) {
        if(num1 == null || num1.length() == 0 
            || num2 == null || num2.length() == 0
            || (num1.length() == 1 && num1.charAt(0)=='0')
            || (num2.length() == 1 && num2.charAt(0)=='0')) {
            return "0";
        }
        int base = 0;
        String ret="0";
        String result = "";
        for(int i = num1.length()-1; i>=0; i--) {
            result = multiply(num1.charAt(i), base, num2);
            // System.out.println(result);
            ret = addition(result, ret);
            // System.out.println(ret);
            base += 1;
        }
        return ret;
    }
    private String multiply(char c, int base, String num2) {
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while(base-- > 0) {
            builder.append('0');
        }
        for(int i = num2.length()-1; i >= 0; i--) {
            int sum = (c-'0')*(num2.charAt(i)-'0') + carry;
            builder.append(sum%10);
            carry = sum / 10;
        }
        if(carry > 0) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
    private String addition(String num1, String num2) {
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        int i = num1.length()-1, j = num2.length()-1;
        int sum = 0;
        while(i >=0 && j >= 0) {
            sum = carry + num2.charAt(j)-'0' + num1.charAt(i)-'0';
            builder.append(sum%10);
            carry = sum / 10;
            i--;
            j--;
        }
        while(i>=0) {
            sum = carry + num1.charAt(i)-'0';
            builder.append(sum%10);
            carry = sum / 10;
            i--;
        }
        while(j>=0) {
            sum = carry + num2.charAt(j)-'0';
            builder.append(sum%10);
            carry = sum / 10;
            j--;
        }
        if(carry > 0) {
            builder.append(carry);
        }
        return builder.reverse().toString();
    }
}

/**
 * ## analysis, solution without long
 * - O(n*m)
 * 
 * But if long is allowed, the problem will be easier. Refer to Solution1.
 * 
 * Solution2 is also genius, it makes use of the array and indices, but not easy to think of.
 */



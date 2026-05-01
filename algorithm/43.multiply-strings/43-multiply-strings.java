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
 * But if long is allowed, the problem will be easier. Refer to Solution1. long will be TLE
 * 
 * Solution2 is also genius, it makes use of the array and indices, but not easy to think of.
 */


/**
 * n1 * n1 = ans, so len(ans) = at most len(n1) + len(n2), example, 9*9
 * 
 * to fill ans[i+j+1] and you will get a same sequence as 13*13=169
 * 
 * int ans[], we get the result in first round, mod, divide in second round
 * then get ride of leading 0
 */
class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int ans[] = new int[m+n];
        int ibase = 0;
        for(int i = m -1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                ans[i+j+1] += (num1.charAt(i) - '0') * (num2.charAt(j)-'0');
            }   
        }
        int carry = 0;
        for(int i = m+n-1; i >= 0; i--) {
            int tmp = (ans[i]+carry)  % 10;
            carry = (ans[i]+ carry) / 10;
            ans[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(; i < ans.length; i++) {
            if(ans[i] != 0) break;
        }
        for(; i < ans.length; i++) {
            sb.append(ans[i]);
        }
        return sb.length() != 0 ? sb.toString() : "0";
        
    }
}
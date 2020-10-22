/*
 * @lc app=leetcode id=67 lang=java
 *
 * [67] Add Binary
 */

 /**
  * formula:
  * sum   carry   ans[idx]  
  *  0      0       0
  *  1      0       1
  *  2      1       0
  *  3      1       1
  */
// @lc code=start
class Solution {
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        int i = m-1, j = n-1;
        int carry = 0;

        StringBuilder ans = new StringBuilder();
        for(; i>=0 && j>=0; i--,j--) {
            int sum = a.charAt(i)-'0' + b.charAt(j)-'0' + carry;
            if(sum > 1) {
                carry = 1;
                ans.append(sum-2);
            } else {
                carry = 0;
                ans.append(sum);
            }
        }
        while(i>=0) {
            int sum = a.charAt(i)-'0'+ carry;
            if(sum > 1) {
                carry = 1;
                ans.append(sum-2);
            } else {
                carry = 0;
                ans.append(sum);
            }
            i--;
        }

        while(j>=0) {
            int sum = b.charAt(j)-'0' + carry;
            if(sum > 1) {
                carry = 1;
                ans.append(sum-2);
            } else {
                carry = 0;
                ans.append(sum);
            }
            j--;
        }
        if(carry > 0) {
            ans.append(carry);
        }
        return ans.reverse().toString();

    }
}
// @lc code=end



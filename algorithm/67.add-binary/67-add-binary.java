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

// 2025-03-13
class Solution {
    public String addBinary(String a, String b) {
        int ca = 0;
        int i = a.length()-1, j = b.length()-1;
        StringBuilder sb = new StringBuilder();
        while(i>=0 && j>=0) {
            int left = a.charAt(i)-'0';
            int right = b.charAt(j)-'0';
            int sum = left + right + ca;
            ca = sum / 2;
            sb.append(sum%2);
            i--;
            j--;
        }
        while(i>=0 ) {
            int left = a.charAt(i)-'0';
            int sum = left +  ca;
            ca = sum / 2;
            sb.append(sum%2);
            i--;
        }
        while( j >=0) {
            int right = b.charAt(j)-'0';
            int sum = right + ca;
            ca = sum / 2;
            sb.append(sum%2);
            j--;
        }
        if(ca > 0){
            sb.append(ca);
        }
        return sb.reverse().toString();

    }
}
// @lc code=end



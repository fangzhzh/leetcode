/*
 * @lc app=leetcode lang=java
 *
 * [91] Decode Ways
 */
/*
*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

// ##questions
/*
    - what about zero?
    - what about empty list or null?
*/
// ## formula
/**
 * Dp[i]=dp[i-1]+(10<=a(i-2,i-1]<=26)?dp[i-2]:0
 */

 /* ## how come the formula
 ### case 1
    - 1:        A
    - 12:       AB,     L
    - 121:      ABA,    AU,     LA
    - 1212:     ABAB,   AUB,    LAB,    
                ABL,    LL
    - dp[i] = dp[i-1] + dp[i-2]

### case 2
    - 1:       A
    - 12:      AB,      L
    - 129:     AT,      LI
    - dp[i] = dp[i-1]
*/
// @lc code=start
class Solution {
    public int numDecodings(String s) {
        int len = s.length();
        if(s == null || s.length() ==0) {
            return 0;
        }
        int []dp = new int[len+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= len; i++) {
            int ith = s.charAt(i-1) - '0';
            int tenth = s.charAt(i-2) - '0';
            if(ith>=1 && ith <= 9) {
                dp[i] += dp[i-1];
            }
            int value = tenth * 10 + ith;
            if(value>=10 && value <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[len];
    }
}



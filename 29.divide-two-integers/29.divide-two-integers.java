/*
 * @lc app=leetcode id=29 lang=java
 *
 * [29] Divide Two Integers
 */

// @lc code=start
class Solution {
    public int divide(int dividend, int divisor) {
        // 思路
        // no multiplication, division and mod
        // so it's like a binary search to find the nearest element to achive 
        // divisor plus divisor and it repeats x times
        // going to nowhere
        
        // after reading some solution I realised why it has so many downvote
        // edge case, edge case, edge case
        // basically it use >>> to mock multiplication
        // devidend - (x) to mock mod
        
        // some thought after a lot of try:
        // the Integer.MAX_VALUE + 1, MAX_MIN_VALUE -1 is very dangrous and prone to err
        
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int dividendRemain = Math.abs(dividend);
        int divisorAbs = Math.abs(divisor);
        int ans = 0;
        while(dividendRemain - divisorAbs>= 0) {
            int divisorMult = divisorAbs;
            int cnt = 1;
            while(dividendRemain - (divisorMult<<1) >= 0) {
                divisorMult <<= 1;
                cnt <<= 1;
            }
            ans += cnt;
            dividendRemain -= divisorMult;
        }
        int sign = (dividend > 0 ^ divisor > 0) ? -1 : 1;
        return sign == -1? 0 - ans: ans;        
    }
}
// @lc code=end


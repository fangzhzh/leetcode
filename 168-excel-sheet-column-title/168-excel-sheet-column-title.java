/*
 * @lc app=leetcode lang=java
 *
 * [168] Excel Sheet Column Title
 */

// @lc code=start
/**
 * intuitive way: 
 * - if not modulo by 26, then it's A-Y, add it and devided by 26
 * - if modulo by 26, then it's Z. and it and remove a 26 then devide it to get a correct quotient
 * - dividend modulo(mod) divisor= division / reminder
 * - 5(divident) %          4   = 1 / 1

 * - dividend divided by divisor = quotient
 */
class Solution {
    public String convertToTitle(int n) {
        StringBuilder builder = new StringBuilder();
        while(n > 0) {
            int tmp = n % 26;
            if(tmp > 0) {
                builder.append(char(tmp-1+'A'));
                n /=26;
            } else {
                builder.append('Z');
                n -= 26;
                n /= 26;
            }
        }
        return builder.reverse().toString();
    }
}

/**
 * of course a better solution, everytime, we do n-1, then
 * divident + 'A' is A, 25 is 'Z', we don't have to specially consider the 26 case.
 * Check the Solution.java
 */
// @lc code=end

/**
 * enough example analysis
 * 1    ->  A
 * 2    ->  B
 * 26   -> Z
 * 27   -> AA
 * 28   -> AB
 * divide, reminder and divident
 * because we need to `reminder + 'A'` so that we must shift the reminder to left one bit
 * so that 'A' + 0 => 'A'
 * 
 * Base 26, digits are 0...25
 * 
 */
class Solution {
    public String convertToTitle(int n) {
        int tmp = n;
        StringBuilder ans = new StringBuilder();
        while(tmp > 0) {
            tmp = tmp - 1;
            int reminder = tmp % 26;
            ans.append((char)(reminder + 'A'));
            tmp /= 26;
        }
        return ans.reverse().toString();
    }
}

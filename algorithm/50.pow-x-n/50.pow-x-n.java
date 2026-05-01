/*
 * @lc app=leetcode id=50 lang=java
 *
 * [50] Pow(x, n)
 */

// @lc code=start
/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e. xn).

 

Example 1:

Input: x = 2.00000, n = 10
Output: 1024.00000
Example 2:

Input: x = 2.10000, n = 3
Output: 9.26100
Example 3:

Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
 */

/**
 * The idea is good, if n %2 == 0, pow(n) = pow(x*x, n/2)
 * else force convert it to n % 2 y pow(n) = x * pow(x*x, (n-1)/2)
 * 
 * But this algorithem fails at n == Integer.MIN_VALUE which -2147483648
 * because -x become to 2147483648 which is int overflow
 */ 

class Solution {
    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n<0){
            n = -n;
            x = 1/x;
        }
        return (n%2==0) ? myPow(x*x, n/2) : x*myPow(x*x, (n-1)/2);
    }
}


/**
 * double x, double myPow, 
 * 2^7
 * = 2 * 2*2^(7/2) = 2 * 4^3
 * = 2 * 4 * (4*4)^(3/2) = 2 * 4 * 16^1
 * = 2 * 4 * 16 = 2^(1+2+4) 
 * = 2^7
 */


/**
 * This works.
 * if n < 0, -(n+1) == -(n-1) which avoids the Integer.MIN_VALUE case, 
 * and since it removes one from n, it 1/x * myPow(1/x, -(n+1))
 * 
 */
class Solution {
    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        if(n<0){
            return 1/x * myPow(1/x, -(n + 1));
        }
        return (n%2==0) ? myPow(x*x, n/2) : x*myPow(x*x, (n-1)/2);
    }
}
// @lc code=end

/**
 * iterative, but also handle the Integer.MIN_VALUE case.
 */
class Solution {
    public double myPow(double x, int n) {
        if(n==0) {
            return 1;
        }
        double ret = 1.0;
        if(n < 0) {
            x = 1 / x;
            ret = x;
            n = -(n+1);
        }
        while(n > 0) {
            if(n%2 != 0) {
                ret *= x;
            }
            x *= x;
            n >>= 1;
        }
        return ret;
    }
}
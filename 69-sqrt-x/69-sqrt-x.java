/*
 * @lc  id=69 lang=java
 *
 * [69] Sqrt(x)
 */

 /**
  * Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
  */
// @lc code=start

/**
 * ## analysiss:
 * - tricky as hell
 * - trap 1: mid = (left + right) / 2 might be **overflow**
 * - trap 2: mid * mid might be **overflow** 
 *  + 32bit int = 2^32 = 4294967296
 *  + sqrt(4294967296) = sqrt(2^32) = sqrt(2^16) = 65535
 * - trap 3: x / mid = ret
 *  + (ret > mid) to left or right?
 *  + (ret < mid) to right or left?
 *  + example: 10/5 = 2, (2<5) and we know it should go to left, 
 *  + so ret < mid left, 
 * 
 * OMG, I felt for every trap again.
 */
class Solution {
    public int mySqrt(int x) {
        if(x == 0) {
            return 0;
        }
        int left = 0, right = x;
        while(left+1<right) {
            int mid = left + (right - left)/2;
            int ret = x / mid;
            if(ret == mid) {
                return mid;
            } else if(ret > mid) {
                left = mid;
            } else {
                right = mid ;
            }
        }
        if(right == x / right) {
            return right;
        }
        return left;
    }
}
// @lc code=end


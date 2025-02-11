/*
 * @lc app=leetcode id=509 lang=java
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).

 

Example 1:

Input: n = 2
Output: 1
Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
Example 2:

Input: n = 3
Output: 2
Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
Example 3:

Input: n = 4
Output: 3
Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 

Constraints:

0 <= n <= 30
 * [509] Fibonacci Number
 */

// @lc code=start
class Solution {
    public int fib(int n) {
        return fib_bottomup(n);
    }

    public int fib_topdown(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fib(n-1)+fib(n-2);
    }
    private int fib_topdown_mem(int n) {
        int[] memo = new int[n+1];
        return fib_topdown_mem(n, memo);
    }
    private int fib_topdown_mem(int n, int[] memo) {
        if(memo[n] != 0) {
            return memo[n];
        }
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }

        memo[n] = fib_topdown_mem(n-1, memo) + fib_topdown_mem(n-2, memo);
        return memo[n];
    }

    private int fib_bottomup(int n) {
        if(n < 2) {
            return n;
        }
        int[] memo = new int[n+1];
        memo[0] = 0;
        memo[1] = 1;
        for(int i = 2; i <= n; i++) {
            memo[i] = memo[i-1] + memo[i-2];
        }
        return memo[n];
    }

}
// @lc code=end


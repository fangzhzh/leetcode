/*
 * @lc app=leetcode id=461 lang=java
 *
 * [461] Hamming Distance
 */

 /**
  * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
  */


/**
 * Analysis:
 * A quick reading of the question, it's a simple to binary problem. After toBinary, jut count the different
 * 149/149 cases passed (0 ms)
Your runtime beats 100 % of java submissions
Your memory usage beats 11.82 % of java submissions (35.7 MB)
 *
 * Possible optimization, in place
 */
// @lc code=start
class Solution {
    public int hammingDistance(int x, int y) {
        int[] xb = new int[32];
        toBinary(x, xb);
        int[] yb = new int[32];
        toBinary(y, yb);
        int cnt = 0;
        for(int i = 0; i < 32; i++) {
            if(xb[i] != yb[i]) cnt++;
        }
        return cnt;
    }
    private void toBinary(int x, int[] ans) {
        int idx = 0;
        while(x > 0) {
            int sum = x % 2;
            ans[idx++] = sum;
            x /= 2;
        }
    }
}
// @lc code=end

/**
 * No improvement in memory usage, strange
 * 149/149 cases passed (0 ms)
Your runtime beats 100 % of java submissions
Your memory usage beats 11.82 % of java submissions (35.5 MB)
 */
class Solution {
    public int hammingDistance(int x, int y) {
        int cnt = 0;
        while(x > 0 && y > 0) {
            if(x%2 != y%2) {
                cnt++;
            }
            x /= 2;
            y /= 2;
        }
        while(x > 0)  {
            if(x%2 == 1) {
                cnt++;
            }
            x /= 2;
        }
        while(y > 0) {
            if(y%2 == 1) {
                cnt++;
            }
            y /= 2;
        }
        return cnt;
    }

}
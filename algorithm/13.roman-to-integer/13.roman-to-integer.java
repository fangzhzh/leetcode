/*
 * @lc app=leetcode id=13 lang=java
 *
 * [13] Roman to Integer
 */

// @lc code=start
class Solution {
    // just straignt forward, one loop and calculate
    // TC O(n)
    // SC O(n)
    public int romanToInt(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int val = 0;
            switch (c) {
                case 'M':
                    val = 1000;
                    break;
                case 'D':
                    val = 500;
                    break;
                case 'C':
                    if (i < s.length() - 1 && (s.charAt(i + 1) == 'D' || s.charAt(i + 1) == 'M')) {
                        val = -100;
                    } else {
                        val = 100;
                    }
                    break;
                case 'L':
                    val = 50;
                    break;
                case 'X':
                    if (i < s.length() - 1 && (s.charAt(i + 1) == 'L' || s.charAt(i + 1) == 'C')) {
                        val = -10;
                    } else {
                        val = 10;
                    }
                    break;
                case 'V':
                    val = 5;
                    break;
                case 'I':
                    if (i < s.length() - 1 && (s.charAt(i + 1) == 'V' || s.charAt(i + 1) == 'X')) {
                        val = -1;
                    } else {
                        val = 1;
                    }
                    break;
            }
            ans += val;
        }
        return ans;
    }
}
// @lc code=end


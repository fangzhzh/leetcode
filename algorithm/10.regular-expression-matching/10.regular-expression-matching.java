/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] match = new boolean[sLen+1][pLen+1];
        match[0][0] = true;
        
        // for checking if it matches #*#*#*#*.
        for(int i = 2; i <= pLen; i+=2) {
            if(p.charAt(i-1) == '*'){
                if(match[0][i - 2]) {
                    match[0][i] = true;
                }
            }
        }
        for(int i = 1; i <= sLen; i++) {
            for(int j = 1; j <= pLen; j++) {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') {
                    match[i][j] = match[i-1][j-1];
                }
                if(p.charAt(j-1) == '*') {
                    if(p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.') {
                        match[i][j] = match[i][j-2];
                    } else {
                        match[i][j] = (match[i][j-1] || match[i-1][j] || match[i][j-2]);
                    }
                }
            }
        }
        return match[sLen][pLen];
    }
}
// @lc code=end


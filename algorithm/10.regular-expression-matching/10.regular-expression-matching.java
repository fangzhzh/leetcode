/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 */

// @lc code=start
// looking back, the i-1, i-2 looks horrible, this solution must involved a lot of trial and errors
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

// version 2: 
// much cleaner version where no  i-2, the code is much more readable and understandable
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
        return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*') {  // second char is '*'
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p);
            }
            return false;
        } else {                                     // second char is not '*'
            if(s.length() > 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
    }
}

// version 3, the dp version
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++ ) {
            if(p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                char pj = p.charAt(j-1);
                if(pj == '*') {
                    dp[i][j] |= dp[i][j-2];

                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)) {
                        dp[i][j] |= dp[i-1][j];
                    }
                } else {
                    if(pj == '.' || pj == s.charAt(i-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }
        return dp[m][n];
    }
}
// @lc code=end


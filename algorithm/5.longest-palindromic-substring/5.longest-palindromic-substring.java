/*
 * @lc app=leetcode.cn id=5 lang=java
 *
 * [5] 最长回文子串
 */

// @lc code=start
class Solution {
    public String longestPalindrome(String s) {
       return longestPalindromeExpand(s);
    }

    private String longestPalindromeExpand(String s) {
        // 每个字符[i,i],[i,i+1]往外扩展，遇到s[i] != s[j]即可停止
        // [b,b,b,b,b,a,b,b,b,b,b,a]
        //       <--- ^ ------>
        int start = 0;
        int len = 0;
        for(int i = 0; i < s.length(); i++) {
            // char c = s.charAt(i);
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i+1);
            int tmp  = Math.max(len1, len2);
            if(tmp > len) {
                len = tmp;
                start = i - (len-1)/2;
            }
        }
        return s.substring(start, start + len);

    }

    private int expand(String s, int l , int r) {
        for(; l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r); l--, r++) {
        }
        return r-l-1;
    }

    private String longestPalindromeDp(String s) {
         // Time O(n^2)
        // Space O(n^2)


        // 从len=3，每个字符扫过去，s[i,i+len-1]是不是回文
        // [b,b,b,b,b,a,b,b,b,b,b,a]
        //       ^---len--^

        // f(i,j) = f(i+1,j-1) && (s[i] == s[j])
        int n = s.length();
        // 不用n+1，应为无dp[0][j], dp[i][0]的初始状态
        boolean[][] dp = new boolean[n][n];
        int start = 0;
        int maxLen = 1;
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // 遍历所有len的可能，遍历所有题解空间
        for(int len = 2; len <= n; len++) {
            // [0..n]每个字符
            for(int i = 0; i<n; i++) {
                int j = i + len - 1;
                if(j >= n) {
                    break;
                }
                if(s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if(len <= 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
                if(dp[i][j] && len > maxLen) {
                    start = i;
                    maxLen = len;
                }
            }

        }
        return s.substring(start, start + maxLen);
    }
}
// @lc code=end


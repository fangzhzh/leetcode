/*
 * @lc app=leetcode id=647 lang=java
 *
 * [647] Palindromic Substrings
 */

// @lc code=start

/**
 * 
 * Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 */


/**
 * looking at the title "substring"
 * It leads to the "longest inc/dec substr/subseq" and "max/min len/range in array/str"
 *  
 * Spent some time to realize these two algoritm is to get one specific result,
 * Max/Min/Longest xxx
 * 
 * However this question is to get count of xxx
 * 
 * Obviously, count of something is only two approach, dp and bfs
 * 
 * - for dp, inital thought is count[][], then all the boolean palin[i][j] to needed as well, 
 * as a result, get ride of count, use boolean palin[i][j] and a counter
 * 
 * 
 */ 
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len+1][len+1];
        int cnt = 0;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i)==s.charAt(j)) {
                    if(i == j || i == j+1) {
                        dp[i][j] = true;
                        cnt++;
                    } else {
                        if(dp[i-1][j+1]==true) {
                            dp[i][j] = true;
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
        
    }
}
// @lc code=end



/**
 * 
 * Of course, our bfs version
 */

 // @lc code=start
class Solution {
    public int countSubstrings(String s) {
        if(s== null || s.length() == 0) {
            return 0;
        }
        List<String> ans = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            helper(ans, "", s, i);
        }
        return ans.size();
    }

    void helper(List<String> ans, 
    String cur,
    String s,
    int idx) {
        if(idx >= s.length()) {
            return;
        }
        cur+=s.charAt(idx);
        if(isPalin(cur)) {
            ans.add(new String(cur));
        }
        helper(ans, cur, s, idx+1);
    }
    boolean isPalin(String str) {
        for(int i =0; i < str.length(); i++) {
            if(str.charAt(i) != str.charAt(str.length()-1-i)) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

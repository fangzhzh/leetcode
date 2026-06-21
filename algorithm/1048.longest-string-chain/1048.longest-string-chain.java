/*
 * @lc app=leetcode id=1048 lang=java
 * @lcpr version=30403
 *
 * [1048] Longest String Chain
 */

import java.util.Arrays;

// @lc code=start
class Solution {
    public int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int maxChain = 1;
        Arrays.sort(words, (a, b) -> a.length() - b.length());

        for(int i = 1; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(predecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxChain = Math.max(maxChain, dp[i]);
        }
        return maxChain;
    }

    private boolean predecessor(String w1, String w2) {
        if (w1.length() + 1 != w2.length()) {
            return false;
        }
        int i = 0, j = 0;
        while (i < w1.length() && j < w2.length()) {
            if (w1.charAt(i) == w2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == w1.length();
    }
}
// @lc code=end



/*
// @lcpr case=start
// ["a","b","ba","bca","bda","bdca"]\n
// @lcpr case=end

// @lcpr case=start
// ["xbc","pcxbcf","xb","cxbc","pcxbc"]\n
// @lcpr case=end

// @lcpr case=start
// ["abcd","dbqca"]\n
// @lcpr case=end

 */

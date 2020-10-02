/*
 * @lc id=139 lang=java
 *
 * [139] Word Break
 */

// @lc code=start
/**
 * 
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

 /**
  * ## Solution brute force
  * Timne Limnit Exceeeded: as expected, too many recalcultion
  */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp[i] = dp[i-n] && s.substring(i, i+n) in workDict
        // backpack
        // System.out.println(s);
        boolean result = false;
        for(int i = 0; i < s.length(); i++) {
            String left = s.substring(0, i+1);
            // System.out.println("cheking " + left);
            if(wordDict.contains(left)) {
                // System.out.println(left + " again");
                if(i == s.length()-1) {
                    return true;
                }

                result |= wordBreak(s.substring(i+1), wordDict);
            }
        }
        return result;    }
}
// @lc code=end


/**
 * ## solution 2, DP
 * - dp(n) represents whether the s[0, n] is segmented
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
               // dp[i] = dp[i-n] && s.substring(i, i+n) in workDict
        // backpack
        int len = s.length();
        boolean []dp = new boolean[len+1];
        dp[0]=true;

        for(int i = 1; i <= len; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}


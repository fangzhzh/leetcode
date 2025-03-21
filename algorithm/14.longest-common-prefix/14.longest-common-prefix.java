/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 */

// @lc code=start
// TC O(n log n), n = strs.length
class Solution {
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length-1];
        int i = 0;
        for(; i < first.length(); i++) {
            if(first.charAt(i) != last.charAt(i)) break;
        }
        return first.substring(0, i);
    }
}
// @lc code=end


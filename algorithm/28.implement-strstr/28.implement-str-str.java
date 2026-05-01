/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 */

// @lc code=start
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) {
            return 0;
        }
        int m = haystack.length(), n = needle.length();
        for(int i = 0; ; i++) {
            for(int j = 0;;j++) {
                if(j == needle.length()) {
                    return i;
                }

                if(i + j == m) {
                    return -1;
                }
                if(haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }

            }

        }
    }
}
// @lc code=end


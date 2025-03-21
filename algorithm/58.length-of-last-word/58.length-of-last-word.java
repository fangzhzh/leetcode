/*
 * @lc app=leetcode id=58 lang=java
 *
 * [58] Length of Last Word
 */

// @lc code=start
// TC O(n) length of last word
class Solution {
    public int lengthOfLastWord(String s) {
        // int s = 0;
        for(int i = s.length()-1; i>=0; i--) {
            if(s.charAt(i) != ' ') {
                int left = i;
                while(left >= 0) {
                    if(s.charAt(left) == ' ') {
                        break;
                    }
                    left--;
                }

                return i-left ;
            }
        }
        return s.length();
    }
}
// @lc code=end


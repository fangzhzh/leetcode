/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

 /**
  * Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
  */

 /**
  * scan the string, and update the len
  * trace is occurence of every char
  * two pointers to track current range,
  * counter is whether current substr valid
  * when counter say it's a valid range, expand / shrink thr untial it's validity changed
  */
// @lc code=start
class Solution {
    public int lengthOfLongestSubstring(String s) {
        char[] sChar = s.toCharArray();
        char[] track = new char[256];
        int left = 0, right = 0, len = 0, count = 0;
        while(right < sChar.length) {
            if(track[sChar[right]] > 0) {
                count++;
            }
            track[sChar[right]]++;
            right++;
            while(count > 0) {
                if(track[sChar[left]] > 1) {
                    count--;
                }
                track[sChar[left]]--;
                left++;
            }
            if(right - left > len) {
                len = right - left;
            }
        }
        return len;
        
    }
}
// @lc code=end


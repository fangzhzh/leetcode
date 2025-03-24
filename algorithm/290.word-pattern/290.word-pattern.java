/*
 * @lc app=leetcode id=290 lang=java
 *
 * [290] Word Pattern
 */

// @lc code=start
class Solution {
    // TC O(n log n)
    public boolean wordPattern(String pattern, String s) {
        Map<String, Character> map = new HashMap<>();
        String[] words = s.split(" ");
        if(words.length != pattern.length()) {
            return false;
        }
        int i = 0;
        for(String word : words) {
            if(i >= pattern.length()) {
                return false;
            }
            char c = pattern.charAt(i);
            if(!map.containsKey(word)) {
                if(map.containsValue(c)) {
                    return false;
                }
                map.put(word, c);
            } else {
                if(map.get(word) != c) {
                    return false;
                }
            }
            i++;
        }
        return true;
    }
}
// @lc code=end


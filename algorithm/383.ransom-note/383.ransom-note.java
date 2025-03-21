/*
 * @lc app=leetcode id=383 lang=java
 *
 * [383] Ransom Note
 */

// @lc code=start
// TC O(max(m, n))
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for( char c : ransomNote.toCharArray()) {
            map.put(c, map.getOrDefault(c,0)-1);
            if(map.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end


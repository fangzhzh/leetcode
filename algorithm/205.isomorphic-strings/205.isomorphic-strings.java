/*
 * @lc app=leetcode id=205 lang=java
 *
 * [205] Isomorphic Strings
 */

// @lc code=start
class Solution {
    public boolean isIsomorphic(String s, String t) {
        int m = s.length(), n = t.length();
        if(m!=n) return false;
        Map<Character, Character> map = new HashMap<>();
        for(int i = 0; i < m; i++) {
            char a = s.charAt(i);
            char b = t.charAt(i);
            if(map.containsKey(a)) {
                if(!map.get(a).equals(b)) {
                    return false;
                }
            } else {
                if(map.containsValue(b)) {
                    return false;
                }
                map.put(a, b);
            }
        }
        return true;
    }
}
// @lc code=end


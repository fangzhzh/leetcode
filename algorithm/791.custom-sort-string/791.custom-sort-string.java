/*
 * @lc app=leetcode id=791 lang=java
 *
 * [791] Custom Sort String
 */

/**
 *S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 */
// @lc code=start
/**
 * two ways
 *  - bucket/map for counting T, scan order S and fill the string
 *  - Scan S to determin order, then sort T
 */
class Solution {
    public String customSortString(String S, String T) {
        Map<Character, String> map = new HashMap<>();
        for(int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            map.put(c, map.getOrDefault(c, "")+c);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S.length(); i++) {
            sb.append(map.getOrDefault(S.charAt(i), ""));
            map.remove(S.charAt(i));
        }
        for(char c : map.keySet()) {
            sb.append(map.get(c));
        }
        return sb.toString();
    }
}
// @lc code=end



/*
 * @lc app=leetcode id=953 lang=java
 *
 * [953] Verifying an Alien Dictionary
 */


/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 * 
 *  */ 
// @lc code=start

/**
 * mapping order to int,
 * so that just mapping words to int
 * 
 * 
 */
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }
        
        for(int i = 0; i < words.length-1; i++) {
            String s = words[i];
            String t = words[i+1];
            boolean finished = false;
            for(int j=0, k=0; j < s.length() && k < t.length();j++,k++) {
                if(s.charAt(j) != t.charAt(k)) {
                    if(map.get(s.charAt(j)) > map.get(t.charAt(k))) {
                        return false;
                    } else {
                        finished = true;
                         break;
                    }
                }
            }
            if(!finished && s.length() > t.length()) {
                return false;
            }
        }
        return true;
    }
    
}
// @lc code=end



/**
 * 
 * to optimize the solution, a new helper function make the compare logic cleaner
 * 
 * 
 */
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
    Map<Character, Integer> map = new HashMap<>();
    for(int i = 0; i < order.length(); i++) {
        map.put(order.charAt(i), i);
    }
    
    for(int i = 0; i < words.length-1; i++) {
        String s = words[i];
        String t = words[i+1];
        if(!compare(map, s, t)) return false;
    }
    return true;
}

boolean compare(Map<Character, Integer> map, String s, String t) {
    for(int j=0, k=0; j < s.length() && k < t.length();j++,k++) {
        if(s.charAt(j) != t.charAt(k)) {
            if(map.get(s.charAt(j)) > map.get(t.charAt(k))) {
                return false;
            } else {
                return true;
            }
        }
    }
    return s.length() <= t.length();
}

}
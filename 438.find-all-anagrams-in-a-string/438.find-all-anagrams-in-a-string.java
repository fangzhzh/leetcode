/*
 * @lc app=leetcode id=438 lang=java
 *
 * [438] Find All Anagrams in a String
 */

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 *  */ 


// @lc code=start

/**
 * sub string slicing window problem
 * 
 * I was just one step away from the correct answer
 * 
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        
        int cnt = 0;
        int[] track = new int[26];
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        
        for(char v : pChars) {
            track[v-'a']++;
            cnt++;
        }
        
        int left = 0, right = 0;
        while(right < sChars.length){
            track[sChars[right]-'a']--;
            if(track[sChars[right]-'a'] >= 0) {
                cnt--;
            }
            right++;
            while(cnt == 0) {
                track[sChars[left]-'a']++;
                if(track[sChars[left]-'a'] > 0) {
                    cnt++;
                }
                if(right - left == pChars.length) {
                    ans.add(left);
                }
                left++;
            }
        }
        return ans;
    }
}
// @lc code=end


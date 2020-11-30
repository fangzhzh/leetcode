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

/**
 * Slicing window is good, and the first thought once you know it
 *
 * but how about a good brutal force? 
 *
 * for 0..sLen-pLen, it checks every substring(i, i+pLen) whether is Anagram
 * not fance, but a clear solution
 * 
 * The gem of the is Anagram is still the track to track down the occurrency of every char
 * 
 * 
 */

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // intutitive
        int sLen = s.length(), pLen = p.length();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < sLen-pLen+1; i++) {
            String sp = s.substring(i, i+pLen);
            if(isAnagram(sp, p)) {
                ans.add(i);
            }
        }
        return ans;
    }

    boolean isAnagram(String a, String b) {
        int[]track = new int[128];
        for(int i = 0;i < a.length(); i++) {
            track[a.charAt(i)]++;
            // map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0)++);
        }
        for(int i = 0;i < b.length(); i++) {
            track[b.charAt(i)]--;
            if(track[b.charAt(i)] < 0) return false;
        }
        return true;

    }

    boolean isAnagram(String a, String b) {
        int[]tracka = new int[128];
        for(int i = 0;i < a.length(); i++) {
            tracka[a.charAt(i)]++;
            // map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0)++);
        }
        int[]trackb = new int[128];
        for(int i = 0;i < b.length(); i++) {
            trackb[b.charAt(i)]--;
        }
        return Arrays.equals(tracka, trackb);

    }

}

/**
 * a slightly improved version is keep a track for long string and compare in place
 *
 * the tracks keeps a track for s[i, j)
 *  - if valid, add i to ans
 *  - if no valid, slicing window left to i+1, and update traces accordingly
 */


class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // intutitive
        int sLen = s.length(), pLen = p.length();
        List<Integer> ans = new ArrayList<>();
        int[] tracks = new int[26];
        int[] trackp = new int[26];
        for(int i = 0; i < pLen; i++) {
            trrackp[p.charAt(i)]++;
        }
        int j = 0;
        for(int i = 0; i < sLen; i++) {
            while(j < sLen && j-i+1 < pLen)
                tracks[s.charAt(j)]++;
            }
            if(Arrays.equals(tracks, trackp)) {
                ans.add(i);
            }
            tracks[s.chartAt(i)]--;
        }
        return ans;
    }
}


/**
 * Still want to mention my native version after fail to make sense of the slicing windows one
 * idea is the same, but checking anagram by set
 * 
 * 
 * It fails in such ways
 *      - set can't test the case: 'aaab', 'aab', because once you remove the a from set,
 *       -  it will not match the second a
 *      - create a hshset every time
 *
 *
 *  Hashset doesn't fit the occurency of chars in a string, especially fail duplicate scenrio.
 */

 class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // intutitive
        int sLen = s.length(), pLen = p.length();
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < sLen-pLen+1; i++) {
            String sp = s.substring(i, i+pLen);
            if(isAnagram(sp, p)) {
                ans.add(i);
            }
        }
        return ans;
    }

    boolean isAnagram(String a, String b) {
        Set<Character> set = new HashSet();
       for(int i = 0;i < b.length(); i++) {
            set.add(b.charAt(i));
        }   
        for(int i = 0;i < a.length(); i++) {
            if(set.contains(a.charAt(i))) {
                set.remove(a.charAt(i));
            } else {
                return false;
            }
        }
        return true;

    }
 }
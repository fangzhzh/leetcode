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


// Solution 2
// same idea, but different implementation,
// valid 标准不一样，expand的不一样
class Solution {
    public int lengthOfLongestSubstring(String s) {

        int left = 0, right = 0, count = 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        while(right < s.length()) {
            // expand
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.getOrDefault(c, 0) >  1) {
                count++;
            }

            if(count > 0) {
                char cl = s.charAt(left);
                map.put(cl, map.get(cl)-1);
                if(map.get(cl) > 0) {
                    count--;
                }
                // shrink
                left++;
            }
            right++;
            ans = Math.max(ans, right - left);
        }
        return ans;

    }
}

// Version 3 
// also slicing window, using set to count duplication, 
// left, right to keep the windows,
// I thought about a solution like this, but give up half way
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0, right = 0;
        int ans = 0;
        Set<Character> set = new HashSet<>();
        while(right < s.length()) {
            char c = s.charAt(right);
            if(!set.contains(c)) {
                set.add(c);
                ans = Math.max(ans, right - left + 1);
            } else {
                while(set.contains(c)) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(c);
            }
            right++;
        }
        return ans;
    }
}
// @lc code=end

// Version 3 
// also slicing window, using set to count duplication, 
// left, right to keep the windows,
class Solution {
    // tc O(n)
    // sc O(1)
    public int lengthOfLongestSubstring(String s) {
        // [l,r] 
        // prove: s[k] == s[r+1], max(s[0,r+1]) = max(s[k+1, r+1])
        // 如果s[k] == s[r+1], 那么包含s[r+1]的最长字串必然从k+1开始，因为如果包含s[k]，那么s[r+1]就重复了
        // 左边界只进不退，因为被淘汰的点，已经不能够再产生更长的串
        // 本质上这是一个单调性论证：当右端点向右扩展遇到冲突时，左端点必须前进，而前进跳过的所有候选起点都是"已被淘汰"的。


        int l = 0, r = 0, len = 0;
        Set<Character> set = new HashSet<>();
        while(r < s.length()) {
            char c = s.charAt(r);
            while(set.contains(c)) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(c);
            len = Math.max(len, r - l + 1);
            r++;
        }
        return len;
    }
}


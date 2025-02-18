/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */
/
// @lc code=start
/***
 * 
 * ADOBECODEBANC, 
 * ABC
 * 
   end:0,A,count2,map:0,A
' +
  'end:1,D,count2,map:-1,AD
' +
  'end:2,O,count2,map:-1,ADO
' +
  'end:3,B,count1,map:0,ADOB
' +
  'end:4,E,count1,map:-1,ADOBE
' +
  'end:5,C,count0,map:0,ADOBEC
' +
  'start:0,A,count1,map:1,minStart0,minLength6,ADOBEC
' +
  'moving start to:1,D
' +
  'end:6,O,count1,map:-2,DOBECO
' +
  'end:7,D,count1,map:-2,DOBECOD
' +
  'end:8,E,count1,map:-2,DOBECODE
' +
  'end:9,B,count1,map:-1,DOBECODEB
' +
  'end:10,A,count0,map:0,DOBECODEBA
' +
  'start:1,D,count0,map:-1,minStart0,minLength6,DOBECODEBA
' +
  'moving start to:2,O
' +
  'start:2,O,count0,map:-1,minStart0,minLength6,OBECODEBA
' +
  'moving start to:3,B
' +
  'start:3,B,count0,map:0,minStart0,minLength6,BECODEBA
' +
  'moving start to:4,E
' +
  'start:4,E,count0,map:-1,minStart0,minLength6,ECODEBA
' +
  'moving start to:5,C
' +
  'start:5,C,count1,map:1,minStart0,minLength6,CODEBA
' +
  'moving start to:6,O
' +
  'end:11,N,count1,map:-1,ODEBAN
' +
  'end:12,C,count0,map:0,ODEBANC
' +
  'start:6,O,count0,map:0,minStart0,minLength6,ODEBANC
' +
  'moving start to:7,D
' +
  'start:7,D,count0,map:0,minStart0,minLength6,DEBANC
' +
  'moving start to:8,E
' +
  'start:8,E,count0,map:0,minStart8,minLength5,EBANC
' +
  'moving start to:9,B
' +
  'start:9,B,count1,map:1,minStart9,minLength4,BANC
' +
  'moving start to:10,A

 */

/**
 * 
 * Most 'substring' problems in notes.
 * 
 * track[] to track occurrence of char from target needed to find in s.
 * trace[a] == 1 means one a is not found in s yet.
 * This end, start scan goes one scan, so the trace[char] will be
 *  `--` in the end scan, ++ in the start scan
 * 
 * The `end` scan to find next valid s substring contains all t
 * The first scan to squeece out the leftest char in s belongs to t 
 * which is next invalid substring
 * 
 * We can get the minLen and minStart in the start loop
 */
class Solution {
    public String minWindow(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int count = 0;
        int []track = new int [256];
        for(char c: tChar) {
          track[c]++;
          count++;
        }

        int start = 0, end = 0, minLen = Integer.MAX_VALUE, minStart = 0;;
        while(end < sChar.length) {
          if(track[sChar[end]] > 0) {
            count--;
          }
          track[sChar[end]]--;
          while(count == 0) {
            if(end - start + 1 < minLen) {
              minLen = end - start + 1;
              minStart = start;
            }
            track[sChar[start]]++;
            if(track[sChar[start]] > 0) {
              count++;
            }
            start++;
          }
          end++;

        }
        if(minStart+minLen > sChar.length) return "";
        return s.substring(minStart, minStart+minLen);

    }
}
// @lc code=end


/**
 * second try
 * - first, I forget the minimum, and just want to return s.substring(left, right);
 * - then, then small length is (right-left+1); right++; if right ++ behind the second while loop
 * - otherwise, it gonna be right++, minLen = (right - left)
 */

class Solution {
  public String minWindow(String s, String t) {
      int[] state = new int[256];
      int cnt = 0;
      for(int i = 0; i < t.length(); i++) {
          state[t.charAt(i)]++;
          cnt++;
      }
      int left = 0, right = 0;
      int minLen = Integer.MAX_VALUE, minStart = 0;
      while(right < s.length()) {
          char c = s.charAt(right);
          if(state[c] > 0) {
              cnt--;
          }
          state[c]--;
          while(cnt==0) {
              if(right - left + 1 < minLen) {
                  minLen = right - left + 1;
                  minStart = left;
              }
              char cl = s.charAt(left);
              state[cl]++;
              if(state[cl] > 0) {
                  cnt++;
              }
              left++;
          }
          right++;
      }
      if(minStart+minLen > s.length()) return "";
      return s.substring(minStart, minStart + minLen);
  }
}
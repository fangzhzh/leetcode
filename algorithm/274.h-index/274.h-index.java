/*
 * @lc app=leetcode id=274 lang=java
 *
 * [274] H-Index
 */

// @lc code=start
// understand, induction, 
class Solution {
    public int hIndex(int[] citations) {
        int h = 0;
        Arrays.sort(citations);
        for(int i = citations.length-1; i>=0; i--) {
            int cite = citations[i];
            int len = citations.length-1-i+1;
            if(cite > len) {
                 h = Math.max(len, h);
            } else {
                h = Math.max(cite, h);
            }
        }
        return h;
    }
}

// version 2, idea is built citation cnt in the first round iteration, 
// check citation of idx >= idx in the second iteration
class Solution {
    // TC O(n)
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] cnt = new int[len + 1]; // citation count at i
        for (int i = 0; i < len; i++) {
            if (citations[i] >= len) {
                cnt[len]++;
            } else {
                cnt[citations[i]]++;
            }
        }

        int total = 0;
        for (int i = len; i > 0; i--) {
            total += cnt[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=670 lang=java
 *
 * [670] Maximum Swap
 */

 /**
  * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
  */


/**
 * Brutal force
 * Worst case O(n), O(n^2)
 * 
 * Some rare case to consider:
 * 1. two same max digit for swap: 1993: fixed by chars[j] >= max
 * 2. same biggest digit for scan: 98368, fix by max != chars[i]
 */  
// @lc code=start
class Solution {
    public int maximumSwap(int num) {
        String str = String.valueOf(num);
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            char max = chars[i];
            int idx = i;
            for(int j = i+1; j < chars.length; j++) {
                if(chars[j] >= max) {
                    max = chars[j];
                    idx = j;
                }
            }
            if(idx != i && max != chars[i]) {
                swap(chars, i, idx);
                return Integer.valueOf(String.valueOf(chars));
            }
        }
        return num;
    }
    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }
}
// @lc code=end


for(int i = 0; i < len; i++) {
	
}
/**
 * better solution O(n)
 * I thought about the Map solution, but found the buckets in leetcode.
 * bucket is O(n*9) = O(n)
 * If I use map, then I have to loop the keySet which is n, then O(n^2) again
 */

class Solution {
    public int maximumSwap(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        int []buckets = new int[10];
        for(int i = 0; i < chars.length; i++) {
            buckets[chars[i]-'0'] = i;
        }
        for(int i = 0; i < chars.length; i++) {
            for(int j = 9; j >= 0; j--) {
                if(j <= (chars[i]-'0')) {
                    break;
                }
                if(buckets[j] > i) {
                    swap(chars, i, buckets[j]);
                    return Integer.valueOf(String.valueOf(chars));
                }
            }
        }
        return num;

    }
}
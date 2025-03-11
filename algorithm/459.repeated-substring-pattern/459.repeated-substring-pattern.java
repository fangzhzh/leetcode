/*
 * @lc app=leetcode id=459 lang=java
 *
 * [459] Repeated Substring Pattern
 */

// @lc code=start
class Solution {
    // TC O(n^2) because of 
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if(n<2) {
            return false;
        }
        
        for(int j = 0; j < n/2; j++) {
            int size = j+1;
            if(n%size != 0) {
                continue;
            }
            StringBuilder sb = new StringBuilder();
            int mul = n/size;
            String sub = s.substring(0, j+1);
            while(mul>0) {
                sb.append(sub);
                mul--;
            }
            if(sb.toString().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end


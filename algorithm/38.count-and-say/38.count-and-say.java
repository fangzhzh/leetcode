/*
 * @lc app=leetcode id=38 lang=java
 *
 * [38] Count and Say
 */

// @lc code=start
// TC(n*len(k))
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        while(n-1>0) {
            StringBuilder sp = new StringBuilder();
            int i = 0;
            while(i < s.length()) {
                int cnt = 0;
                char c=s.charAt(i);
                while(i < s.length() && s.charAt(i)==c) {
                    cnt++;
                    i++;
                }
                sp.append(cnt);
                sp.append(c);
            }
            s = sp.toString();
            n--;
            if(n==1) return s;
        }
        return s;

    }
}
// @lc code=end


/*
 * @lc app=leetcode id=151 lang=java
 *
 * [151] Reverse Words in a String
 */

// @lc code=start
class Solution {
    // TC O(length)
    public String reverseWords(String s) {
        if(s.length() == 0) return "";

        // 1 manual split and stack
        // return manualSplit(s);
        // 2, split and the same
        String[] strs = s.split(" ");

        StringBuilder sb = new StringBuilder();
        for(int i = strs.length-1; i>=0; i--) {
            if(!strs[i].isEmpty()) {
                if(sb.length() > 0) {
                    sb.append(' ');
                }
                sb.append(strs[i]);
            }
        }
        return sb.toString();

    }

    private String manualSplit(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') {
                if(sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        if(sb.length() > 0) {
            stack.push(sb.toString());
        }

        sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
            if(!stack.isEmpty()) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }
}
// @lc code=end


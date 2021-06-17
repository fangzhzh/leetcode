/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */

// @lc code=start
class Solution {
// time O(n) n is the length of the string
    // space O(n)
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch(c) {
                case '(':
                case '[':
                case '{':
                stack.push(c);
                break;
                case ')':
                if(stack.isEmpty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
                break;
                case '}':
                if(stack.isEmpty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
                break;
                case ']':
                if(stack.isEmpty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
                break;
            }
        }
        return stack.isEmpty();
    }
    
}
// @lc code=end


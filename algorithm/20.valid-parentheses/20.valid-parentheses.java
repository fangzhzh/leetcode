/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */

import java.util.Stack;

// @lc code=start
class Solution {
    // 方法 1：神仙解法（压入反向括号法）
    // 时间复杂度: O(n), 空间复杂度: O(n)
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}

/* 
class Solution_Old {
    // 方法 2：经典的 switch-case 写法（正向匹配）
    // 这是你之前写的逻辑非常严密的版本
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
                    if(stack.isEmpty() || stack.peek() != '(') return false;
                    stack.pop();
                    break;
                case '}':
                    if(stack.isEmpty() || stack.peek() != '{') return false;
                    stack.pop();
                    break;
                case ']':
                    if(stack.isEmpty() || stack.peek() != '[') return false;
                    stack.pop();
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }
}
*/
// @lc code=end

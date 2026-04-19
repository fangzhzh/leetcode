/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */

import java.util.Stack;

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
// @lc code=end


// 算法不在可以的去用switch case了，因为逻辑上其实if else也很清晰了
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                char cc = stack.pop();
                if((c == ')' && cc == '(' )
                || (c == ']' && cc == '[')
                || (c == '}' && cc == '{')
                ) {
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

// 最优解法（神仙解法）没有写入这里，看看下次是否能做到
/*
 * @lc app=leetcode id=150 lang=java
 *
 * [150] Evaluate Reverse Polish Notation
 */

// @lc code=start
// TC O(n)
// SC O(n)
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for(String word : tokens) {
            switch(word) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int a1 = stack.pop();
                    int b1 = stack.pop();
                    stack.push(b1-a1);
                    break;
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "/":
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push((int)Math.floor(b/a));
                    break;
                default:
                    stack.push(Integer.valueOf(word));
                    break;
            }
        }
        return stack.pop();

    }
}
// @lc code=end


/*
 * @lc app=leetcode id=71 lang=java
 *
 * [71] Simplify Path
 */

// @lc code=start
// TC O(n)
// SC O(n)
class Solution {
    public String simplifyPath(String path) {
        String[] words = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String word : words) {
            switch(word) {
                case "":
                case " ":
                case "\n":
                case ".":
                case "/":
                    break;
                case "..":
                    if(!stack.isEmpty()) {
                        stack.pop();
                        // stack.pop();
                    }
                    break;
                default:
                    stack.push(word);
                    break;
            }
        }
        if(stack.isEmpty()) {
            return "/";
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, '/');
        }
        return sb.toString();
    }
}
// @lc code=end


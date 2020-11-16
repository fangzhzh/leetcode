/*
 * @lc app=leetcode id=301 lang=java
 *
 * [301] Remove Invalid Parentheses
 */

// @lc code=start

/***
 * 
 * 
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */

/**
 * 
 * BFS, don't need the recursive and level bfs, only get the mimumum edit
 * for(i = 0; i < str.length; i++) {
 *  String = str.substring(0, i) + str.substring(i+1);
 * }
 * 
 * a way to remove ith char
 * 
 * 
 * also, isValid is a good way to check validation of parentheses
 *  */ 
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null) return ans;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;

        while(!queue.isEmpty()){
            String str = queue.poll();
            if(isValid(str)) {
                ans.add(str);
                found = true;
            }
            if(found) continue;
            
            for(int i = 0 ; i < str.length(); i++) {
                if(str.charAt(i) != '(' && str.charAt(i) != ')') continue;
                String t = str.substring(0,i) + str.substring(i+1);
                if(!visited.contains(t)) {
                    visited.add(t);
                    queue.offer(t);
                }
            }

        }
        return ans;
    }
    boolean isValid(String s) {
        int count = 0;
    
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '(') count++;
        if (c == ')' && count-- == 0) return false;
      }
    
      return count == 0;
    }

}
// @lc code=end


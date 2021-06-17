/*
 * @lc app=leetcode id=282 lang=java
 *
 * [282] Expression Add Operators
 */

/**
 * 
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
 * 
 * 
 *  */ 
// @lc code=start

/**
 * bfs typical
 * - tricky parts
 *      + multed
 *      + long parse, singnature trail n err,
 * If it's only the single digit operation, it's pure bfs and math
 * 
 * But adding the multile didit number, it's ugly
 */
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if(num==null ||num.length()==0) {
            return ans;
        }
        helper(ans, "", num, target, 0, 0, 0);
        return ans;
    }
    private void helper(List<String> ans,
    String cur,
    String num,
    int target,
    long value,
    int idx, 
    long multed
    ) {
        if(idx >= num.length()) {
            if(value == target) {
                ans.add(cur);
            }
            return;
        }
        for(int i = idx; i < num.length(); i++){
            if(i != idx && num.charAt(idx) == '0') break;
            long n = Long.valueOf(num.substring(idx, i + 1));
            if(idx == 0) {
                helper(ans, cur+n, num, target, n, i+1, n);
            } else {
                helper(ans, cur+"+"+n, num, target, value+n, i+1, n);
                helper(ans, cur+"-"+n, num, target, value-n, i+1, -n);
                helper(ans, cur+"*"+n, num, target, value-multed + multed*n, i+1, multed * n);
            }
        }
    }
}
// @lc code=end


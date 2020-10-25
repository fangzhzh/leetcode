/*
 * @lc app=leetcode id=216 lang=java
 *
 * [216] Combination Sum III
 */

 /**
  * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

 

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
*/




/***
 * Typical backpacking, restriction is on the slot, but it's one parameter away
 * 
 * 
 */
// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<>(), k, n, 1);
        return ans;
    }
    private void helper(List<List<Integer>> ans,
    List<Integer> list,
    int slot, 
    int target,
    int start) {
        if(target == 0 && slot == 0) {
            ans.add(new ArrayList<Integer>(list));
            return;
        }
        if(target < 0 || slot < 0) {
            return;
        }
        for(int i = start; i < 10; i++) {
            list.add(i);
            helper(ans, list, slot-1, target - i, i+1);
            list.remove(list.size()-1);
        }
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 */

// @lc code=start
/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
 */

 /**
  * The solution takes me some time come up
  * 
  * However the gem of the crowns of the backpacking problem is 
        list.add(candidates[idx]);
        bfs(ans, list, candidates,  target-candidates[idx], idx);
        list.remove(list.size()-1);
  * 
  * With this logic, 
  * We got
        list.add(candidates[idx]);
        bfs(ans, list, candidates,  target-candidates[idx], idx);
        list.remove(list.size()-1);
        bfs(ans, list, candidates,  target, idx+1);

  * or we got
  * for(int i = 0; i < candidates.size; i++) {
        list.add(candidates[i]);
        bfs(ans, list, candidates,  target-candidates[i], i);
        list.remove(list.size()-1);
  }
  */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<>(), candidates, target, 0);
        return ans;
    }
    private void helper(List<List<Integer>> ans, List<Integer> list, int[] candidates, int target, int idx) {
        if(target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if(target < 0) {
            return;
        }
        if(idx >= candidates.length) {
            return;
        }

        list.add(candidates[idx]);
        bfs(ans, list, candidates,  target-candidates[idx], idx);
        list.remove(list.size()-1);
        bfs(ans, list, candidates, target, idx+1);
        
    }
}
// @lc code=end


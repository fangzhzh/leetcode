/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */

 /***
  * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

 

Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8
Output: 
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
*/

/**
 * Regarding explaination in 39, the for loop is easier to understand.
 * Firstly, I'm confused at the statement:
  if(i>idx && candidates[i] == candidates[i-1]) continue;
 * why we skip candidate[i] if it equals to candidates[i-1], it doesn't make much sense
 * what if the candidate[i] will generate another different pair of combination.
 * 
 * But checking with the discusstion, one of the solution use the varable idx as start
 * if(i>start && candidates[i] == candidates[i-1]) continue;
 * 
 * Then the explaination goes like: 
 * helper(List<List<Integer>> ans, List<Integer> list,
    int[] candidates, int target, int start) 
 *
 * it means, find all the combintation from candidates sum as target
 * **from position start**, then it makes all sense
 * 
 * Take an example [1,1,2,3,4] target 4
 * The array is sorted, combination from index 0 will definitely contains all combination from index 1.
 * from idx 0: [1,1,2], [1,3]
 * from idx 1: [1,3]
 */
// @lc code=start
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        helper(ans, new ArrayList<>(), candidates, target, 0);
        return ans;
    }
    private void helper(List<List<Integer>> ans, List<Integer> list,
    int[] candidates, int target, int start) {
        if(target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if(target < 0) {
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            if(i>start && candidates[i] == candidates[i-1]) continue;
            list.add(candidates[i]);
            helper(ans, list, candidates, target-candidates[i], i+1);
            list.remove(list.size()-1);
        }
    }
}
// @lc code=end


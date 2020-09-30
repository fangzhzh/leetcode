/*
 * @lc id=78 lang=java
 *
 * [78] Subsets
 */
/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

 */

 /**
  * ## analysis:
    - typical backtracking
    - other answers: add and traversal
    - this one: traversal then add when finish
  */
// @lc code=start
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        helper(result, nums, current, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, int[] nums, 
        List<Integer> current, int idx) {
        if(idx >= nums.length) {
            result.add(current);
            return;
        }
        helper(result, nums, new ArrayList<>(current), idx + 1);
        current.add(nums[idx]);
        helper(result, nums, new ArrayList<>(current), idx + 1);
    }
}
// @lc code=end



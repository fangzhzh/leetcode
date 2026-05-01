/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

 /**
  * 
  Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
  */

/**
 * ## analysis:
 *  - it's hard to get right.
 *  - but a example will make it a little bit easier
 *  - 1,2,2,2,2,2
 *      + normally the whole process is 
 *          - add current, continue to next
 *          - remove current, continue to next
 *      + but duplicated nums in this nums
 *      + if the next element is equal to the current one, 
 *      + we don't do the remove current and add the next because it will introduces duplicate sets
 *      + we just find the next non-equal idx
 *  - if this part is clear, the coding is clear to write
 */
// @lc code=start
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    private void helper(List<List<Integer>>result, List<Integer> current,
            int[] nums, int start) {
                if(start >= nums.length) {
                    result.add(current);
                    return;
                }
                current.add(nums[start]);
                helper(result, new ArrayList<>(current), nums, start+1);
                current.remove(current.size()-1);
                start++;
                while(start<nums.length && nums[start] == nums[start-1]) {
                    start++;
                }
                helper(result, new ArrayList<>(current), nums, start);
    }
}
// @lc code=end



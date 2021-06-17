public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<Integer>(), nums, 0);
        return result;
    }
    void backtracking(List<List<Integer>> result, List<Integer> tmp, int[] nums, int start) {
        result.add(new ArrayList<>(tmp));
        for(int i = start; i < nums.length; ++i) {
            tmp.add(nums[i]);
            backtracking(result, tmp, nums, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}

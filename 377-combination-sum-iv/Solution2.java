public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int len = nums.length, sum = 0;
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, nums, new ArrayList<>(), target);
        return result.size();
    }
    void backtracking(List<List<Integer>> result, int[] nums, List<Integer> b, int target) {
        if(target == 0) {
            result.add(new ArrayList<>(b));
            // System.out.println(b.toString());
        }
        if(target < 0){
            return;
        }
        for(int i = 0; i < nums.length; ++i) {
            b.add(nums[i]);
            backtracking(result, nums, b, target-nums[i]);
            b.remove(b.size()-1);
        }
    }
}

public class Solution {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if(nums.length == 0) return result;
		Arrays.sort(nums);
		backtrack(result, new ArrayList<>(), nums, 0);
		return result;
	}

	void backtrack(List<List<Integer>> result, List<Integer> tmp, int[] nums, int start) {
		result.add(new ArrayList(tmp));
		for(int i = start; i < nums.length; ++i) {
			if(i>start && nums[i] == nums[i-1]) continue;
			tmp.add(nums[i]);
			backtrack(result, tmp, nums, i+1);
			tmp.remove(tmp.size()-1);
		}
	}
}

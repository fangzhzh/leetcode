public class Solution {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		backtrack(list, new ArrayList(), nums);
		return list;
	}
	void backtrack(List<List<Integer>> list, List<Integer> tmp, int[] nums ) {
		if(tmp.size() == nums.length) {
			list.add(new ArrayList<>(tmp));
		} else {
			for(int i=0; i < nums.length; ++i) {
				if(tmp.contains(nums[i])) continue;
				tmp.add(nums[i]);
				backtrack(list, tmp, nums);
				tmp.remove(tmp.size()-1);
			}
		}
	}

}

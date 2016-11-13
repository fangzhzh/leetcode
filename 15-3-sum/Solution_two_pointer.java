// two pointers, o(n^2)
public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		for(int k = 0; k < nums.length; ++k) {
			if(k>0 && nums[k] == nums[k-1]) continue;
			int j = nums.length-1;
			for(int i=k+1; i<nums.length;++i) {
				if(i<j&&i>k+1&&nums[i] == nums[i-1]) continue;
				for(; j>i;--j) {
					if(j<nums.length-1 && nums[j]==nums[j+1]) continue;
					if(nums[i]+nums[j]+nums[k]>0) continue;
					else if(nums[i]+nums[j]+nums[k]<0) break;
					else list.add(Arrays.asList(nums[k],nums[i],nums[j]));
				}
			}
		}
		return list;
	}
}


public class Solution {
	public int combinationSum4(int[] nums, int target) {
		int[] d = new int[target+1];
		d[0]=1;
		Arrays.sort(nums);
		for(int i=1;i<d.length;++i) {
			for(int j=0;j<nums.length && nums[j] <= i; ++j){
				d[i] += d[i-nums[j]];
			}
		}
		return d[target];
	}
}

public class Solution {
	public int threeSumSmaller(int[] nums, int target) {
		int sum = 0;
		Arrays.sort(nums);
		for(int i = 0; i < nums.length; ++i) {
			for(int j=i+1;j<nums.length;++j){
				for(int k=j+1;k<nums.length;++k) {
					int tmp = nums[i]+nums[j]+nums[k];
					if(tmp < target) {
						sum++;
					} else {
						break;
					}
				}
			}
		}
		return sum;
	}
}


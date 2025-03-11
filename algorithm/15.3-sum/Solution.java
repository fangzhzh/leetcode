public class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		for(int i = 0; i < nums.length-2; ++i){
			if(i>0 && nums[i] == nums[i-1]) continue;

			int k = nums.length-1; 
			for(int j = i+1;j<nums.length;++j){
				if(j<k&&j>i+1&&nums[j] == nums[j-1]) continue;
				for(; k > j; --k) {
					if(k<nums.length-1 && nums[k] == nums[k+1]) continue;
					if(nums[j]+nums[k]+nums[i]<0) break;
					else if(k!= j && nums[j]+nums[k]+nums[i] == 0) {
						List<Integer> l = Arrays.asList(nums[i],nums[j],nums[k]);
						result.add(l);
					}  
				}
			}
		}
		return result;
	}
}

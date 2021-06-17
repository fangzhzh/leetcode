public class Solution {
    public int removeDuplicates(int[] nums) {
		if(nums.length == 0) return 0;
		int j=0;
		for(int i = 0; i < nums.length-1; ++i) {
				if(nums[i] == nums[i+1]) {
					int pivot = i+1;
					j = i+2;
					while(j<nums.length-1 && nums[i] == nums[j]) j++;
					while(j<nums.length) nums[pivot++] = nums[j++];
				}
			}
		return j;
        
    }
}

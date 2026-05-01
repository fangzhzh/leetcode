public class Solution {
    public int removeDuplicates(int[] nums) {
        int cnt = 0;
        for(int i = 0; i+cnt < nums.length-1; ++i) {
            if(nums[i] != nums[i+1]) {
                continue;
            }
            if(i+2+cnt < nums.length && nums[i+1] == nums[i+2]) {
                for(int j = i+2; j < nums.length; ++j) {
                    nums[j-1] = nums[j];
                }
                i--;
                cnt++;
            }
        }
        return nums.length-cnt;
    }
}

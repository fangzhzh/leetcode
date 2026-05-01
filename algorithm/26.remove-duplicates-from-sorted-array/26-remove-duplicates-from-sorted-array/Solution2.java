public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length ==0) return 0;
        int cnt = 0;
        for(int i = 0; i+cnt < nums.length-1; ++i) {
            if(nums[i] == nums[i+1]) {
                for(int j = i+1; j+cnt < nums.length; ++j) {
                    nums[j-1] = nums[j];
                }
                i--;
                cnt++;
            }
        }
        return nums.length-cnt;
    }
}

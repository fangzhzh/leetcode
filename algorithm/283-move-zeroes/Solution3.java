public class Solution {
    public void moveZeroes(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return;
        }
        int i = 0;
        for(i = 0; i < nums.length; i++) {
            if(nums[i] == 0) break;
        }
        for(int j = i+1; j < nums.length; ++j) {
            if(nums[j] != 0) {
                nums[i++] = nums[j];
            }
        }
        for(; i < nums.length; ++i) {
            nums[i] = 0;
        }
    }
}

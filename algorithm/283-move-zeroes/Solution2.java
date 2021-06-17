// no need to keep order
// is this right?
public class Solution {
    public void moveZeroes(int[] nums) {
        int start=0, end = nums.length-1;
        while(start<end) {
            if(nums[start]!=0) {
                start++;
                continue;
            }

            if(nums[end]==0) {
                end--;
                continue;
            }
            nums[start]=nums[end];
            nums[end]=0;
        }
    }
}

public class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min=Integer.MAX_VALUE, secMin=Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if(cur <= min) {
                min = cur;
            } else if( cur < secMin) {
                secMin = cur;
            } else if(cur> secMin){
                return true;
            }

        }
        return false;
    }
}

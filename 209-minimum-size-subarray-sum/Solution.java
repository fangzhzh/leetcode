public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length ==0)
            return 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0, j = 0;
        while(i<nums.length) {
            sum += nums[i++];
            while (sum >= s) {
                min = Math.min(min, i - j);
                sum -= nums[j++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }
}


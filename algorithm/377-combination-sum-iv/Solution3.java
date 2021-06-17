public class Solution {
    public int combinationSum4(int[] nums, int target) {
        int dp[] = new int[target+1];
        dp[0] = 1;
        Arrays.sort(nums);
        for(int i = 0; i < target+1; ++i) {
            for(int j = 0; j < nums.length && i >= nums[j]; ++j) {
                dp[i] += dp[i-nums[j]];
            }
        }
        return dp[target];
    }
}

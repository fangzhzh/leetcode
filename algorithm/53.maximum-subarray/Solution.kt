class Solution {
    fun maxSubArray(nums: IntArray): Int {
        val len = nums.size
        if(len == 0) {
            return 0
        }
        if(len == 1) {
            return nums[0]
        }
        // dp[i] means max sub until nums[i-1]
        val dp = IntArray(len+1)
        dp[0] = 0
        var max = Int.MIN_VALUE
        for(i in 1..len) {
            dp[i] = Math.max( dp[i-1] + nums[i-1], nums[i-1])
            if(max < dp[i]) {
                max = dp[i]
            }
        }
        return max
    }
}

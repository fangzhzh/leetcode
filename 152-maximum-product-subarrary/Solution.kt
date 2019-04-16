class Solution {
    fun maxProduct(nums: IntArray): Int {
        val len = nums.size
        if(len == 0) {
            return 0
        }
        // dp[i][0] means nums[i-1] max production
        // dp[i][1] mean nums[i-1] max minus production
        val dp = Array<IntArray>(len+1, {IntArray(2)})
        if(nums[0] >= 0) {
            dp[1][0] = nums[0]
            dp[1][1] = nums[0]
        } else {
            dp[1][0] = nums[0]
            dp[1][1] = nums[0]
        }
        var max = Math.max(dp[1][0], dp[1][1])
        for(i in 2..len) {
                if(nums[i-1] >= 0) {
                    dp[i][0] = Math.max(dp[i-1][0] * nums[i-1], nums[i-1])
                    dp[i][1] = dp[i-1][1] * nums[i-1]
                } else {
                    dp[i][0] = dp[i-1][1] * nums[i-1]
                    dp[i][1] = Math.min(dp[i-1][0] * nums[i-1], nums[i-1])
                }
            if(max < dp[i][0]) {
                max = dp[i][0]
            }
            if(max < dp[i][1]) {
                max = dp[i][1]
            }
        }

        return max

    }
}

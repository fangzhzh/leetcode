class Solution {
    fun lengthOfLIS(nums: IntArray): Int {
        val len = nums.size
        if(len == 0) {
            return 0
        }
        if(len == 1) {
            return 1
        }


        // dp[i] means len of lis until nums[i-1]
        var dp = Array(len+1, {1})
        for(i in 1..len) {
            for(j in 1..i) {
                if(nums[i-1] > nums[j-1] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1
                }
            }
        }

        var result = 1
        for(length in dp) {
            if(length > result) {
                result = length
            }
        }
        return result
    }

}

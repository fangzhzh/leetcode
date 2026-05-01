class Solution {
    fun canPartition(nums: IntArray): Boolean {
        val len = nums.size
        val sum = nums.sum()
        if(sum%2 != 0) {
            return false
        }
        val value = sum / 2
        val dp = IntArray(value+1, {0})
        
        for(i in 1..len) {
            for(v in value downTo nums[i-1]) {
                dp[v] = Math.max(dp[v], dp[v-nums[i-1]] + nums[i-1])
            }
        }
        if(dp[value] == value) {
            return true
        }
        return false
    }
}

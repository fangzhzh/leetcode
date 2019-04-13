class Solution {
    fun combinationSum4(nums: IntArray, target: Int): Int {
        // dp[i] means the number of target's combination ways
        val dp = IntArray(target + 1)
        dp[0] = 1
        for(i in 1..target) {
            for(k in nums) {
                if(i >= k) {
                    dp[i] += dp[i-k]
                }
            }
        }
        return dp[target]
    }
}

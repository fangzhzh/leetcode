class Solution {
    fun numSquares(n: Int): Int {
        val nums = ArrayList<Int>()
        for(i in 1..n+1) {
            nums.add(0, i*i)
        }
        val len = nums.size
        // least number to this sum
        val dp = IntArray(n+1, {it})
        for(i in 1..len) {
            for(v in nums[i-1]..n) {

                dp[v] = Math.min(dp[v], dp[v-nums[i-1]] + 1)
            }
        }
        return dp[n]
    }
}

class Solution {
    // bottom up
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val size = triangle.size
        // least sum of k level
        val dp = IntArray(size+1)
        dp[size] = 0
        for(level in size - 1 downTo 0) {
            val list = triangle[level]
            for(i in 0..list.size-1) {
                dp[i] = Math.min(dp[i], dp[i+1]) + list[i]
            }
        }
        return dp[0]

    }
}

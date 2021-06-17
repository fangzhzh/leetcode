class Solution {
    fun nthUglyNumber(n: Int): Int {
        val dp = IntArray(n+1)
        val nums = arrayOf(1,2,3,5)
        // ugly number at i
        dp[0] = 1
        // pointer
        var t1 = 0
        var t2 = 0
        var t3 = 0
        for(i in 1..n) {
            dp[i] = Math.min(dp[t1]*2, Math.min(dp[t2]*3, dp[t3]*5))
            if(dp[i] == dp[t1]*2) t1++
            if(dp[i] == dp[t2]*3) t2++
            if(dp[i] == dp[t3]*5) t3++
        }
        return dp[n-1]

    }
}

class Solution {
    fun uniquePaths(m: Int, n: Int): Int {
        // dp[i][j] num of path to this pos[i-1][j-1]
        val dp = Array(m+1, {IntArray(n+1)})
        for(row in 1..m) {
            dp[row][1] = 1
        }
        for(col in 1..n) {
            dp[1][col] = 1
        }
        for(row in 2..m) {
            for(col in 2..n) {
                dp[row][col] =  dp[row][col-1] + dp[row-1][col]
                
            }
        }
        return dp[m][n]
    }
}

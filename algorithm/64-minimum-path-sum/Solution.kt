class Solution {
    fun minPathSum(grid: Array<IntArray>): Int {
        val m = grid.size
        if(m == 0) {
            return 0
        }
        val n = grid[0].size
        val dp = Array<IntArray>(m+1, {IntArray(n+1)})
        for(i in 0..m) {
            dp[i][0] = 0
        }
        for(i in 0..n) {
            dp[0][i] = 0
        }

        for(i in 1..m) {
            for(j in 1..n) {
                val value = grid[i-1][j-1]
                if(i-1==0) {
                    dp[i][j] = dp[i][j-1] + value
                } else if(j-1==0) {
                    dp[i][j] = dp[i-1][j] + value
                } else {
                    dp[i][j] = Math.min(
                            dp[i-1][j] + value,
                            dp[i][j-1] + value
                    )
                }
                // println("dp[$i][$j] = ${dp[i][j]}")
            }
        }
        return dp[m][n]

    }
}

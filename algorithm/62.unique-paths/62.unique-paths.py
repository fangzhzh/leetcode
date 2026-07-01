#
# @lc app=leetcode id=62 lang=python3
#
# [62] Unique Paths
#

# @lc code=start
class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        # dp[i][j] 表示到达第 i 行第 j 列的路径数
        # dp 定义为 m+1 行，n+1 列
        # 递推公式：dp[i][j] = dp[i-1][j] + dp[i][j-1]
        dp = [[0] * (n + 1) for _ in range(m + 1)]
        # 哨兵：让 dp[1][1] 能被正确推导出 1
        dp[0][1] = 1
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                dp[i][j] = dp[i-1][j] + dp[i][j-1]
        return dp[m][n]
# @lc code=end

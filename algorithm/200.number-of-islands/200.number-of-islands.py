#
# @lc app=leetcode id=200 lang=python3
#
# [200] Number of Islands
#

# @lc code=start
class Solution:
    # TC O(m*n)
    # SC O(m*n)
    def numIslands(self, grid: List[List[str]]) -> int:
        m, n = len(grid), len(grid[0])
        direction = [[0,1], [0,-1], [1,0], [-1,0]] # directions
        visited = [[0 for _ in range(n)] for _ in range(m)]
        self.ans = 0
        
        def helper(i: int, j: int, visited: list[list[int]]) -> None:
            visited[i][j] = 1
            if grid[i][j] == '0':
                return
            for (x, y) in direction:
                ni, nj = i+x, j+y
                if(ni >= 0 and ni < m and nj >= 0 and nj < n
                    and visited[ni][nj] == 0):
                    helper(ni, nj,visited)

        for i in range(0, m):
            for j in range(0, n):
                if visited[i][j] == 0 and grid[i][j] == '1':
                    helper(i, j , visited)
                    self.ans += 1

        return self.ans




        
# @lc code=end



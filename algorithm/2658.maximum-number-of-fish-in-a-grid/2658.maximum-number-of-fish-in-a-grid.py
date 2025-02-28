#
# @lc app=leetcode id=2658 lang=python3
#
# [2658] Maximum Number of Fish in a Grid
#

# @lc code=start
class Solution:
    # row/col <=====> pos

    maxSize = 0
    def findMaxFish(self, grid: List[List[int]]) -> int:
        if len(grid) == 0:
            return 0

        parent = {}
        size = {}
        m, n = len(grid), len(grid[0])
        def union(x: int, y: int) -> None:
            px, py = find(x), find(y)
            if px != py:
                parent[py] = px
                size[px] += size[py]
                self.maxSize = max(self.maxSize, size[px])


        def find(x) -> int:
            p = x       
            while parent[p] != p:
                p = parent[p]
            while parent[x]!= p:
                x, parent[x] = parent[x], p
            return p

        def cell_id(x: int, y: int) -> int:
            return x*n + y
        direction = [[0, 1], [0,-1], [1,0], [-1,0]]
        for i in range(0, m):
            for j in range(0, n):
                if grid[i][j] == 0:
                    continue
                pos = cell_id(i, j)
                parent[pos] = pos
                size[pos] = grid[int(pos/n)][pos%n]
                self.maxSize = max(self.maxSize, size[pos])
        for i in range(0, m):
            for j in range(0, n):
                if grid[i][j] == 0:
                    continue
                pos = cell_id(i, j)
                for dir in direction:
                    newi, newj = i+dir[0], j+dir[1]
                    if newi >= 0 and newi < m and newj >= 0 and newj < n and grid[newi][newj] > 0:
                        newp = cell_id(newi, newj)
                        union(pos, newp)
        return self.maxSize



# @lc code=end


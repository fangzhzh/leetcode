#
# @lc app=leetcode id=547 lang=python3
#
# [547] Number of Provinces
#

# @lc code=start
# TC union: O(n), isConnected O(1), get O(1)
class UnionFind:
    def __init__(self):
        self.parent = {}
    def union(self, x: int, y: int):
        if not x in self.parent:
            self.parent[x] = x
        if not y in self.parent:
            self.parent[y] = y
        px = self.find(x)
        py = self.find(y)
        if px != py:
            self.parent[px] = py
    def find(self, x: int):
        root = x
        while self.parent[root] != root:
            root = self.parent[root]
        while self.parent[x] != root:
            self.parent[x] = root
            x = self.parent[x]
        return root
        
    def get(self) -> int:
        cnt = set()
        # print(self.parent)
        for x in self.parent:
            cnt.add(self.find(x))
        return len(cnt)

class Solution:
    
    def findCircleNum(self, isConnected: List[List[int]]) -> int:
        m, n = len(isConnected), len(isConnected[0])
        self.ans = 0
        visited = [0 for _ in range(0, n)]
        def uinonfind()->int:
            uf = UnionFind()
            for i in range(0, m):
                for j in range(0, n):
                    if(isConnected[i][j] == 1):
                        uf.union(i, j)
            return uf.get()

        # this grid is not a grid of point, they are n cities and grid of link between n cities
        def helper( i: int)->None:
            visited[i] = 1
            for j in range(0, m):
                if(isConnected[i][j] == 1 and visited[j] == 0):
                    helper(j)

        for i in range(0, m):
            if(visited[i] == 0):
                helper(i)
                self.ans += 1
        return self.ans
        # return uinonfind
        
# @lc code=end


    #
# @lc app=leetcode id=1971 lang=python3
#
# [1971] Find if Path Exists in Graph
#
"""
Find if Path Exists in Graph
Category	Difficulty	Likes	Dislikes
algorithms	Easy (53.86%)	4030	237
Tags
Companies
Unknown

There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.

"""
# @lc code=start
# typical union find solution
class Solution1:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        parent = {x: x for x in range(n)}
        rank = {x:1 for x in range(n)}
        def union(x: int, y: int) -> None:
            px, py = find(x), find(y)
            if px != py:
                if rank[px] > rank[py]:
                    parent[py] = px
                elif rank[px] < rank[py]:
                    parent[px] = py
                else:
                    parent[py] = px
                    rank[px] += 1
        def unionWithRank(x: int, y: int) -> None:
            px, py = find(x), find(y)
            if px!= py:
                if rank[px] > rank[py]:
                    parent[py] = px
                elif rank[px] < rank[py]:
                    parent[px] = py
                else:
                    parent[py] = px

        def find(x: int) -> int:
            px = x
            while parent[px] != px:
                px = parent[px]
            while parent[x] != px:
                x, parent[x] = parent[x], px
            return px
        def isConnected(x: int, y: int)->bool:
            return find(x) == find(y)
        
        for edge in edges:
            x, y = edge[0], edge[1]
            union(x, y)
        return isConnected(source, destination)

class Solution2:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        parent = {x: x for x in range(n)}
        def union(x: int, y: int) -> None:
            px, py = find(x), find(y)
            if px != py:
                parent[py] = px

        def find(x: int) -> int:
            px = x
            while parent[px] != px:
                px = parent[px]
            while parent[x] != px:
                x, parent[x] = parent[x], px
            return px
        def isConnected(x: int, y: int)->bool:
            return find(x) == find(y)
        
        for edge in edges:
            x, y = edge[0], edge[1]
            union(x, y)
        return isConnected(source, destination)

# @lc code=end


class Solution2:
    def validPath(self, n: int, edges: List[List[int]], source: int, destination: int) -> bool:
        parent = {x: x for x in range(n)}
        rank = {x:1 for x in range(n)}
        def union(x: int, y: int) -> None:
            px, py = find(x), find(y)
            if px != py:
                if rank[px] > rank[py]:
                    parent[py] = px
                elif rank[px] < rank[py]:
                    parent[px] = py
                else:
                    parent[py] = px
                    rank[px] += 1

        def find(x: int) -> int:
            px = x
            while parent[px] != px:
                px = parent[px]
            while parent[x] != px:
                x, parent[x] = parent[x], px
            return px
        def isConnected(x: int, y: int)->bool:
            return find(x) == find(y)
        for edge in edges:
            x, y = edge[0], edge[1]
            union(x, y)
        return isConnected(source, destination)


#
# @lc app=leetcode id=128 lang=python3
#
# [128] Longest Consecutive Sequence
#

# @lc code=start

"""
The union find solution, thought it is not the best solution, and Time Limit Exceeded in leetcode.
But union find idea is nice.
"""
class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        if not nums:
            return 0
        parent = {}
        size = {}
        rank = {}
        def union(x: int, y: int) -> None:
            px = find(x)
            py = find(y)
            if px != py:
                if rank[px] > rank[py]:
                    parent[py] = px
                    size[px] += size[py]
                elif rank[px] < rank[py]:
                    parent[px] = py
                    size[py] += size[px]
                else:
                    parent[py] = px
                    size[px] += size[py]
                    rank[px] += 1
        def find(x: int) -> int:
            root = x
            while parent[root]!= root:
                root = parent[root]
            
            while parent[x] != root:
                x, parent[x] = parent[x], root
            
            return root
        for num in nums:
            parent[num] = num
            size[num] = 1
            rank[num] = 0
        for num in nums:
            if num + 1 in parent:
                union(num, num + 1)

        return max(size[find(num)] for num in nums)


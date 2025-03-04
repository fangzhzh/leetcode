#
# @lc app=leetcode id=218 lang=python3
#
# [218] The Skyline Problem
#

# @lc code=start
class SegmentTree:
    def __init__(self, n: int)->None:
        self.tree = [0] * (4*n)
    def update_range(self, node: int, start: int, end: int, left: int, right: int, val: int):
        if start > right or end < left:
            return
        if left <= start and end <= right:
            self.tree[node] = max(self.tree[node], val)
            return
        mid = start + (end - start) // 2
        self.update_range(2*node+1, start, mid, left, right, val)
        self.update_range(2*node+2, mid+1, end, left, right, val)
    def print(self)->None:
        print(self.tree)
    def query(self, node: int, start: int, end: int, index: int):
        if start == end:
            return self.tree[node]
        mid = start + (end - start) // 2
        if index <= mid:
            return max(self.tree[node], self.query(2*node+1, start, mid, index))
        else:
            return max(self.tree[node], self.query(2*node+2, mid+1, end, index))
class Solution:
    def getSkyline(self, buildings: List[List[int]]) -> List[List[int]]:
        n = len(buildings)
        points = set()
        for l, r, h in buildings:
            points.add(l)
            points.add(r)
        sorted_points = sorted(points)
        point_to_idx = {x: i for i, x in enumerate(sorted_points)}
        # print(point_to_idx)
        n = len(sorted_points)
        st = SegmentTree(n)
        for l, r, h in buildings:
            st.update_range(0, 0, n-1, point_to_idx[l], point_to_idx[r]-1, h)
        # st.print()
        prev = 0
        result = []
        for i, x in enumerate(sorted_points):
            cur = st.query(0, 0, n-1, i)
            if cur != prev:
                result.append([x, cur])
                prev = cur
        return result
# @lc code=end


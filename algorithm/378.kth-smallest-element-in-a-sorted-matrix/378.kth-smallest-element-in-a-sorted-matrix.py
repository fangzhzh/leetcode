#
# @lc app=leetcode id=378 lang=python3
#
# [378] Kth Smallest Element in a Sorted Matrix
#

# @lc code=start
class Solution:
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        m, n = len(matrix), len(matrix[0])
        queue = []
        for i in range(0, m):
            for j in range(0, n):
                heapq.heappush(queue, -matrix[i][j])
                if len(queue) > k:
                    heapq.heappop(queue)
        return -queue[0]
                
# @lc code=end


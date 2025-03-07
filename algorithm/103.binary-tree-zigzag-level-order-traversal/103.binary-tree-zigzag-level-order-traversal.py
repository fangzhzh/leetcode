#
# @lc app=leetcode id=103 lang=python3
#
# [103] Binary Tree Zigzag Level Order Traversal
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:

    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if root is None:
            return []
        ans = []
        queue = deque() # queue
        queue.append(root)
        zigzag = True
        while queue:
            tmp = []
            # print(queue)
            for i in range(0, len(queue)):
                node = queue.popleft()
                tmp.append(node.val)
                if node.left is not None:
                    queue.append(node.left)
                if node.right is not None:
                    queue.append(node.right)
            ans.append(tmp if zigzag else tmp[::-1])
            zigzag = not zigzag
        return ans



# @lc code=end


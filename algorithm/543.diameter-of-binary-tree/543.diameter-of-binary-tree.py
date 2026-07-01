#
# @lc app=leetcode id=543 lang=python3
#
# [543] Diameter of Binary Tree
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def diameterOfBinaryTree(self, root: Optional[TreeNode]) -> int:
        self.ans = 0

        def get_height(root: Optional[TreeNode]) -> int:
            if root is None:
                return 0
            left_h = get_height(root.left)
            right_h = get_height(root.right)
            # 穿过当前节点的直径 = 左子树高度 + 右子树高度
            self.ans = max(self.ans, left_h + right_h)
            return max(left_h, right_h) + 1

        get_height(root)
        return self.ans
# @lc code=end

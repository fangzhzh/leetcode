#
# @lc app=leetcode id=112 lang=python3
#
# [112] Path Sum
#

# @lc code=start
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def hasPathSum(self, root: Optional[TreeNode], targetSum: int) -> bool:
        def rec()->bool:
            if root is None:
                return False
            if root.left is None and root.right is None and targetSum-root.val == 0:
                return True
            return self.hasPathSum(root.left, targetSum-root.val) or self.hasPathSum(root.right, targetSum - root.val)
        def iter()->bool:
            if not root:
                return False
            stack=[]
            stack.append((root, root.val))
            while stack:
                node, val = stack.pop()
                if val == targetSum and not node.left and not node.right:
                    return True
                if node.right is not None:
                    stack.append((node.right, val + node.right.val))
                if node.left is not None:
                    stack.append((node.left, val + node.left.val))
            return False
        return iter()
        
# @lc code=end


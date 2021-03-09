/*
 * @lc app=leetcode id=105 lang=java
 *
 * [105] Construct Binary Tree from Preorder and Inorder Traversal
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
           return buildTree(preorder, 0, preorder.length,
           inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd,
    int[] inorder, int inStart, int inEnd) {
        if(preStart >= preEnd) {
            return null;
        }
        int idx = inStart;
        int rootVal = preorder[preStart];
        for(int i = inStart; i < inEnd; i++) {
            if(inorder[i] == rootVal) {
                idx = i;
                break;
            }
        }
        int leftSize = idx-inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, preStart+1, preStart + leftSize + 1, 
            inorder, inStart, idx);
        root.right = buildTree(preorder, preStart + leftSize+1, preEnd, 
            inorder, idx+1, inEnd);
        return root;
    }
}
// @lc code=end


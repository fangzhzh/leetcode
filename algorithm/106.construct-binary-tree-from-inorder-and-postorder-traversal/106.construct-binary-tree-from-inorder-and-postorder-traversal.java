/*
 * @lc app=leetcode id=106 lang=java
 *
 * [106] Construct Binary Tree from Inorder and Postorder Traversal
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

 /**
 Category	Difficulty	Likes	Dislikes
algorithms	Medium (48.39%)	2483	49
Tags
Companies
Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

 

Example 1:


Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]
Example 2:

Input: inorder = [-1], postorder = [-1]
Output: []
 

Constraints:

1 <= inorder.length <= 3000
postorder.length == inorder.length
-3000 <= inorder[i], postorder[i] <= 3000
inorder and postorder consist of unique values.
Each value of postorder also appears in inorder.
inorder is guaranteed to be the inorder traversal of the tree.
postorder is guaranteed to be the postorder traversal of the tree.
 
  */
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length, 
        postorder, 0, postorder.length);
    }
    private TreeNode buildTree(int[]inorder, int inStart, int inEnd, 
        int[]postorder, int pStart, int pEnd) {
            if(pStart >= pEnd || inStart >= inEnd) {
                return null;
            }
            int idx = inStart;
            int rootVal = postorder[pEnd-1];
            for(int i = inStart; i < inEnd; i++) {
                if(rootVal == inorder[i]) {
                    idx = i;
                }
            }
            TreeNode root = new TreeNode(rootVal);
            int leftSize = idx - inStart, rightSize = inEnd - idx - 1;
            root.left = buildTree(inorder, inStart, idx, 
                postorder, pStart, pEnd-rightSize-1);
            root.right = buildTree(inorder, idx+1, inEnd, 
                postorder, pStart+leftSize, pEnd-1);
            return root;
        }
}
// @lc code=end


/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
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
  * I didn't get it right the first time though my reasoning is correct.
  * loop through the all the node and find the one which has biggest sum of depth of left and right
  * 
  * The provided method itself don't have to be the main recursive method
  */
class Solution {
    int max = Integer.MIN_VALUE;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        depthOfBT(root);
        return max;
    }
    private int depthOfBT(TreeNode root) {
        if(root == null) return 0;
        int left = depthOfBT(root.left);
        int right = depthOfBT(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }


}
// @lc code=end


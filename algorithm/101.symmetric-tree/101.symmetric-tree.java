/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
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
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricBFS(root);
    }
    // Time O(n), Space O(n)
    private boolean isSymmetricRecursive(TreeNode left, TreeNode right) {
        // verify left & right
        if(left == null && right == null) return true;
        if(left == null || right == null) return false;
        if(left.val != right.val) return false;
        // recurive verify left.leff == right.right && left.right == right.left
        return isSymmetricRecursive(left.left, right.right) && isSymmetricRecursive(left.right, right.left);
    }

    // Time O(n), space O(n)
    private boolean isSymmetricBFS(TreeNode root) {
        // level traversal and compare one level
        if(root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while(!queue.isEmpty()) {
            // every left, rigth, check the symmetric
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if(left == null && right == null) continue;
            if(left == null || right == null) return false;
            if(left.val != right.val) return false;
            // we push nodes in order of left.left, right.rigth, left.right, right.left to make sure the top two are symmetric nodes
            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

// @lc code=end


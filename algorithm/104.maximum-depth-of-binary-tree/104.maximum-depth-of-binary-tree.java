/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
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
 // method1, recursive
    // Time O(n), space O(n) for the stack trace
    public int maxDepth(TreeNode root) {
        return maxDepthRecur(root);
    }

    private int maxDepthRecur(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left),
                maxDepth(root.right)) + 1;
    }

    // Time O(n),
    //  space O(n) for queue memory
    private int maxDepthBFS(TreeNode root) {
        if(root == null) return 0;
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                TreeNode cur = queue.poll();
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            max++;
        }
        return max;
    }

}
// @lc code=end


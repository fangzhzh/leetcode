/*
 * @lc app=leetcode id=226 lang=java
 *
 * [226] Invert Binary Tree
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
     public TreeNode invertTree(TreeNode root) {
        return invertTreeDFSPre(root);
    }
    // recursive bottom up
    // starts by resolving base cases and incrementally build towards the final solution.
    private TreeNode invertTreeBU(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    // recursive top down
    // starts with original problem and recursively break into sub problems
    private TreeNode invertTreeTD(TreeNode root) {
        if(root == null) {
            return null;
        }
        
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
    public TreeNode invertTreeRecursive(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertTreeRecursive(root.left);
        invertTreeRecursive(root.right);
        return root;
    }
    public TreeNode invertTreeBFS(TreeNode root) {
        if(root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if(cur.left != null) {
                queue.offer(cur.left);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
            }
        }
        return root;
    }
    public TreeNode invertTreeDFSPre(TreeNode root) {
        if(root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
            if(cur.left != null) {
                stack.push(cur.left);
            }
            if(cur.right != null) {
                stack.push(cur.right);
            }

        }
        return root;

    }
}
// @lc code=end


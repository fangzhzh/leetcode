/*
 * @lc app=leetcode id=144 lang=java
 *
 * [144] Binary Tree Preorder Traversal
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
    public List<Integer> preorderTraversal(TreeNode root) {
        return morrisTraversalPreOrder(root);
    }

    // Morris Traversal, the key point is to find the data access chance(result.add())
    private List<Integer> morrisTraversalPreOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        TreeNode cur = root;
        while(cur != null) {
            if(cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode mostR = cur.left;
                while(mostR.right != null && mostR.right != cur) {
                    mostR = mostR.right;
                }
                if(mostR.right == null) {
                    mostR.right = cur;
                    result.add(cur.val);
                    cur = cur.left;
                } else {
                    mostR.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }

}
// @lc code=end


/*
 * @lc app=leetcode id=617 lang=java
 *
 * [617] Merge Two Binary Trees
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
    // time O(min(m,n)), space O(min(m,n))
    // the traversal only go as deep as the minumum tree
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null) {
            return root2;
        } 
        if(root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }

        TreeNode mergeTreesIterative(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root1);
        q.offer(root2);
        while (!q.isEmpty()) {
            TreeNode cur1 = q.poll();
            TreeNode cur2 = q.poll();

            if (cur1 == null && cur2 == null) {
                continue;
            }
            // before we update the node. 
            // offer pairs, so we process pair by pair
            q.offer(cur1 != null ? cur1.left : null);
            q.offer(cur2 != null ? cur2.left : null);
            q.offer(cur1 != null ? cur1.right : null);
            q.offer(cur2 != null ? cur2.right : null);

            TreeNode cur = null;
            cur = cur1 != null ? cur1 : cur2;
            cur.val = (cur1 != null? cur1.val:0) + (cur2 != null ? cur2.val : 0);

            cur.left = cur1 != null&&cur1.left != null  ? cur1.left : cur2 != null? cur2.left : null;
            cur.right = cur1 != null && cur1.right !=null ? cur1.right : cur2 != null ? cur2.right : null;
        }
        return root1;
    }
}
// @lc code=end


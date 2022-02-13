/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
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
    public List<Integer> inorderTraversal(TreeNode root) {
        return morrisTraversal(root);
    }

    private List<Integer> recursiveInorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        recursiveInorder(root, result);
        return result;
    }
    private void recursiveInorder(TreeNode root, List<Integer> result) {
        if(root == null) return;
        recursiveInorder(root.left, result);
        // if(root.left == null) {
            result.add(root.val);
        // }
        recursiveInorder(root.right, result);
    }


    /**
     * 有意思的是，morris 的空间复杂度是O(1)
     * 如果是调用mostR函数，运行速度非常慢，只beat 30%的solution
     * 如果直接while循环找，那么beat 100%
     */
    private List<Integer> morrisTraversal(TreeNode root) {
        // 1. left == null, visit cur
        // 2 left != null find most right mostR in leftTree
        //      3.1 if leftMostR.right == null => mostR.right = cur; cur = cur.left
        //      3.2 if leftMostR.right == cur => mostR.right = null; cur = cur.right
        List<Integer> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        TreeNode cur = root;
        while(cur != null) {
            if(cur.left != null) {
                TreeNode mostR = cur.left;
                while(mostR.right != null && mostR.right != cur) {
                    mostR = mostR.right;
                }
                if(mostR.right == null) {
                    mostR.right = cur;
                    cur = cur.left;
                } else { // mostR.right == cur
                    result.add(cur.val);
                    mostR.right = null;
                    cur = cur.right;
                }
            } else {
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }

    TreeNode findMostRInLeft(TreeNode root) {
        TreeNode cur = root.left;
        while (cur.right != null && cur.right != root) {
            cur = cur.right;
        }
        return cur;
    }
}
// @lc code=end


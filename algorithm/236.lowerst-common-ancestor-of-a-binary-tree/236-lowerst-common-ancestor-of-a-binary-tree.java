/*
 * @lc app=leetcode lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 /**
  * 
  Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]


 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
  */


class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        return left != null ? left : right;
    }
}
// @lc code=end

/**
 * 
 * ## Analysis:
 * - find one/two/null from left, another/two/null from right
 * - if left != null, right != null, the root is the lca
 * - if left == null, right is the lca
 * - if right == null, left is the lca
 * ## why?
 * 1. p and q will exist
 * 2. all node.val are unique & p != q
 * ==> for root, find p frist in left, q is not in right, and q is exist, then q must be descendant of p
 */

 /**
  * Same solution, but in different writting flow
  * 
  */
class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      if(root == null)  return null;
      if(root.val == p.val || root.val == q.val) return root;
      TreeNode left = lowestCommonAncestor(root.left, p, q);
      TreeNode right = lowestCommonAncestor(root.right, p, q);
      if(left != null && right != null) return root;
      else if(right == null) return left;
      else return right;

  }
}

Your runtime beats 100 % of java submissions
Your memory usage beats 8.14 % of java submissions (41.4 MB)
/*
 * @lc app=leetcode id=235 lang=java
 *
 * [235] Lowest Common Ancestor of a Binary Search Tree
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
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]


 

Example 1:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:

Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
  */

/**
 * Solution 1: Runtime Exceed
 * If we parse the tree as PreDFS, [6,2,8,0,4,7,9,null,null,3,5], the parent of a node is 
 * (index-1)/2
 * 
 * In this way, we memorize it and then divide it down to the same parent value
 * 
 * though the tricky part is that the null value can't be just ignored because their null children are counted in indexing
 *  
 * And it's no surprise time limit exceed.
 */  
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
         int low = -1, high = -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = -1;
        Map<Integer, TreeNode> map = new HashMap<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            i++;
            if(low != -1 && high!= -1) {
                break;
            }
            if(node == null) {
                queue.offer(null);
                queue.offer(null);
                continue;
            }
            map.put(i, node);
            if(node.val == p.val) {
                low = i;
            }
            if(node.val == q.val) {
                high = i;
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }
        while(true) {
            if(high > low) {
                high = (high-1)/2;
            }
            if(low > high) {
                low = (low-1)/2;
            }
            if(high == low) {
                return map.get(low);
            }
        }
        return root;

    }
}
 

/**
 * A better solution.
 * Use the BST knowledge to it's full extent.
 * If the root.val is in left.val..right.val, it must be the common ancestor
 * if root.val <left.val && right.val, go to left to find
 * if root.val > reft val && right.val, go to right to find
 * 
 * Your runtime beats 100 % of java submissions
 * This algorithm is master piece
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if(root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }


    }
}
// @lc code=end


/**
 * Another recursive solution.
 * It doesn't use too much BST, but it takes advantage of the LCA knowledge
 * 
 * parent -> left, right
 * if left is not null, right is not null, parent is the LCA,
 * else if left is null, right is LCA,
 * else the left is LCA.
 * 
 * Your runtime beats 54.74 % of java submissions
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
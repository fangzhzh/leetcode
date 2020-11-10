/*
 * @lc id=98 lang=java
 *
 * [98] Validate Binary Search Tree
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
  * Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
*/



/**
 * Solution -1, 0:
 * isValidBST(TreeNode node, List<TreeNode> paths)
 * 
 * It's not working because the paths lost the property of path, left/right
 * 
 * Solution -1: 
 * isValidBST(TreeNode node) 
 * {
 *      if(node?.left.val >= node.val || node?.right?.val <= node.val) {
 *          return false;
 *      }
 * }
 * 
 * It's not working because it only make sure the node itself is BST, 
 * but the the node and left/right subtree
 */


/**
   * ## Solution 1: Recursive
   * Max/Min version is very cleaver.
   * But I thought about the idea max, min, but I didn't reach as far as the solution
   * The biggest obstable here is how to udpate the max/min value
   * I even made it wrong the first time after I read the solution and try to write it on my own
   */
class Solution {
    public boolean isValidBST(TreeNode root) {

        return isValidBST(root, null, null);
    }
    private boolean isValidBST(TreeNode node, TreeNode min, TreeNode max) {
        if(node == null) {
            return true;
        }
        if(min != null && node.val <= min.val) return false;
        if(max != null && node.val >= max.val) return false;
        return isValidBST(node.left, min, node) && isValidBST(node.right, node, max);
    }
}



/**
 * ## Solution 2: Recursive
 * This solution is not correct because the pre need to be persisit during the traversal
 * 
class Solution {
    public boolean isValidBST(TreeNode root) {
        return inorder(root, null);
    }
    private boolean inorder(TreeNode root, TreeNode pre) {
        if(root == null) {
            return true;
        }
        if(!inorder(root.left, null)) {
            return false;
        }
        // root
        if(pre != null && pre.val >= root.val) {
            return false;
        }
        if(!inorder(root.right, root)) {
            return false;
        }
        return true;
    }
}
 */




/**
 * ## Solution 3: Recursive
 * This solution is based on the understanding of BST
 * Inorder BST should be a increasing sequences, not very fancy, but it combine recursive & BST & inorder
 * 
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        inorder(root, list);
        for(int i = 0; i < list.size()-1; i++) {
            if(list.get(i).val >= list.get(i+1).val) {
                return false;
            } 
        }
        return true;
    }
    private void inorder(TreeNode root, List<TreeNode> list) {
        if(root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root);
        inorder(root.right, list);
    }
}


/**
 * ## solution 4: iterative, Inorder, break when you find it
 */


/**
 * recursive, also the idea of max, min
 * It's hard to understand the logic updating the max, min.
 * BST, left children must less than root, right children must great than root.
 * 
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    boolean isValidBST(TreeNode root, long max, long min) {
        if(root == null) {
            return true;
        }
        if(root.val >= max || root.val <= min) {
            return false;
        }
        return isValidBST(root.left, root.val, min) && 
        isValidBST(root.right, max, root.val);
    }
}

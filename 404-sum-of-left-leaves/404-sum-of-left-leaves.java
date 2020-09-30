/*
 * @lc id=404 lang=java
 *
 * [404] Sum of Left Leaves
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
  * ind the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
  */

/**
 * ## analysis:
 * - recursive make me happy
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right, false);
    }
    private int sumOfLeftLeaves(TreeNode node, boolean fromLeft) {
        if(node == null) {
            return 0;
        }
        if(node.left == null && node.right == null) {
            return fromLeft? node.val : 0;
        }
        return sumOfLeftLeaves(node.left, true) + sumOfLeftLeaves(node.right, false);
    }
}
// @lc code=end


/**
 * Another solution in DFS
 * There are a tons of solutions in DFS, BFS
 * - just find the left and do the check and addition
 * - it's not optimal but it's a solution.
 * 
 */
class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        boolean fromLeft = true;
        int sum = 0;
        while(node != null) {
            stack.push(node);
            node = node.left;
            if(node != null && node.left == null && node.right == null) {
                sum += node.val;
            }
        }
        while(!stack.isEmpty()) {
            node = stack.pop();
            fromLeft = false;
            if(node.right != null) {
                node = node.right;
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                    if(node != null && node.left == null && node.right == null) {
                        sum += node.val;
                    }
        
                }
            }
    
        }
        return sum;
    }
}





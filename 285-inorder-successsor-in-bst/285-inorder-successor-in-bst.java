public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        return helper(root, p);
    }
    
    private TreeNode helper(TreeNode node, TreeNode target) {
        if(node == null) {
            return node;
        }
        if(node.val < target.val) {
            return helper(node.right, target);
        }
        if(node.val == target.val) {
            return helper(node.right, target);
        }
        TreeNode ret = helper(node.left, target);
        return ret == null?node:ret;
        
    }
}



/**
 * this recursive is very clear.
 * It use the BST knowledge to the deepest
 * - left less than root
 * - right greater than root
 * 
 * So the inOrderSuccesssor goes
 * - if the val is less equall then target, go right
 * - go right,
 * - until the find the first element great than the target
 *     + "This target/it's left son must be the successor"
 * - so we go deep to find the left, so node/left
 */

 /**
  * How to understand the recursive using stack
  * /right/right/right/left/left
  * /right/right/right/left/value
  * /right/right/right/value
  * /right/right/value
  * /right/value
  * value
  */
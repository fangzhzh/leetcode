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
        if(node.val <= target.val) {
            return helper(node.right, target);
        }
        TreeNode ret = helper(node.left, target);
        return ret == null?node:ret;
        
    }
}



/**
 * this recursive is very clear and clever.
 * It use the BST knowledge to the deepest
 * - left less than root
 * - right greater than root
 * 
 * It's next value in the inorder traversal.
 * It's either p's parent or the smallest node in p's right branch.
 * 
 * 
let's take the successor for example, basically we always want to find p's closest node (in inorder traversal) and the node's value is larger than p's value, right? That node can either be p's parent or the smallest node in p' right branch.

When the code runs into the else block, that means the current root is either p's parent or a node in p's right branch.

If it's p's parent node, there are two scenarios: 1. p doesn't have right child, in this case, the recursion will eventually return null, so p's parent is the successor; 2. p has right child, then the recursion will return the smallest node in the right sub tree, and that will be the answer.

If it's p's right child, there are two scenarios: 1. the right child has left sub tree, eventually the smallest node from the left sub tree will be the answer; 2. the right child has no left sub tree, the recursion will return null, then the right child (root) is our answer.
 * 
 * So the inOrderSuccesssor goes
 * - if the node is less equal than target, successor is in recursive find the greater right side
 * - if the node is greater than target, we get the smaller left child 
 * - the found value must be the value 
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
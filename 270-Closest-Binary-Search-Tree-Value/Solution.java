/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        double minDelta = Double.MAX_VALUE;
        int result = root.val;
        TreeNode node = root;
        while(node != null) {
            double curDelta = Math.abs(node.val - target);
            if(curDelta < minDelta) {
                minDelta = curDelta;
                result = node.val;
            }
            if(node.val > target) {
                node = node.left;
            } else if (node.val < target) {
                node = node.right;
            } else {
                return result;
            }
        }
        return result;
        
    }
}

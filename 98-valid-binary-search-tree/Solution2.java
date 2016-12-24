public class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return checkValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    boolean checkValid(TreeNode node, long min, long max) {
        if(node == null) return true;
        if(node.val >= max || node.val <= min) return false;
        return checkValid(node.left, min, node.val) && checkValid(node.right, node.val, max);
    }

}
 

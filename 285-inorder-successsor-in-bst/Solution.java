/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur=root, last=null;
        while(!stack.isEmpty() || cur != null){
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if(last != null ) {
                    return cur;
                }
                if(cur != null && cur.val == p.val) {
                    last = cur;
                }
                cur = cur.right;
            }
        }

        return cur;
    }
}

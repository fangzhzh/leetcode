/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;

public class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        boolean findFirst = false;
        int max = Integer.MIN_VALUE;
        while(!stack.isEmpty() || cur != null) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if(!findFirst) {
                    findFirst = true;
                    max = cur.val;
                } else {
                    if(cur.val > max) {
                        max = cur.val;
                    } else {
                        return false;
                    }
                }
                cur = cur.right;
            }
        }
        return true;
    }
}

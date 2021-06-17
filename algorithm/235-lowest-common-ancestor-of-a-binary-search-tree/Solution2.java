import java.util.*;
///**
//  * Definition for a binary tree node.
 public class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
 // */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null) {
            return null;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root);
        trackNode(stack1, p);
        trackNode(stack2, q);
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            while(stack1.size() > stack2.size()) {
                stack1.pop();
            }
            while(stack2.size() > stack1.size()) {
                stack2.pop();
            }
            while(stack1.peek().val != stack2.peek().val) {
                stack1.pop();
                stack2.pop();
            }
            if(!stack1.isEmpty()){
                return stack1.pop();
            }
        }
        return null;
        
    }
    void trackNode(Stack<TreeNode> stack, TreeNode p) {
        TreeNode cur = stack.pop();
        while(cur != null) {
            stack.push(cur);
            if(cur.val < p.val) {
                cur = cur.right;
            } else if( cur.val > p.val) {
                cur = cur.left;
            } else {
                break;
            }
        }
        stack.push(p);
    }
}


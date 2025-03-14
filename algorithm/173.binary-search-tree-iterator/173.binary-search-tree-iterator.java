/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
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

// Solution 1: an optimised/cleaner version
class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode p;
    public BSTIterator(TreeNode root) {
        if(root == null) {
            return;
        }
        this.p = root;    
    }
    
    public int next() {
        while(!stack.isEmpty() || p != null) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode tmp = stack.pop();
                p = tmp.right;
                return tmp.val;
            }
        }
        return -1;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty() || p != null;        
    }
}


// Solution 2: a easier rememeber version
// 1. Current to trace where we are in the traversal
// 2. two phorase process: Left-Push-Pop-Process-Righ
// 2.1: Push all left nodes onto stack. This ensures we'll process leftmost nodes first
// 2.2: Process current node (which is the leftmost unprocessed node)
// 2.3 Move to right subtree and repeat


class BSTIterator {
    TreeNode p;
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        this.p = root;
    }
    
    public int next() {
        while(!stack.isEmpty() || p!= null) {
            // push to the left
            while(p!=null) {
                stack.push(p);
                p=p.left;
            }

            p = stack.pop();
            int val = p.val;
            p = p.right;
            return val;
        }
        return -1;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty() || p != null;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end


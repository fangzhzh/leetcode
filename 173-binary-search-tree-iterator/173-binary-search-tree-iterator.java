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
  * In order DFS, this value is `visit`, this problem breaks the iterative dfs, 
  * recursive should be able do the same with yield, a google search no. 
  * java yield is for thread.
  * python yield is generator instead.
  */
class BSTIterator {
    private Stack<TreeNode> stack = new Stack();
    private TreeNode cur = null;
    public BSTIterator(TreeNode root) {
        cur = root;
    }
    
    /** @return the next smallest number */
    public int next() {
        while(hasNext()) {
            if(cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                int value = cur.val;
                cur = cur.right;
                return value;
            }
        }
        return 0;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || cur != null;
    }
}

/*
 * @lc lang=java
 *
 * [102] Binary Tree Level Order Traversal
 */

 /**
  * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
    For example:
    Given binary tree [3,9,20,null,null,15,7],
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
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList();
        List<List<Integer>> results = new ArrayList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            ArrayList<Integer> result = new ArrayList<>();
            TreeNode tmp = null;
            for(int i = 0; i < len; i++) {
                tmp = queue.poll();
                if(tmp == null) {
                    continue;
                }
                result.add(tmp.val);
                if(tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if(tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            if(!result.isEmpty()) {
                results.add(result);
            }
        }
        return results;
    }
}


// Typical bfs problem, only trick here is that we need to separate level to a single array which can be achieved
// easily by a level counter.
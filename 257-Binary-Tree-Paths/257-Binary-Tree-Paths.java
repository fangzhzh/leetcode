/*
 * @lc  lang=java
 *
 * [257] Binary Tree Paths
 */

// @lc code=start

/***
 * Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

 */
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
 * ## analysis
 * - iterative
 * - Time O(n)  
 * - Space O(n)
 *   
 */
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if(root == null) {
            return result;
        }
        dfs(result, "", root);
        return result;
    }
    private void dfs(List<String> result, String path, TreeNode node) {
        if(path.length() == 0) {
            path += node.val;
        } else {
            path += "->" + node.val;
        }
        if(node.left == null && node.right == null) {
            result.add(path);
            return;
        }
        if(node.left != null) {
            dfs(result, path, node.left);
        }
        if(node.right != null) {
            dfs(result, path, node.right);
        }
    }
}
// @lc code=end


/**
 * Typical pre-DFS, the trick here is remain the trace of traversal, so I keep an array and a temporary path
 * Important information is the definition of leaf which means no left and no right.
 */

/**
 * ## another approach:
 * - recursive
 */
private preTraversal(List<String> result, String path, TreeNode node) {
    if(node.left == null && node.right == null) result.add(path+root.val);
    if(node.left != null) preTraversal(result, path+node.val+"->", node.left);
    if(node.right != null) preTraversal(result, path+node.val+"->", node.right);
}

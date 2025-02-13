/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
 * 
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 

Example 1:


Input: root = [3,9,20,null,null,15,7]
Output: 3
Example 2:

Input: root = [1,null,2]
Output: 2
 

Constraints:

The number of nodes in the tree is in the range [0, 104].
-100 <= Node.val <= 100
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
 // method1, recursive
    // Time O(n), space O(n) for the stack trace
    public int maxDepth(TreeNode root) {
        return maxDepthRecur(root);
    }

    private int maxDepthRecur(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left),
                maxDepth(root.right)) + 1;
    }

    // Time O(n),
    //  space O(n) for queue memory
    private int maxDepthBFS(TreeNode root) {
        if(root == null) return 0;
        int max = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-->0) {
                TreeNode cur = queue.poll();
                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
            max++;
        }
        return max;
    }

        // TC: O(n)
    // SC: O(n) for queue memory and map
    private int maxDepthIterativeItemLevel(TreeNode root) {
        int level = 1;
        if(root == null) {
            return 0;
        }
        // queue + nothing
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        Map<TreeNode, Integer> map = new LinkedHashMap<>();
        map.put(root, 1);
        while(!q.isEmpty()) {
            TreeNode cur = q.poll();
            if(cur.left != null) {
                q.offer(cur.left);
                level = map.get(cur)+1;
                map.put(cur.left, level);
            }
            if(cur.right != null) {
                q.offer(cur.right);
                level = map.get(cur)+1;
                map.put(cur.right, level);
            }
        }
        return level;

    }

}
// @lc code=end


/*
 * @lc app=leetcode id=124 lang=java
 * @lcpr version=30403
 *
 * [124] Binary Tree Maximum Path Sum
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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGainPathSum(root);
        return max;
    }
    // 当前节点为起点，向其子节点延伸的单向向下最大路径和
    private int maxGainPathSum(TreeNode root) {
        if(root == null) return 0;
        int lv = Math.max(0, maxGainPathSum(root.left));
        int rv = Math.max(0, maxGainPathSum(root.right));
        max = Math.max(max, root.val + lv + rv);
        return root.val + Math.max(lv, rv);
        
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3]\n
// @lcpr case=end

// @lcpr case=start
// [-10,9,20,null,null,15,7]\n
// @lcpr case=end

 */

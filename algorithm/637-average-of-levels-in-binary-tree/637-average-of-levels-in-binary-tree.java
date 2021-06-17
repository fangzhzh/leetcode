/*
 * @lc app=leetcode id=637 lang=java
 *
 * [637] Average of Levels in Binary Tree
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
/**
 * typical level bfs
 */
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        double sum = 0.0;
        queue.offer(root);
        int cnt = 0;
        int size = queue.size();
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            sum += cur.val;
            size--;
            cnt++;
            if(cur.left != null)  queue.offer(cur.left); 
            if(cur.right != null) queue.offer(cur.right); 
            if(size == 0) {
                ans.add(sum/cnt);
                size = queue.size();
                sum = 0.0;
                cnt = 0;
            }
        }
        return ans;
    }
}
// @lc code=end


/**
 * while checking queue
 * for loop level
 * This solution is cleaner
 */ 
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        double sum = 0.0;
        queue.offer(root);
        int cnt = 0;
        int size = queue.size();
        while(!queue.isEmpty()) {
            double sum = 0.0;
            int n = queue.size();
            for(int i = 0; i < n; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if(cur.left != null)  queue.offer(cur.left); 
                if(cur.right != null) queue.offer(cur.right); 
            }
            ans.add(sum / n);
        }
        return ans;
    }


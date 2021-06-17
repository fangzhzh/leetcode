/*
 * @lc app=leetcode id=652 lang=java
 *
 * [652] Find Duplicate Subtrees
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
  *Find Duplicate Subtrees
Category	Difficulty	Likes	Dislikes
algorithms	Medium (51.16%)	1837	237
Tags
Companies
Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

 

Example 1:


Input: root = [1,2,3,4,null,2,4,null,null,4]
Output: [[2,4],[4]]
Example 2:


Input: root = [2,1,1]
Output: [[1]]
Example 3:


Input: root = [2,2,2,3,null,3,null]
Output: [[2,3],[3]]
 

Constraints:

The number of the nodes in the tree will be in the range [1, 10^4]
-200 <= Node.val <= 200
  *
  */



/**
  * to find the duplicate subtrees, postTraversal
  * Trick:
  *     null => "#",
  *     String concate by ","
  */
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        HashMap<String, Integer> memo = new HashMap<>();
        findDuplicateSubtrees(root, res, memo);
        return res;
    }

    private String findDuplicateSubtrees(TreeNode root,
        List<TreeNode> res,
        HashMap<String, Integer> memo
    ) {
        if(root == null) return "#";
        String left = findDuplicateSubtrees(root.left, res, memo);
        String right = findDuplicateSubtrees(root.right, res, memo);
        String tree = left + "," + right + "," + root.val;
        int freq = memo.getOrDefault(tree, 0);
        if(freq == 1) {
            res.add(root);
        }
        memo.put(tree, freq + 1);
        return tree;
    }
}
// @lc code=end


{/*
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
/**
 * ## anaylysis
 * - Time O(N)
 * - Space O(N)
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


/***
 * 1. while is shorter than for loop
 * 2. dont' have to handle the tmp is null case once you handle the left, right and root.
 * 3. size-- > 0
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size>0) {
                TreeNode tmp = queue.poll();
                list.add(tmp.val);
                if(tmp.left != null) queue.offer(tmp.left);
                if(tmp.right != null) queue.offer(tmp.right);
                size--;
            }
            ans.add(list);
        }
        return ans;
    }
}


/**
 * sure, we'll have the more verbose recursivve value
 */

 /**
  * 1. one more map to map the level to the list
  */

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Map<Integer, List<Integer>> map = new HashMap<>();
        helper(root, map, ans, 0);
        return ans;
    }

    private void helper(TreeNode node, Map<Integer, List<Integer>> map,
        List<List<Integer>> ans, int level) {
            if(map.get(level) == null) {
                List<Integer> list = new ArrayList<>();
                ans.add(list);
                map.put(level, list);
            }
            List<Integer> newList = map.get(level);
            newList.add(node.val);
            if(node.left != null) {
                helper(node.left, map, ans, level+1);
            }
            if(node.right != null) {
                helper(node.right, map, ans, level+1);
            }
    }
}
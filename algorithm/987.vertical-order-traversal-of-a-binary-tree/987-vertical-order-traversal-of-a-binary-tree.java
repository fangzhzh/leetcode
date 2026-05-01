/*
 * @lc lang=java
 *
 * [987] Vertical Order Traversal of a Binary Tree
 */


 /**
  * 
  Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

 

Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        Queue<TreeNode> queue =  new LinkedList<>();
        Queue<Integer> cols =  new LinkedList<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        queue.offer(root);
        cols.offer(0);
        while(!queue.isEmpty()) {
            int len = queue.size();
            Map<Integer, List<Integer>> levelMap = new HashMap<>();
            for(int i = 0; i < len; i++) {
                TreeNode cur = queue.poll();
                int col = cols.poll();
                if(min > col) {
                    min = col;
                }
                if(max < col) {
                    max = col;
                }
                if(!levelMap.containsKey(col)) {
                    levelMap.put(col, new ArrayList<>());
                }
                
                List<Integer> list= levelMap.get(col);
                list.add(cur.val);
                if(cur.left != null) {
                    queue.offer(cur.left);
                    cols.offer(col-1);
                }
                if(cur.right != null) {
                    queue.offer(cur.right);
                    cols.offer(col+1);
                }
            }

            for(int i : levelMap.keySet()) {
                List<Integer> curlist = levelMap.get(i);
                Collections.sort(curlist);
                if(!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                }
                List<Integer> list = map.get(i);
                list.addAll(curlist);
            }
        }
        
        List<List<Integer>> results = new ArrayList<>();
        for(int i = min; i <= max; i++) {
            List<Integer> list = map.get(i);
            results.add(list);
        }
        return results;
    }
}




/**
 * This question is overquestioning.
 * It's bfs traversal, vertical traversal and a level traversal.
 * The code is long, tedious and error-prone.
 */
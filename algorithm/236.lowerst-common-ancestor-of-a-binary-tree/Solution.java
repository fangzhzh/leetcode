import java.util.*;
///**
// * Definition for a binary tree node.
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
// */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> map = new HashMap<>();	
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        map.put(root, null);
        TreeNode cur;
        while(!map.containsKey(p) || !map.containsKey(q) ) {
            cur = queue.poll();
            if(cur.left != null) {
                queue.offer(cur.left);
                map.put(cur.left, cur);
            }
            if(cur.right != null) {
                queue.offer(cur.right);
                map.put(cur.right, cur);
            }
        }
        Set<TreeNode> parents = new HashSet<>();
        while(p != null) {
            parents.add(p);
            p = map.get(p);
        }
        while(q != null && !parents.contains(q)) {
            q = map.get(q);
        }
        return q;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;
public class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if( root == null 
				|| p == null
				|| q == null) {
			return null;
				}
		Set<Integer> set = new HashSet<>();
		TreeNode cur = root;
		while(cur != null) {
			set.add(cur.val);
			if(cur.val < p.val) {
				cur = cur.right;
			} else if(cur.val > p.val) {
				cur = cur.left;
			} else {
				break;
			}
		}
		if( cur == null) {
			return null;
		}

		cur = root;
		TreeNode lca = root;
		while(cur != null) {
			if(set.contains(cur.val)) {
				lca = cur;
			}
			if(cur.val < q.val) {
				cur = cur.right;
			} else if(cur.val > q.val) {
				cur = cur.left;
			} else {
				break;
			}
		}
		return lca;
	}
}

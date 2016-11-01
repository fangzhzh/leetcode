public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {
		val = x; 
	}
}
public class Solution {
	
	public int sumOfLeftLeaves(TreeNode root) {
		if(root == null || 
		(root.left == null && root.right == null )) return 0;
		Queue<TreeNode> queue = new LinkedList<>();
		TreeNode cur;
		queue.offer(root);
		int sum = 0;
		while( queue.size() > 0) {
			cur = queue.poll();
			if(cur.left == null && cur.right == null ) sum += cur.val;
			if(cur.right != null) {
				queue.offer(cur.right);
				if(cur.right.left == null
				&& cur.right.right == null)
				sum -= cur.right.val;	
			}
			if(cur.left != null) queue.offer(cur.left);
		}
	}
}	
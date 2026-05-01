public class BSTIterator {
	Stack<TreeNode> stack = new Stack<>();

	public BSTIterator(TreeNode root) {
		if(root == null) return;
		TreeNode cur = root;
		stack.push(cur);
		while(cur.left!=null) {
			stack.push(cur.left);
			cur = cur.left;
		}
	}
	/** @return whether we have a next smallest number */
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	/** @return the next smallest number 
     * If the returned TreeNode doesn't have a right branch, it is O(1). If it has a right branch, it will traversal until its right child's left-most TreeNode. Now, considering the code under "// traversal right branch". After I use next() to go through the entire tree once, this part of code will traversal each node with a right child once. The total run time of this part is O(n). And since I go through the entire tree, the average next() run time is O(n) / n = O(1)
     * */
	public int next() {
		TreeNode node = stack.pop();
		if(node.right != null) {
			stack.push(node.right);
			TreeNode cur = node.right;
			while(cur.left != null) {
				stack.push(cur.left);
				cur = cur.left;
			}
		}
		return node.val;
	}
}
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */


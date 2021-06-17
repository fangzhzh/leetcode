public class DFS {
	
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
  };
 
	// recursive
	public void preDFS(TreeNode root) {
		if(root == null) return;
		System.println.out(root.val);
		if( root.left != null ) preDFS(root.left);
		if( root.right != null ) preDFS(root.right);
	}

	public void inDFS(TreeNode root) {
		if(root == null) return;
		if(root.left != null) inDFS(root.left);
		System.println.out(root.val);
		if(root.right != null) inDFS(root.right);
	}
	
	public void postDFS(TreeNode root) {
		if(root == null) return;
		if(root.right != null) postDFS(root.right);
		if(root.left != null) postDFS(root.left);
		System.println.out(root.val);
	}
	
	
	// non recursive
	public void preDFS_norecursive (TreeNode root){
		Deque<TreeNode> st = new ArrayDeque<>();
		TreeNode cur;
		st.offer(root);
		while(st.size() != 0) {
			cur = st.pool();
			System.println.out(cur.val);
			
			if(cur.right != null) st.offer(cur.right);
			if(cur.left != null) st.offer(cur.left);
		}
	}
	
	public void inDFS_norecursive(TreeNode root) {
		Deque<TreeNode> st = new ArrayDeque<>();
		TreeNode cur = root;
		st.offer(root);
		while(st.size() != 0) {
			if(cur.left != null) {
				st.offer(cur.left);
				cur = cur.left;
			} else {
				cur = st.pool();
				visit(cur);
				if(cur.right != null) {								st.offer(cur.right);
				}
			}
		}
		
	}
	
	public void postDFS_nonRecursive(TreeNode root) {
		Deque<TreeNode> st = new ArrayDeque<>();
		st.push(root);
		TreeNode cur;
		while(st.size() != 0) {
			
		}
	}
	
	private void visit(TreeNode node) {
		System.println.out(node.val);
	}
	
	
	//
	public List<String> binaryTreePaths(TreeNode root) {
		
	}
}
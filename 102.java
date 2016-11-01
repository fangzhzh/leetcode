public List<List<Integer>> levelOrder2(TreeNode root) {
	List<List<Integer>> results = new ArrayList<>();
	Queue<TreeNode> q = new LinkedList<>();
	q.add(root);
	while(q.size() > 0) {
		List<Integer> result = new ArrayList<>();
		int size = q.size();
		for(int i = 0; i < size; ++i) {
			TreeNode cur = q.peek();
			result.add(cur.val);
			if(cur.left != null) q.add(q.left);
			if(cur.right != null) q.add(q.right);
		}
		results.add(result);
	}

}

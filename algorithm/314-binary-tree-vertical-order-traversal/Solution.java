/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if(root==null)  return result;
		Map<Integer, List<Integer>> map = new HashMap<>();
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<Integer> cols = new LinkedList<>();
		int min=0, max=0;

		queue.offer(root);
		cols.offer(0);

		while(!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			int col = cols.poll();
			if(!map.containsKey(col)){
				map.put(col, new ArrayList<>());
			}
			List<Integer> l = map.get(col);
			l.add(cur.val);
			min=Math.min(min, col);
			max=Math.max(max, col);
			if(cur.left!=null) {
				queue.offer(cur.left);
				cols.offer(col-1);
			}
			if(cur.right!=null) {
				queue.offer(cur.right);
				cols.offer(col+1);
			}
		}
		for(int i=min;i<=max;++i){
			result.add(map.get(i));
		}
		return result;
    }
}

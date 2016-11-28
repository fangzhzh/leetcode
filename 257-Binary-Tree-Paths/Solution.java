public class Solution {
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode cur = root;
        while(cur!=null) {
            while(cur.left!=null){
                stack.push(cur.left);
                cur = cur.left
            }

        }
		return result;	
	}
}	

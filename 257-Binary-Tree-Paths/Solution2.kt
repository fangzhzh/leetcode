/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
import java.util.Stack
class Solution {
    fun binaryTreePaths(root: TreeNode?): List<String> {
        val list = ArrayList<String>()
        if(root == null) {
            return list
        }
        val stack = Stack<TreeNode>()
        val paths = Stack<String>()
        var curNode: TreeNode? = null

        stack.push(root)
        var path = ""
        while(!stack.isEmpty()) {
            curNode = stack.pop()
            if(!paths.isEmpty()) {
                path = paths.pop()
            }
            if(curNode.left == null && curNode.right == null) {
                list.add(path + curNode.`val`)
            }
            if(curNode.right != null) {
                paths.push(path + curNode.`val` + "->")
                stack.push(curNode.right)
            }
            if (curNode.left != null) {
                paths.push(path + curNode.`val` + "->")
                stack.push(curNode.left)
            }

        }
        return list
    }
}

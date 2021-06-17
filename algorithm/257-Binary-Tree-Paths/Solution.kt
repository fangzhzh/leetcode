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
class Solution {
    fun binaryTreePaths(root: TreeNode?): List<String> {
        if(root == null) {
            return listOf()
        }
        val result = ArrayList<String>()
        
        helper(root, "", result)
        return result
    }
    
    fun helper(node: TreeNode, path: String, paths: ArrayList<String>) {
        if(node.left == null && node.right == null) {
            paths.add("$path${node.`val`}")
            return
        }
        if(node.left != null) {
            helper(node.left, "$path${node.`val`}->", paths)
           
        }
        if(node.right != null) {
            helper(node.right, "$path${node.`val`}->", paths)
           
        }
    
         
    }
}

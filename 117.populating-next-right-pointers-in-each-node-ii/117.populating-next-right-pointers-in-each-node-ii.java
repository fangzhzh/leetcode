/*
 * @lc app=leetcode id=117 lang=java
 *
 * [117] Populating Next Right Pointers in Each Node II
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

/**Given a binary tree

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 

Example 1:



Input: root = [1,2,3,4,5,null,7]
Output: [1,#,2,3,#,4,5,7,#]
Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 

Constraints:

The number of nodes in the given tree is less than 6000.
-100 <= node.val <= 100 

*/

/**
 * idea is level traversal
 * 
 * queue is the best choice, and it makes the solution easy
 * But it's not a constant space
 * 
 * 
 */

/**
 * the first solution is a very smart but not easy to understand one, especially the tempChild one
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/discuss/37811/Simple-solution-using-constant-space
 */ 
class Solution {
    public Node connect(Node root) {
        Node node = root;
        while(node != null){
            Node tempChild = new Node(0);
            Node currentChild = tempChild;
            while(node!=null){
                if(node.left != null) { 
                    currentChild.next = node.left; 
                    currentChild = currentChild.next;
                }
                if(node.right != null) { 
                    currentChild.next = node.right; 
                    currentChild = currentChild.next;
                }
                node = node.next;
            }
            node = tempChild.next;
        }
        return root;
    }
}
// @lc code=end



/**
 * this solution is a intuitive one,
 * 
 * High level idea is scan a level and populate next level's next
 * 
 * head point to first element of next level, pre points to the prevois node of this level
 * 
 * Details and care is needed in this solution.
 */
class Solution {
    public Node connect(Node root) {
        Node head = null, prev = null;
        Node cur = root;
        while(cur != null) {
            while(cur != null) {
                if(cur.left != null) {
                    if(prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                if(cur.right != null) {
                    if(prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            prev = null;
            head = null;
        }
        return root;
    }
}
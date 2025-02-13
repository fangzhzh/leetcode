/*
 * @lc app=leetcode id=430 lang=java
 *
 * [426] Convert Binary Search Tree to Sorted Doubly Linked List
 * Description
Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

bstdlloriginalbst

We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.

bstdllreturndll

Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

bstdllreturnbst

Only $39.9 for the "Twitter Comment System Project Practice" within a limited time of 7 days!

WeChat Notes Twitter for more information（WeChat ID jiuzhangfeifei）


Example
Example 1:

Input: {4,2,5,1,3}
        4
       /  \
      2   5
     / \
    1   3
Output: "left:1->5->4->3->2  right:1->2->3->4->5"
Explanation:
Left: reverse output
Right: positive sequence output
Example 2:

Input: {2,1,3}
        2
       /  \
      1   3
Output: "left:1->3->2  right:1->2->3"
Related Knowledge
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        // Write your code here.
        TreeNode tail = helper(root);
        TreeNode head = tail;
        // TreeNode cur = tail;
        while(head.left != null) {
            head = cur.left;
        }
        tail.right = head;
        head.left = tail;
        return head;    
    }
    private TreeNode helper(TreeNode root) {
        if(root == null) return null;
        TreeNode left = treeToDoublyList(root.left);
        if(left != null) {
            left.right = root;
            root.left = left;
        }
        TreeNode right = treeToDoublyList(root.right);
        if(right != null) {
            root.right = right;
            right.left = root;
            return right;
        }
        return root;
    }
}
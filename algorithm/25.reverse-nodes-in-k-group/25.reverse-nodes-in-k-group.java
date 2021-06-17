/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

Follow up:

Could you solve the problem in O(1) extra memory space?
You may not alter the values in the list's nodes, only nodes itself may be changed.
 

Example 1:


Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:


Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Example 3:

Input: head = [1,2,3,4,5], k = 1
Output: [1,2,3,4,5]
Example 4:

Input: head = [1], k = 1
Output: [1]
 *  */ 


/**
 * 
 * non-typical recursive
 * recursive & loop
 * my first try solution fails at this loop part
 * 1. if less then k, return  head, otherwise,
 * 2. reverse the following link, and get the new head as pre
 * 3. reverse the k node.
 *  */ 
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = head;
        for(int i = 0; i < k; i++) {
            if(node == null) {
                return head;
            }
            node = node.next;
        }
        ListNode pre = reverseKGroup(node, k);

        for(int i = 0; i < k; i++){
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }
}
// @lc code=end


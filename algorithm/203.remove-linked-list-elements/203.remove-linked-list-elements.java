/*
 * @lc app=leetcode id=203 lang=java
 *
 * [203] Remove Linked List Elements
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
class Solution {
    // recursive, for current node, we cansider different cases
    // Time O(n), 
    // space O(n), no extra space, but n stack
    public ListNode removeElements(ListNode head, int val) {
        // null, return null
        if(head == null) return null;
        // head has the val, remote had, return removeElements(remaining)
        if(head.val == val) return removeElements(head.next, val);
        // head is not the val, head.next = removeElements(remaining), return head
        ListNode next = removeElements(head.next, val);
        head.next = next;
        return head;
    }
    // iterative Time O(n), Space O(1)
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        // remove leading val nodes
        while(head.val == val) {
            head = head.next;
            if(head == null) return null;
        }
        // process middle nodes
        ListNode cur = head;
        ListNode prev = head;
        while(cur != null) {
            if(cur.val == val) {
                cur = cur.next;
                prev.next = cur;
                continue;
            }
            prev = cur;
            cur = cur.next;
        }
        return head;
    }

}
// @lc code=end


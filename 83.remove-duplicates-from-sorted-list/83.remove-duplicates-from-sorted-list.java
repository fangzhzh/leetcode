/*
 * @lc app=leetcode id=83 lang=java
 *
 * [83] Remove Duplicates from Sorted List
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
    // recursive: TimeO(n),  n is the length of list
    // space O(n), n is the stack space
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        if(head.next == null) return head;
        if(head.val == head.next.val) return deleteDuplicates(head.next);
        ListNode next = deleteDuplicates(head.next);
        head.next = next;
        return head;
    }

    // non recurisve Time O(n)
    // Space O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return null;
        ListNode cur = head;
        ListNode next = cur.next;
        while(next != null) {
            if(cur.val == next.val) {
                next = next.next;
                cur.next = null;
                continue;
            }
            cur.next = next;
            cur = next;
            next = next.next;
        }
        return head;
    }
}
// @lc code=end


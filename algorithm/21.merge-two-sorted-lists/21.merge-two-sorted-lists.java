/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // method 1: recursive Time O(n), n is the total nodes of two lists, space O(n), no extra space, but the stack
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val) {
            ListNode next = l1.next;
            l1.next = mergeTwoLists(next, l2);
            return l1;
        }  else {
            ListNode next = l2.next;
            l2.next = mergeTwoLists(next, l1);
            return l2;
        }
    }
    // method2: non recursive
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(), cur = head;
        while(l1 != null || l2 != null) {
            if(l2 == null) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
                continue;
            }
            if(l1 == null) {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
                continue;
            }
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        return head.next;
    }

    }
}
// @lc code=end


/*
 * @lc app=leetcode id=61 lang=java
 *
 * [61] Rotate List
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
    public ListNode rotateRight(ListNode head, int k) {
        if (head==null||head.next==null) return head;
        ListNode dummy = new ListNode();
        dummy.next = head;
        int size = 0;
        ListNode fast = dummy;
        while(fast != null && fast.next != null) {
            fast = fast.next;
            size++;
        }
        k = k % size;
        ListNode slow = dummy;
    

        for(int i = 0; i < size - k; i++) {
            slow = slow.next;
        }
        
        fast.next = dummy.next;
        dummy.next = slow.next;
        slow.next = null;

        return dummy.next;
    }
}
// @lc code=end


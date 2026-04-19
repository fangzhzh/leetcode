/*
 * @lc app=leetcode id=19 lang=java
 *
 * [19] Remove Nth Node From End of List
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
// TC O(n)
// SC O(1)

// 1. slow, fast pointer
//      1.1 fast pointer move n steps first
//      1.2 then slow pointer move together with fast pointer
// 2. dummy for edge case
// 这个走了n步，然后在while里判断fast.next != null, 不是很好理解，第二个更好的方案
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        for(int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while(fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode tmp = slow.next;
        if(tmp == null) {
            slow.next = null;
        } else {
            slow.next = tmp.next;
        }
        return dummy.next;
    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 删除倒数第n个节点，找到倒数第n+1个节点
        ListNode newHead = new ListNode();
        newHead.next = head;

        ListNode fast = newHead;
        for(int i = 0; i < n+1; i++) {
            fast = fast.next;
        }
        // 
        ListNode slow = newHead;
        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        if(slow.next != null) {
            slow.next = slow.next.next;
        }
        return newHead.next;
}
// @lc code=end


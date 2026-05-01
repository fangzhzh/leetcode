/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = revertList(slow.next, null);
        slow = slow.next;
        while(slow != null) {
            if(head.val != slow.val)
                return false;
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    ListNode revertList(ListNode node, ListNode head) {
        if(node == null) {
            return head;
        }
        ListNode next = node.next;
        node.next = head;
        return revertList(next, node);
    }
}

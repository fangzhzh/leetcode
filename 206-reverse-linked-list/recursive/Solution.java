/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	public ListNode reverseList(ListNode head) {
		return reverseIn(head, null);
	}

	ListNode reverseIn(ListNode head, ListNode newHead) {
		if(head == null) return null;
		ListNode next=head.next;
		head.next=newHead;
		if(next==null) return head;
		return reverseIn(next, head);
	}
}

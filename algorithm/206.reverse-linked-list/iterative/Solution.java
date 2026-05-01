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
		if(head == null) return null;
		ListNode cur = head;
		ListNode next = cur.next;
		head.next = null;
		while(next != null) {
			ListNode nn = next.next;
			next.next = cur;
			cur = next;
			next = nn;
		}
		return cur;
	}
}

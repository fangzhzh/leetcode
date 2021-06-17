/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }
    ListNode reverseList(ListNode cur, ListNode prev) {
        if(cur == null) {
            return prev;
        }
        ListNode next = cur.next;
        cur.next = prev;
        return reverseList(next, cur);
    }
}

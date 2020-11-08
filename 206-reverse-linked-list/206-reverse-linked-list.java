/*
 * @lc id=206 lang=java
 *
 * [206] Reverse Linked List
 */

 /**
  * Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
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
  * recursive, node == null, return parent
  */
class Solution {
    public ListNode reverseList(ListNode head) {
        return reverseList(head, null);
    }
    private ListNode reverseList(ListNode node, ListNode parent) {
        if(node == null) {
            return parent;
        }
        ListNode tmp = node.next;
        node.next = parent;
        return reverseList(tmp, node);
    }
}
// @lc code=end

/*
 * @lc  id=206 lang=java
 *
 * [206] Reverse Linked List
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
 * ## iterative
 * - take care of the cur, and iterate
 * cur.next = pre; 
 * don't update next in current loop
 *  */  
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode newHead = head;
        ListNode previous = null;
        while(cur != null) {
            ListNode next = cur.next;
            cur.next = previous;
            previous = cur;
            cur = next;
        }
        return previous;
    }
    
}
// @lc code=end



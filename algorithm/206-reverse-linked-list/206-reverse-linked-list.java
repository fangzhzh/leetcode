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

Example 2: 

Input 1->2->NULL
Output: 2->1->NULL

Example 3:

Input: head = []
Output: []

Constraints:

* The number of nodes in the list is the range [0, 5000].
* -5000 <= Node.val <= 5000
*/


// side note: refer the Recursive to learn the differences, pros and cons between the topdown and bottom-up

/**
 * 2025-02-07
 * Top down: tail-recursive
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        // linked list: prev -> cur -> next
        return reverseList(null, head);
    }

    // Top down, every iteration, the parent sub problem is resolved, we only need to take care of the sub problems.

    // every recursive, iteration, we split the list into two parts
    // | reverted linked list| -> Cur -> |linked list needs to be reverted|
   /*
    * 1. Problem resolved iteratively in top-down manner
    * 2. Parent subproblem is resolved before moving to child subproblem
    * 3. The base case is the last node, and the result is built as the recursion progresses.
    */
    private ListNode reverseList(ListNode prev, ListNode cur) {
        if(cur.next == null) {
            cur.next = prev;
            return cur;
        }
        ListNode next = cur.next;
        cur.next = prev;
        return reverseList(cur, next);
    }
}

/*
 * 2025-02-07
 * bottom-up: Non-tail-recursive
 */
class Solution {
    ListNode nHead;
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        // linked list: prev -> cur -> next
        reverseList(null, head);
        return nHead;
    }

   // bottom up, it goes into the very bottom(end node), then level ups to solve sub problem
   /*
    * Bottom-up
    * 1. go to deepest level(end node)
    * 2. The base case is the last node, and the result is built as the recursion unwinds.
    * 3. the reversed list is built as the recursion returns
    */
    private ListNode reverseList(ListNode prev, ListNode cur) {
        if(cur.next == null) {
            cur.next = prev;
            nHead = cur;
            return cur;
        }
        ListNode next = reverseList(cur, cur.next);
        cur.next = prev;
        next.next = cur;
        return cur;
    }
}

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



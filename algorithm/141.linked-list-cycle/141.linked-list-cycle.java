/*
 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    // method1, set as cache, if see the same node, hasCycle true
    // Time O(n), space O(n)
    public boolean hasCycle(ListNode head) {
        Set<ListNode> cache = new HashSet<>();
        while(head != null) {
            if(!set.contains(head)) {
                set.add(head);
                head = head.next;
            } else {
                return true;
            }
        }
        return false;
    }

    // method 2 fast and slow pointer
    // Time O(n), space O(1)
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode slow = head, fast = head.next;
        while(slow != fast) {
            if(fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
// @lc code=end


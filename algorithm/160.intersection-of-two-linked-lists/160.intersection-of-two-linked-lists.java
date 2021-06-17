/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    // method 1: hash set
    // Time O(n), Space O(n)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // hash set as cache
        Set<ListNode> set = new HashSet<>();
        while(headA != null || headB != null) {
            if(headA != null) {
                if(set.contains(headA)) {
                    return headA;
                } else {
                    set.add(headA);
                }
                headA = headA.next;
            }
            if(headB != null) {
                if(set.contains(headB)) {
                    return headB;
                } else {
                    set.add(headB);
                }
                headB = headB.next;
            }
        }
        return null;
        
    }

    // method 2, two pointers
    // The issue of the problem is that two linked list might has different distance from head to the intersection, our algorithm is to make them reach to the point
    // Time O(n), Space O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA, pB = headB;
        while(pA != pB) {
            System.out.println("visit pA " + pA.val + " pb:" + pB.val);
            pA = pA == null? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
// @lc code=end


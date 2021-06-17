/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
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

/***
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 */



/**
 * most intuitive solution, k idx, find min and update the list, 
 * 
 * dummy as a virtual head, the dummy.next is the real next of the new list
 * 
 *  */ 
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        ListNode[] idxs = new ListNode[lists.length];
        int idx = 0;
        while(true) {
            ListNode min = null;
            int minIdx = -1;
            for(int i = 0; i < lists.length; i++) {
                if(lists[i] == null) {
                    continue;
                }
                if(min == null || min.val > lists[i].val) {
                    min = lists[i];
                    minIdx = i;
                }
            }

            if(min == null && minIdx == -1) {
                break;
            }
  
            cur.next = min;
            cur = cur.next;

            lists[minIdx] = lists[minIdx].next;
        }
        return dummy.next;
    }
}
// @lc code=end


/**
 * Priority is nother solution, but time compleixty is not as good as mine
 * 
 * 
 */

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null||lists.size()==0) return null;

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        PriorityQueue<ListNode> queue  = new PriorityQueue<>(lists.length, 
        ( a,  b) -> a.val - b.val);

        for(ListNode node : lists) {
            if(node != null) {
                queue.offer(node);
            }
        }

        while(!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if(cur.next != null) {
                queue.offer(cur.next);
            }
        }
        return dummy.next;
    }
}
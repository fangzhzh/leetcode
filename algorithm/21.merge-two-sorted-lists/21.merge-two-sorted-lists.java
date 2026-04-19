/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
 * You are given the heads of two sorted linked lists list1 and list2.
 *
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 *
 * Return the head of the merged linked list.
 *
 * 
 *
 * Example 1:
 *
 *
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Example 2:
 *
 * Input: list1 = [], list2 = []
 * Output: []
 * Example 3:
 *
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * 
 *
 * Constraints:
 *
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
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
 * 核心思路总结：
 * 方案一：递归（黑盒委托与甩锅法则）。代码优美，但有栈溢出风险。
 * 方案二：while(||)（大包大揽的上帝视角）。需要在循环内处理所有单一链表为空的边界情况，不仅循环判断冗余，而且不利于阅读。
 * 方案三：while(&&)（经典的归并模型）。分离主要矛盾，只在双方都有数据时比较大小，任一方耗尽后，利用链表O(1)拼接特性直接收尾。
 */
class Solution {
    // 终极优化版：方案三（归并思想，利用链表特性）
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        
        // 1. 公平对抗阶段
        while(list1 != null && list2 != null) {
            if(list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        
        // 2. 打扫战场阶段（仅需O(1)，直接拼合剩余的全部节点）
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;
        
        return newHead.next;
    }

    /* 方案一：递归法
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l2.next, l1);
            return l2;
        }
    }
    */
}
// @lc code=end

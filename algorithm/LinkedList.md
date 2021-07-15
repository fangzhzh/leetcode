# Linked List

Most of the linked list problem can be resolved by recursive because of it's recursive nature, Node and Node.next.

链表基本问题，就是增删改查

链表的问题基本上都能递归解决，即使不是递归解决，也都要理清楚每一步指针的状态

## API
* 反转链表 reverseList
* 找中间节点 middleNode
* 合并列表 mergeList

很多题目可以组合这几个api来完成任务
## 经典题目
* [206. 反转链表](./206-reverse-linked-list/)
* [21. 合并两个有序链表](./21.merge-two-sorted-lists)
* [141. 环形链表](./141.linked-list-cycle/)
* [83. 删除排序链表中的重复元素](./83.remove-duplicates-from-sorted-list)
* [234. 回文链表](./234-palindrome-linked-list/234.md)
* [160. 相交链表](./160.intersection-of-two-linked-lists/)
* [203. 移除链表元素](./203.remove-linked-list-elements)
* [237. 删除链表中的节点](./237.delete-node-in-a-linked-list)
* [876. 链表的中间结点](./876.middle-of-the-linked-list)
    * also check the [LinkedList](./LinkedListFastSlow.md) slow and fast pointer analysis
* [143. 重排链表]



### 经典问题解析

143. 重排链表

```
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:

给定链表 1->2->3->4, 重新排列为 1->4->2->3.
示例 2:

给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
```

```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode reversed = reverseList(mid.next);
        mid.next = null;
        ListNode newHead = mregeList(head, reversed);
        return newHead;
    }
}
```
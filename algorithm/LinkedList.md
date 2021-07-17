# Linked List

Most of the linked list problem can be resolved by recursive because of it's recursive nature, Node and Node.next.

链表基本问题，就是增删改查

链表的问题基本上都能递归解决，即使不是递归解决，也都要理清楚每一步指针的状态

## API
* 反转链表 reverseList
    * O(n)
* 找中间节点 middleNode
    * O(n)
    * 中间节点的找法可以有两种
        * middleNode(ListNode head); // 只能找一次，
        * middleNode(ListNode head, ListNode end); // 可以处理流数据，可以像二分查找一样，找出中点，然后对[left, mid), (mid, end)再次查找，代码演示
* 合并列表 mergeList
    * O(m+n)
* dfs+O(n)遍历 
    * 参考109. 有序链表转换二叉搜索树 题解
    ```
    curNode; dfsO_n(head) { 
        dfsO_n(?); 
        process(curNode); 
        curNode = curNode.next; 
        dfsO_n(?)
    }
    ```

### middleNode模板    

```java
    // [head, end]
    ListNode middleNode(ListNode head, ListNode end) {
        if(head == end) return head;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
```
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

* 143.重排链表

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


* 109.有序链表转换二叉搜索树
```
    给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

    示例:

    给定的有序链表： [-10, -3, 0, 5, 9],

    一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
        0
        / \
        -3   9
        /   /
      -10  5
```

```java
class Solution {
    ListNode curNode;
    public TreeNode sortedListToBST(ListNode head) {
        curNode = head;
        int length = getLength(head);
        return buildTree(0, length - 1);
    }
    public int getLength(ListNode head) {
        int ret = 0;
        while (head != null) {
            ++ret;
            head = head.next;
        }
        return ret;
    }
    /*
    为什么 curNode = curNode.next
    因为这是dfs，代码第一次到达`curNode = curNode.next`的时候，是调用buildTree(left, mid-1)来build左子树。我们用链表节点第一个元素创建左子树。然后调用`curNode = curNode.next`，curNode指向了第二个元素，这个元素会复制给左节点的父节点。然后`curNode = curNode.next`再次被调用，curNode指向第三个元素。
*/
    // 链表+dfs
    // Time O(n), space O(n LogN)
    public TreeNode buildTree(int left, int right) {
        if (left > right) { // [left, rigth]
            return null;
        }
        //[left+right]/2 向下取整，两个值，选左边的值为mid
        //[left+right+1]/2 向上取整，两个值，选右边的值为mid
        int mid = (left + right ) / 2; 
        TreeNode root = new TreeNode();
        root.left = buildTree(left, mid - 1);
        root.val = curNode.val;
        curNode = curNode.next;
        root.right = buildTree(mid + 1, right);
        return root;
    }
}

```
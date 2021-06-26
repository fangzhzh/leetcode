# leetcode
## 算法复盘
* 1 完全没有思路 SSH方法
    * Similar：找到解决过的相似题目：
    * Smaller：解决一道更简单的题目
    * High level：假定子问题已经被解决
* 2 做过的题做不出来
    * 需要进行总结和回顾算法以及数据结构
* 看不懂题解
    * 先从简单和中等的题目开始，有的题目可能涉及比较多子问题或者比较多数据结构，需要懂得拆分问题。
* 有思路但是写不对
    * 一般情况下是思路不够清晰，一些细的关键点没有想清楚，或者对于编程语言的熟悉不够

## 递归
* [394. 字符串解码](./394.decode-string/394.md)
* [排序算法](./sort.md)
## 分治

## Linked List
* [Linked List](./LinkedList.md)
    * [206. 反转链表](./206-reverse-linked-list/)
    * [21. 合并两个有序链表](./21.merge-two-sorted-lists)
    * [141. 环形链表](./141.linked-list-cycle/)
    * [83. 删除排序链表中的重复元素](./83.remove-duplicates-from-sorted-list)
    * [234. 回文链表](./234-palindrome-linked-list/234.md)
    * [160. 相交链表](./160.intersection-of-two-linked-lists/)
    * [203. 移除链表元素](./203.remove-linked-list-elements)
    * [237. 删除链表中的节点](./237.delete-node-in-a-linked-list)
    * [876. 链表的中间结点](./876.middle-of-the-linked-list)
        * also check the [LinkedList](./LinkedList.md) slow and fast pointer analysis
## Binary Tree
* [二叉树](./binaryTree.md)
    * [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)
    * [104. 二叉树的最大深度](./104.maximum-depth-of-binary-tree/104.md/)
    * [101. 对称二叉树](./110.balanced-binary-tree/)
    * [617. 合并二叉树](./617.merge-two-binary-trees/)
    * 二叉树比较(相同，对称，镜像)
        * [100. 相同的树](./100.same-tree)
        * [101. 对称二叉树](./101.symmetric-tree)
## Binary Search 
* [二分查找](./binarySearch.md)

## Stack
* [栈](./stack.md)

## Hash/double pointers
* [哈希表和双指针](./hashTwoPointers.md)
    * []
## Dynamic programming
# [动态规划](./dynamicProgramming.md)
## String

## Binary


## Miscs
* int加减乘除的题目处理溢出

    处理溢出情况，通常要处理下次迭代溢出， x > MAX_VALUE || x < MIN_VALUE
    比如，7 整数反转这个题目，每次迭代就是 x *= 10， 所以 这里的条件是
    res < Integer.MIN_VALUE/10 || res > Integer.MAX_VALUE / 10


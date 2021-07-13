# 移动链表节点相关总结

移动链表节点的题目包括但不限于
* 排序
    * 86.分隔链表   
    * 148.排序链表
* 反转
    * 92.反转链表 II
* 删除
    * 82.删除排序链表中的重复元素 II
    * 19.删除链表的倒数第 N 个结点


和数组移动不同，数组移动只需要两个idx即可 
```java
void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}
```
要在链表移动节点，即移动source节点到target节点,并没有真正的移动，而是改变了指针指向，因为要改变指针指向，就需要知道节点的父节点。

所以移动，涉及到四个节点
* source parent
* source
* target
* target parent

source parent -> **source** -> source next -> ... -> target parent -> **target** -> target.next -> ... -> null

source parent -> **target** -> source.next -> ... -> target parent -> **source** -> target.next -> ... -> null




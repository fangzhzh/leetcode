# priorityQueue API

`Priority Queue is ordered as per their natural ordering or based on a custom Comparator supplied at the time of creation.`

**有限队列是一个小顶堆**

    [3]             offer(3)
    [2,3]           offer(2)
    [2,3,5]         offer(5)
    [2,3,4,5]       offer(4)
    [1,2,3,4,5]     offer(1)
    -----
    1 [2,3,4,5]     poll()
    2 [3,4,5]       poll()
    3 [4,5]         poll()
    4 [5]           poll()
    5 []             poll()


优先队列的排序是按照自然排序，或者按照自定义比较符号定义的顺序。

`The front of the priority queue contains the least element according to the specified ordering, and the rear of the priority queue contains the greatest element.` 

初始化优先队列
```java
PriorityQueue<String> queue  = new PriorityQueue<>(length);
```

## 时间复杂度
* **enqueing and dequeing methods (offer, poll, remove() and add) is O(log(n))**
* **retrieval methods (peek, element, and size) O(1)**
* **remove(Object) and contains(Object) O(n)**

添加N个元素，想要得到稳定的O(n log n), 初始化的好死后需要制定长度，这样能避免queue的动态扩容。

## 比较符

```java
PriorityQueue<ListNode> queue  = new PriorityQueue<>(lists.length, 
        ( a,  b) -> a.val - b.val);
```


## [Priority 实现](./sortHeapSort.md)



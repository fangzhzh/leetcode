# priorityQueue API

`Priority Queue is ordered as per their natural ordering or based on a custom Comparator supplied at the time of creation.`

优先队列通常使用堆来实现。
## 堆
优先队列首先是一个队列，queue。实现上用堆来实现，而堆(heap)是使用数组来表示完全二叉树。

堆(Heap) 是一种特殊的完全二叉树，它满足堆属性：

- 在最小堆(小顶堆)中，父节点的值小于或等于其子节点的值
- 在最大堆(大顶堆)中，父节点的值大于或等于其子节点的值

## 示例
**优先队列是一个小顶堆**

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
#### 示例说明：
假设我们有数据：5, 3, 7, 1, 8

1. 小顶堆 中的排列（内部）：[1, 3, 7, 5, 8]
   - peek() 返回 1
   - poll() 移除并返回 1，堆变成 [3, 5, 7, 8]
2. 大顶堆 中的排列（内部）：[8, 5, 7, 1, 3]
   
   - peek() 返回 8
   - poll() 移除并返回 8，堆变成 [7, 5, 3, 1]

#### Priority Queue new comparator

PriorityQueue<ListNode> queue  = new PriorityQueue<>(lists.length, 
        ( a,  b) -> a.val - b.val);

#### PriorityQueue 比较器的简单理解方法

1. **小顶堆**（最小元素优先出队,所以留下的是最大元素）：
   ```java
   // 按照自然顺序排列（小的在前）
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
   // 或者更简单地使用默认构造
   PriorityQueue<Integer> minHeap = new PriorityQueue<>();
   ```

2. **大顶堆**（最大元素优先出队，所以留下的是最小元素）：
   ```java
   // 按照逆序排列（大的在前）
   PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
   // 或者更简单使用默认构造器
   PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
   ```

#### 直观理解方法

想象你在**排队**，比较器决定谁站在前面：

- 如果你想让**小的数**排在前面（小顶堆）：`(a, b) -> a - b`
  - [1, 3, 7, 5, 8] 中，1 排在最前面
  - 当 a < b 时，返回负数，a 排在 b 前面
  - 当 a > b 时，返回正数，b 排在 a 前面

- 如果你想让**大的数**排在前面（大顶堆）：`(a, b) -> b - a`
  - [8, 5, 7, 1, 3] 中，8 排在最前面
  - 当 a < b 时，返回正数，b 排在 a 前面
  - 当 a > b 时，返回负数，a 排在 b 前面

#### 使用 Comparator 方法

如果觉得减法不够直观，可以使用 Comparator 的方法：

```java
// 小顶堆
PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

// 大顶堆
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
```

对于自定义对象，可以这样写：

```java
// 小顶堆 - 按频率升序
PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(node -> node.freq));

// 大顶堆 - 按频率降序
PriorityQueue<Node> maxHeap = new PriorityQueue<>(Comparator.comparing(node -> node.freq, Comparator.reverseOrder()));
// 或者
PriorityQueue<Node> maxHeap = new PriorityQueue<>(Comparator.comparing((Node node) -> node.freq).reversed());
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



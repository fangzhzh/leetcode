# Binary Heap, Priority Queue & Heap Sort

在这篇文档中，我们将厘清三个经常被混用，但实际上处于不同抽象层级的概念：

1. **Binary Heap (二叉堆)**：这是一种**底层数据结构**。它是一个基于完全二叉树的逻辑结构，并且通常用一维数组来实现物理存储。它是构建 Priority Queue 和实现 Heap Sort 的基础工具。
2. **Priority Queue (优先队列)**：这是一种**抽象数据类型 (ADT)** 或者说 API 定义。它规定了我们必须能随时拿到“优先级最高”的元素。虽然可以用普通数组或链表来实现它，但由于 Binary Heap 在获取极值和插入操作上都能达到 O(log N) 的绝佳平衡，所以 **Binary Heap 是实现 Priority Queue 的最佳（且最常见）方式**。在 Java 中，`java.util.PriorityQueue` 底层就是一个基于数组的 Binary Heap。
3. **Heap Sort (堆排序)**：这是一种**算法**。它巧妙地利用了 Binary Heap 每次都能选出最大值的特性。它的本质就是：先把无序数组原地构建成一个 Binary Heap，然后不断地把堆顶的最大值换到数组末尾，最终得到一个有序数组。

简单来说：**Binary Heap (二叉堆) 是实现 Priority Queue (优先队列) 的数据结构，同时也是支撑 Heap Sort (堆排序) 算法的核心。**

---
## 二叉堆 Binary Heap

堆其实就是利用完全二叉树的结构来维护的一维数组，但堆并不一定是完全二叉树

二叉堆就是一种完全二叉树，其中父节点大于/小于其子节点。

A Binary Heap is a Complete Binary Tree where items are stored in a special order such that the value in a parent node is greater(or smaller) than the values in its two children nodes. The former is called **max heap** and the latter is called **min-heap**. The heap can be represented by a binary tree or array.

* 大顶堆：每个结点的值都大于或等于其左右孩子结点的值
* 小顶堆：每个结点的值都小于或等于其左右孩子结点的值

我们用简单的公式来描述一下堆的定义就是：（读者可以对照上图的数组来理解下面两个公式）

* 大顶堆：heap[i] >= heap[2i+1] && heap[i] >= heap[2i+2]
* 小顶堆：heap[i] <= heap[2i+1] && heap[i] <= heap[2i+2]

也就是说，大顶堆(max-heap)，小顶堆(min-heap)的是根据根节点的特性来命名的。

```
插入 (offer / push): O(log N)
删除堆顶 (poll / pop): O(log N)
查看堆顶 (peek / top): O(1)
建堆 (heapify): Top-down 是 O(N log N)，Bottom-up (从倒数第二层开始下沉) 是 O(N)。
```

**ALL the code snippet below build a max heap**
## 堆排序  Heapsort API

```java
void heapSort() {
    heapify(heap);// 建堆: swimUp or sinkDown 
    // 排序阶段：使用sinkDown
    for(int i = n - 1; i > 0; i--) {
        swap(heap, 0, i);     // 最大值放到末尾
        sinkDown(heap, 0, i); // 新root需要下沉
    }
}

void heapifySwimUp() {
    for(int i = 0; i < n; i++) {
        // element is inserted last and swim up
        swimUp(heap, i, heap[i]);
    }
}
void heapifySinkDown() {
    // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 heap.length / 2-1
    for (int i = heap.length / 2 - 1; i >= 0; i--) {
        sinkDown(heap, i, heap.length);
    }
}
/**
 * nums: the nums
 * index: insert the value into the index and just **heapify**
 * /
void swimUp(int[] nums, int index, int value)

/**
 * nums: the nums
 * index: the element at the index to sinkDown
 * size: the remaining elements need to be handled
 * 
 * 假设[index+1, size)中间已经有序，
 * 那么Sinkdown(index), 可以排序[index, size)
 * /
void sinkDown(int[] heap, int index, int size) 

```

## 上浮建堆(swimUp)
![上浮建堆过程](./graphs/bottom_up_heapify.drawio.svg)

```java
// 上浮建堆, 
void swimUp(int[] nums, int index, int value) {
    nums[index] = value;
    while(index > 0) {
        int parent = (index - 1) / 2;  // 用于大顶堆
        if(nums[parent] >= nums[index]) break;
        swap(nums, parent, index);
        index = parent;
    }
}
```

![下沉建堆过程](./graphs/top_down_heapify.drawio.svg)
```java
void sinkDown(int[] heap, int index, int size) {
    while(2*index+1 < size) {
        int left = 2*index+1;
        int right = left +1;
        int largest = left;
        if(right < size && heap[right] > heap[left]) {
            largest = right;
        }
        if(heap[index] >= heap[largest]) break;
        swap(heap, index, largest);
        index = largest;
    }
}
```

## 堆排序（使用Top-down建堆）
* 思路：
    1. 建堆阶段：使用swimUp（Top-down方式）建立初始大顶堆
    2. 排序阶段：重复执行"交换堆顶和末尾元素，并对新堆顶执行sinkDown"
* TC: O(N log N)

```java
public void heapSort(int[] heap) {
    int n = heap.length;
    // 1. only swim build heap
    heapify(heap)

    // 2. sort: swap and sink
    for(int i = n - 1; i > 0; i--) {
        swap(heap, 0, i);
        sinkDown(heap, 0, i);
    }
}
private  void heapify(int[] heap) {
    for(int i = 0; i < n; i++) {
        swimUp(heap, i, heap[i]);
    }
}
void swimUp(int[] heap, int index, int value) {
    heap[index] = value;
    while(index > 0) {
        int parent = (index - 1) / 2;
        if(heap[parent] >= heap[index]) break;
        swap(heap, parent, index);
        index = parent;
    }
}


void sinkDown(int[] heap, int index, int size) {
    while(2*index+1 < size) {
        int left = 2*index+1;
        int right = left +1;
        int largest = left;
        if(right < size && heap[right] > heap[left]) {
            largest = right;
        }
        if(heap[index] >= heap[largest]) break;
        swap(heap, index, largest);
        index = largest;
    }
}
```
## 堆排序（使用sinkDown建堆）
* 思路：
    1. 建堆阶段：使用sinkDown（Bottom-up方式）建立初始大顶堆
    2. 排序阶段：重复执行"交换堆顶和末尾元素，并对新堆顶执行sinkDown"
从最后一个非叶子节点开始，依次执行下沉操作，构建堆结构。

TC *O(N)*, 
![堆排序](./graphs/top_down_heap_sort.drawio.svg)


```java
public  void heapSort(int[] heap) {
    // 构建初始大顶堆
    heapify(heap);
    int len = heap.length-1;
    for (int i = len; i > 0; i--) {
        // 将最大值交换到数组最后
        swap(heap, 0, i);
        // 调整剩余数组，使其满足大顶堆
        sinkDown(heap, 0, i);
    }
}
// 构建初始大顶堆
private  void heapify(int[] heap) {
    // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 heap.length / 2-1
    for (int i = heap.length / 2 - 1; i >= 0; i--) {
        sinkDown(heap, i, heap.length);
    }
}
// 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
void sinkDown(int[] heap, int index, int size) {
    while(2*index+1 < size) {
        int left = 2*index+1;
        int right = left +1;
        int largest = left;
        if(right < size && heap[right] > heap[left]) {
            largest = right;
        }
        if(heap[index] >= heap[largest]) break;
        swap(heap, index, largest);
        index = largest;
    }
}
```

# Heap in Java
Java's `PriorityQueue` by default is min-heap, but can be max-heap using a custom comparator

```java 
import java.util.PriorityQueue;
import java.util.Collections;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // 1. Lambda
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b ) -> Integer.compare(b, a));


```

# 应用
Here are the common applications of min heap and max heap besides sorting:

1. Priority Queue Implementation
   - Task scheduling (highest/lowest priority tasks)
   - Process scheduling in operating systems
   - Event-driven simulations

2. Finding K-th Elements
   - Find k-th largest element (min heap)
   - Find k-th smallest element (max heap)
   - Median maintenance (using both min and max heaps)

3. Stream Processing
   - Finding median in a stream of numbers
   - Top K frequent elements
   - Running median of a data stream

4. Graph Algorithms
   - Dijkstra's shortest path algorithm (min heap)
   - Prim's minimum spanning tree algorithm (min heap)

5. Data Compression
   - Huffman coding (min heap)

6. Specific Examples from Your Leetcode Problems:
    * 215. 数组中的第K个最大元素
    * 23. 合并 K 个升序链表

The key advantage of heaps in these applications is their O(log n) time complexity for insertions and deletions while maintaining a sorted order of elements.
# 题目
* 215. 数组中的第K个最大元素
* 23. 合并 K 个升序链表

## PriorityQueue
```java
public class FixedIntPriorityQueue {
    private final int[] heap;
    private int size;
    private final int capacity;

    public FixedIntPriorityQueue(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    public boolean offer(int val) {
        if (size >= capacity) {
            // For min heap: if new value is larger than root, ignore it
            if (val <= heap[0]) {
                return false;
            }
            // Replace root with new element
            heap[0] = val;
            sinkDown(0);
        } else {
            heap[size] = val;
            swimUp(size);
            size++;
        }
        return true;
    }

    public int poll() {
        if (size == 0) throw new IllegalStateException("Queue is empty");
        int result = heap[0];
        heap[0] = heap[--size];
        if (size > 0) {
            sinkDown(0);
        }
        return result;
    }

    private void swimUp(int index) {
        int item = heap[index];
        while (index > 0) {
            int parent = (index - 1) / 2
            // For min heap: parent should be smaller than child
            if (heap[parent] <= item) break;
            heap[index] = heap[parent];
            index = parent;
        }
        heap[index] = item;
    }

    private void sinkDown(int index) {
        int item = heap[index];

        while (index * 2 +1 < size) {
            int child = (index * 2) + 1;
            int right = child + 1;
            int smallest = child;
            
            // For min heap: find smaller child
            if (right < size && heap[right] < heap[child]) {
                smallest = right;
            }
            if (item <= heap[smallest]) break;
            
            heap[index] = heap[smallest];
            index = smallest;
        }
        heap[index] = item;
    }

    public int peek() {
        if (size == 0) throw new IllegalStateException("Queue is empty");
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }
}
```
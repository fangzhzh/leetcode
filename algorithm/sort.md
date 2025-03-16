# Sort

排序用来优化运行时间，当一个题目提到能不能O(logN)，那么肯定就是要排序来减少O(n)
## Merge Sort

## Quick Sort
Hoare partition scheme O(n log n)

关于Quick Sort背后的思想[双指针](./hashTwoPointers.md)

*two pointers关键要清楚指针的定义，这个指针位置左边都是XX，包不包含当前坐标。*

```java
void quickSort(int[] array) {
    quickSort(array, 0, array.length-1);
}

void quickSort(int[] array, int left,   int right) {
    int index = partition(array, left, right);
    if (left < index - 1){ // Sort left half
        quickSort(array, left, index - 1);
    }
    if (index + 1 < right) { // Sort right half
        quickSort(array, index + 1, right);
    }
}
// 相向而行
int partition(int[] array, int left, int right) {
    int pivot = array[(left + right) / 2]; // Pick pivot point
    // (when left > right ), the left index represents the first position where all elements to its left are less than the pivot, and all elements to its right are greater than or equal to the pivot.
    while (left <= right) {
        // Find element on left that should be on right, meaning the num >= pivot
        while (array[left] < pivot)
            left++;
        // Find element on right that should be on left, meaning the num <= pivot
        while (array[right] > pivot)
            right--;

        // Swap elements, and move left and right indices
        if (left <= right) {
            swap(array, left, right); // swaps elements
            left++; // the very next element where num >= pivot
            right--;
        }
    }
    return left; // it's not returning the index of the pivot element itself, but rather the index that divides the array into two parts:
                // - Elements less than the pivot (to the left of left )
                // - Elements greater than or equal to the pivot (at left and to the right)
}

// 同向而行
// [1,4,3,5,2,7]
int partition(int[] array, int left, int right) {
    // find a index, so that array[0, index-1] < array[index] and array[index, right) >= array[index]

    int pivot = array[(left + right) / 2]; // Pick pivot point
    int curR = left;
    while(curR <= right) {
        if(array[curR] < pivot) {
            swap(array, left, curR);
            left++;
        }
        curR++;
    }
    return left;
}

// Iterative implementation of quicksort
void quickSortIter(int[] array, int left, int right) {
    Stack<Integer> stack = new Stack<>();
    
    // Push initial values
    stack.push(left);
    stack.push(right);
    
    while (!stack.isEmpty()) {
        right = stack.pop();
        left = stack.pop();
        
        int index = partition(array, left, right);
        
        if (left < index - 1) {
            stack.push(left);
            stack.push(index - 1);
        }
        
        if (index < right) {
            stack.push(index);
            stack.push(right);
        }
    }
}



void swap(int array[], int left, int right){
    int temp = array[left];
    array[left] = array[right];
    array[right] = temp;
}
```

快速排序的核心思想是“找到一个partition，那么这个partition 左边都小于目标值， 右边都不小于目标值。”

每次partition把问题区间分为三部分

* [0, left) partition前半部分，
* [left, right) 为partition后半部分
* [right, length) 待处理元素

## heap sort 堆排序
Heap sort is a comparison-based sorting technique based on Binary Heap data structure.
* 二叉堆

堆其实就是利用完全二叉树的结构来维护的一维数组，但堆并不一定是完全二叉树

* 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
* 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]

也就是说，大顶堆(max-heap)，小顶堆(min-heap)的是根据根节点的特性来命名的。此外，我们会控制入堆/出堆条件来实现某种功能。

小顶堆，如果我们每次把用比root大的值替换root，那么遍历完所有元素后，堆内就是top k最大的元素。

### 堆排序  Heapsort API

```java
void heapSort() {
    heapify(heap)// 建堆: swimUp or sinkDown 
    // 排序阶段：使用sinkDown
    for(int i = n - 1; i > 0; i--) {
        swap(heap, 0, i);     // 最大值放到末尾
        sinkDown(heap, 0, i); // 新root需要下沉
    }
}
```

[更多内容](./sortHeapSort.md)
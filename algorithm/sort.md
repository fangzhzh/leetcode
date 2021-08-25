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

void quickSort(int[] array, int left, int right) {
    int index = partition(array, left, right);
    if (left < index - 1){ // Sort left half
        quickSort(array, left, index - 1);
    }
    if (index < right) { // Sort right half
        quickSort(array, index, right);
    }
}

// 相向而行
int partition(int[] array, int left, int right) {
    int pivot = array[(left + right) / 2]; // Pick pivot point
    while (left <= right) {
        // Find element on left that should be on right
        while (array[left] < pivot)
            left++;
        // Find element on right that should be on left
        while (array[right] > pivot)
            right--;

        // Swap elements, and move left and right indices
        if (left <= right) {
            swap(array, left, right); // swaps elements
            left++;
            right--;
        }
    }
    return left;
}

// 同向而行
// [1,4,3,5,2,7]
int partition(int[] array, int left, int right) {
    int pivot = array[(left + right) / 2]; // Pick pivot point
    int curR = left;
    while(curR <= righg) {
        if(array[curR] < pivot) {
            swap(array, left, curR);
            left++;
        }
        curR++;
    }
    return left;
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

## heap sort
Heap sort is a comparison-based sorting technique based on Binary Heap data structure.
* 二叉堆

堆其实就是利用完全二叉树的结构来维护的一维数组，但堆并不一定是完全二叉树

二叉堆就是一种完全二叉树，其中父节点大于/小于其子节点。

A Binary Heap is a Complete Binary Tree where items are stored in a special order such that the value in a parent node is greater(or smaller) than the values in its two children nodes. The former is called max heap and the latter is called min-heap. The heap can be represented by a binary tree or array.

* 大顶堆：每个结点的值都大于或等于其左右孩子结点的值
* 小顶堆：每个结点的值都小于或等于其左右孩子结点的值

我们用简单的公式来描述一下堆的定义就是：（读者可以对照上图的数组来理解下面两个公式）

* 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2] 
* 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2] 


### 建堆 How to build the heap? 

Heapify procedure can be applied to a node only if its children nodes are heapified. So the heapification must be performed in the bottom-up order.
```
Input data: 4, 10, 3, 5, 1
         4(0)
        /   \
     1(1)   3(2)
    /   \
 5(3)    10(4)

The numbers in bracket represent the indices in the array 
representation of data.

Applying heapify procedure to index 1:
         4(0)
        /   \
    10(1)    3(2)
    /   \
5(3)    1(4)

Applying heapify procedure to index 0:
        10(0)
        /  \
     5(1)  3(2)
    /   \
 4(3)    1(4)
The heapify procedure calls itself recursively to build heap in top down manner.
```
![上浮建堆过程](./graphs/bottom_up_heapify.drawio.svg)
```
public void swim (int index) {
    while (index > 1 && nums[index/2] > nums[index]) {
        swap(index/2,index);//交换
        index = index/2;
    }
}
```

![下沉建堆过程](./graphs/top_down_heapify.drawio.svg)

```

public void sink (int[] nums, int index,int len) {
        while (true) {
            //获取子节点
            int j = 2 * index;
            if (j < len-1 && nums[j] < nums[j+1]) {
                j++;
            }
            //交换操作，父节点下沉，与最大的孩子节点交换
            if (j < len && nums[index] < nums[j]) {
                swap(nums,index,j);
            } else {
                break;
            } 
            //继续下沉
            index = j;
        }
    }
```


建堆我们这里提出两种方法，利用上浮操作，也就是不断插入元素进行建堆，另一种是利用下沉操作，遍历父节点，不断将其下沉，进行建堆，我们一起来看吧。

### 排序
我们已经有了大顶堆，如何排序呢，思路就是把大顶移除，然后对剩下对元素再建堆，然后移除大顶，对剩下元素再建堆，知道完成index 0的元素。

[堆排序](./graphs/top_down_heap_sort.drawio.svg)


```java
public  void heapSort(int[] arr) {
    // 构建初始大顶堆
    heapify(arr);
    int len = arr.length-1;
    for (int i = len; i > 0; i--) {
        // 将最大值交换到数组最后
        swap(arr, 0, i);
        // 调整剩余数组，使其满足大顶堆
        sink(arr, 0, i);
    }
}
// 构建初始大顶堆
private  void heapify(int[] arr) {
    // 从最后一个非叶子结点开始调整大顶堆，最后一个非叶子结点的下标就是 arr.length / 2-1
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
        sink(arr, i, arr.length);
    }
}
// 调整大顶堆，第三个参数表示剩余未排序的数字的数量，也就是剩余堆的大小
private  void sink(int[] arr, int i, int heapSize) {
    // 左子结点下标 2 * i + 1
    // 右子结点下标 2 * i + 1 + 1;
    // 记录左子树结点、右子树节点的最大值下标
    int largest = 2*i+1;
    if (largest+1 < heapSize && arr[largest] < arr[largest+1]) {
        largest++;
    }

    if(arr[i] < arr[largest]) {
        return;
    }
    // 将最大值交换为根结点
    swap(arr, i, largest);
    // 再次调整交换数字后的大顶堆
    sink(arr, largest, heapSize);
}
private  void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

```

```java
class Solution {
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    public void heapSort(int[] nums) {
        int len = nums.length - 1;
        // build 一个大顶堆
        buildMaxHeap(nums, len);
        for (int i = len-1; i > 0; --i) {
           // 移除大顶，继续build大顶堆
            swap(nums, i, 0);
            maxHeapify(nums, 0, i);
        }
    }

// [6,2,1,3,5,4,]
//   6
//   2 1

    public void buildMaxHeap(int[] nums, int len) {
       // 从第一个第一个非叶子节点，开始build heap
        for (int i = len / 2; i >= 0; --i) {
            maxHeapify(nums, i, len);
        }
    }

   // 下沉
    public void maxHeapify(int[] nums, int i, int heapSize) {
        for (; i*2 + 1 <= heapSize;) {
            int lson = i*2 + 1;
            int rson = i*2 + 2;
            int large;
            // 父左右孩子中最大的
            if (lson <= heapSize && nums[lson] > nums[i]) {
                large = lson;
            } else {
                large = i;
            }
            if (rson <= heapSize && nums[rson] > nums[large]) {
                large = rson;
            }
            // 如果i不是最大，那么需要和较大那个孩子换一下，然后，继续从换后的位置重新继续下沉
            if (large != i) {
                swap(nums, i, large);
                i = large;
            } else {
               // 此元素即为i，不需要换，本元素已经处理完
                break;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

```

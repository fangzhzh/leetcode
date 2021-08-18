# 一些常用模板


## 快速幂法
```java
    double quickMul(double x, long long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

```
## 快速乘法

采用的是倍增思想

```java
// 10*15
// ans, a, k
// 0, 10, 15
// 10, 20, 7,
// 30, 40, 3
// 70, 80, 1
// 150, 160, 0 ==> End

// 10 * 8
// ans, a, k
// 0, 10, 8
// 0, 20, 4
// 0, 40, 2
// 0, 80, 1
// 80, 160, 0 ==> End
long mul(long x, long N) {
    long ans = 0;
    while (N > 0) {
        if ((N & 1) == 1) ans += x; 
        N >>= 1;
        x += x;
    }
    return ans;
}

// 链接：https://leetcode-cn.com/problems/divide-two-integers/solution/shua-chuan-lc-er-fen-bei-zeng-cheng-fa-j-m73b/

```


## 二分模板

二分的思想是把[left, right)分为[left, mid), [mid+1, right)两部分

所以`lower_bound`是`nums[mid]>=target`时，`right = mid`,`uppder_bound`是`nums[mid]<=target`时，`left=mid+1`


## 快速排序

最重要的是其中的partition函数

```java
public class Solution {
	public int findKthLargest(int[] nums, int k) {
        k = nums.length-k;
        int low = 0, high = nums.length-1;
        while(low < high) {
            int pivot = partition(nums, low, high);
            if(pivot < k) {
                low = pivot + 1;
            } else if( pivot > k) {
                high = pivot -1;
            } else {
                break;
            }
        }
        return nums[k];
    }

        // pivot = pickPivot()
        // [0,left] <= pivot
        // [left+1, hight) > pivot
    int partition(int[] nums, int low, int high) {
        int left = low;
        int pivot = nums[high];
        for( int i = left; i < high; ++i) {
            if( nums[i] <= pivot) {
                swap(nums, i , left);
                left++;
            }
        }
        swap(nums, left, high);
        return left;
    }

    void swap(int[] nums, int left, int right) {
        final int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

}
```


## 归并排序 

## 二叉树中序遍历
```java
void inOrderDFS(Node root) {
    if(root == null)
        return;

    Stack<Node> stack = new Stack<>();
    Node node = root;

    while(!stack.isEmpty() || p != null){
        if(node != null){ // if it is not null, push to stack and go down the tree to left
            stack.push(node);
            node = p.left;
        } else { // if no left child pop stack, process the node then let p point to the right
            Node temp = (Node)stack.pop();
            visit(temp);
            node = temp.right;
        }
    }
}

```
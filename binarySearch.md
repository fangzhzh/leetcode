# 二分查找
## 二分查找的问题类别
二分查找有序

* 查找一个数

[1,2,3,4,5,6], looking for 2

* 查找左侧边界

[1,2,2,2,2,3,4,5,6], looing for first 2

* 查找右侧边界

[1,2,2,2,2,3,4,5,6], looing for last 2

## 步骤
* 找到数据单调递增/递减的区间
* 定义推出条件（搜索区间为空）
* 定义缩减空间步骤

##  二分查找的模版

```
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...; // 定义搜索区间  right = nums.length / nums.length - 1

    while(...) { // 定义退出条件（搜索区间为空), 
                // left < right === [left, right), 退出条件 left == right
                // left <= right === [left, right] 退出条件 left == right + 1

        int mid = left + (right - left) / 2;
        // 找到mid之后，需要把区间分成两段
        // [left, right] => [left, mid-1] and [mid+1, right]
        // [left, right) => [left, mid) and [mid+1, right)
        if (nums[mid] == target) {
            ...  // return or 缩减空间
        } else if (nums[mid] < target) {
            left = ... // 缩减左区间
        } else if (nums[mid] > target) {
            right = ... // 缩减右区间 
        }
    }
    return ...;
}
```

![二分查找](./graphs/binarySearch.drawio.svg)

## 代码示例

```java
int lower_bound(int[] numbers, int target) {
    int left = 0, right = nums.length;
    while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}

int upper_bound(int[]nums, int target) {
    int left = 0, right = nums.length;
    while(left < right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```





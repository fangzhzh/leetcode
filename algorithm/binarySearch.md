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

```java
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
// Template 1
// [left, right], exis consideration of left > right, meaning the search space is empty
// return index of target or -f if not found

int binary_search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;  // Found target, return immediately
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
}

// Template 2 for lower bound and upper bound(looking for boundary)
// [left, right), exit condition is left == right, which means you've found the boundary.
// return boundary position 
// frist element >= target for lower bound
// first element <= target for upper bound
int lower_bound(int[] numbers, int target) {
    int left = 0, right = nums.length;
    while(left < right) {
        int mid = left + (right - left) / 2;
        // 大于等于target，收缩右边界
        if(nums[mid] >= target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}

int upper_bound(int[]nums, int target) {
    int left = 0, right = nums.length;
    while(left < right) {
        int mid = left + (right - left) / 2;
        // 小于等于target，收缩左边界
        if(nums[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    return left;
}
```





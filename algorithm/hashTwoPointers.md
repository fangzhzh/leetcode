# hashmap and two pointers
O(1) find 所以HashMap会被用在很多需要查找的场景

hashmap的使用通常是tow pass，第一遍build map,第二遍使用

但是有时候one pass hash也很好，一遍build一遍使用，可避免在处理某个元素的时候，从hashmap里取出自己


## two pointers

双指针的走法有相向而行，快慢指针(同向而行)

### 283. 移动零

    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    示例:

    输入: [0,1,0,3,12]
    输出: [1,3,12,0,0]


解法需要用到双指针，双指针的移动类似于 快速排序partition函数里的两个指针

```
int partition(int[] nums, int left, int right) {
    // define the pivot nums[high] or nums[low] or nums[random]
    // while(left < right) {
        // find an inversion
        // nums[left] > pivot && nums[right] < pivot, do swap(nums, left, right)
    }
    // find the partition point
}
```
* [0, left) < pivot
* [left, length) > pivot
* [left, rigth] 需要处理


The idea is to find a partition so that all elements before the partition  less than nums[partition], all elements after the partition are greater than nusm[partition].

所以这个题完全可以理解为找到一个partition，那么这个partition 左边都不等于0， 右边都等于0

two pointers关键要清楚指针的定义，这个指针位置左边都是XX，包不包含当前坐标。

和快排稍微不同的是
* 快排根据大于小于移动，跑到不能再跑移动。
* 这题左边不等0，右边等0，跑到不能再跑
* 因为要保证顺序，所以要同向而行
* [0, left) != 0
* [left, right) == 0
* [right, length) 需要处理
* 移动right，找到一个稳定态，找到一个不满足上述条件的left，right，swap，继续

```java
void moveZeros(int[] nums) {
    int left = 0, right = 0;
    while(right < nums.length) {
        if(nums[right] != 0) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;
            left++;
        }
        right++;
    }
}
```

![moveZeros图解](./graphs/moveZeros.drawio.svg)
# hashmap and two pointers 和 滑动窗口
## HashMap
O(1) find 所以HashMap会被用在很多需要查找的场景

hashmap的使用通常是two pass，第一遍build map,第二遍使用

但是有时候one pass hash也很好，一边build一边使用，可避免在处理某个元素的时候，从hashmap里取出自己

HashMap可以存字符，也可以存字符位置，也可以存-1表示某种状态

* LC387. 字符串中的第一个唯一字符

## two pointers

两个指针解决问题。相向或同向。

双指针问题的关键是定义好两个指针的含义，题目一般要求将一个数据从一种状态变为另一种状态，比如无序变有序

* [0, left) 题目要求下需要处理并处理过的数据
* [left, length) 题目要求里不需要处理的数据
* [left, rigth] 未知的数据

## Two pointer数组移动元素
双指针在数组的元素移动中很常见，常见的题型是根据一些规则挪动后边的元素去override数组中前边的一些元素。

常见的思路误区就是找到一个i, 把[j, n)里的元素挪动到[i, n-(j-1)),然后继续处理，但是数组移动的题型一般数据量都在10^3以上，每次移动，都需要一个O(n)的时间复杂度，效率不高！

所以这种题型的解决方案是i指向元素插入的地方，j扫描并寻找下一个元素。

> **one for pivot, one for scanning**
### 283. 移动零

    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

    示例:

    输入: [0,1,0,3,12]
    输出: [1,3,12,0,0]

two pointers关键要清楚指针的定义，这个指针位置左边都是XX，包不包含当前坐标。

和快排稍微不同的是
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

### [26.删除有序数组中的重复项](./26.remove-duplicates-from-sorted-array/)
### [27.移除元素](./27.remove-element/)
# Sliding window
* [sliding window](./slidingWindow.md)
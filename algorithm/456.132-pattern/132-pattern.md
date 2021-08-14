# 456.132 pattern

在看题目之前，首先说明，这个题目的神奇之处在于，这个题目的题解基本上覆盖了所有的数组里找元素的方法
* 找最大 O(n)
* 找最小 O(n)
* 一堆里找某个 O(n log n)
* 从前往后找
* 从后往前找
* 从整个sub 数组里找

当在做找元素的问题时，可以借鉴此处的思路。

    Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

    Return true if there is a 132 pattern in nums, otherwise, return false.

     

    Example 1:

    Input: nums = [1,2,3,4]
    Output: false
    Explanation: There is no 132 pattern in the sequence.
    Example 2:

    Input: nums = [3,1,4,2]
    Output: true
    Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
    Example 3:

    Input: nums = [-1,3,2,0]
    Output: true
    Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
     

    Constraints:

    n == nums.length
    1 <= n <= 2 * 10^5
    -109 <= nums[i] <= 109



因为n的范围到2*10^5，所以O(n^3)必然超时，从O(n^3)优化到O(n^2)的方案也会超时。

我们要找出小于O(n^2)的方案。

因为我们要找出三个元素， i,j,k 满足 `i<j<k`，并且`nums[i] < nums[k] < nums[j]`

**抽象的说，我们只能考虑枚举其中一个下标，并用合适的数据结构维护另外2个下标的可能值**

    抽象的说，我们只能考虑枚举其中一个下标，并用合适的数据结构维护另外2个下标的可能值


## 枚举3
思路: 
枚举3是最容易想到的。

3是模式中的最大值，并且出现在1和2中间，我们只需要从左到右枚举3的下表j，那么
* 1是最小值，我们枚举j的公式，维护数组a[0..j-1]的最小值a[i], 其中a[i] < a[j]
* 2是次小值，我们可以用有序集合(例如平衡树)维护数组a中的右侧元素a[j+1...n-1]的所有值
    * 确定a[i]和a[j]以后，从有序结合中查询严格比a[i]大的最小的元素，其中a[k]必须满足a[k]<a[j]


### 代码

此处代码可以学习`TreeMap`的用法
```java
// [3,1,4,2]
public boolean find132pattern(int[] nums) {
    int n = nums.length;
    if(n < 3) {
        return false;
    }

    int leftMin = nums[0];
    TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();
    for(int k = 2; k < n; k++>) {
        rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
    }
    for(int j =  1; j < n-1; j++) {
        if(leftMin < nums[j]) {
            Integer next = rightAll.cellingKey(leftMin+1); 
            if(next != null && next < nums[j]) {
                return true;
            }
        }
        leftMin = Math.min(leftMin, nums[j]);
        // rightAll维护数组a中的右侧元素a[j+1...n-1]的所有值
        // 所以处理完j，我们需要把j+1去除，因为下一步，我们处理j+1，要保证rightAll的循环不变式成立
        
        rightAll.put(nums[j+1], rigthAll.get(nums[j+1])-1);
        if(rightAll.get(nums[j+1]) == 0) { 
            rightAll.remove(nums[j+1]);
        }
    }
    return false;
}
```    

## 枚举2

## 枚举1
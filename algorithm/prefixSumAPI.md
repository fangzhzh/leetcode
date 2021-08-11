# prefixSumAPI

对数组nums，对第i个元素来说，前缀和 presum[i]就是num[0..i]的和。

```java
int n = nums.length;
// 前缀和数组
int[] preSum = new int[n + 1];
preSum[0] = 0;
for (int i = 0; i < n; i++) {
    preSum[i + 1] = preSum[i] + nums[i];

}
```
前缀和可以迅速的求出满足某些和条件的区间
`presum[i]-presum[j] == k`， so nums[i]... nums[j]所有元素的和是k

有时，这个前缀和也可以是乘积，比如例题[238. Product of Array Except Self]


## 例题

* [238. Product of Array Except Self](https://leetcode-cn.com/problems/product-of-array-except-self/)

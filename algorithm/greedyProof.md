# greedyProof 贪心题目及其证明

## 56.Merge Intervals
    Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

     

    Example 1:

    Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].


### 思路
头部排序，然后可以merge的必然相邻，然后遍历merge即可

### 证明

能合并的前提就是`a[j].start > a[i].start`的情况下，`a[i].end > a[j].start`

我们保证start排序的情况下，只需要比较a[i+i].start是否和a[j].end 相交。
```latex

上述算法的正确性可以用反证法来证明：在排完序后的数组中，两个本应合并的区间没能被合并，那么说明存在这样的三元组 (i, j, k)(i,j,k) 以及数组中的三个区间 a[i], a[j], a[k]a[i],a[j],a[k] 满足 i < j < ki<j<k 并且 (a[i], a[k])(a[i],a[k]) 可以合并，但 (a[i], a[j])(a[i],a[j]) 和 (a[j], a[k])(a[j],a[k]) 不能合并。这说明它们满足下面的不等式：

a[i].end < a[j].start \quad (a[i] \text{ 和 } a[j] \text{ 不能合并}) \\ a[j].end < a[k].start \quad (a[j] \text{ 和 } a[k] \text{ 不能合并}) \\ a[i].end \geq a[k].start \quad (a[i] \text{ 和 } a[k] \text{ 可以合并}) \\
a[i].end<a[j].start(a[i] 和 a[j] 不能合并)
a[j].end<a[k].start(a[j] 和 a[k] 不能合并)
a[i].end≥a[k].start(a[i] 和 a[k] 可以合并)

我们联立这些不等式（注意还有一个显然的不等式 a[j].start \leq a[j].enda[j].start≤a[j].end），可以得到：

a[i].end < a[j].start \leq a[j].end < a[k].start
a[i].end<a[j].start≤a[j].end<a[k].start

产生了矛盾！这说明假设是不成立的。因此，所有能够合并的区间都必然是连续的。



```

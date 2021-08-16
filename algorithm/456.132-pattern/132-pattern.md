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

### 复杂度分析

时间复杂度：O(nlogn)。在初始化时，我们需要 O(nlogn) 的时间将数组元素 a[2..n-1]a[2..n−1] 加入有序集合中。在枚举 jj 时，维护左侧元素最小值的时间复杂度为 O(1)O(1)，将 a[j+1]a[j+1] 从有序集合中删除的时间复杂度为 O(logn)，总共需要枚举的次数为 O(n)，因此总时间复杂度为 O(nlogn)。

空间复杂度：O(n)，即为有序集合存储右侧所有元素需要使用的空间。

## 枚举1

我们来看如何枚举1.

    如果我们从左到右的枚举1的下标i，那么j,k的下标范围在减少，这样不利于对他们的维护。因此，我们考虑从右到左的枚举i。

    这个思考可以应用到所有搜索。

如何维护j,k呢？我们知道1<3, 2 < 3,所以
* 使用数据结构维护所有遍历过的元素，作为2的候选元素，假如数据解噢股
* 在遍历新元素，考虑是否作为3。如果作为3，那么**数据结构中所有 严格小于他的元素都可以作为2**，我们将这些元素全部从纾解结构中移除，并使用一个变量维护**所有被移除的元素的最大值**。

所以，这个"数据结构"是这样的：
* 支持添加一个元素
* 支持移除所有严格小于给定阈值的所有元素;（）
* 两步操作依次进行，先用阈值移除元素，再将阈值加入数据结构。

这就是“单调栈”。
* 严格递减
* 给定x，不断弹出栈顶元素，知道x严格小于栈顶元素，或者栈为空 。此时我们再入栈x
* 维护栈的单调性

涉及到本题，算法如下:
* 单调栈维护2的候选元素。单调栈有唯一元素a[n-1]。还需要一个变量maxK记录所有可以作为2的元素的最大值
* 随后我们从n-2开始从右往左枚举元素a[i]:
    * 首选判断a[i]是否作为1， 如果a[i] < maxK，就可以作为1，找到一组132
    * 随后判断a[i]是否可以作为3，找出哪些可以作为2.比较a[i]和stack.top()，如果a[i]大，那么stack.top()就是2，弹出并更新maxK
    * 最后我们把a[i]作为2候选 元素放入单调栈
* 枚举完，美国没找到132，那么说明不存在。

```java
class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<Integer>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }

        return false;
    }
}

```


## 枚举2

这个方法不需要提前知道整个数组，所以适合流的题目。

鉴于这个算法有点难理解，

TODO
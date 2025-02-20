# 需要了解并实现的特殊解法：
## Morris 遍历
* [Morris 遍历​](./morrisTraveral.md)
* [94. 二叉树的中序遍历](https://leetcode.com/problems/binary-tree-inorder-traversal/)
* [144. 二叉树的前序遍历](https://leetcode.com/problems/binary-tree-preorder-traversal/)
## Floyd's Cycle Detection Algorithm（快慢指针）
* [141. 环形链表](https://leetcode.com/problems/linked-list-cycle/)
* [287. 寻找重复数](https://leetcode.com/problems/find-the-duplicate-number/)
## Boyer–Moore 投票
* [Boyer moore](./boyerMoore.md)
* [169. 多数元素](https://leetcode.com/problems/majority-element/)
* [229. 求众数 II](https://leetcode.com/problems/majority-element-ii/)
## 轮转算法
* [189. 轮转数组](https://leetcode.com/problems/rotate-array/)
```java
void rotate(int[] arr, int k) {
    reverse(arr, 0, arr.length);
    reverse(arr, 0, k);
    reverse(arr, k+1, arr.length);
}
// [1,2,3,4,5,6,7], k = 3
          ^
// 1. [7,6,5,4,3,2,1] // reverse(arr, 0, k)
// 2. [5,6,7,4,3,2,1] // reverse(arr, 0, k)
// 3. [5,6,7,1,2,3,4] // reverse(arr, k+1, arr.length)
```
## 快速幂
* [50. Pow(x, n)](https://leetcode.com/problems/powx-n/)
## 洗牌算法
* [384. 打乱数组](https://leetcode.com/problems/shuffle-an-array/)
### Fisher-Yates 洗牌算法

-- To shuffle an array a of n elements (indices 0..n-1):
for i from n−1 downto 1 do
     j ← random integer such that 0 ≤ j ≤ i
     exchange a[j] and a[i]

```java
    Random random = new Random();
    for (int i = 0; i < nums.length; ++i) {
            // random index in [0, n-i]
            // newRandom = i+random ==> [i, n]
            // swap (nums, i, newRandom) => nums[i] is the random, we continue the process
            int j = i + random.nextInt(nums.length - i);
            swap(nums, i, j);
    }

```

## 特殊二分查找
* [300. 最长递增子序列](https://leetcode.com/problems/longest-increasing-subsequence/)
## 快速选择
* [215. 数组中的第K个最大元素](https://leetcode.com/problems/kth-largest-element-in-an-array/)
## 判断质数
* [204. 计数质数](https://leetcode.com/problems/count-primes/)
### 判断质数
`boolean isPrime(n)`
#### 1. naive
for i from 2 to n, step 1 do
    if n % i = 0 then return false;
#### 2. prime-sieve
for i from 2 to i*i<n>, step 1 do
    if n % i = 0 then return false;
### 找出所有质数
`int primeCounter(n)`
#### 1. naive
for i from 0 to n, step 1 do
    isPrime(i)

#### 埃氏筛 Eratosthenes
* 将其所有质数的倍数都标记为合数
```java
class Solution {
    public int countPrimes(int n) {
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }
}

```
#### 欧式筛法 - 线性筛
```java
class Solution {
    public int countPrimes(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }
}
```
## 最近公共祖先
* [236. 二叉树的最近公共祖先](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
* [1676. 二叉树的最近公共祖先 IV](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/)
## 最大公约数
* [1979. 找出数组的最大公约数](https://leetcode.com/problems/find-greatest-common-divisor-of-array/)
## 数学与排列组合
* [62. 不同路径](https://leetcode.com/problems/unique-paths/)
## 拓扑排序
* [210. 课程表 II](https://leetcode.com/problems/course-schedule-ii/)
## Dijkstra 算法
* [743. Network Delay Time](https://leetcode.com/problems/network-delay-time/)
* [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
* [1514. Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/)
## Bellman–Ford 算法
* [787. K 站中转内最便宜的航班](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
* [743. 网络延迟时间](https://leetcode.com/problems/network-delay-time/)

# 只需要了解的特殊解法
- 牛顿迭代法，KMP 算法，Prim 算法，Floyd-Warshall 算法

# 需要记下来的代码模版
* 二分查找 lower_bound, upper_bound,
* 前缀树，前缀和
* 滑动窗口（找最长以及最短）
* 单调栈
* 拓扑排序
* Union Find
* 滚动哈希
* 快速选择算法

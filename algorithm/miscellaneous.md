# 需要了解并实现的特殊解法：
## Morris 遍历
* [Morris 遍历​](./morrisTraveral.md)
* [94. 二叉树的中序遍历](https://leetcode.com/problems/binary-tree-inorder-traversal/)
* [144. 二叉树的前序遍历](https://leetcode.com/problems/binary-tree-preorder-traversal/)
## Floyd's Cycle Detection Algorithm（快慢指针）
* [Floyd's Cycle Detection Algorithm（快慢指针）​](./FloydCycleDetection.md)
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
* 将其所有的倍数都标记为合数
* 每个合数只被标记一次
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
### idea
`TreeNode lowestCommonAncestor(root, p, q)`
Return value definition:
* The node p if found,
* The node q if found,
* The lowest common ancestor **`root`**if both nodes are found,
* null if neither node is found.

I think the lowestCommonAncestor could be `tryLCS()`
### Code
```java
// 236
public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

    //base case
    if (root == null || root == p || root == q) {
        return root;
    }
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);

    //result
    if(left == null) {
        return right;
    }
    else if(right == null) {
        return left;
    }
    else { //both left and right are not null, we found our result
        return root;
    }
}
```
#### Iterative version
```java
public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null) return null;

    // Use a stack to perform DFS
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    // To keep track of parent pointers
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    parent.put(root, null);

    // Perform DFS to populate the parent map
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();

        // If we found p or q, we can stop searching
        if (node.left != null) {
            parent.put(node.left, node);
            stack.push(node.left);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            stack.push(node.right);
        }
    }

    // Now we can find the path from p to the root
    Set<TreeNode> ancestors = new HashSet<>();
    while (p != null) {
        ancestors.add(p);
        p = parent.get(p);
    }

    // Now we can find the first ancestor of q that is in the path to the root
    while (!ancestors.contains(q)) {
        q = parent.get(q);
    }

    return q; // This is the lowest common ancestor
}
```



* [1676. 二叉树的最近公共祖先 IV](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/)


## 最大公约数
* [1979. 找出数组的最大公约数](https://leetcode.com/problems/find-greatest-common-divisor-of-array/)
### Idea
`gcd(a, b)`
* simple version
    ```java
    gcd(a, b)
    for i from 0 to min(a, b), step 1 do
        if (a % i == 0 && b % i == 0) then return i;
    ```
* Euclid's Algorithm
    * `gcd(a, b) = gcd(b, a % b)`
## 数学与排列组合
* [62. 不同路径](https://leetcode.com/problems/unique-paths/)
## Bellman-Ford 算法
### 算法思想三维度
1. 松弛哲学 （Relaxation Philosophy）
    - 通过V-1轮对所有边的松弛操作，逐步逼近最短路径，也就是每轮（单轮）处理所有边
    - 每次松弛都是局部最优向全局最优的演进（类似 `dynamicProgramming.md` 中的最优子结构）
2. 路径构造模型
```java
// 关键松弛操作代码结构
for (int i = 0; i < n-1; i++) {
    for (Edge edge : edges) {
        if (dist[edge.u] + edge.weight < dist[edge.v]) {
            dist[edge.v] = dist[edge.u] + edge.weight; // 状态转移
        }
    }
}
 ```

3. 负权环检测机制
- 额外执行第V轮松弛，若仍可更新则存在负权环
- 体现 `dynamicProgramming.md` 中的子问题重叠检测思想

### 关键组成要素表
| 组件            | 动态规划对应概念          | 时间复杂度贡献 | LeetCode应用场景          |
|-----------------|---------------------------|----------------|---------------------------|
| 距离数组dist[]  | 状态存储数组              | O(V)           | 743.网络延迟时间          |
| 边遍历顺序      | 状态转移顺序              | O(E)           | 787.K站中转最便宜航班     |
| V-1次松弛迭代   | 自底向上的计算顺序        | O(V)           | 带限制的最短路径问题      |
| 负权环检测      | 无效子问题识别            | O(1)           | 含负权值的图问题         |

### 动态规划视角解析
1. **状态定义**：
   - `dist[k][v]`：最多经过k条边到达节点v的最小成本
   - 空间优化后简化为`dist[v]`（滚动数组技巧）

2. **状态转移方程**：
   ```python
   dist[v] = min(dist[v], dist[u] + w(u, v))  # ∀(u,v)∈E
   ```

3. **边界条件**：
   ```java
   dist[source] = 0;          // 起点初始状态
   dist[v] = INF ∀v ≠ source  // 未访问节点初始状态
   ```

### 算法实现模板
```java
class Solution {
    public int bellmanFord(int[][] edges, int n, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        for (int i = 0; i <= k; i++) { // 允许k次中转→k+1条边
            int[] temp = Arrays.copyOf(dist, n); // 防止并行更新
            boolean updated = false;
            
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                // dist[u] 上一轮的计算结果
                // temp[v] 本轮咱村结果
                if (dist[u] != Integer.MAX_VALUE && temp[v] > dist[u] + w) {
                    temp[v] = dist[u] + w;
                    updated = true;
                }
            }
            
            if (!updated) break; // 提前终止
            dist = temp;
        }
        
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
```

### 复杂度双维度分析
1. **时间复杂度**：
   - 基础版本：O(V*E)
   - 空间优化版：保持相同复杂度但常数更优
   - 提前终止优化：最佳情况O(E)

2. **空间复杂度**：
   - 未优化：O(V) （使用滚动数组）
   - 原始版：O(V^2) （若保留所有中间状态）

### 应用模式识别
1. **必须使用Bellman-Ford的场景**：
   - 含负权边的最短路径问题
   - 有边数限制的最短路径问题（如LC787）
   - 需要检测负权环的图问题

2. **应避免使用的场景**：
   - 仅含正权边的图（Dijkstra更高效）
   - 稠密图（V接近E时复杂度接近O(V^3)）
   - 需要处理全源最短路径（应使用Floyd-Warshall）
### 应用
* [787. K 站中转内最便宜的航班](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
* [743. 网络延迟时间](https://leetcode.com/problems/network-delay-time/)

# 只需要了解的特殊解法
- 牛顿迭代法，KMP 算法，Prim 算法，Floyd-Warshall 算法

# 需要记下来的代码模版
## [二分查找 lower_bound, upper_bound](./binarySearch.md)
## [前缀树](./trie.md)
## [滑动窗口（找最长以及最短）](./slidingWindow.md)
## [单调栈](./monotoneStack.md)
## [拓扑排序](./topologicalSorting.md)
## [Union Find](./unionFindSet.md)
## 滚动哈希 Rolling hash
```java
    private static final int P = 31; // 选择一个质数
    private static final int MOD = 1_000_000_007; // 大素数

    public static int[] computeHash(String s) {
        int n = s.length();
        int[] hash = new int[n + 1];
        long pPow = 1;

        for (int i = 0; i < n; i++) {
            hash[i + 1] = (int) ((hash[i] + (s.charAt(i) - 'a' + 1) * pPow) % MOD);
            pPow = (pPow * P) % MOD; // 更新p的幂
        }
        return hash;
    }
```

### 应用
Robin-karp
```java
public class RabinKarp {
    private static final int BASE = 31; // Base for the hash function
    private static final int MOD = 1_000_000_007; // A large prime modulus

    public static int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0; // If the needle is empty, return 0
        }

        int m = needle.length(); // Length of the needle
        int n = haystack.length(); // Length of the haystack
        long needleHash = 0; // Hash value for the needle
        long haystackHash = 0; // Hash value for the current window in haystack
        long powerMax = 1; // To calculate power of the base for the rolling hash

        // Calculate the hash for the needle and the first window of the haystack
        for (int i = 0; i < m; i++) {
            needleHash = (needleHash * BASE + needle.charAt(i)) % MOD;
            haystackHash = (haystackHash * BASE + haystack.charAt(i)) % MOD;
            if (i > 0) {
                powerMax = (powerMax * BASE) % MOD; // Update power of the base
            }
        }

        // Slide the window over the haystack
        for (int i = 0; i <= n - m; i++) {
            // Check if the hash values match
            if (haystackHash == needleHash) {
                // Confirm the match by comparing the actual strings
                if (haystack.substring(i, i + m).equals(needle)) {
                    return i; // Return the starting index of the match
                }
            }

            // Update the hash value for the next window
            if (i < n - m) {
                haystackHash = (haystackHash - haystack.charAt(i) * powerMax) % MOD; // Remove the leading character
                if (haystackHash < 0) {
                    haystackHash += MOD; // Ensure the hash value is non-negative
                }
                haystackHash = (haystackHash * BASE + haystack.charAt(i + m)) % MOD; // Add the trailing character
            }
        }

        return -1; // Return -1 if no match is found
    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        int index = strStr(haystack, needle);
        System.out.println("Pattern found at index: " + index); // Output: Pattern found at index: 2
    }
}
```
## [快速选择算法](./codeAPI.md)


# 需要了解并实现的特殊解法：
## Morris 遍历
* [Morris 遍历​](./morrisTraveral.md)
- [94. 二叉树的中序遍历](https://leetcode.com/problems/binary-tree-inorder-traversal/)
- [144. 二叉树的前序遍历](https://leetcode.com/problems/binary-tree-preorder-traversal/)
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
## Bellman–Ford 算法
* [787. K 站中转内最便宜的航班](https://leetcode.com/problems/cheapest-flights-within-k-stops/)
* [743. 网络延迟时间](https://leetcode.com/problems/network-delay-time/)

# 只需要了解的特殊解法
- 牛顿迭代法，KMP 算法，Prim 算法，Floyd-Warshall 算法

# 需要记下来的代码模版
* [二分查找 lower_bound, upper_bound](./binarySearch.md)
* [前缀树，前缀和](./trie.md)
* [滑动窗口（找最长以及最短）](./slidingWindow.md)
* [单调栈](./monotoneStack.md)
* [拓扑排序](./topologicalSorting.md)
* [Union Find](./unionFindSet.md)
* 滚动哈希 Rolling hash
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

#### 应用
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
* [快速选择算法](./codeAPI.md)

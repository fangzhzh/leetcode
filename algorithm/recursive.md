# Recursive
理清父问题和子问题的关系。

看一个问题
* 能不能分解成子问题
* 如果能，递归。
* 关系是什么,
    * 父依赖子 top down, DFS+memo / stack + memo / dynamic programming
    * 子依赖父 bottom up, BFS+memo / queue / dynamic programming

tabulation or memoization


## 遍历方式
### DFS
### BSF LEVEL

## Top down VS bottom up
# **Formalized and Systematic Comparison of Top-Down and Bottom-Up Approaches**  

```python  
// Top-Down
def fib(n, memo={}):
    if n in memo:
        return memo[n]  
    if n <= 1:
        return n  
    memo[n] = fib(n-1, memo) + fib(n-2, memo)  
    return memo[n]  


// Bottom up
def fib(n):
    if n <= 1:
        return n  
    dp = [0] * (n+1)
    dp[1] = 1  
    for i in range(2, n+1):
        dp[i] = dp[i-1] + dp[i-2]  
    return dp[n]  

``` 

## **1. Conceptual Differences**  
| **Aspect** | **Top-Down Approach** | **Bottom-Up Approach** |
|------------|----------------------|----------------------|
| **Definition** | Starts with the original problem and **recursively** breaks it into subproblems, solving them as needed. | Starts by solving base cases and incrementally, **iteratively** building towards the final solution. |
| **Flow of Computation** | Works from the problem definition down to smaller subproblems. | Works from the smallest subproblems up to the full solution. |
| **Dependency Structure** | **Parent depends on children** (父依赖子). | **Children depend on parents** (子依赖父). |
| **Common Algorithms** | **DFS + Memoization(reduce duplicated computation)**, **Stack + Memoization**, **Recursive Dynamic Programming**. | **BFS + Memoization**, **Queue-based Processing**, **Iterative Dynamic Programming**. |
| **Execution Style** | **Lazy evaluation**: Only solves necessary subproblems. | **Eager evaluation**: Precomputes results for all subproblems. |

---


## **2. Implementation Paradigms**  
| **Feature** | **Top-Down** | **Bottom-Up** |
|------------|-------------|-------------|
| **Primary Control Flow** | **Recursive function calls** (with memoization to avoid recomputation). | **Explicit loops or queue-based iterations**. |
| **Data Structures Used** | **Recursion stack, hash maps, arrays (for memoization)**. | **Queues, arrays, DP tables**. |
| **Code Complexity** | More intuitive for problems naturally expressed recursively. | Often requires more effort to define the correct iteration order. |

---

### **3. When to Use Each Approach**
| **Situation** | **Top-Down (DFS + Memoization)** | **Bottom-Up (Iterative / Queue / BFS)** |
|--------------|---------------------------------|----------------------------------|
| **Tree-based problems (e.g., recursion on trees, depth-first traversal)** | ✅ Natural fit (e.g., calculating tree height) | ❌ Harder to implement iteratively |
| **Problems with a few subproblems that actually get used** | ✅ Efficient (only computes needed subproblems) | ❌ Might compute unnecessary subproblems |
| **Problems with many states (e.g., DP table problems)** | ❌ Risky (too many recursive calls) | ✅ More efficient (avoids recursion overhead) |
| **Graph problems (e.g., shortest path, layers)** | ❌ Not ideal (DFS can be inefficient) | ✅ BFS-based approaches work better |

---

### Tail recursion
Tail recursion is **a special case of Top-Down recursion** that can be optimized into an iterative approach, making it behave more like Bottom-Up.  

#### **1. Tail Recursion in Top-Down**
- In a **tail-recursive function**, the recursive call is the **last operation** before returning.  
- This means **no additional work is needed** after the recursive call returns.  
- Some compilers and interpreters can **optimize tail recursion** into an iterative loop, eliminating the extra function call overhead (called **Tail Call Optimization (TCO)**).  

✅ **Example: Tail-Recursive Fibonacci (Top-Down with TCO)**  
Instead of storing intermediate results in a hash table (memoization), we **pass results as function arguments** to eliminate extra calls:  

```python
def fib_tail(n, a=0, b=1):
    if n == 0:
        return a
    if n == 1:
        return b
    return fib_tail(n-1, b, a+b)  # Tail call
```
- **This is still Top-Down** (recursive breakdown)  
- But since the **recursive call is the last operation**, it **can be optimized into a loop**  

---

#### **2. Tail Recursion vs. Bottom-Up**
- **Tail recursion behaves like Bottom-Up** if optimized correctly.
- Instead of **storing intermediate results in memory (memoization)**, it **carries results forward in function arguments**.
- Many functional programming languages (like Haskell, Scala) **auto-optimize tail recursion into a loop** to avoid deep recursion stack issues.

✅ **Equivalent Bottom-Up Iterative Version (No Recursion)**  
Since the recursive function just carries forward values, we can replace it with an **explicit loop**:

```python
def fib_iter(n):
    a, b = 0, 1
    for _ in range(n):
        a, b = b, a + b
    return a
```
- No recursion = **pure Bottom-Up**  
- Same efficiency as a tail-recursive function  

---

#### **3. When to Use Tail Recursion vs. Bottom-Up**
| Approach | Description | Pros | Cons |
|----------|------------|------|------|
| **Top-Down (Standard Recursion + Memoization)** | Recursively breaks problem into subproblems, caches results | Simple & intuitive, avoids redundant calculations | Uses recursion stack, risk of stack overflow |
| **Top-Down (Tail Recursion)** | Recursion where the last operation is the recursive call | Can be optimized into iteration | Some languages don’t support TCO |
| **Bottom-Up (Iterative DP)** | Starts from base cases and builds solution iteratively | No recursion overhead, best for performance | Can be less intuitive |

---
## 参数与返回值
### 参数
一般有能够递归需要的的变量，和当前的结果

### 返回值
* 可以是要求的值，比如最大。。。的个数，那么返回值是int
* 可以是void，需要的值要更新参数列表的某个object，或者更新某个全局的变量，
    * List<Integer> ans
    * this.max = Math.max(this.max, curValue)
* 在上一种情况下，返回值也可以用做计算全局变量
    * this.max = Math.max(this.max, curValue+dfs(root.left) + dfs(root.right));

## 例题
```java
1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

 

示例 1：

输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace" ，它的长度为 3 。
示例 2：

输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
示例 3：

输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。


class Solution {
/*
* 能不能分解成子问题
* 如果能，递归。
* 关系是什么,
    * 父依赖子 top down, DFS (memo)optional / stack + memo / dynamic programming
    * 子依赖父 bottom up, BFS (memo)optional / queue
*/
    public int longestCommonSubsequence(String text1, String text2) {
        Map<Integer, Integer> cache = new HashMap<>();
        // return longestCommonSubsequenceTopDownDFSMemo(text1, 0, text2, 0, cache);
        // return longestCommonSubsequenceDP(text1, text2);
        return longestCommonSubsequenceBFS(text1, 0, text2, 0, 0);
    }

    // Time O(m^2*n^2)
    // Space O(m*n)
    // can add memo to decrease redundent calculation, but it doesn't decrease complexity
    private int longestCommonSubsequenceBFS(String s, int i, String t, int j, int level) {
        if(j >= t.length() || i >= s.length()) return level;
        char c1 = s.charAt(i);
        char c2 = t.charAt(j);
        int newLevel = 0;
        if(c1 == c2) {
            newLevel = longestCommonSubsequenceBFS(s, i+1, t, j+1, level + 1);
        } else {
            // 一个问题变成两个子问题，所以Time O(m^2 * n^2)
            newLevel = Math.max(
                longestCommonSubsequenceBFS(s, i+1, t, j, level),
                longestCommonSubsequenceBFS(s, i, t, j+1, level)
            );
        }
        return newLevel;
    }

    private int longestCommonSubsequenceBFSQueue(String s, String t) {
        // queue 也是可行的
        Queue<int[]> queue = new LinkedList<>();
        queue.push(new int[]{i*10000+j, level});

    }

    // Time O(m*n)
    // Space O(m*n)
    private int longestCommonSubsequenceDP(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i = 1; i < s.length()+1; i++) {
            for(int j = 1; j < t.length()+1; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(
                        dp[i-1][j],
                            dp[i][j-1]
                        );
                }
            }
        }
        return dp[s.length()][t.length()];
    }
    // Time O(m^2*n^2)
    // Space O(m*n)
    private int longestCommonSubsequenceTopDownDFSMemo(String text1, int i, String text2, int j, Map<Integer, Integer> cache) {
        if(j >= text2.length() || i >= text1.length()) return 0;
        int key = i*10000+j;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        int cnt = 0;
        if(text1.charAt(i) == text2.charAt(j)) {
            cnt =  1 + longestCommonSubsequenceTopDownDFSMemo(text1, i+1, text2, j+1, cache);
        } else {
            // 一个问题变成两个子问题，所以Time O(m^2 * n^2)
            cnt =  Math.max(longestCommonSubsequenceTopDownDFSMemo(text1, i+1, text2, j, cache),
            Math.max(longestCommonSubsequenceTopDownDFSMemo(text1,i, text2,j+1, cache),
            longestCommonSubsequenceTopDownDFSMemo(text1,i+1, text2,j+1, cache)));
        }
        cache.put(key, cnt);
        return cnt;

    }
}
```

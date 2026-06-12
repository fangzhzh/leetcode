# Python 刷题语法与基础速查 (Python Cheat Sheet) 🐍

本文档合并了 Python 的基础语法（Python 101）以及针对 LeetCode 刷题的速查模板，方便在准备算法面试时快速查阅。

---

## 1. 基础数据类型与数值 (Basic Types)

### 数值类型
* `int`, `float`, `complex`

### 极值定义 (对应 Java 的 Integer.MAX_VALUE / MIN_VALUE)
* **最大值 (Infinity)**: `float('inf')`
* **最小值 (-Infinity)**: `float('-inf')`

---

## 2. 基础数据结构 (Data Structures)

### 2.1 列表 (List) & 数组
```python
# 初始化
arr = []                              # 空列表
arr = [1, 2, 3]                       # 带初始值
dp = [0] * n                          # 长度为 n 的一维数组
grid = [[0] * cols for _ in range(rows)] # rows * cols 的二维数组 (避免浅拷贝问题)

# 列表推导式 (List Comprehension)
my_list = [x for x in range(5)]       # [0, 1, 2, 3, 4]
my_list = [x*2 for x in range(3)]     # [0, 2, 4]

# 增删操作
arr.append(4)                         # 尾部添加 O(1)
arr.insert(1, 5)                      # 在索引 1 处插入 5，O(n)
arr.pop()                             # 弹出最后一个元素 O(1)
arr.pop(0)                            # 弹出第一个元素 O(n)
arr.remove(3)                         # 移除第一个值为 3 的元素，若不存在抛出 ValueError，O(n)

# 排序
arr.sort()                            # 原地升序排序
arr.sort(reverse=True)                # 原地降序排序
arr.sort(key=lambda x: x[1])          # 按自定义规则排序 (例如按元组/列表的第二个元素)
sorted_arr = sorted(arr)              # 返回排序后的新列表，不修改原列表

# 常用函数
len(arr)                              # 获取长度 O(1)
```

#### 切片语法 (Slicing Notation)
> **注意：Python 的切片会创建并返回一个新的列表对象。**

* `arr[start:stop:step]`：提取从索引 `start` 到 `stop-1` 的元素，步长为 `step`。
* `arr[-k:]`：获取后 `k` 个元素。
* `arr[:-k]`：获取除了后 `k` 个元素之外的所有元素。
* `arr[::-1]`：反转列表/字符串 (步长为 -1)。例如 `"abc"[::-1]` $\rightarrow$ `"cba"`。

---

### 2.2 元组 (Tuple)
```python
t = (1, 2, 3)                         # 元组声明
# 元组是不可变 (Immutable) 的，一旦创建不可修改
# 因为不可变，元组可以作为 dict 的 key，而 list 则不行
```

---

### 2.3 字符串 (String)
```python
s = "hello"
s[0]                                  # 访问字符 'h'
s[::-1]                               # 翻转字符串
s.split(" ")                          # 按空格分割成 list
"".join(["a", "b"])                   # 将 list 拼接为字符串 "ab"
s.isdigit()                           # 是否全部由数字组成
s.isalpha()                           # 是否全部由字母组成
ord('a')                              # 获取字符的 ASCII 码 (97)
chr(97)                               # 将 ASCII 码转换回字符 ('a')
```

---

### 2.4 集合 (Set)
```python
# 初始化
s = set()                             # 空集合 (注意：s = {} 会创建空 dict，而不是 set!)
s = {1, 2, 2, 3}                      # 字面量初始化，自动去重，生成 {1, 2, 3}
s = set([1, 2, 2, 3])                 # 从列表转换

# 增删查
s.add(1)                              # 添加元素
s.remove(1)                           # 移除元素，若不存在则抛出 KeyError
s.discard(2)                          # 移除元素，若不存在不会报错 (更安全)
val = s.pop()                         # 随机移除并返回一个元素
s.clear()                             # 清空集合
1 in s                                # 成员查找 O(1)
len(s)                                # 获取元素个数
```

---

### 2.5 映射 (Dict / HashMap)
```python
# 初始化
d = {}                                # 空字典
parent = {x: x for x in range(n)}     # 字典推导式初始化

# 查改操作
d["key"] = 1
val = d.get("key", 0)                 # 安全获取值，不存在时返回默认值 0
"key" in d                            # 判断键是否存在 O(1)
del d["key"]                          # 删除键值对

# 遍历
for k, v in d.items():
    print(k, v)

# 字典按值或键排序
sorted_d = sorted(d.items(), key=lambda item: item[1]) # 按 value 排序
```

#### Counter (计数器)
```python
from collections import Counter
cnt = Counter("aabbc")                # {'a': 2, 'b': 2, 'c': 1}
cnt.most_common(2)                    # 返回出现频率最高的 2 个元素: [('a', 2), ('b', 2)]
```

#### defaultdict (自动初始化字典)
```python
from collections import defaultdict
graph = defaultdict(list)
graph[u].append(v)                    # 无需先判断 u 是否在字典中

count = defaultdict(int)
count[key] += 1                       # 默认值为 0，可直接进行累加
```

---

## 3. 栈、队列与堆 (Stack, Queue & Heap)

### 3.1 栈 (Stack)
直接使用 Python 内置的 `list` 即可，尾部操作均为 $O(1)$。
```python
stack = []
stack.append(x)                       # push (压栈)
val = stack.pop()                     # pop (弹栈)
top = stack[-1]                       # peek (查看栈顶)
```

### 3.2 双端队列 (Queue / Deque)
推荐使用 `collections.deque`，两端增删均为 $O(1)$，而使用 list 做队列头部删除是 $O(n)$。
```python
from collections import deque
q = deque()
q.append(x)                           # 从右侧入队
q.appendleft(x)                       # 从左侧入队
val = q.pop()                         # 从右侧出队
val = q.popleft()                     # 从左侧出队 (最常用，FIFO)
front = q[0]                          # 查看队头
```

### 3.3 堆 / 优先队列 (Heap / Priority Queue)
Python 默认实现的是**最小堆** (Min-Heap)。
```python
import heapq
heap = []

heapq.heappush(heap, 3)               # 压入元素
val = heapq.heappop(heap)             # 弹出最小值
min_val = heap[0]                     # 查看堆顶最小值

# 最大堆实现：将数值取相反数入堆
heapq.heappush(heap, -val)
actual_val = -heapq.heappop(heap)

# 数组堆化
arr = [3, 2, 1]
heapq.heapify(arr)                    # 原地转化为堆，时间复杂度 O(n)

# 获取 Top K 元素
k_largest = heapq.nlargest(k, arr)
k_smallest = heapq.nsmallest(k, arr)
```

### 3.4 双向链表 & LRU Cache (Doubly Linked List)
*   **核心优势**：在已知节点指针时，$O(1)$ 时间复杂度删除该节点。
*   **哨兵节点 (Sentinels)**：使用伪头 (dummy head) 和 伪尾 (dummy tail) 可以极大简化边界判断。

#### 1. 手动实现 (面试高频推荐)
```python
class Node:
    def __init__(self, k=0, v=0):
        self.key, self.val = k, v
        self.prev = self.next = None

class LRUCache:
    def __init__(self, capacity: int):
        self.cap = capacity
        self.cache = {} # key -> Node
        # 哨兵节点：灵魂初始化
        self.head, self.tail = Node(), Node()
        self.head.next, self.tail.prev = self.tail, self.head

    def get(self, key: int) -> int:
        if key in self.cache:
            node = self.cache[key]
            self._move_to_head(node)
            return node.val
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self.cache:
            self._remove_node(self.cache[key])
        node = Node(key, value)
        self.cache[key] = node
        self._add_to_head(node)
        if len(self.cache) > self.cap:
            lru = self.tail.prev
            self._remove_node(lru)
            del self.cache[lru.key]

    # --- 辅助函数：O(1) 肌肉记忆 ---
    def _remove_node(self, node):
        node.prev.next = node.next
        node.next.prev = node.prev

    def _add_to_head(self, node):
        node.next = self.head.next
        node.prev = self.head
        self.head.next.prev = node
        self.head.next = node

    def _move_to_head(self, node):
        self._remove_node(node)
        self._add_to_head(node)
```

#### 2. OrderedDict 快捷实现 (Pythonic)
```python
from collections import OrderedDict

class LRUCache(OrderedDict):
    def __init__(self, capacity):
        self.cap = capacity

    def get(self, key):
        if key not in self: return -1
        self.move_to_end(key) # 默认移动到末尾 (作为最新)
        return self[key]

    def put(self, key, value):
        if key in self: self.move_to_end(key)
        self[key] = value
        if len(self) > self.cap:
            self.popitem(last=False) # 弹出开头 (最旧)
```

---

## 4. 常用算法模板 (Algorithms & Templates)

### 4.1 单调栈模板 (Monotonic Stack)
```python
stack = []
for i, num in enumerate(nums):
    # 维持单调递增/递减属性
    while stack and nums[stack[-1]] < num:
        prev_idx = stack.pop()
        # 处理逻辑...
    stack.append(i)
```

### 4.2 广度优先搜索 (BFS)
```python
from collections import deque

def bfs(graph, start):
    visited = {start}
    q = deque([start])
    while q:
        node = q.popleft()
        for nei in graph[node]:
            if nei not in visited:
                visited.add(nei)
                q.append(nei)
```

### 4.3 深度优先搜索 (DFS) & 记忆化
```python
import sys
from functools import lru_cache

# 增加递归深度 (处理深层树或图，默认 1000)
sys.setrecursionlimit(2000)

# 自动记忆化 (DP/DFS 常用)
@lru_cache(None) # Python 3.9+ 推荐用 @cache
def dfs(node):
    if node == end: return 1
    # ...
    return res

# 基础 DFS 模板
def dfs_standard(graph, node, visited):
    visited.add(node)
    for nei in graph[node]:
        if nei not in visited:
            dfs_standard(graph, nei, visited)
```

### 4.4 二分查找 (Binary Search)
```python
from bisect import bisect_left, bisect_right

# 假定 arr 已排序
i = bisect_left(arr, target)          # 第一个元素值 >= target 的索引 (若不存在则为插入点)
i = bisect_right(arr, target)         # 第一个元素值 > target 的索引
# 查找是否存在：
# idx = bisect_left(arr, x); if idx < len(arr) and arr[idx] == x: ...
```

### 4.5 回溯模板 (Backtracking)
```python
result = []
def backtrack(path, choices):
    if 满足条件:
        result.append(path[:])         # 注意：必须放浅拷贝 path[:]
        return
    for i in range(len(choices)):
        path.append(choices[i])        # 做选择
        backtrack(path, choices[i+1:]) # 递归
        path.pop()                     # 撤销选择
```

### 4.6 矩阵邻居迭代 (Matrix Traversal)
```python
# 遍历二维矩阵中 (x, y) 的四个方向的邻居
directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
for dx, dy in directions:
    nx, ny = x + dx, y + dy
    if 0 <= nx < rows and 0 <= ny < cols:
        # 进行业务处理...
```

### 4.7 并查集模板 (Union Find / DSU)
```python
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.count = n # 连通分量数量

    def find(self, i):
        if self.parent[i] == i:
            return i
        self.parent[i] = self.find(self.parent[i]) # 路径压缩
        return self.parent[i]

    def union(self, i, j):
        root_i = self.find(i)
        root_j = self.find(j)
        if root_i != root_j:
            self.parent[root_i] = root_j
            self.count -= 1
            return True
        return False
```

---

## 5. 常用技巧与写法 (Tips & Tricks)

### 变量交换与解构
```python
a, b = b, a                           # 优雅交换两数
a, b, *rest = [1, 2, 3, 4, 5]         # rest = [3, 4, 5]
```

### 迭代利器 (itertools)
```python
import itertools

# 排列组合
itertools.permutations([1, 2, 3])      # 全排列
itertools.combinations([1, 2, 3], 2)   # 组合 (n 选 k)

# 前缀和
list(itertools.accumulate([1, 2, 3]))  # [1, 3, 6]

# 分组 (处理连续相同元素)
for key, group in itertools.groupby("AAAABBBCC"):
    # key: 'A', group: ['A','A','A','A']
    pass
```

### 自定义比较排序
```python
from functools import cmp_to_key

def my_compare(a, b):
    if a + b > b + a: return -1        # 返回负数表示 a 排在前面
    if a + b < b + a: return 1
    return 0

nums.sort(key=cmp_to_key(my_compare))
```

### 常用位运算进阶
* `n & 1` : 判断奇偶 (结果为 1 是奇数，0 是偶数)。
* `n & (n - 1)` : 清除最右侧的 `1` (判断 2 的幂, 计算 1 的个数)。
* `n & -n` : 获取最右侧的 `1` (树状数组常用)。
* `bin(n).count('1')` : 统计 `1` 的个数。

### 常用数学函数 (math)
```python
import math
math.gcd(12, 18)                      # 最大公约数 (6)
math.lcm(12, 18)                      # 最小公倍数 (36)
math.comb(n, k)                       # 组合数 C(n, k)
math.ceil(4.2)                        # 上取整 (5)
math.floor(4.8)                       # 下取整 (4)
```

### ACM 模式快速输入输出
```python
import sys
input = sys.stdin.read                # 读取全部输入
# 或者按行快速读取
# for line in sys.stdin:
#     data = line.split()
```

---

## 6. 三种语言方法对比 (Java vs Python vs C++)

### Stack & Queue 方法对比

| 数据结构 | Java | Python (`deque`/`list`) | C++ |
| :--- | :--- | :--- | :--- |
| **Stack (栈)** | `Stack` | `list` | `stack` |
| 压栈 | `st.push(val)` | `st.append(val)` | `st.push(val)` / `push_back()` |
| 弹栈 | `st.pop()` | `st.pop()` | `st.pop()` / `pop_back()` |
| 查看栈顶 | `st.peek()` | `st[-1]` | `st.top()` |
| **Queue (队列)** | `Queue` / `LinkedList` | `collections.deque` | `queue` |
| 入队 | `q.add(val)` / `offer(val)` | `q.append(val)` | `q.push(val)` |
| 出队 | `q.poll()` | `q.popleft()` | `q.pop()` |
| 查看队头 | `q.peek()` | `q[0]` | `q.front()` |

### 核心语法对照速记

| 功能 | Java | Python |
| :--- | :--- | :--- |
| **一维数组初始化** | `int[] arr = new int[n]` | `arr = [0] * n` |
| **哈希映射** | `HashMap<K,V>` | `dict` / `defaultdict` |
| **哈希集合** | `HashSet<T>` | `set()` |
| **优先队列/堆** | `PriorityQueue` | `heapq` |
| **双端队列/队列** | `Queue<T>` / `Deque<T>` | `deque` |
| **排序** | `Arrays.sort(arr)` / `Collections.sort()` | `arr.sort()` / `sorted(arr)` |
| **空值** | `null` | `None` |
| **无穷大** | `Integer.MAX_VALUE` | `float('inf')` |
| **字符串定位字符** | `str.charAt(i)` | `s[i]` |
| **字符串长度** | `str.length()` | `len(s)` |
| **集合/列表大小** | `list.size()` | `len(list)` |

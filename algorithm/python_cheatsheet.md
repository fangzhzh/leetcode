# Python 刷题语法速查 🐍

## 基础数据结构

```python
# List
arr = [1, 2, 3]
arr.append(4)          # 尾部添加
arr.pop()              # 弹出最后一个
arr.pop(0)             # 弹出第一个
arr.sort()             # 原地排序
arr.sort(reverse=True) # 降序
arr.sort(key=lambda x: x[1])  # 按自定义排序
arr[::-1]              # 反转
arr[1:3]               # 切片 [1,2]
len(arr)

# List 初始化
dp = [0] * n
grid = [[0] * cols for _ in range(rows)]

# String
s = "hello"
s[0]                   # 'h'
s[::-1]                # 反转
s.split(" ")           # 按空格分割
"".join(["a","b"])     # "ab"
s.isdigit()            # 是否全数字
s.isalpha()            # 是否全字母
ord('a')               # 97
chr(97)                # 'a'

# Dict (HashMap)
d = {}
d["key"] = 1
d.get("key", 0)        # 不存在返回0
"key" in d              # 查找 O(1)
del d["key"]
for k, v in d.items():

# Counter（刷题神器）
from collections import Counter
cnt = Counter("aabbc")  # {'a':2, 'b':2, 'c':1}
cnt.most_common(2)      # [('a',2), ('b',2)]

# Set
s = set()
s.add(1)
s.remove(1)
s.discard(1)            # 不存在不报错
1 in s                  # O(1)

# Tuple（不可变，可做dict的key）
t = (1, 2)
```

## 栈 & 队列

```python
# 栈 — 直接用 list
stack = []
stack.append(x)    # push
stack.pop()        # pop
stack[-1]          # peek

# 队列 — 用 deque（O(1) 两端操作）
from collections import deque
q = deque()
q.append(x)        # 右边入
q.appendleft(x)    # 左边入
q.pop()            # 右边出
q.popleft()        # 左边出（常用）

# 单调栈模板
stack = []
for i, num in enumerate(nums):
    while stack and nums[stack[-1]] < num:
        stack.pop()
    stack.append(i)
```

## 堆 (Priority Queue)

```python
import heapq
heap = []
heapq.heappush(heap, 3)
heapq.heappop(heap)       # 弹出最小值
heap[0]                    # peek 最小值

# 最大堆 → 取负
heapq.heappush(heap, -val)

# 从 list 建堆 O(n)
heapq.heapify(arr)

# Top K
heapq.nlargest(k, arr)
heapq.nsmallest(k, arr)
```

## 排序 & 二分

```python
# 排序
arr.sort()                          # 原地
sorted(arr)                         # 返回新 list
sorted(arr, key=lambda x: x[1])    # 按第二个元素

# 二分查找
from bisect import bisect_left, bisect_right
i = bisect_left(arr, target)   # 第一个 >= target 的位置
i = bisect_right(arr, target)  # 第一个 > target 的位置
```

## 常用算法模板

```python
# BFS
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

# DFS
def dfs(graph, node, visited):
    visited.add(node)
    for nei in graph[node]:
        if nei not in visited:
            dfs(graph, nei, visited)

# 二叉树遍历
def inorder(root):
    if not root: return
    inorder(root.left)
    print(root.val)
    inorder(root.right)

# 回溯模板
def backtrack(path, choices):
    if 满足条件:
        result.append(path[:])
        return
    for choice in choices:
        path.append(choice)
        backtrack(path, 剩余choices)
        path.pop()  # 撤销
```

## 技巧 & 常用写法

```python
# 无穷大
float('inf')
float('-inf')

# 交换
a, b = b, a

# 解构
a, b, c = [1, 2, 3]

# 列表推导
squares = [x**2 for x in range(10)]
even = [x for x in arr if x % 2 == 0]

# enumerate（带索引遍历）
for i, val in enumerate(arr):

# zip（并行遍历）
for a, b in zip(arr1, arr2):

# defaultdict（省去初始化判断）
from collections import defaultdict
graph = defaultdict(list)
graph[u].append(v)

count = defaultdict(int)
count[key] += 1

# 位运算
n & 1          # 判断奇偶
n >> 1         # 除以2
n << 1         # 乘以2
n & (n-1)      # 去掉最低位的1
bin(n).count('1')  # 数1的个数

# 数学
abs(-5)        # 5
max(a, b)
min(a, b)
sum(arr)
divmod(10, 3)  # (3, 1) → 商和余数
pow(2, 10)     # 1024
```

## 和 Java 对比速记

| Java | Python |
|------|--------|
| `int[] arr = new int[n]` | `arr = [0] * n` |
| `HashMap<K,V>` | `dict` / `defaultdict` |
| `HashSet<T>` | `set()` |
| `PriorityQueue` | `heapq` |
| `Queue<T>` | `deque` |
| `Arrays.sort()` | `arr.sort()` |
| `null` | `None` |
| `Integer.MAX_VALUE` | `float('inf')` |
| `str.charAt(i)` | `s[i]` |
| `str.length()` | `len(s)` |
| `list.size()` | `len(list)` |

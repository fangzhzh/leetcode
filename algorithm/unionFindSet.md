# 并查集
* 并查集是一种数据结构
* 并查集这三个字，一个字代表一个意思。
* 并（Union），代表合并
* 查（Find），代表查找
* 集（Set），代表这是一个以字典为基础的数据结构，它的基本功能是合并集合中的元素，查找集合中的元素

并查集的典型应用是有关连通分量的问题

并查集解决单个问题（添加，合并，查找）的时间复杂度都是O(1)

因此，并查集可以应用到在线算法中

Union Find = Disjoint Set

## Core concepts
* `find(x)`: Determine which set x belongs to.
* `union(x, y)`: Merge two sets together

## Common applications
* Finding connected components in a graph
* Detecting cycles in a graph
* Network connectivity
    * [[0, 1], [1, 2], [3, 4]]
* Image processing (connected pixels)


## 实现

并查集跟树很像，只不过，在并查集这个数据结构里，节点记录父节点，树记录子节点

> [[0, 1], [1, 2], [3, 4]]`
### 实现
```java
class UnionFind {
    private int[] parent;
    
    public UnionFind(int size) {
        parent = new int[size];
        
        // Initially, each element is its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    // Find without path compression
    public int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }
    
    // Union without rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            parent[rootY] = rootX;  // Simply make rootX the parent of rootY
        }
    }
    
    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

## Optimizations
### Optimization1: 路径压缩(path compressoin)
图中的并查集可能会退化成长长列表，0->1->2->......->999->1000,这是时间复杂度退化为O(n)

```java
int find(x) {
    int root = x;
    while[fa[root] != root] {
        r = fa[root];
    }

    while(fa[x] != x) {
        int parent = fa[x];
        fa[x] = root;
        x = parent;
    }
    return x;
}
```
![路径压缩版本的find](./graphs/unionFindSetCompression.drawio.svg)



```java
class UnionFind {
    private Map<Integer,Integer> father;
    
    public UnionFind() {
        father = new HashMap<Integer,Integer>();
    }
    
    public void add(int x) {
        if (!father.containsKey(x)) {
            father.put(x, null);
        }
    }
    
    public void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY){
            father.put(rootX,rootY);
        }
    }
    
    public int find(int x) {
        int root = x;
        
        while(father.get(root) != null){
            root = father.get(root);
        }
        
        while(x != root){
            int original_father = father.get(x);
            father.put(x,root);
            x = original_father;
        }
        
        return root;
    }
    
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
} 
```

### Optimization 2: Uinon by rank
```java
class UnionFind {
    private int[] parent;
    private int[] rank;  // Used for path compression
    
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        
        // Initially, each element is its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }
    
    // Union by rank
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX != rootY) {
            // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;  // Smaller tree (rootX) attaches to larger tree (rootY)
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;  // Smaller tree (rootY) attaches to larger tree (rootX)
        } else {  // Same rank
            parent[rootY] = rootX;  // Arbitrary choice
            rank[rootX]++;  // Height increases by 1
        }
        }
    }
    
    // Check if two elements are in the same set
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

#### How union by rank help optimize the algorithm
```
Initial state:     After union without rank:    After union with rank:
1       4          1                           4
|       |          |                          / \
2       5          2                         1   5
|                  |                         |
3                  3                         2
                   |                         |
                   4                         3
                   |
                   5
Height: 2,2        Height: 5                 Height: 3

```
## Union Find Algorithm Visualization
### Initial State
```
Initially, each element is its own parent:

Elements:   0   1   2   3   4   5
Parents:    0   1   2   3   4   5
Rank:       1   1   1   1   1   1

Visualization:
0    1    2    3    4    5
⭕   ⭕   ⭕   ⭕   ⭕   ⭕
(Each node is a separate tree)
```

### Step 1: Union(1, 2)
```
After union(1, 2):

Elements:   0   1   2   3   4   5
Parents:    0   1   1   3   4   5
Rank:       1   2   1   1   1   1

Visualization:
0       1       3    4    5
⭕      ⭕      ⭕   ⭕   ⭕
       /
      2
```

### Step 2: Union(3, 4)
```
After union(3, 4):

Elements:   0   1   2   3   4   5
Parents:    0   1   1   3   3   5
Rank:       1   2   1   2   1   1

Visualization:
0       1       3        5
⭕      ⭕      ⭕       ⭕
       /      /
      2      4
```

### Step 3: Union(1, 3)
```
After union(1, 3) (union by rank - 1 and 3 have same rank, arbitrarily choose 1 as root):

Elements:   0   1   2   3   4   5
Parents:    0   1   1   1   3   5
Rank:       1   3   1   2   1   1

Visualization:
0       1           5
⭕      ⭕          ⭕
      /  \
     2    3
          |
          4
```

### Path Compression Example
```
When we call find(4), before path compression:
4 -> 3 -> 1

After path compression:
4 directly points to 1

Elements:   0   1   2   3   4   5
Parents:    0   1   1   1   1   5
Rank:       1   3   1   2   1   1

Visualization:
0       1           5
⭕      ⭕          ⭕
     /  |  \
    2   3   4
```

### Key Points

1. **Path Compression**
   - Makes the tree flat
   - All nodes point directly to root
   - Improves future operations

2. **Union by Rank**
   - Keeps trees balanced
   - Attaches smaller tree to larger tree
   - Prevents long chains

3. **Time Complexity**
   ```
   Without optimizations:
   - find():  O(N)
   - union(): O(N)

   With path compression and union by rank:
   - find():  ~O(1)
   - union(): ~O(1)
   ```

## Common Operations Visualization

### find(x) Operation
```
1. Start at node x
2. Follow parent pointers until reaching root
3. (With path compression) Make all nodes point to root

Before:    After path compression:
  1           1
 /           / \
2           2   3
 \
  3
```

### union(x, y) Operation
```
1. Find roots of x and y
2. Compare ranks
3. Attach smaller rank tree to larger rank tree

Before:     After union(1,4):
1    4           1
|    |          / \
2    5         2   4
                   |
                   5
```

## 例题
* Number of Connected Components in an Undirected Graph
* Friend Circles / Number of Provinces
* Redundant Connection
* Accounts Merge
* Number of Islands II

### 323.无向图中连通分量的数目

```
给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。

示例 1:

输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

输出: 2
示例 2:

输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

输出:  1

```

很明显的一个可以用并查集的问题，但是图中的问题，连通分量的数目，并查集本身并不能给出答案，这时往往我们需要另一个辅助的数据结构来保存结果

```java
class Solution {
    // 323. 无向图中连通分量的数目
    // Time O(n)
    // Space O(n)
    Map<Integer, Integer> uf = new HashMap<>(); // 储存连通分量
    Set<Integer> set = new HashSet<>(); // 储存跟节点个数

    void add(int x) {
        if(!uf.containsKey(x)) {
            uf.put(x, x);
        }
    }
    void merge(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX != rootY) {
            uf.put(rootY, rootX);
        }
    }
    int find(int x) {
        int root = x;
        while(root != uf.get(root)) {
            root = uf.get(root);
        }
        while(x != root) { // 压缩路径
            int tmp = uf.get(x);
            uf.put(tmp, root);
            x = tmp;
        }
        return root;
    }
    public int countComponents(int n, int[][] edges) {
        // 初始化并查集，每个节点都不需加入
        for(int i = 0; i < n; i++) {
            add(i);
        }
        for(int[] edge: edges) { // 构建并查集
            merge(edge[0], edge[1]);
        }
        for(int i = 0; i < n; i++) { // 计算根节点个数，也就是连通分量的个数
            set.add(find(i));
        }
        return set.size();
    }
}
```


## 并查集需要处理的情况
由上题可以看出，并查集只能解决连通分量的问题，在问题里，还需要处理更多的问题
* 有多少个连通分量
* 连通分量的个数
* 连通分量有没有环
* 

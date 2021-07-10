# 并查集
* 并查集是一种数据结构
* 并查集这三个字，一个字代表一个意思。
* 并（Union），代表合并
* 查（Find），代表查找
* 集（Set），代表这是一个以字典为基础的数据结构，它的基本功能是合并集合中的元素，查找集合中的元素

并查集的典型应用是有关连通分量的问题

并查集解决单个问题（添加，合并，查找）的时间复杂度都是O(1)O(1)

因此，并查集可以应用到在线算法中

Union Find = Disjoint Set

## 实现

并查集跟树很像，只不过，在并查集这个数据结构里，节点记录父节点，树记录子节点

### 实现图解
![并查集的实现图解](./graphs/unionFind.drawio.svg)



### 路径压缩
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
# 拓扑排序 Topological sorting

给定一个包含 n 个节点的有向图 G，其中一条有向边(u,v)，表示一种依赖关系，即v依赖u，即v要在u完成之后才能进行。

我们给出它的节点编号的一种排列，如果满足：

    对于图 G 中的任意一条有向边 (u, v),u 在排列中都出现在 v 的前面。

那么该排列是图G的拓扑排序。

求出一种拓扑排序方法的最优时间复杂度是O(m+n)，其中m和n分别是有向图 G的节点数和边数。

判断G是否存在拓扑排序，至少需要对其进行一次完整的遍历，O(m+n)，所以不存在一种优于O(m+n)的解法。

## 深度优先

### 思路：

我们可以将深度优先搜索和拓扑排序求解联系起来，用dfs来查找(存储)所有**已经搜索完成的节点**.

什么是一个已经搜索完成的节点呢

    对于一个节点u，如果它所有的相邻节点都已经搜索完成，那么在搜索回溯到u的时候，u本身会变成一个已经搜索完成的节点。

    这里的“相邻节点”指从u出发通过一条有向边可以到达的 所有节点。

    已经搜索完成的节点在拓扑排序里就是一个无依赖的节点。


* 有向图DFS -> 有向图递归找依赖关系中的终点 -> 找到入度为0的节点 -> 终点就是**已经搜索完成的节点**

* 回溯 -> DFS回溯到u时，意味着u的所有相邻节点都已经搜索完成，此时，u本身也变成一个已经搜索完成的节点。

![图示DFS拓扑排序](./graphs/toplogicSortingDFS.drawio.svg)

### 例题  207.Course Schedule
```
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
```
标准的拓扑排序，那么我们用前几天学到的DFS来解决这个拓扑排序

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 邻接表表示有向图
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<prerequisites.length;i++){
            int[] prerequisite = prerequisites[i];
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        return canFinishDFS(numCourses,     graph);
    }

    // Time O(numCouse ^2 )
    boolean canFinishDFS(int numCourses, List<List<Integer>>graph) {
        // 有向无环图
        // dfs
        int[] visited = new int[numCourses];
        // 对每一个节点进行dfs
        for(int i=0; i<numCourses; i++) {
            if(visited[i] == 0) {
                if(!dfs(graph, i, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean dfs(List<List<Integer>>graph, int idx, int[]visited) {
        // 对每个节点的相邻节点
        for(int v : graph.get(idx)) {
            visited[idx] = 1; // 正在visit
            if(match(visited, v)) { // 准备visit节点v，此时v状态是1 正在visit，那么我们找到一个环，拓扑排序不成立
                return false;
            }
            if(visited[v] == 0) {
                if(!dfs(graph, v, visited)){
                    return false;
                }
            }
            visited[idx] = 2;
        }
        return true;
    }

    boolean match(int[] visited, int idx) {
        return visited[idx] == 1;
    }
}
```

### 例题:210.Course Schedule II

210.Course Schedule II
```java
There are` a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]
```

解答

```java
class Solution {
    boolean isValid = true;
    // topologic DFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 理解题意: topologic order, bfs的比较好想，就是按出度0入栈，不停出栈，修改其他节点的出度，见0进栈
        // dfs有点麻烦，递归的dfs不好传递"环"(isValid==false)回顶层调用，我们用一个isValid来帮助

        // graph，邻接表表示图，节省空间，代码好处理
        List<Integer>[] map = new List[numCourses];
        for(int i = 0; i < numCourses; i++) {
            map[i] = new ArrayList<>();
        }
        buildGraph(numCourses, prerequisites, map);

        List<Integer> ans = new ArrayList<>();
        // visited 三种状态 
            // 0(未处理), 等待处理 
            // 1(处理中)，处理到一个1的节点，证明碰到环，那么是一个无效的
            // 2(处理完)，已经被处理过，不需要再次处理
        int[]visited = new int[numCourses];
        for(int i = 0; i< numCourses; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                dfsFindOrder(map, i, ans, visited);
                visited[i] = 2;
                ans.add(i);
            }
 
        }
        if(!isValid) return new int[0];

        int[] ansa = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            ansa[i] = ans.get(i);
        }
        return ansa;
    }
    void buildGraph(int numCourses, int[][] prerequisites, List<Integer>[]map) {
        for(int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            map[edge[0]].add(edge[1]);
        }
    }

    void dfsFindOrder(List<Integer>[]map, int idx, List<Integer> ans, int[] visited) {
        List<Integer> deps = map[idx];
        for(int i =0; i<deps.size(); i++) {
            int j = deps.get(i);
            if(visited[j] == 1) {
                isValid = false;
                return;
            }
            if(visited[j] == 0) {
                visited[j] = 1;
                dfsFindOrder(map, j, ans, visited);
                visited[j] = 2;
                ans.add(j);
            }
        }
    }
}
```
## 广度优先
这个是经典解法，我们在构造DAG的同时，构造一个入度数组。

初始化，将所有入度为0的节点放到队列里。

广度优先的每一步，我们取出队首节点u
* u放入答案
* 移除u的所有出边，也就是所有相邻节点的入度减1，如果某个相邻节点v入度变为0，将v入列


![图示BFS拓扑排序](./graphs/toplogicSortingBFS.drawio.svg)

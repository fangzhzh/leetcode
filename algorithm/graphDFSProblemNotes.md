# 图论DFS错题本

## backtrack的易错写法

[dfsBackTracking笔记](./dfsBacktracking.md)写到dfs问题的模板



```
function DFSProblem:
    定义路径，定义选择状态
    定义result
    for 可能解 in 选择列表
        backtrack(选择列表，路径(可能解+选择状态), result)
    end
    return result

def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

有时候，dfs进去的时候，做选择，然后backtrack这块，会有一种错误的写法
```
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    *做选择*1
    for 选择 in 选择列表:
        *做选择*2
        backtrack(路径, 选择列表)
        撤销选择
```

这种写法，先对当前选择做选择，然后对选择列表做选择，那么结束条件就变得非常难写，因为要考虑当前做了选择，也要考虑forloop里也做了选择。当有这种需求的时候，要把做选择1，提到caller函数那里。

### DFS backtrack错误写法

```
547. Number of Provinces
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

```

#### 错误解法1

```java
    int dfs2(int[][] isConnected) {
        int n = isConnected.length;
        int cnt = 0;
        int[] cnt1 = new int[1];
        boolean[] visited =  new boolean[n];
        dfs2(isConnected, visited, 0, cnt1);
        return cnt1[0];
    }

    void dfs2(int[][] isConnected, boolean[] visited, int idx, int[] idxRt) {
        int n = isConnected.length;
        if(idx == n || visited[idx]) return;
        visited[idx] = true; // 设置true
        for(int i = 0; i < n; i++) {
            if(i != idx && visited[i] == false && isConnected[idx][i] == 1) {
                visited[i] = true; // 设置true
                dfs2(isConnected, visited, i, idxRt);
                idxRt[0]++;
            }
        }
        dfs2(isConnected, visited, idx+1, idxRt);
    }
```
这种解法会忽略掉只有一个节点的路径，因为没有机会进入到for循环里的if语句。

#### 错误解法2

可以加上一个条件，判断是不是唯一节点
```java
    int dfs2(int[][] isConnected) {
        int n = isConnected.length;
        int cnt = 0;
        int[] cnt1 = new int[1];
        boolean[] visited =  new boolean[n];
        dfs2(isConnected, visited, 0, cnt1);
        return cnt1[0];
    }

    void dfs2(int[][] isConnected, boolean[] visited, int idx, int[] idxRt) {
        int n = isConnected.length;
        if(idx == n || visited[idx]) return;
        visited[idx] = true; // 设置true
        boolean hasNeighbor = false;
        for(int i = 0; i < n; i++) {
            if(i != idx && visited[i] == false && isConnected[idx][i] == 1) {
                hasNeighbor = true;
                visited[i] = true; // 设置true
                dfs2(isConnected, visited, i, idxRt);
                idxRt[0]++;
            }
        }
        if(!hasNeighbor) {
            idxRt[0]++;
        }
        dfs2(isConnected, visited, idx+1, idxRt);
    }
```

这种写法又会把连通图的最后一个节点重复计算。

#### 正确的解法
```java
    int dfs1(int[][] isConnected) {
        int n = isConnected.length;
        int cnt = 0;
        boolean[] visited =  new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i] == false) {
                visited[i] = true;
                dfs1(isConnected, visited, i);
                cnt++;
            }
        }
        return cnt;
    }
    void dfs1(int[][] isConnected, boolean[] visited, int idx) {
        int n = isConnected.length;
        for(int i = 0; i < n; i++) {
            if(!visited[i] && isConnected[idx][i] ==1) {
                visited[i] = true;
                dfs1(isConnected, visited, i);
            }
        }
    }
```

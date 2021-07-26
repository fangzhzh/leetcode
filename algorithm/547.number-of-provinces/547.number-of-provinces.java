class Solution {
    // 相似题目: 721账户合并
    public int findCircleNum(int[][] isConnected) {
        return findCircleNumUnionFind(isConnected);
    } 
    private int findCircleNumUnionFind(int[][] isConnected) {
        int n = isConnected.length;
        // build
        int[] parent = new int[n];
        // Map<Integer, Integer> parent = new HashMap<>();
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // merge
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(isConnected[i][j] == 1) {
                    merge(parent, i, j);
                }
            }
        }
        // find
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(parent[i] == i) {
                cnt++;
            }
        }
        return cnt;
    }
    private void merge(int[] parent, int i, int j) {
        int pi = find(parent, i);
        int pj = find(parent, j);
        parent[pj] = pi;
    }

    private int find(int[] parent, int i) {
        int p = i;
        while(parent[p] != p) {
            p = parent[p];
        }
        return p;
    }
    // Time O(n^2)  Space O(n^2)
    private int findCircleNumDFS(int[][] isConnected) {
        int n = isConnected.length;
        // 标准的无向图 求连通
        boolean[]visited = new boolean[n];
        int cnt = 0;
        // 深度优先遍历
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                cnt++;
                dfs(isConnected, i, visited);
            }
        }
        return cnt;
    }

    // def backtrack(路径, 选择列表):
    // graph 选择列表, visited结束条件
    private void dfs(int[][] isConnected, int idx,boolean[]visited) {
        int n = isConnected.length;
        // 退出条件
        if(visited[idx]) {
            return;
        }
        // 操作
        visited[idx] = true;
        // 继续遍历条件
        for(int i = 0; i < n; i++) {
            if(isConnected[idx][i]==1 && !visited[i]) {
                dfs(isConnected, i, visited);
            }
        }
    }
}
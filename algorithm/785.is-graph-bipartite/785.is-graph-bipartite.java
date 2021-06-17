/*
 * @lc app=leetcode id=785 lang=java
 *
 * [785] Is Graph Bipartite?
 */



/**
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B, such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

 

Example 1:


Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:


Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: We cannot find a way to divide the set of nodes into two independent subsets.
 

Constraints:

1 <= graph.length <= 100
0 <= graphp[i].length < 100
0 <= graph[i][j] <= graph.length - 1
graph[i][j] != i
All the values of graph[i] are unique.
The graph is guaranteed to be undirected. 
 */
// @lc code=start


/**
 * From the graph, it's hard to tell what's biparties grap
 * but ![](https://media.geeksforgeeks.org/wp-content/uploads/bipartitegraph-1.jpg) works
 * {0,2} <=> {1,3}, it simply means two adjacent node will not in the same set
 * - method 1, union find
 * - method2, bfs, dfs to color
 * 
 * why color words? If we paint a v as red, and it's all adjacent blue, 
 * it a v will pained two different colors, it's not a biparties
 * https://www.geeksforgeeks.org/bipartite-graph/
 * Please note, a cycle graph might be a bipartite graph as well.
 */
class Solution {
    public boolean isBipartite(int[][] graph) {
        int[]color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();

            queue.offer(i);
            color[i] = 1;
            while(!queue.isEmpty()) {
                int v = queue.poll();
                int newColor = color[v] == 1 ? 2 : 1;
                for(int tail : graph[v]) {
                    if(color[tail] == color[v]) {
                        return false;
                    }
                    if(color[tail] == 0) {
                        color[tail] = newColor;
                        queue.offer(tail);
                    }
                }

            }
        }
        return true;
    }
}
// @lc code=end


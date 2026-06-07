/**
 * 323. Number of Connected Components in an Undirected Graph
 * 
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] 
 * indicates that there is an edge between ai and bi in the graph.
 * 
 * Return the number of connected components in the graph.
 * 
 * Example 1:
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 * 
 * Example 2:
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 * 
 * Constraints:
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai <= bi < n
 * ai != bi
 * There are no repeated edges.
 */
public class Solution {
    /**
     * @param n The number of nodes in the graph.
     * @param edges The list of undirected edges.
     * @return The number of connected components.
     */
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        // Initialize each node as its own parent
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int size = n;
        for (int[] edge : edges) {
            int root0 = find(parent, edge[0]);
            int root1 = find(parent, edge[1]);

            // If they belong to different components, union them
            if (root0 != root1) {
                parent[root0] = root1;
                size--;
            }
        }

        return size;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        // Path compression: update the parent to the root during the recursive climb
        int root = find(parent, parent[i]);
        parent[i] = root;
        return root;
    }
}
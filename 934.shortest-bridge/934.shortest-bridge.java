/*
 * @lc app=leetcode id=934 lang=java
 *
 * [934] Shortest Bridge
 */
/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)

Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.

Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)

 

Example 1:

Input: A = [[0,1],[1,0]]
Output: 1
Example 2:

Input: A = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 */


/**
 * dfs to mark
 * bfs to find level
 *  */ 
// @lc code=start
class Solution {
    public int shortestBridge(int[][] A) {
        if(A==null || A.length==0) return 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue= new LinkedList<>();
        int m = A.length, n = A[0].length;
        boolean found = false;
        for(int i = 0; i < m; i++) {
            if(found) {
                break;
            }
            for(int j = 0; j < n; j++) {
                if(A[i][j] == 1) {
                    dfs(A, i, j, queue);
                    found = true;
                    break;
                }
            }
        }
        
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int[] tmp = queue.poll();
                for(int[] dir : dirs) {
                    int i = tmp[0] + dir[0];
                    int j = tmp[1] + dir[1];
                    if(i <0 || i>=m || j < 0 || j >= n) {
                        continue;
                    }
                    if(A[i][j] == 1) {
                        return level;
                    } 
                    if (A[i][j] == 0) {
                        A[i][j] = 2;
                        queue.offer(new int[]{i, j});
                    }
                
                } 
            }
            level++;
            
        }
        return level;
        

    }
    void dfs(int[][]grid, int i , int j, Queue<int[]> queue) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }

        grid[i][j] = 2;
        queue.offer(new int[]{i, j});
        dfs(grid, i - 1, j, queue);
        dfs(grid, i + 1, j, queue);
        dfs(grid, i, j - 1, queue);
        dfs(grid, i, j + 1, queue);
    }
}
// @lc code=end


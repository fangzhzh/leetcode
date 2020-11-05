/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 */

/**
 * 
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 * 
 */
// @lc code=start
/**
 * Intuition is to calculate count from left top to right bottom
 * grid[i][j] == '1' and grid[i-1][j] == '0' && grid[i][j-1] == '0', ans++
 * 
 * But it's not correct
 * 
 * '1','1','0','0','0'
 * '1','1','0','0','0'
 * '1','0','1','1','0'
 * '1','1','1','1','0'
 * 
 * Consider the above example, gritd[2][2] will be ans++, but grid[3][2] is '1', 
 * make it a part of grid[0][0] island.
 * 
 * But if we consider the bottom one, how about the right one. 
 * if every consider points around, the boundary checking will be a disaster.
 * 
 * 
 */
/**
 * We take advantage the fact that islands only be calculated once,
 * so once find one, we weep this island by make any conjacent '1' to '0'.
 */
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if(m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if(n == 0) {
            return 0;
        }
        int ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j =0; j < n; j++) {
                if(grid[i][j] == '1') {
                    ans++;
                    sweep(grid, m, n, i, j);
                }
            }
        }
        return ans;
    }
    private void sweep(char[][]grid, int m, int n, int i, int j) {
        if( i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') return;
        grid[i][j] = '0';
        sweep(grid, m, n, i-1, j);
        sweep(grid, m, n, i+1, j);
        sweep(grid, m, n, i, j-1);
        sweep(grid, m, n, i, j+1);
    }
}
// @lc code=end


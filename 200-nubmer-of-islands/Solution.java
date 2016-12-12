public class Solution {
    public int numIslands(char[][] grid) {
        int sum = 0;
        if(grid.length == 0 || grid[0].length == 0) return sum;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == '1') {
                    sum++;
                    clearRow(grid, i, j, m, n);
                }
            }
        }
        return sum;
    }

    void clearRow(char[][] grid, int i, int j, int m, int n) {
        if(grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        if(i > 0) clearRow(grid, i-1, j, m, n);
        if(i < m-1) clearRow(grid, i+1, j, m, n);
        if(j  > 0) clearRow(grid, i, j-1, m, n);
        if(j < n-1) clearRow(grid, i, j+1, m, n);

        /*
        int y = j;
        while(y < n && grid[i][y] == 1) {
            grid[i][y] = 0;
            y++;
        }
        if(grid[i+1][j] == 1) {
            clearRow(grid, i+1, j, n);
        }
        */
    }
}

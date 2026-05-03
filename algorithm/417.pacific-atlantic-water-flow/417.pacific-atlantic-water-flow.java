/*
 * @lc app=leetcode id=417 lang=java
 * @lcpr version=30403
 *
 * [417] Pacific Atlantic Water Flow
 */

// @lc code=start
class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean pac[][] = new boolean[m][n];
        boolean atl[][] = new boolean[m][n];

        for(int i = 0; i < m; i++) {
            pac[i][0] = true;
            atl[i][n-1] = true;
        }
        for(int i = 0; i < n; i++) {
            pac[0][i] = true;
            atl[m-1][i] = true;
        }

        for(int i = 0; i < m; i++) {
            dfs(heights, i, 0, pac);
        }
        for(int i = 0; i < n; i++) {
            dfs(heights,  0, i, pac);
        }

        for(int i = 0; i < m; i++) {
            dfs(heights,  i, n-1, atl);
        }
        for(int i = 0; i < n; i++) {
            dfs(heights, m-1, i, atl);
        }


        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(pac[i][j] == true && atl[i][j] == true) {
                    List<Integer> list = new ArrayList<>(); 
                    list.add(i);
                    list.add(j);
                    res.add(list);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] heights, int i, int j, boolean canReach[][]) {
        if(!canReach[i][j]) {
            return;
        }
        int m = heights.length;
        int n = heights[0].length;
        int[][] dirs = new int[][]{
            {0,1},
            {0,-1},
            {1,0},
            {-1,0},
        };

        for(int[] dir: dirs) {
            int newI = i+dir[0];
            int newJ = j+dir[1];
            if(newI < 0 || newI >= m || newJ < 0 || newJ >= n) {
                continue;
            }
            if(canReach[newI][newJ] == true) continue;

            if(heights[i][j] <= heights[newI][newJ]) {
                canReach[newI][newJ] = true;
                dfs(heights, newI, newJ, canReach);
            }
        }
    }
}
// @lc code=end



/*
// @lcpr case=start
// [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]\n
// @lcpr case=end

// @lcpr case=start
// [[1]]\n
// @lcpr case=end

 */


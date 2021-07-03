class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 1 2 3
        // 4 5 6
        // 7 8 9 
        // 顺时针,
        int[][] dirs = {{0,1}, {1,0},{0,-1}, {-1,0}};
        // 逆时针, 1,4,7,8,9,6,3,2,5
        // int[][] dirs = {{0,1}, {1,0},{0,-1}, {-1,0}};

        // 左上角，对角线,从上往下扫描到右对角线
        // [0,0], [[0,1],[1,0]],[[0,2],[1,1],[2.0]],[[1,2],[2,1]],[2,2]
        // 1, [4,2],[3,5,7],[6,8],[9]
        // 起点顺时针螺旋，螺旋到右下角，每移动一次，从上往下扫对角线
        // int[][]

        // 左上角，对角线，从下往上扫描到右下角
        // [0,0], [[1,0],[0,1]],[[2.0],[1,1],[0,2]],[[2,1],[1,2]],[2,2]
        // 1, [4,2], [7,5,3], [8,6],9
        // 左上角，逆时针螺旋，螺旋到右下角，每移动一次，从下往上扫对角线


        // top, left, right, bottom, visited
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        boolean [][] visited = new boolean[m][n];
        int idx = 0;
        int row = 0, col = 0;
        for(int cnt =0; cnt < m*n; cnt++) {
            result.add(matrix[i][j]);
            visited[row][col] = true;
            int nrow = i+dirs[idx][0];
            int ncol = j+dirs[idx][1];
            if(nrow <0 || nrow>=m || ncol<0 || ncol>=n || visited[nrow][ncol])  {
                idx = (idx+1) % 4;
            }
            nrow += dirs[idx][0];
            ncol += dirs[idx][1];
        }
        return result;
    }
}

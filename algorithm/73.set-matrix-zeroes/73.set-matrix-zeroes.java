class Solution {
    public void setZeroes(int[][] matrix) {
        setZeroesO1(matrix);
    }
    
    private void setZeroesO1(int[][] matrix) {
        // 不需要额外空间，那就把flag投射到第一行第一列
        // 投射前，还需要知道第一行，第一列里本来有没有0，有的话，第一行第一列也许要置零
        int row = matrix.length;
        int col = matrix[0].length;

        boolean zeroInFistRow = false;
        boolean zeroInFistCol = false;
        for(int i = 0; i < col; i++) {
            if(matrix[0][i] == 0) {
                zeroInFistRow = true;
                break;
            }
        }

        for(int i = 0; i < row; i++) {
            if(matrix[i][0] == 0) {
                zeroInFistCol = true;
                break;
            }
        }
        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for(int i = 1; i < col; i++) {
            if(matrix[0][i] == 0) {
                for(int j = 1; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for(int i = 1; i < row; i++) {
            if(matrix[i][0] == 0) {
                for(int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(zeroInFistRow) {
            for(int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }

        // System.out.println("zeroInFistCol");
        if(zeroInFistCol) {
            for(int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    // space O(m+n)
    public void setZeroesMPlusN(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // flag记录行列需要置零
        boolean[] rowFlag = new boolean[row];
        boolean[]colFlag = new boolean[col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    rowFlag[i] = true;
                    colFlag[j] = true;
                }
            }
        }
        for(int i = 0; i < row; i++) {
            if(rowFlag[i]) {
                for(int j = 0; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i = 0; i < col; i++) {
            if(colFlag[i]) {
                for(int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

    }

    // space O(m*n)
    private void  setZeroesMXN(int[][]matrix) {
        // 遍历，看到没有访问过的0，，则清0行列，从非0变0的，标记
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0 && !visited[i][j]) {
                    setZeroes(matrix, i, j, visited);
                }
            }
        }

    }

    private void setZeroes(int[][] matrix, int curRow, int curCol, boolean[][]visited) {
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0; i < row; i++) {
            if(matrix[i][curCol] != 0) {
                visited[i][curCol] = true;
            }
            matrix[i][curCol] = 0;
        }
        for(int i = 0; i < col; i++) {
            if(matrix[curRow][i] != 0) {
                visited[curRow][i] = true;
            }
            matrix[curRow][i] = 0;
        }
    }

}
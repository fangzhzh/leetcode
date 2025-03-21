/*
 * @lc app=leetcode id=36 lang=java
 *
 * [36] Valid Sudoku
 */

// @lc code=start
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++) {
            Set<Character> setRow = new HashSet();
            Set<Character> setCol = new HashSet();
            for(int j = 0; j < n; j++) {
                if(board[i][j] != '.') {
                    if(setRow.contains(board[i][j])) {
                        // System.out.println("line:9 " + i + " "+ j);
                        return false;
                    }
                    setRow.add(board[i][j]);

                }
                if(board[j][i] != '.') {
                    if(setCol.contains(board[j][i])) {
                        // System.out.println("line:13 " + i + " "+ j);
                        return false;
                    }
                    setCol.add(board[j][i]);
                }

            }
        }
        for(int i = 0; i < m; i+=3) {
            for(int j = 0; j < n; j+=3) {
                if(!valid(board, i, j)) {
                    // System.out.println("line:23 " + i + " "+ j);
                    return false;
                }
            }
        }
        return true;
    }

    boolean valid(char[][] board, int i, int j) {
        Set<Character> set = new HashSet();
        // System.out.println("line:36 valid");
        for(int ni = i; ni < i+3; ni++) {
            for(int nj = j; nj < j+3; nj++) {
                if(board[ni][nj] == '.') continue;
                // System.out.println("line:37 (" + ni + " "+ nj + "):" + board[ni][nj]);
                if(set.contains(board[ni][nj])) {
                    return false;
                }
                set.add(board[ni][nj]);
            }
        }
        return true;
    }
}
// @lc code=end


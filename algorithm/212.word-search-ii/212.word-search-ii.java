/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 */

/**
 * 
 * 
 * Given an m x n board of characters and a list of strings words, return all words on the board.
Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.


Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
 *  */ 




/**
 * It's just a one step further than the word search problem, 
 * for(word: words) if(find(word)) {ans.add(word)}
 * 
 *  */ 
// @lc code=start
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();
        int m = board.length, n = board[0].length;
        for(String word : words) {
            boolean[][] visited = new boolean[m][n];
            if(findWord(board, word, visited)) {
                ans.add(word);
            }
        }
        return ans;
    }

    boolean findWord(char[][] board, String word, boolean[][] visited) {
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(helper(board, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    boolean helper(char[][] board, String word, int row, int col, int idx, boolean[][] visited) {
        int m = board.length, n = board[0].length;
        if(idx >= word.length()) return true;
        if(row < 0 || row >= m || col < 0 || col >=n) {
            return false;
        }
        if(visited[row][col]) {
            return false;
        }
        if(board[row][col] != word.charAt(idx)) {
            return false;
        }
        visited[row][col]  = true;
        if(
            helper(board, word, row+1, col, idx+1, visited)
            ||
            helper(board, word, row-1, col, idx+1, visited)
            ||
            helper(board, word, row, col+1, idx+1, visited)
            ||
            helper(board, word, row, col-1, idx+1, visited)
        ) {
            return true;
        }
        visited[row][col]  = false;
        return false;
    }
}
// @lc code=end


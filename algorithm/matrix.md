# martrix

矩阵也是一个大项目，虽然题型并不多，旋转，顺时针，逆时针，对角线。

## 遍历
* 顺时针
* 逆时针
* 对角线
* 负对角线
* 环形遍历

下面我们用一道题引入各种遍历

![54.spiral-matrix 螺旋矩阵](./graphs/matrix.drawio.svg)

## API
* 对角线: row == col
* 负对角线: row + col == n - 1

## 置零
根据一些条件的置零，
* 另建一个二维数组标记状态，然后根据状态标记 O(m*n)
* 零件两个一位数组标记状态，然后根据状态标记 O(m+n)
* 投射到矩阵的第一行，第一列
    * 这个需要注意，在处理时，需要避开第一行第一列，回头单独处理

[73.set-matrix-zeroes 矩阵置零](./73.set-matrix-zeroes)

![矩阵置零图解](./graphs/73-set-matrix-zeroes.drawio.svg)

## 赋值

根据某些条件，改变矩阵的某些元素的值
* mark 
    * mark可以区分原值和目的值
    * 一个作用是在重新赋值根据mark确定值
    * 另一个作用，dfs的时候，可以作为visited
* 重新赋值

例子 130. 被围绕的区域，一个naive，直接的的版本，就是找到每一个'O',然后dfs，找到所有'O',再查找是不是有处于边界的，如果是，就不处理。如果没有边界元素，全部赋值'X'。

 [130. 被围绕的区域](./graphs/surroundedRegions.drawio.svg)

本处，有一个需要注意的地方，需要处理所有dfs出来以后的结果以后，操作需要写到dfs同层的函数

```java
class Solution {

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i < n; i++) {
            if(board[0][i] == 'O') {
                dfs(board, 0, i);
            }
            if(board[m-1][i] == 'O') {
                dfs(board, m-1, i);
            }
        }
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if(board[i][n-1] == 'O') {
                dfs(board, i, n-1);
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if(board[i][j]=='O') {
                    board[i][j] = 'X';
                }

            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        int m = board.length;
        int n = board[0].length;

        if(row < 0 || row == m || col < 0 || col == n || board[row][col] != 'O') {
            return;
        }
        board[row][col] = 'A';
        dfs(board, row+1, col);
        dfs(board, row-1, col);
        dfs(board, row, col+1);
        dfs(board, row, col-1);
    }
}
```


## 计数
有些题目是要计算在matrix里每行每列有多少个X元素，然后需要计算个数来比较条件。
下面看例题

```
348. Design Tic-Tac-Toe
Assume the following rules are for the tic-tac-toe game on an n x n board between two players:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Implement the TicTacToe class:

TicTacToe(int n) Initializes the object the size of the board n.
int move(int row, int col, int player) Indicates that player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
Follow up:
Could you do better than O(n2) per move() operation?

 

Example 1:

Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
```
题目很长，其实就是玩家在matrix落子，谁先连上行、列、对角线、负对角线谁就胜利。

brutal force的方案就是每次move，把matrix这个地方赋值1，然后遍历matrix，每一行，每一列，每一对角线，每一个负对角线，算出来和n对比。


但是本题有一个关键的描述，**The move is guaranteed to be a valid move.**，如果能保证每一个move都是有效的，那么我们不需要计算落点，而是keep一个落子个数的hashmap。

```
x行 -> number
y列 -> number
z对角线 -> number
k负对角线 -> number
```

每次move的落子时，我们只需要更新对应的hashtable就好.
注意，如果此题目不能保证每一个都是valid move，比如改变条件，玩家可以把子下到以前已经下过的地方，我们可能就不能简单计数，就需要整个martrix，每次来计数；或者keep一个visited，如果访问过，则不用计数。

```java
class TicTacToe {

    // 相似: 73.set-matrix-zeroes
    // 理解题意: 计算出每行列对角线负对角线落子个数
    // 重新阐述: 设计数据结构跟踪计算出每行列对角线负对角线落子个数
    // 设计方案: map来快速访问行列对角线负对角线，也要能区分player

    int[][] rows;
    int[][] cols;
    int[] diag = new int[3];
    int n;
    int[] reverseDiag = new int[3];
    public TicTacToe(int n) {
        rows = new int[3][n];
        cols = new int[3][n];
        this.n = n;

    }
    
    // Time O(n) 
    // Space O(n)
    public int move(int row, int col, int player) {
        rows[player][row]++;
        cols[player][col]++;
        if(row == col) diag[player]++;
        if(row+col == n-1) reverseDiag[player]++;
        if(rows[player][row]==n || cols[player][col] == n || diag[player] == n || reverseDiag[player] == n) {
            return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
```

##  Rotate
### Clock wise rotate
/*
 * clockwise rotate
 * first reverse up to down, then swap the symmetry 
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
*/

```java
class Solution {
    public void rotate(int[][] matrix) {
        // reverse
        int m = matrix.length;
        for(int i = 0; i < m/2; i++) {
            int[] tmp = matrix[i];
            matrix[i] = matrix[m-i-1];
            matrix[m-1-i] = tmp;
        }
        // swap the Synmmetry
        for(int i = 0; i < m; i++) {
            for(int j = i+1; j < m; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
```
### Anti-clockwise rotate
/*
 * anticlockwise rotate
 * first reverse left to right, then swap the symmetry
 * 1 2 3     3 2 1     3 6 9
 * 4 5 6  => 6 5 4  => 2 5 8
 * 7 8 9     9 8 7     1 4 7
*/

```java
class Solution {
    public void rotate(int[][] matrix) {
        // reverse
        int m = matrix.length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][m-1-j];
                matrix[i][m-1-j] = tmp; 
            }
        }
        // swap the Synmmetry
        for(int i = 0; i < m; i++) {
            for(int j = i+1; j < m; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
```
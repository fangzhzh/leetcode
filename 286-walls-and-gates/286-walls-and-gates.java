/**
 * 
 * You are given a m x n 2D grid initialized with these three possible values.
	1.	-1 - A wall or an obstacle.
	2.	0 - A gate.
	3.	INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
For example, given the 2D grid:
INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:
  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
 */

/**
 * bfs, used a visited to track traversal
 * from every door, update the distance and put neighbour into queue
 * iterate the queue and for every room, update it's neighbours' distances
 */ 
public class Solution {
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    int INF = 2147483647;
    public void wallsAndGates(int[][] rooms) {
        if(rooms==null || rooms.length ==0 || rooms[0].length == 0) {
            return;
        }
        int row = rooms.length, col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(rooms[i][j] == 0) {
                    queue.offer(new int[]{i,j});
                }
            }
        }
        boolean [][]visited = new boolean[row][col];
        // write your code here
        while(!queue.isEmpty()) {
            int[] head = queue.poll();
            int i = head[0], j = head[1];
            if(visited[i][j]) {
                continue;
            }
            visited[i][j] = true;
            
            if(i+1 < row && rooms[i+1][j] != -1) {
                rooms[i+1][j] = Math.min(rooms[i][j] + 1, rooms[i+1][j]);
                queue.offer(new int[]{i+1, j});
            }
            if(i-1 >= 0 && rooms[i-1][j] != -1) {
                rooms[i-1][j] = Math.min(rooms[i][j] + 1, rooms[i-1][j]);
                queue.offer(new int[]{i-1, j});
            }
            if(j+1 < col && rooms[i][j+1] != -1) {
                rooms[i][j+1] = Math.min(rooms[i][j] + 1, rooms[i][j+1]);
                queue.offer(new int[]{i, j+1});
            }
            if(j-1 >= 0&& rooms[i][j-1] != -1) {
                rooms[i][j-1] = Math.min(rooms[i][j] + 1, rooms[i][j-1]);
                queue.offer(new int[]{i, j-1});
            }
        }
        
        
    }
}

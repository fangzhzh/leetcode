/**
 * 
Question
 Solution

Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

Example 1:

Input: grid =
[[1, 0, 0, 1, 0],
[0, 0, 1, 0, 1],
[0, 0, 0, 1, 0],
[1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
Example 2:

Input: grid =
[[1, 1, 1],
[1, 1, 1],
[1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
Example 3:

Input: grid =
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.
 */

 /**
  * first try, brutal force, for every point, it scan to right down
  * and if the four corner, if valid, ans++, run time limit TLE
  * O(n^4)
  */

public class Solution {
    /**
     * @param grid: the grid
     * @return: the number of corner rectangles
     */
    public int countCornerRectangles(int[][] grid) {
        // Write your code here
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int right = 1; right < m; right++) {
                    for(int down = 1; down < n; down++) {
                        if(i+right >=m || j+down >= n) break;
                        if(grid[i][j] == 1 && grid[i+right][j] == 1
                        && grid[i][j+down] == 1 && grid[i+right][j+down] == 1) {
                            ans++;
                        }
                        
                    }
                }
            }
        }
        return ans;
    }
}

/**
 * Take advantage the math, for every two rows, we find valid corner line 
 * [row1][col] == 1, [row2][col] == 1, the number of rectangle is to select any two lines from `n` lines
 * which is n * (n-1) / 2
 * 
 * O(m^2 * n)
 * 
 */
public class Solution {
    /**
     * @param grid: the grid
     * @return: the number of corner rectangles
     */
    public int countCornerRectangles(int[][] grid) {
        // Write your code here
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j = i+1; j < m; j++) {
                int count = 0;
                for(int k = 0; k < n; k++) {
                    if(grid[i][k]==1 && grid[j][k] == 1) count++;
                }
                ans += count * (count-1) / 2;
            }
        }
        return ans;
    }
}
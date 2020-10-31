/*
 * @lc app=leetcode id=764 lang=java
 *
 * [764] Largest Plus Sign
 */

/**
 * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.

An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1s.

Examples of Axis-Aligned Plus Signs of Order k:

Order 1:
000
010
000

Order 2:
00000
00100
01110
00100
00000

Order 3:
0000000
0001000
0001000
0111110
0001000
0001000
0000000
 */


/**
 * brutal force, TLE, of course, but it's DP, leave it for now.
 * 
 */
// @lc code=start
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int max = 0;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < mines.length; i++) {
            String key = makeKey(mines[i][0],mines[i][1]);
            set.add(key);
        }
        for(int i = 0; i < N; i++ ) {
            for(int j = 0; j < N; j++) {
                if(set.contains(makeKey(i,j))) {
                    continue;
                }
                int cur = 1;
                for(int level = 1; level < N; level++) {
                    if(
                        i+level > N-1
                        || 
                        i - level < 0
                        || 
                        j+level > N-1
                        || 
                        j - level < 0
                    ) {
                        break;
                    }
                    if(
                        set.contains(makeKey(i+level, j))
                        ||
                        set.contains(makeKey(i-level, j))
                        ||
                        set.contains(makeKey(i, j+level))
                        ||
                        set.contains(makeKey(i, j-level))
                    )
                    {
                        break;
                    }
                    cur++;
                }
                if(cur > max) {
                    // System.out.println(i+","+j+":"+cur);
                    max = cur;
                }
            }

        }
        return max;
    }
    String makeKey(int i, int j) {
        return i + "," + j;
    }
}
// @lc code=end


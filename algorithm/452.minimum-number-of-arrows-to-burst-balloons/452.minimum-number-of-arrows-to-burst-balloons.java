/*
 * @lc app=leetcode id=452 lang=java
 *
 * [452] Minimum Number of Arrows to Burst Balloons
 */

// @lc code=start
class Solution {
    public int findMinArrowShots(int[][] points) {
        // TC O(n)
        // SC O(1)
        if(points.length == 1) return 1;
        // sort by start
        Arrays.sort(points, (a, b)->Integer.compare(a[0], b[0]));
        // 1---6
        //   2----8
        //        7----12
        //           10-----16
        // number
        int ans = 1;
        int s = points[0][0];
        int e = points[0][1];
        for(int i = 1; i < points.length; i++) {
            int[] point = points[i];
            if(point[0] <= e) {
                s = Math.max(s, point[0]);
                e = Math.min(e, point[1]);
            } else {
                ans++;
                s = point[0];
                e = point[1];
            }
        }
        return ans;
    }
}
// @lc code=end


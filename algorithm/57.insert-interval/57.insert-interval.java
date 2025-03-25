/*
 * @lc app=leetcode id=57 lang=java
 *
 * [57] Insert Interval
 */

// @lc code=start
class Solution {
    // 画图帮助理解
    // 1--2 3--5 6--7 8--10 12--16
    //        4-------8
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int ns = newInterval[0], ne = newInterval[1];
        List<int[]> ans = new ArrayList<>();
        int i = 0; 
        while(i < n && intervals[i][1] < ns) {
            ans.add(intervals[i]);
            i++;
        }
        while(i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        ans.add(newInterval);
        while(i < n) {
            ans.add(intervals[i]);
            i++;
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}
// @lc code=end


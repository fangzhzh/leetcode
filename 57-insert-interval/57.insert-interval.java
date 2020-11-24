/*
 * @lc app=leetcode id=57 lang=java
 *
 * [57] Insert Interval
 */

// @lc code=start
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int len = intervals.length;
        int i = 0;
        while(i < len && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }
        while(i < len && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        list.add(newInterval);
        while(i < len && intervals[i][0] > newInterval[1]) {
            list.add(intervals[i]);
            i++;
        }
        return list.toArray(new int[list.size()][0]);
    }
}
// @lc code=end


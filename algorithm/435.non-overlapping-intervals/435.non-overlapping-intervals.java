/*
 * @lc app=leetcode id=435 lang=java
 * @lcpr version=30403
 *
 * [435] Non-overlapping Intervals
 */

// @lc code=start
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0] : a[1] - b[1]);
        int ans = 0; 
        int lastEnd = intervals[0][1];
        for(int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if(interval[0] < lastEnd) {
                ans++;
            } else {
                lastEnd = interval[1];
            }
        }
        return ans;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [[1,2],[2,3],[3,4],[1,3]]\n
// @lcpr case=end

// @lcpr case=start
// [[1,2],[1,2],[1,2]]\n
// @lcpr case=end

// @lcpr case=start
// [[1,2],[2,3]]\n
// @lcpr case=end

 */


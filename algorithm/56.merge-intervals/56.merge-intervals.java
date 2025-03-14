/*
 * @lc app=leetcode id=56 lang=java
 *
 * [56] Merge Intervals
 */

 /**
  * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 
  */


/**
 * was `HARD`, is `Medium`
 * 
 * It's simple idea, iterate and populate the list
 * This implementation ridiculous complex
 * 
 *   */  
// @lc code=start
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> list= new ArrayList<>();
        Arrays.sort(intervals, (a, b)->a[0]-b[0]);
        for(int i = 0; i < intervals.length; i++) {
            if(list.size() > 0 && list.get(list.size()-1)[1] >= intervals[i][0]) {
                list.get(list.size()-1)[1] = Math.max(intervals[i][1], list.get(list.size()-1)[1]);
            } else if(i < intervals.length-1 && intervals[i][1] >= intervals[i+1][0]) { // line 41-21 are useless
                list.add(new int[]{intervals[i][0], Math.max(intervals[i+1][1], intervals[i][1])});
            } else {
                list.add(new int[]{intervals[i][0], intervals[i][1]});
            }
        }
        // list to array
        return list.toArray(new int[list.size()][2]);

        // int[][] ans = new int[list.size()][2];
        // for(int i = 0; i < list.size(); i++) {
        //     ans[i] = list.get(i);
        // }
        // return ans;
    }
}

// version2: hold a last interval, and compare with the current one
class Solution {
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) {
            return intervals;
        }
        int len = intervals.length;
        Arrays.sort(intervals, (a,b)->a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        int[] last = intervals[0];
        ans.add(last);
        for(int i = 1; i < len; i++) {
            int[] cur = intervals[i];
            if(last[1] >= cur[0]) {
                last[1] = Math.max(cur[1], last[1]);
            } else {
                ans.add(cur);
                last = cur;
            }
        }
        return ans.toArray(new int[ans.size()][]);

    }
}
// @lc code=end


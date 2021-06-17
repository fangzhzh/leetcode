/*
 * @lc app=leetcode id=986 lang=java
 *
 * [986] Interval List Intersections
 */


/**
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)

 

Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 

Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 *  */ 
// @lc code=start

/**
 * Priority to get ride of the choices of i++ or j++
 * but it's 9ms,  beats only 5.06%
*/
class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        // startA, endA, startB, endB
        PriorityQueue<int[]> queue = new PriorityQueue((a,b)-> {
            if(((int[])a)[0]==((int[])b)[0]) {
                return ((int[])a)[1]-((int[])b)[1];
            } else {
                return ((int[])a)[0] - ((int[])b)[0];
            }
        });
        for(int[] interv: A) {
            queue.offer(interv);
        }
        for(int[] interv: B) {
            queue.offer(interv);
        }
        List<int[]> list = new ArrayList<>();
        int[] last = null;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(last == null) {
                last = cur;
                continue;
            }
            
            if(last[1] < cur[0]) {
                last = cur;
                continue;
            } else {
                list.add(new int[]{cur[0], Math.min(last[1], cur[1])});
                if(last[1] < cur[1]) {
                    last = cur;                    
                }
            }
        }
        int[][] ans = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }
        return ans;
    }
}
// @lc code=end


/**
 * 
 * another try, it takes advantages of some math analysis
 * 
 * overlapping means [maxStart, minEnd], and only the interver[1] == minEnd, need to proceed
 */

 /**
  * 

  Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
Example 3:

Input: intervals = [], newInterval = [5,7]
Output: [[5,7]]
Example 4:

Input: intervals = [[1,5]], newInterval = [2,3]
Output: [[1,5]]
Example 5:

Input: intervals = [[1,5]], newInterval = [2,7]
Output: [[1,7]]
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= intervals[i][0] <= intervals[i][1] <= 105
intervals is sorted by intervals[i][0] in ascending order.
newInterval.length == 2
0 <= newInterval[0] <= newInterval[1] <= 105
  */
 //? idx shiftting

class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        // startA, endA, startB, endB
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        while(i < A.length && j < B.length) {
            int maxStart = Math.max(A[i][0], B[j][0]);
            int minEnd = Math.min(A[i][1], B[j][1]);
            if(maxStart <= minEnd) {
                list.add(new int[]{maxStart, minEnd});
            }

            if(A[i][1] == minEnd) i++;
            if(B[j][1] == minEnd) j++;

            // or
            // if(A[i][1]>B[j][1]) j++;
            // else i++;
        }
        return list.toArray(new int[list.size()][0]);
    }
}



/**
 * inplace merge, and add merged to interval
 */

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
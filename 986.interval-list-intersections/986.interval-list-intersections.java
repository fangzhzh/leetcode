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
        int[][] ans = new int[list.size()][2];
        for(int k = 0; k < list.size(); k++) {
            ans[k][0] = list.get(k)[0];
            ans[k][1] = list.get(k)[1];
        }
        return ans;
    }
}
/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example
Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
Example2

Input: intervals = [(2,7)]
Output: 1
Explanation: 
Only need one meeting room
 */ 


/**
 * sort by start, then we check every meeting
 * how we decide needing a new meeting room for meeting A, if there is a meeting ends before A's start
 * The smallest ends time is smaller than A's start time
 * 
 * Then the algoritm goes:
 * - sort by begin,
 * - keep a sorted end
 * - looping, for every meeting A, check A.start >= smallest end, 
 *     + yes, remove the smallest and replace with A.end
 *     + no, new room with A.end
 * - the number of ends is the number of meeting room needed.
 * 
 * check 218 skylin problem, same priority queue pattern
 */
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        Collections.sort(intervals, (Interval in1, Interval in2)-> in1.start - in2.start);
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        for(int i = 0; i < intervals.size(); i++) {
            Interval in1 = intervals.get(i);
            if(pQueue.isEmpty()) {
                pQueue.offer(in1.end);
            } else {
                if(in1.start >= pQueue.peek()) {
                    pQueue.poll();
                }
                pQueue.offer(in1.end);
            }
	
        }
        return pQueue.size();
        
    }
}

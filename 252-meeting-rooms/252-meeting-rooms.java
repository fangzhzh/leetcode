/**
 * 
 * 
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,

Given [[0, 30],[5, 10],[15, 20]],

return false.
 */
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        for(int i = 0; i < intervals.size()-1; i++) {
            Interval head = intervals.get(i);
            Interval tail = intervals.get(i+1);
            if((head.start < tail.start && head.end < tail.end) 
            || 
            (head.start > tail.start && head.end > tail.end)) {
                
            } else {
                return false;
            }
        }
        return true;
    }
}


/**
 *  ## analysis
 * It's a simple question, it's true if no two intervals has overlap.
 */
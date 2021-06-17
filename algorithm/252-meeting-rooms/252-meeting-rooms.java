/**
 * 
 * 
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,

Given [[0, 30],[5, 10],[15, 20]],

return false.
 */

/**
 *  ## analysis
 * It's a simple question, it's true if no two intervals has overlap.
 * However, this solution is not correct, consider the following case:
 * [(0,5),(5,10),(10, 15),(15,20),(0,5)], it returns true, but it's false
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
 * spent long time to wondering whether we need a sort, I think so. 
 * Without sort, the previous code will get a false positive.
 */
public class Solution {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: if a person could attend all meetings
     */
    public boolean canAttendMeetings(List<Interval> intervals) {
        // Write your code here
        // sort
        int len = intervals.size();
        Comparator<Interval> comparator = (Interval in1, Interval in2) -> in1.start - in2.start;
        intervals.sort(comparator);
        for(int i = 0; i < len-1; i++) {  
            Interval in1 = intervals.get(i);
            Interval in2 = intervals.get(i+1);
            if(in2.start < in1.end ) {
                return false;
            }
	
        }
        return true;
        

    }
}
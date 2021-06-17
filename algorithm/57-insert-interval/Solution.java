/*
import java.util.*;
public class Interval {
int start;
int end;
Interval() { start = 0; end = 0; }
Interval(int s, int e) { start = s; end = e; }
}
*/

public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> newIntervals = new ArrayList<>();
        if(intervals.size()==0) {
            newIntervals.add(newInterval);
            return newIntervals;
        }
        int i = 0, j = 0;
        for(; i < intervals.size(); ++i) {
            Interval interval = intervals.get(i);
            if(interval.end >= newInterval.start) {
                break;
            }
        }
        if(i == intervals.size()) {
            intervals.add(newInterval);
            return intervals;
        }
        for(j=i; j < intervals.size(); ++j) {
            Interval interval = intervals.get(j);
            if(interval.start > newInterval.end) {
                break;
            }
        }
        if(j == 0) {
            intervals.add(0, newInterval);
            return intervals;
        }
        Interval comInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start),
                    Math.max(newInterval.end, intervals.get(j-1).end));
        newIntervals.addAll(intervals.subList(0, i));
        newIntervals.add(comInterval);
        newIntervals.addAll(intervals.subList(j, intervals.size()));
        return newIntervals;
    }
}

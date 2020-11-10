/*
 * @lc app=leetcode id=621 lang=java
 *
 * [621] Task Scheduler
 */

 /**
  * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
  * 
  */


/**
 * Following some example, it will came up a solution we fill the most occurreny to every n space 
 * to form a frame, then fill others into this frame.
 * though this formula is hard to get: (track[25] - 1) * (n+1) + 25 -i
 * 
 * https://leetcode.com/problems/task-scheduler/discuss/104496/concise-Java-Solution-O(N)-time-O(26)-space
 * 
 */  
// @lc code=start
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int [] track = new int[26];
        for(char i : tasks) {
            track[i-'A']++;
        }
        Arrays.sort(track);
        int i = 25;
        while(i>=0 && track[i] == track[25]) i--;
        return Math.max(
            tasks.length,
            (track[25]-1) * (n+1) + 25 -i);
    }
}
// @lc code=end


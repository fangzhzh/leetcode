/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 */

/**
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
 

Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5 */ 
// @lc code=start

/**
 * Toplogic sort
 * 
 * remove inScore[i] == 0, edige[i].list.forEach(it -> inScores[it]--);
 * 
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inScores = new int[numCourses];
        List<Integer>[] edges = new List[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            int head = prerequisites[i][1], tail = prerequisites[i][0];
            inScores[tail]++;
            if(edges[head] == null) {
                edges[head] = new ArrayList<>();
            }
            edges[head].add(tail);
        }
        
        boolean[] visited = new boolean[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        for(int i = 0; i < inScores.length; i++) {
            if(inScores[i] == 0) {
                queue.offer(i);
                cnt++;
            }
        }
        
        while(!queue.isEmpty()) {
            int course = queue.poll();
            if(visited[course]) return false;
            if(edges[course]==null) continue;
            for(int tail : edges[course]) {
                inScores[tail]--;
                if(inScores[tail]==0) {
                    queue.offer(tail);
                    cnt++;
                }
            }
        }
        return cnt == numCourses;
    }
}
// @lc code=end


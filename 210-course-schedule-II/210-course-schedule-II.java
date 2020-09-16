/**
 * There are a total of n courses you have to take labelled from 0 to n - 1.

Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.

Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.

If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
*/

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];

        // map for relationship
        Map<Integer, List<Integer>> map = new HashMap<>();
        // score for score
        int [] score = new int[numCourses];
        // queue for queue
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 0; i < prerequisites.length; i++) {
            int head = prerequisites[i][0];
            int tail = prerequisites[i][1];
            if(!map.containsKey(head)) {
                map.put(head, new ArrayList());
            }
            List<Integer> list = map.get(head);
            list.add(tail);
            score[tail]--;
        }

        for(int i = 0; i < numCourses; i++)  {
            if(score[i] == 0) {
                queue.offer(i);
            }
        }

        int idx = numCourses-1;
        while(!queue.isEmpty()) {
            int head = queue.poll();
            result[idx] = head;
            idx--;
            List<Integer> list = map.get(head);
            if(list == null) {
                continue;
            }
            for(int tail : list) {
                score[tail]++;
                if(score[tail] == 0) {
                    queue.offer(tail);
                }
            }
        }

        if(idx > 0) {
            return new int[0];
        }
        return result;
    }
}


/**
 * The solution is long and full of details.
 * map to construct the directed graph
 * queue for quick iteration a data structure based on some linking relationship, like binary tree, linkedList, graph
 * 
 */
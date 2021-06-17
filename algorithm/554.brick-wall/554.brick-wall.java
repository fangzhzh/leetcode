/*
 * @lc app=leetcode id=554 lang=java
 *
 * [554] Brick Wall
 */

 /**
  * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.

The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.

 

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]


Output: 2

Explanation: 

Note:

The width sum of bricks in different rows are the same and won't exceed INT_MAX.
The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the wall won't exceed 20,000.
*/


/**
 * A simple draw will get the correct idea
 * The most occurence of a sum will be the line with least acrossed.
 *      [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

        [[1,3,5,6],
        [3,4,6],
        [1,4,6],
        [2,6],
        [3,4,6],
        [1,4,5,6]]
        1: 3=>6-4
        2: 1=>6-1:
        3: 3=>6-3
        4: 4=>6-4
        5: 2=>6-2

The trick part here is that:
    - 0 and sum should be excluded
    - if 0 is the only founded, then the acrossed is the row size

    [[1],[1],[1]]

Another trick part is the memory:
To improve the time complexity, I used int[] = new int[sum], but it exceeds memory 
    [[10,000],[10,000],[10,000]]
 */
// @lc code=start
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if(wall==null||wall.get(0).size()==0) {
            return 0;
        }
        int row = wall.size();
        int sum = 0;
        for(int i =0; i < wall.get(0).size(); i++) {
            sum += wall.get(0).get(i);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < wall.size(); i++) {
            List<Integer> list = wall.get(i);
            int tmp = 0;
            for(int j = 0; j < list.size(); j++) {
                tmp += list.get(j);
                if(map.get(tmp) == null) {
                    map.put(tmp, 1);
                } else {
                    map.put(tmp, map.get(tmp) + 1);
                }
            }
        }
        int idx = -1, max = 0;
        for(int key : map.keySet()) {
            if(key == sum) continue;
            if(map.get(key) > max) {
                max = map.get(key);
            }
        }
        return max == 0 ? row : row - max;
    }
}
// @lc code=end


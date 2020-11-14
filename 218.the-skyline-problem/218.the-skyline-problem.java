/*
 * @lc app=leetcode id=218 lang=java
 *
 * [218] The Skyline Problem
 */

/**
 * 
 * 
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...] *  */ 
// @lc code=start

/**
 * The key point is to find the key points
 * treeMap to trace maxHeight -> cnt
 * scan the building heights
 *  - meet a new building, update the treeMap cnt++
 *  - meet the end of a builing, 
 *      + if(cnt > 1) cnt--
 *      + if cnt == 1, remove it
 *      + the 1 to handle the case that multiple building has the same height, remove a height when we scaned all
 *  - check map lastkey
 *      + not equal to prevMax, a new critical point, add (h[0], cur) 
 *      + and update last <= cur
 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for(int[] building : buildings) {
            heights.add(new int[]{building[0], -building[2]});
            heights.add(new int[]{building[1], building[2]});
        }

        Collections.sort(heights, (int[] a, int[]b)-> {
            if(a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });


        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        int prev = 0;
        for(int[] h: heights){
            if(h[1] < 0) {
                map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
            } else {
                if(map.get(h[1]) > 1) {
                    map.put(h[1], map.getOrDefault(h[1], 0) - 1);
                } else {
                    map.remove(h[1]);
                }
            }
            int cur = map.lastKey();
            if(cur != prev) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(h[0]);
                tmp.add(cur);
                ans.add(tmp);
                prev = cur;
            }
        }
        return ans;
    }
}
// @lc code=end


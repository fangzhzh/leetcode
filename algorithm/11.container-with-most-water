/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length-1;
        int maxW = 0;
        while(i < j) {
            maxW = Math.max(maxW, (j-i)* Math.min(height[i], height[j]));
            if(height[i]<height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxW;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
// proof
// left, right. 
// 0， 1 起步，宽度，高度同时变，没有办法淘汰一个部分。
// 0，n-1起步，宽度已经是最大，收缩时，宽度必然单调递减； 一个变量单调递减，面积变大，那就是寻找更高的高度。于是引出了清晰的“淘汰标准”
// Let's say left is smaller, then absolute maximum of area is limited by left.  eliminating all combintations with left.
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


// O(n^2) solution
class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        for(int i = 0; i < height.length -1; i++) {
            for(int j = i+1; j < height.length; j++) {
                ans = Math.max(Math.min(height[i], height[j])*(j-i), ans);
            }

        }
        return ans;
    }
}

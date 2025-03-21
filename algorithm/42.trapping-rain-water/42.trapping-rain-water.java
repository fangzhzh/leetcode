/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {
    // TC O(n)
    // SC O(n)
    public int trap(int[] height) {
        int n = height.length;
        int[] lm = new int[n];
        int[] rm = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++) {
            lm[i] = max;
            max = Math.max(max, height[i]);
        }
        max = 0;
        for(int i = n-1; i>=0; i--) {
            rm[i] = max;
            max = Math.max(max, height[i]);
        }
        int ans = 0;
        for(int i = 0; i < n; i++) {
            ans += Math.max(0, Math.min(lm[i], rm[i]) - height[i]);
        }
        return ans;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 */

// @lc code=start
class Solution {
    // 右边界问题 [1,x]里，找出最大的k使得k^2 < x
    // Time O(logN), space O(1)
    public int mySqrt(int x) {
        int left = 1, right = x;
        // [1, x]
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int tmp = x / mid;
            // 找到target，收缩左边界
            if(tmp == mid) {
                left = mid + 1;
            } else if(tmp > mid) {
                // mid太大， 收缩左边界
                left = mid + 1;
            } else if(tmp < mid) {
                // mid太小，收缩右边界
                right = mid - 1;
            }
        }
        // right就是所求k
        return right;
    }
}
// @lc code=end


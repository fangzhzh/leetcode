/*
 * @lc app=leetcode id=852 lang=java
 * @lcpr version=30403
 *
 * [852] Peak Index in a Mountain Array
 */

// @lc code=start
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
                int l = 0, r = arr.length-1;
        while(l < r) {
            int mid = (l+r)/2;
            if(arr[mid] < arr[mid+1]){ 
                l = mid+1;
            } else {
                r =  mid;
            }
        }    
        return l;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [0,1,0]\n
// @lcpr case=end

// @lcpr case=start
// [0,2,1,0]\n
// @lcpr case=end

// @lcpr case=start
// [0,10,5,2]\n
// @lcpr case=end

 */


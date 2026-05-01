/*
 * @lc app=leetcode id=152 lang=java
 * @lcpr version=30403
 *
 * [152] Maximum Product Subarray
 */

// @lc code=start
// TC O(n)
// SC O(1)
class Solution {
    public int maxProduct(int[] nums) {
        int maxP = 1, minP = 1;
        int res = nums[0];
        for(int num : nums) {
            
            int temp = maxP * num;
            int temp2 = minP * num;
            maxP = Math.max(num, Math.max(temp, temp2));
            minP = Math.min(num, Math.min(temp, temp2));
            res = Math.max(res, Math.max(minP, maxP));
        }
        return res;
    }
}


class Solution {
    // removed the temp and temp2
    public int maxProduct(int[] nums) {
        int maxP = 1, minP = 1;
        int res = nums[0];
        for(int num : nums) {
            if(num <0) {
                int tmp = maxP;
                maxP = minP ;
                minP = tmp;
            } 
            maxP = Math.max(num, maxP * num);
            minP = Math.min(num, minP * num);
            res = Math.max(res, maxP);
        }
        return res;
    }
}
// @lc code=end



/*
// @lcpr case=start
// [2,3,-2,4]\n
// @lcpr case=end

// @lcpr case=start
// [-2,0,-1]\n
// @lcpr case=end

 */


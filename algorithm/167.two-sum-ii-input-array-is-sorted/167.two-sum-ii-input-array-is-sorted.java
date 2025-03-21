/*
 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input Array Is Sorted
 */

// @lc code=start
class Solution {
    // O(n)
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int i = 0, j = numbers.length-1;
        while(i < j) {
            int sum = numbers[i] + numbers[j];
            if( sum== target) {
                ans[0] = i+1;
                ans[1] = j+1;
                return ans;`
            } else if(sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return ans;

    }
}
// @lc code=end


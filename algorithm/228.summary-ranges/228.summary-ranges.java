/*
 * @lc app=leetcode id=228 lang=java
 *
 * [228] Summary Ranges
 */

// @lc code=start
// TC O(n) SC O(n), complex solution

// a better solution is for loop i, and extand to the valid right
class Solution {
    public List<String> summaryRanges(int[] nums) {
        if(nums.length == 0) {
            return new ArrayList<>();
        }
        StringBuilder sb = new StringBuilder();
        int first = 0, last = 0;
        List<String> ans = new ArrayList<>();
        sb.append(nums[first]);
        while(last < nums.length) {
            if(last+1<nums.length ) {
                if(Math.abs(nums[last]-nums[last+1]) != 1) {
                    if(first != last) {
                        sb.append("->");
                        sb.append(nums[last]);
                    }
                    ans.add(sb.toString());
                    sb = new StringBuilder();
                    first = last+1;
                    last = first;
                    sb.append(nums[first]);
                } else {
                    last++;
                }
            } else {
                if(first != last) {
                    sb.append("->");
                    sb.append(nums[last]);
                }
                ans.add(sb.toString());
                last++;
            }
        }
        return ans;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
        }

        int max = 0;
        for(int i = 0; i < nums.length; i++) {
            int count = 1;
            int num = nums[i];
            while(set.contains(--num)) {
                count++;
                set.remove(num);
            }
            num = nums[i];
            while(set.contains(++num)) {
                count++;
                set.remove(num);
            }
            if(count > max) {
                max = count;
            }
        }
        return max;
    }
}
// @lc code=end


/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */

// @lc code=start
/**
 * analysis:
 * - O(N^2)
 * my solution 
 * for(i) 
 *  k = len -1 
 *  for(j=i+1; )
 * 
 * VS.
 * Current solution
 * for(i)
 *  low, high
 *  while(low < high)
 *  
 *  The second solution is better because 
 *  - the condition checking is at where it should be
 *  - while (low, high) is much better then j, for(k)
 */ 

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null) {
            return res;
        }
        // sort
        Arrays.sort(nums);
        int len = nums.length;
        // loop
        for(int i = 0; i < len-2; i++) {
            if(i==0 || (i>0 &&nums[i] != nums[i-1])) {
                int low = i +1, high = len -1;
                while(low < high) {
                    if(nums[i]+nums[low]+nums[high] == 0) {
                        res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low+1]) low++;
                        while (low < high && nums[high] == nums[high-1]) high--;
                        low++; high--;
                    } else if(nums[i]+nums[low]+nums[high] > 0) {
                        high--;
                    } else {
                        low ++;
                    }
                }
            }
           
        }
        return res;
    }
}
// @lc code=end



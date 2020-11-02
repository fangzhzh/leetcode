/**
 * 
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
Example 1:
Given nums = [1, -1, 5, -2, 3], k = 3,return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
Example 2:
Given nums = [-2, -1, 2, 1], k = 1,return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 */

/**
 * Analysis: O(n)
 * sum and map, why it works
 * 
 * solution involves sun and subarray sum, map is needed for the idx of sum
 * 
 * because it needs max sub, so we'll need keep the furthest idx of a sum
 */
public class Solution {
    /**
     * @param nums: an array
     * @param k: a target value
     * @return: the maximum length of a subarray that sums to k
     */

     
    public int maxSubArrayLen(int[] nums, int k) {
        // Write your code here
        Map<Integer, Integer> map= new HashMap<>();
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            nums[i] += sum;
            sum = nums[i];
            if(!map.containsKey(sum)) {
    	        map.put(sum, i);
            }
        }
        int ans = 0;
        for(int i =  nums.length-1; i >= 0; i--) {
            if(nums[i] == k) {
                ans = Math.max(ans, i+1);
            } else if(map.containsKey(nums[i]-k)) {
                ans = Math.max(i-map.get(nums[i]-k), ans );
            }
        }
        return ans;
    }
    
}

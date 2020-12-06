/*
 * @lc app=leetcode id=560 lang=java
 *
 * [560] Subarray Sum Equals K
 */

/**
 * 
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 * 
 */

 // @lc code=start
/**
 * first thgout, two pointers, idx shiffting, it works for positive number, but will fail negative number
 * 
 * The main idea of Two pointer is that
 * right to find next valid, left to make it invalid, during these transition to keep a max/min/counter
 * if array [A] has negative number, the condition will not hold:
 *  right to find next valid(go near to sum), left to find invalid(go far to sum)
 * 
 */


/**
 * 
 * This solution, for loop
 * for(i in i..len) {
 *  for(j in i..len)
 * }
 * mean iterator solution space for start from i
 * -------------------
 * for(i in i..len) {
 *  for(j in 0..i)
 * }
 * 
 * mean iterator solution space for ending at i
 * -----------------
  *
 * for this question, if we use ending at i, it will generate a lot of duplicate answer
 * because for every i, we scan 0..i, so for len we will scan 
 * 0..1, 0..2, 0..3, 0..len
 * 
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int cnt = 0;
        for(int i = 0; i < len; i++) {
            int sum = 0;
            for(int j = i; j < len; j++) {
                sum+=nums[j];
                if(sum == k) {
                    cnt++;
                }
            }
        }       
        return cnt;
    }
}
// @lc code=end


/**
 * This algorithm based on the fact that A[i,j] = A[0,j] - A[0,i]
 * We scan the nums 0 .. len, a map to kep the A[0...j], 
 * then for every A[j], if map.containsKey(A[0,j]-k), it means 
 * there is a i, A[i,j] = k, where i < j
 * 
 * For some max/min/at lease k char away problem, the map store **sum->idx**,
 * but for this question, we need the count of sum == k
 * 
 * A[j], if map.containsKey(A[0,j]-k), there is a i, A[i,j] = k, where i < j
 * The num of A[i,j] == k, equalls occurence of A[i] == A[j]-k, 
 * the map in this algorithm will store that value 
 * **sum -> occurrence**
 * 
 * 
 * This algorithm scan the 0..len, find find the occurence of sum == k which ending i
 * Comparing to the above solution, it doesnt scan 0..i every time, but instead we pick all valid
 * 
 * 
 * So if we go from len-1..0, the algorithm should also work, but to find 
 * occurence of sum ==k which begin with i
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for(int i = 0; i < len; i++) {
            sum += nums[i];
            if(map.containsKey(sum-k)) {
                cnt += map.get(sum-k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }

        return cnt;
    }
}
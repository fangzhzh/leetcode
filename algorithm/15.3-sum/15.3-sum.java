/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */

// @lc code=start
/**
 * 
 * 3Sum
Category	Difficulty	Likes	Dislikes
algorithms	Medium (36.38%)	32412	3047
Tags
Companies
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
Submissions | Solution
 */
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


// 2025-03-11
class Solution {
    //TC O(n^2)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i+1, right = n-1;

            while(left < right) {
                int sum = nums[left] + nums[right]+nums[i];
                if(sum < 0) {
                    left++;
                    continue;
                } else if(sum > 0) {
                    right--;
                    continue;
                } else if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    ans.add(list);
                    left++;
                    while(nums[left] == nums[left-1] && left < right) {
                        left++;
                    }
                        while (left < right && nums[right] == nums[right-1]) right--;

                }

            }
        }
        return ans;
    }
}


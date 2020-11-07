/*
 * @lc app=leetcode id=673 lang=java
 *
 * [673] Number of Longest Increasing Subsequence
 */

// @lc code=start
/**
 * 
 * Given an integer array nums, return the number of longest increasing subsequences.

Notice that the sequence has to be strictly increasing.

 

Example 1:

Input: nums = [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:

Input: nums = [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 

Constraints:

1 <= nums.length <= 2000
-106 <= nums[i] <= 106
 */

/**
 * MLE, memoy limited exception
 */
class Solution {
    public int findNumberOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        ans.add(list);
        helper(ans, nums, 1);
        int max = 0;
        int cnt = 0;
        for(List<Integer> tmp : ans) {
            if(tmp.size() > max) {
                max = tmp.size();
                cnt = 0;
            }
            if(tmp.size() == max) {
                cnt++;
            }
        }
        return cnt;
    }
    private void helper(List<List<Integer>> ans, int[] nums, int idx) {
        if(idx >= nums.length) return;
        List<List<Integer>> newAns = new ArrayList<>();
        for(List<Integer> list : ans) {
            if(list.get(list.size()-1) < nums[idx]) {
                List<Integer> newList = new ArrayList<>(list);
                newList.add(nums[idx]);
                newAns.add(newList);
            }
        }
        }
        ans.addAll(newAns);
        helper(ans, nums, idx+1);
    }
}


// @lc code=end


/**
 * another try, dp
 * len[i] the length of Longest Increasing Subsequence ending nums[i]
 * cnt[i] the count of Longest Increasing Subsequence ending nums[i]
 * 
 * 
1) the maximum increasing / decreasing length ends at the current element,
2) its own value ,
3) the total number of maximum length,
and each time when we visit a element, we will use its 2) to update 1) and 3), 
the only difference is for array we use iteration while for tree we use recursion......
Also, for **substring problem**, we usually use only one for loop because for each index, we only care about the relationship between its two neighbors, 
while for subsequence problem, we use two for loops , because for each index, any other indexes can do something...
 */
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if(nums == null || len == 0) {
            return 0;
        }
        int[] length = new int[len];
        int[] cnt = new int[len];
        int ans = 0, maxLen = 0;
        for(int i = 0; i < len; i++) {
            length[i] = 1;
            cnt[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    if(length[i] == length[j]+1) {
                        cnt[i] += cnt[j];
                    } else if(length[i] < length[j]+1) {
                        length[i] = length[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }

            if(maxLen == length[i]) ans += cnt[i];
            if(maxLen < length[i]) {
                maxLen = length[i];
                ans = cnt[i];
            }

        }
        return ans;
    }
}


/*
 * @lc app=leetcode id=268 lang=java
 * @lcpr version=30403
 *
 * [268] Missing Number
 */

// @lc code=start
// 方案3， 数学法，最快
class Solution {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum+=n;
        }
        int relSum = 0;
        for(int i = 0; i <= nums.length; i++) {
            relSum+=i;
        }
  
        return relSum-sum;
    }
}

// 逻辑法1， 排序，找到第一个不匹配的
class Solution {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i) {
                return i;
            }
        }

        return nums.length;
    }
}

// 逻辑法2， hashset，遍历
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet();
        for(int n : nums) {
            set.add(n);
        }
        for(int i = 0; i <= nums.length; i++) {
            if(!set.contains(i)) {
                return i;
            }
        }
  
        return nums.length;
    }
}
// @lc code=end


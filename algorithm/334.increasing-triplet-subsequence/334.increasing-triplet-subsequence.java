/*
 * @lc app=leetcode lang=java
 *
 * [334] Increasing Triplet Subsequence
 */

 /**
  * 
  Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false
  */
// @lc code=start
class Solution {
    public boolean increasingTriplet(int[] nums) {
        if(nums == null || nums.length <3) {
            return false;
        }

        for(int i = 0; i < nums.length; i++) {
            int medium = i+1;
            for(int j = i+1; j < nums.length; j++) {
                if(nums[j] < nums[i]) {
                    break;
                } else if(nums[j] == nums[i]) {
                    continue;
                }  else {
                    int k = j+1;
                    while(k < nums.length) {
                        if(nums[k] > nums[j]) {
                            return true;
                        }
                        k++;
                    }
                }
            }
        }
        return false;
    }
}
// @lc code=end


/**
 * ## analysis
 * 
 * This question belongs to no category.just pure math and logic.
 * - first try: 
 * - to calculate first, medium, if any nums[i] < than them, reset the first
 * - it is wrong because it scan one loop and assume only one path
 * - it failed the case [1,0,0,10,0,0,0,10]
 * - the algorithm will min: 0, medium: 0 when it scaned to 10,0
 * 
 * - After it's fail, it's obvious that it must be a scan for every possible path
 */

 /**
  * How come the previous solution is so complex implemented and I didn't try to find another solution?
  *
  * The new solution take advantage of the fact:
  * Check whether small and equall than min, if yes, reset small;
  * if no, check small and equal than mid,  if yes, reset mid;
  * if no, it's a number greater than small and mid
  */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] <= min) min = nums[i];
            else if(nums[i] <= mid) mid = nums[i];
            else return true;
        }
        return false;
    }
}

//2022-01-13 16:22:15

/*
 * @lc app=leetcode id=334 lang=java
 *
 * [334] Increasing Triplet Subsequence
 */

// @lc code=start
class Solution {
    // 贪心
    public boolean increasingTriplet(int[] nums) {
        return increasingTripletGreedy(nums);
    }
    // greedy
    // 我们假设三元序列 [first, second, num]
    // 我们贪心的思想是**尽量让first, second最小**，同时满足first < second' < num

    // 证明: 假设(first, second, num) 是一个递增三元序列，如果存在一个second', 且second'满足 
    // first < second' < second，并且second'位于first和num的下标之间。因为(first, second', num)也是一个递增三元序列

    // 但是当(first, seoncd', num)是一个递增三元序列的时候，也满足first < second' < second,
    // num未必大于second,因为(first, second, nun)未必是一个递增序列
    // 因此为了找到递增三元序列，第二个数应该尽可能小

    // 同理证明，第一个数也要尽可能小


    // 实现的说明
    // 如果遍历过程中，如果num<=first, 则num替换first，有可能更新后的fist在second的后边，但是在second前边，必然有一个first‘满足first' < second
    // 如果num<=second,num就是替换second
    // 否则，num > second，我们找到了一个递增三元组

    public boolean increasingTripletGreedy(int[] nums) {
        int first = nums[0];
        int second = Integer.MAX_VALUE;
        int n = nums.length;
        for(int i = 1; i < n; i++) {
            int num = nums[i];
            if(num <= first) {
                first = num;
            } else if(num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;

    }
}
// @lc code=end


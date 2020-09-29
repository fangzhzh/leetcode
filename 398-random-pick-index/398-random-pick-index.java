/*
 * @lc id=398 lang=java
 *
 * [398] Random Pick Index
 */
/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
 */
// @lc code=start
class Solution {
    int[] nums;
    Random rdm = new Random();
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int result = -1;
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != target) {
                continue;
            }
            if(rdm.nextInt(++count) == 0) {
                result = i;
            }
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
// @lc code=end

/**
 * ## analysis:
 * - reservior sampling
 * - 从 n = i + 1 个里抽 i 个，任意一个留在池子里的概率是 1 - 1/n = 1 - 1/(i+1)
 * - 1/i * (1-1/(i+1)) 表示抽到第 i + 1 个的时候它留下的概率
 * - 1/(i+2) 表示下一次它被抽出去的概率
 * - 所以到了第 i + 2 次，它可能最终留下的概率 = 之前留下的概率 * 这次留下的概率
 * 
 * 
 * - rdm.nextInt(1) must == 0, but it keeps calculate until all the samples are in.
 * rdm.nextInt(3) = 1/3 for every possieble index
 * 
 * - my initial confusion is that the first rdm.nextInt(1)==0 is obvois 100%
 * - but as the above explaination, the possibility of i in n, is 1/n
 * 
 * 
 */

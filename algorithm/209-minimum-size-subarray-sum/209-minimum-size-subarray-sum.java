/*
 * @lc id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

 /**
  * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n). 
  */
// @lc code=start
/**
 * 
 * O(n^2) is not two pointers, it's two loop, lol
 */
class Solution {
    //O(n^2)
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int[]sums = new int[nums.length];
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sums[i] = sum;
            for(int j = i; j >= 0; j--) {
                if(sums[i] - sums[j] >= s) {
                    if(i - j  < min) {
                        min = i - j ;
                    } 
                    break;
                }
            }
            if(sums[i]>=s) {
                if(i < min) {
                    min = i+1;
                }
            }
        }

        return min==Integer.MAX_VALUE?0:min;
    }
}
// @lc code=end

/**
 * ## analysis
 * two pointers O(n)
 * Why is O(n)?
 * j is not always intialized for every i, it goes parallel with i, only come after i.
 * worst case only n times totally, O(n+n) = O(n)
 * ### Improvement:
 * - binary search
 * 
 */

class Solution {
    // two pointer
    // O(n)
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int j = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= s) {
                ans = Math.min(ans, i-j+1);
                sum -= nums[j++];
            }
        }

        return ans==Integer.MAX_VALUE?0:ans;
    }
}

/**
 * Another try, this solution totally ignored the condition of ">=s", so it's wrong answer
 * regarding all the substring problem,
 * checking the notes: software engineer basic code -> Questions -> Most 'substring' problems
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        // sum
        int sum = 0, len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < len; i++) {
            nums[i] += sum;
            sum = nums[i];
            map.put(sum, i);
         }
        // scan
        int min = Integer.MAX_VALUE;
        for(int i = len-1; i >= 0; i--) {
            if(map.containsKey(nums[i] - s)) {
                min = Math.min(min, i - map.get(nums[i]-s));
            }
        }

        return min==Integer.MAX_VALUE?0:min;
       
    }
}



/**
 * 
 * The slice windows template
 * 
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, right = 0, sum = 0;
        int min = Integer.MAX_VALUE;
        while(right < nums.length) {
            sum += nums[right];
            right++;
            while(sum >= s) {
                if(right - left < min) {
                    min = right - left;
                }
                sum -= nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }
}
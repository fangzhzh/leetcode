/*
 * @lc app=leetcode lang=java
 *
 * [215] Kth Largest Element in an Array
 */

 /**
  * 
  
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
  */


/**
 * version: to find the len-k smallest 
 */
// @lc code=start
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length - 1;
        return findKthLargest(nums, 0, len, nums.length -k);
    }
    private int findKthLargest(int[] nums, int low, int high, int k) {
        int pivok = partition(nums, low, high);

        if(pivok == k) {
            return nums[k];
        } else if(pivok < k){
            return findKthLargest(nums, pivok+1, high, k);
        } else {
            return findKthLargest(nums, low, pivok-1, k);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int left = low;
        for(int i = left; i < high; i++) {
            if(nums[i] <= nums[high]) {
                swap(nums, i, left);
                left++;
            }
        }
        swap(nums, left, high);
        return left;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// @lc code=end

/**
 ## analysisi
 ### kth largest
brutal force, sort and index, o(nlgn).
then it's selection which based on select sort, Hoare selection algorithm.
The basic idea is every scan sort the whole array into two part, left less than, right greater than

Other solutions(in this folder) are all good, but all of them are calculate the right side which is not necessary.

Kth largest, it's length-k smallest.
 */


/**
 * Another solution to find the kth largest
 *  */ 

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length - 1;
        return findKthLargest(nums, 0, len, k-1);
    }
    private int findKthLargest(int[] nums, int low, int high, int k) {
        int pivok = partition(nums, low, high);

        if(pivok == k) {
            return nums[k];
        } else if(pivok < k){
            return findKthLargest(nums, pivok+1, high, k);
        } else {
            return findKthLargest(nums, low, pivok-1, k);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int left = low;
        for(int i = left; i < high; i++) {
            if(nums[i] > nums[high]) {
                swap(nums, i, left);
                left++;
            }
        }
        swap(nums, left, high);
        return left;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
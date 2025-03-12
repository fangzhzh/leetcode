/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */

// @lc code=start
/**
 *
 * You are given an integer array nums sorted in ascending order, and an integer target.

Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

If target is found in the array return its index, otherwise, return -1.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 

Constraints:

1 <= nums.length <= 5000
-10^4 <= nums[i] <= 10^4
All values of nums are unique.
nums is guranteed to be rotated at some pivot.
-10^4 <= target <= 10^4
 
 */


/**
 * It's easy to get a O(n) version, scan and get target
 * But it mustn't be the case.
 * 
 * O(n*lgn) is the answer, so binary search
 * 
 * Either keep two set of low, high,
 * or we find the shift, and every time we binary search, we shift the idx, it still a binary search
 * 
 */ 
class Solution {
    public int search(int[] A, int target) {
        int left = 0, right = A.length-1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(A[mid] > A[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        int shift = 0;
        if(A[left] < A[right]) {
            shift = left;
        } else {
            shift = right;
        }

        left = 0;
        right = A.length-1;
        while(left + 1 < right) {
            int mid = left + (right-left)/2;
            int readMid = (mid + shift) % A.length;
            if(A[readMid] == target) {
                return readMid;
            } else if(A[readMid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        int readMid = (left + shift) % A.length;
        if(A[readMid] == target) {
            return readMid;
        }
        readMid = (right + shift) % A.length;
        if(A[readMid] == target) {
            return readMid;
        }
        return -1;
    }
}

// version 2, binary search in sorted array

// 33. Search in Rotated Sorted Array

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            // handle edge case like [1,2] where left = 0, mid = 0
            if(nums[left] <= nums[mid]) {
                // nums[left] <= target, handle the boundary case
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            } else {
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid-1;
                }
            }
        }
        return -1;
    
    }
}
// @lc code=end


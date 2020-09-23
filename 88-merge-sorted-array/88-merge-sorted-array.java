/*
 * @lc id=88 lang=java
 *
 * [88] Merge Sorted Array
 */

// @lc code=start

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        while(i < m+n && j < n) {
            if(nums1[i] <= nums2[j] && i < m+j) {
                i++;
                continue;
            }
            int k = m+j-1;
            for(;k>=i;k--) {
                nums1[k+1]=nums1[k];
            }
            nums1[i] = nums2[j];
            j++;
        }
        while(j<n){
            nums1[m+n-j] = nums2[j];
            j++;
        }
}
}
// @lc code=end


/**
 * Analysis:
 * Typical two pointers.
 * Idea is simple but the boundary checking is very tricky.
 * 
 */
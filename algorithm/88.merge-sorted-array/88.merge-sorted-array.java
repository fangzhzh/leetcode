/*
 * @lc app=leetcode id=88 lang=java
 *
 * [88] Merge Sorted Array
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.

Merge nums1 and nums2 into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

 

Example 1:

Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
Example 2:

Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].
Example 3:

Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 

Constraints:

nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109
 

Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */

// @lc code=start
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // TC: O(m+n), SC: O(m+n)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx1 = m-1, idx2 = n-1;
        for(int i = m+n-1; i >=0; i--) {
            if(idx1 < 0 && idx2 < 0) {
                break;
            }
            if(idx1 < 0) {
                nums1[i] = nums2[idx2];
                idx2--;
            } else if(idx2 < 0) {
                nums1[i] = nums1[idx1];
                idx1--;

            } else            if(nums1[idx1] > nums2[idx2]) {
                nums1[i] = nums1[idx1];
                idx1--;
            } else {
                nums1[i] = nums2[idx2];
                idx2--;
            }
        }
    }
}
//[1, 2, 3, 0, 0 , 0]  [2, 5, 6], i = 5
//     id1                    idx2 

// [1,2,3,0,0,6]
//[1, 2, 3, 0, 0, 6]  [2, 5, 6], i = 4
//      id1               idx2 

// [1,2,3,0,5,6]
//[1, 2, 3, 0, 0, 6]  [2, 5, 6], i = 3
//      id1            idx2 

// [1,2,3,3,5,6]
//[1, 2, 3, 0, 0, 6]  [2, 5, 6], i = 2
//    id1             idx2 

// [1,2,3,3,5,6]
//[1, 2, 3, 0, 0, 6]  [2, 5, 6], i = 2
//    id1            idx2 

    }
}
// @lc code=end


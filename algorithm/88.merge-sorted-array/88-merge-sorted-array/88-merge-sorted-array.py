"""
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
 """


# iterative solution
# TC(m+n), SC(1)
 class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        i, j, k = m-1, n-1, m+n-1
        while i >= 0 and j >= 0:
            if nums1[i] > nums2[j]:
                nums1[k] = nums1[i]
                i -= 1
            else:
                nums1[k] = nums2[j]
                j -= 1
            k-=1
        while i >= 0:
            nums1[k] = nums1[i]
            i -= 1
            k -= 1
        while j >= 0:
            nums1[k] = nums2[j]
            j -= 1
            k -= 1


        
# recurive version
# TC(m+n), SC(m+n)
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        def helper(i: int, j: int, k: int) -> None:
            if i < 0 and j < 0:
                return
            if i < 0:
                nums1[k] = nums2[j]
                helper(i, j-1, k-1)
                return
            if j < 0:
                nums1[k] = nums1[i]
                helper(i-1, j, k-1)
                return
            if nums1[i] > nums2[j]:
                nums1[k] = nums1[i]
                helper(i-1, j, k-1)
            else:
                nums1[k] = nums2[j]
                helper(i, j-1, k-1)
        helper(m-1, n-1, m+n-1)
                



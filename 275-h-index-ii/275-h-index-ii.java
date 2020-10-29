/*
 * @lc app=leetcode lang=java
 *
 * [275] H-Index II
 */
/**
 * Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity? */

// @lc code=start
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        if(len == 0) {
            return 0;
        }
        int start = 0;
        int end = citations.length-1;
        int max = 0;
        while(start+1 < end) {
            int mid = start + (end-start)/2;
            if(citations[mid]>=len-mid) {
                if(max < len-mid) {
                    max = len-mid;
                }
                end = mid;
            } else {
                start = mid;
            }
        }
        if(citations[start]>=len-start) {
            if(max < len-start) {
                max = len-start;
            }
        }
        if(citations[end] >=len-end) {
            if(max < len-end) {
                max = len-end;
            }
        }
        return max;
    }
}
// @lc code=end


/**
 * # analysis
 * It's ascending, so it's so obvious that it needs binary search
 * O(lgn) is better than O(n)
 * 
 * The binary search just needs some time to write.
 */

 /**
  * binary search
  * the most important is to understand `citation[mid] == len - mid`
  * let h = len - mid
  * at lease h papters has h citation, and all other mid papers have no moer than h
  * because it's sorted.
  * If the mid goes left, citation[mid] go smaller and len-mid go bigger
  * at the same time, if the mid goes right, citation[mid] go bigger and len-mid go smaller
  * "citation[mid] == len - mid" must be the one
  *
  * Other than the optimization, my solution covers all case
  */
class Solution {
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) {
            return 0;
        }
        int len = citations.length;
        int low = 0, high = len - 1;
        while(low + 1 < high) {
            int mid = low + (high-low)/2;
            if(citations[mid] >= (len - mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        if(citations[low] >= (len - low)) {
            return len - low;
        }
        if(citations[high] >= (len - high)) {
            return len - high;
        }

        return 0;
        
    }
}
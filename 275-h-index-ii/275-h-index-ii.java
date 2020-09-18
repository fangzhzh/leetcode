/*
 * @lc app=leetcode lang=java
 *
 * [275] H-Index II
 */
/**
 * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least hcitations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had
received 3, 0, 6, 1, 5 citations respectively.
             Since the researcher has 3 papers with at least 3 citations each and the remaining
             two with no more than 3 citations each, her h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.




Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?
 */

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
 * O(ln) is better than O(n)
 * 
 * The binary search just needs some time to write.
 */
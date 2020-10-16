/*
Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
             
*/        
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        Arrays.sort(citations);
        for(int i = 0; i < len; i++) {
            if(citations[i] >= len-i) {
                return len-i;
            } else {
                continue;
            }
        }
        return 0;
    }
}


/**
 * # analysis
 * - at leave h in the right, N-h not more than h, 
 *      + it's kind of binary search, but it's not middle
 *      + merge sort, but also it's not a easy way to find the h
 * 
 * [3,0,6,1,5]
 * 
 * Let see the sample, first give it a sort
 * [0,1,3,5,6]
 * 
 * To achieve the h-index, the nums[i] >= h-i, let's put them together
 * [0,1,3,5,6]
 *  5,4,3,2,1
 * 
 * it becomes obvious that 
 *           
   if(citations[i] >= len-i) {
        return len-i;
    }
 *  
 * */

/**
 *  ## counting / bucket algorithm to decrease the time complexity to O(N)
 */

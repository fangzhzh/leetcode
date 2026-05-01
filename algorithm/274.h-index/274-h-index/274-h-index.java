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
 * - if we find the idx, then at least h paper in the right, all other N-h has less than h
 *      + it's kind of binary search, but it's not middle, what's the condition of matching
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

/**
 *      3   0   6   1   5 
 * 0:                   0
 * 1:               1   1
 * 2:                   0
 * 3:   1               1
 * 4:                   0
 * 5:           1       2
 * 
 * then count = 0;
 * scan from 5..0
 * in idx = 3, count >= i
 * 
 */

/**
 *
 * Bucket sort, or bin sort, is a sorting algorithm that works by distributing the elements of an array into a number of buckets. Each bucket is then sorted individually, either using a different sorting algorithm, or by recursively applying the bucket sorting algorithm. It is a distribution sort, a generalization of pigeonhole sort, and is a cousin of radix sort in the most-to-least significant digit flavor
 */
class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length;
        int []bucket = new int[len+1];
        for(int i = 0; i < len; i++) {
            if(citations[i] >= len) {
                bucket[len]++;
            } else {
                bucket[citations[i]]++;
            }
        }
        int count = 0;
        for(int i = len; i >=0; i--) {
            count += bucket[i];
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }
}
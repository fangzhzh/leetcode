/*
 * @lc app=leetcode id=477 lang=java
 *
 * [477] Total Hamming Distance
 */


/**
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 */ 

/**
 * Definitely TLE, but the idea is so pure and naive, but it's most intuitve 
 * 
 * I kind of know before I implemented it.
 * 
 * 
 */
// @lc code=start
class Solution {
    public int totalHammingDistance(int[] nums) {
        String[] binaries = new String[nums.length];
        for(int i =0; i < nums.length; i++) {
            binaries[i] = toBinary(nums[i]);
        }
        int sum = 0;
        Arrays.sort(binaries);
        for(int i = 0; i < nums.length; i++) {
            for( int j = i+1; j < nums.length; j++) {
                if(binaries[i].compareTo(binaries[j]) == 0) {
                    continue;
                }
                sum += distance(binaries[i], binaries[j]);
            }
        }
        return sum;
    }
    String toBinary(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n%2);
            n = n/2;
        }
        return sb.reverse().toString();
    }

    int distance(String t, String s) {
        int i = t.length()-1, j = s.length()-1;
        int cnt = 0;
        while(i>=0 && j>=0) {
            if(t.charAt(i) != s.charAt(j)) {
                cnt++;
            }
            i--;
            j--;
        }
        while(i>=0) {
            if(t.charAt(i) != '0') {
                cnt++;
            }
            i--;
        }
        while(j>=0) {
            if(s.charAt(j) != '0') {
                cnt++;
            }
            j--;
        }
        return cnt;
    }
}
// @lc code=end


/**
 * another mathmatics solution
 * 
 * let loop 32 bit, fot bit i, we count all number contains this bit as `1` to cnt
 * then the hamming distance for this bit is 1(distacne) * cnt * (n-cnt)
 * for every number including this bit, for other (n-cnt) numbers, distance is 1*(n-cnt)
 * and we have n number so this bit contributes cnt * (n - cnt)
 */

class Solution {
    public int totalHammingDistance(int[] nums) {
        int[] bits = new int[32];
        int cnt_total = 0;
        for(int i = 0; i < 32; i++) {
            int cnt = 0;
            for(int j = 0; j < nums.length; j++) {
                if((nums[j] >> i & 1) == 1) {
                    cnt++;
                }
            }
            cnt_total += cnt * (nums.length - cnt);
        }
        return cnt_total;
    }
}

/**
 * 10^3 = 2^10=> 10^9=2^30
 * (n >> 1) & 1 == 1 is a genious way go check the ith bit
 * bitCnt 1 and (len-bitCnt) 0, so the diff is bitCnt * (len-bitCnt), primary math.
 */
class Solution {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        for(int i = 0; i < 30; i++) {
            int bitCnt = 0;
            for(int n : nums) {
                if((n >> i & 1) == 1) {
                    bitCnt ++;
                }
            }
            sum += (bitCnt * (nums.length - bitCnt));
        }
        return sum;
    }
}
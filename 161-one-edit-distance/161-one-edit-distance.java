/**
 * Given two strings S and T, determine if they are both one edit distance apart.
One ediit distance means doing one of these operation:

insert one character in any position of S
delete one character in S
change one character in S to other character
Example
Example 1:

Input: s = "aDb", t = "adb" 
Output: true
Example 2:

Input: s = "ab", t = "ab" 
Output: false
Explanation:
s=t ,so they aren't one edit distance apart
 */

/**
 * O(max(m,n))
 *  Idea is very simple, make use of the question information.
 * - one edit away
 * - add, delete, replace
 * - no extra string created
 * 
 * But one more info can be used, the lengths of two strings
 * if(m == n) {
 * } else {
 * }
 * */ 
public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        if(s.compareTo(t)==0) {
            return false;
        }
        int m = s.length(), n = t.length();
        if(Math.abs(m-n) > 1) {
            return false;
        }
        int i = 0, j = 0;
        
        for(;;i++,j++) {
            if(i==m-1 || j == n-1) {
                return true;
            }
            if(s.charAt(i)==t.charAt(j)) {
                continue;
            }
            if(s.substring(i+1).compareTo(t.substring(j))==0) {
                return true;
            }
            if(s.substring(i+1).compareTo(t.substring(j+1))==0) {
                return true;
            }
            if(s.substring(i).compareTo(t.substring(j+1))==0) {
                return true;
            }
            return false;
        }
        // write your code here
    }
}

/**
 * another try
 * use wrong to calculate
 * 
 * it has to take care of the i++, j++, boundary, long, short
 */
public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        if(s.compareTo(t)==0) {
            return false;
        }
        int m = s.length(), n = t.length();
        if(Math.abs(m-n) > 1) {
            return false;
        }
        int dif = 0;
        int i = 0, j = 0;
        String longStr, shortStr;
        if(m > n) {
            longStr = s;
            shortStr = t;
        } else {
            longStr = t;
            shortStr = s;
        }
        while(j < shortStr.length()) {
            if(dif > 1) {
                return false;
            }
            if(longStr.charAt(i) != shortStr.charAt(j)) {
                dif++;
                i++;
                if(m==n) {
                    j++;
                }
            }
            if(longStr.charAt(i) == shortStr.charAt(j)) {
                i++;
                j++;
            }
        }
        if(m-i+n-j == 1) {
            dif = 1;
        }
        return (dif==1);
        // write your code here
    }
}

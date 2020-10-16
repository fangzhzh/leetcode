/*
 * @lc app=leetcode lang=java
 *
 * [38] Count and Say
 */

// last char and counting
class Solution {
    public String countAndSay(int n) {
        return helper("1", n-1);
    }
    private String helper(String num, int step) {
        if(step == 0) {
            return num;
        }
        StringBuilder result = new StringBuilder();
        int cnt = 1;
        char lastChar = ' ';

        for(int i = 0; i < num.length(); i++) {
            if(lastChar == ' ') {
                lastChar = num.charAt(i);
                cnt = 1;
                continue;
            } 
            if(lastChar != num.charAt(i)) {
                result.append(cnt);
                result.append(lastChar);
                lastChar = num.charAt(i);
                cnt = 1;
                continue;
            }
            cnt++;
        }
        result.append(cnt);
        result.append(lastChar);
        return helper(result.toString(),  step-1);
    }
}



// two loop and counting
class Solution1 {
    public String countAndSay(int n) {
        return helper("1", n-1);
    }
    private String helper(String num, int step) {
        if(step == 0) {
            return num;
        }
        StringBuilder result = new StringBuilder();
        int cnt = 0;

        for(int i = 0; i < num.length(); i++) {
            int cnt = 1;
            int j = i+1;
            for(; j < num.length(); j++) {
                if(num.charAt(j)==num.charAt(i)) {
                    cnt++;
                    continue;
                } 
                break;
            }
            result.append(cnt);
            result.append(num.charAt(i));
            i = j-1;
        }
        return helper(result.toString(),  step-1);
    }
}


/**
 * he count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.

Note: Each term of the sequence of integers will be represented as a string.
 */

 /**
  * 
## This question is simple in idea, and medium in implementation.
- the basic idea idea is to count the last char and concat the string,
    + two pointers to counter
    + last char to counter

    It's not a funcy problem but you need be 100% careful to write a correct code without compiler and IDE 
    Either way, we have to handle the edage cases.
  */

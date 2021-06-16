/*
 * @lc app=leetcode id=374 lang=java
 *
 * [374] Guess Number Higher or Lower
 */

// @lc code=start
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
 // typical binary search
    public int guessNumber(int n) {
        int left = 1, right = n;
        // [1, n]
        while(left <= right) {
            int mid = left + (right - left)/2;
            int curGuess = guess(mid);
            // ==, return mid
            if(curGuess == 0) {
                return mid;
            } else if(curGuess < 0) {
                // cur mid > pick, [left, mid -1]
                right = mid -1;
            } else if(curGuess > 0) {
                // cur mid < pick, [mid + 1, right]
                left = mid + 1;
            }
        }
        return -1;
    }
}
// @lc code=end


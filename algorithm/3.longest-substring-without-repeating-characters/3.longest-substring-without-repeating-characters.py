#
# @lc app=leetcode id=3 lang=python3
#
# [3] Longest Substring Without Repeating Characters
#

# @lc code=start
## slicing window
## [left, right] valid window
## (rigth, ∞) to be handled
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        if not s:
            return 0
        left, right, ans = 0, 0, 0
        n = len(s)
        win = {}
        while right < n:
            c = s[right]
            if win.get(c, 0) == 1:
                while left <= right and win[c] != 0:
                    win[s[left]] -= 1
                    left += 1
            win[c] = win.get(c, 0) + 1
            right += 1
            ans = max(right - left, ans)
        return ans
# @lc code=end


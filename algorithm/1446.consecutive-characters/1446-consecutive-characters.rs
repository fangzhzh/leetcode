/*
 * @lc app=leetcode.cn id=1446 lang=rust
 *
 * [1446] 连续字符
 */

// @lc code=start
impl Solution {
    pub fn max_power(s: String) -> i32 {
        let mut cur = '0';
        let mut max = 1;
        let mut len = 1;
        for c in s.chars() {
            if(cur == c) {
                len+=1;
                max = std::cmp::max(max, len);
            } else {
                cur = c;
                len = 1;
            }
        }
        return max;
    }
}
// @lc code=end


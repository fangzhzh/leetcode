class Solution {
    fun isSubsequence(s: String, t: String): Boolean {
        val len = s.length
        if(len == 0) {
            return true
        }
        var idx = 0
        // this is non sense dp
        // dp[i] means is subsequence until s[i]
        val dp = BooleanArray(len) 
        for(char in t) {
            if(s[idx] == char) {
                dp[idx++] = true
                if(idx == len) {
                    return true
                }
            }
        }
        return dp[len-1]
    }
}

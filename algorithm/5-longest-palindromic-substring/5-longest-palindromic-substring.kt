// dp
fun longestPalindrome(s: String): String {
    var result = ""
    val len = s.length
    val dp = Array(len) { BooleanArray(len) }
    // dp(i, j) represents whether s(i ... j) can form a palindromic substring, 

    for (i in len - 1 downTo 0) {
        for (j in i until len) {
            dp[i][j] = (s[i] == s[j]) && (j - i < 3 || dp[i + 1][j - 1])
            // dp[i][j] means s(i...j) and written as s.substring(i, j+1)
            if (dp[i][j] && (result.isEmpty() || j - i +1 > result.length)) {
                result = s.substring(i, j+1)
            }
        }
    }
    return result
}

/*
Accepted
103/103 cases passed (240 ms)
Your runtime beats 74.32 % of kotlin submissions
Your memory usage beats 30.62 % of kotlin submissions (39.7 MB)
 */
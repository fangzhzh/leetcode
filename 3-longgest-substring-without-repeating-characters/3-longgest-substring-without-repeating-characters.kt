// version 1
/*
    1. state: dp[len+1][len+1], dp[i][j] means the longgest substring from in string substring(i,j+1)
    2. function: dp[i][j] = 2.1 dp[i][j-1] + 1
                        2.2 or comlex case, last char equal, last char not equal...
    3. initialier: dp[0][0..len]=0, dp[0..len][0] = 0
    4. result: dp[0][len]

 */
 /*
 Main problem here is that function 2.2 itself is the problem. If we resolve this part, we resolve this problem
 ❎
  */
fun lengthOfLongestSubstring(s: String): Int {
    val len = s.length
    if(len <= 1) {
        return len
    }
    val dp = Array(len+1){IntArray(len+1)}
    dp[0][0] = 1
    var max = 0
    for(i in 0 until len-1) {
        for(j in i+1 until len) {
            if(s.substring(i, j).contains(s[j])) {
                var cur = j-1
                while(s[cur] != s[j]) {
                    cur--
                }
                dp[i][j] = j-cur
            } else {
                dp[i][j] = dp[i][j-
                    1]+1
            }
            if(max < dp[i][j]) {
                max = dp[i][j]
            }
        }
    }
    return max
}


// version 2, slicing window
fun lengthOfLongestSubstring(s: String): Int {
    var max = 0
    val len = s.length
    var i = 0
    while (i<=len) {
        var size = 0
        val visited = HashMap<Char, Int>()
        for(j in i until len) {
            if(!visited.containsKey(s[j])) {
                visited[s[j]]=j
                size++
            } else {
                i=visited[s[j]]!!
                break
            }
            if(max < size) {
                max = size
            }
        }
        i++

    }
    return max
}


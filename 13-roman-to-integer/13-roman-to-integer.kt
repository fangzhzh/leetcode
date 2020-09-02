class Solution {
    fun romanToInt(s: String): Int {
         if(s.isEmpty()) {
        return 0
    }
    val map = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )
    var sum = 0
    for ((index, char) in s.withIndex()) {
        sum += if(index < s.length-1
            && (
                char == 'I' && "VX".contains(s[index+1])
                    ||
                char == 'X' && "LC".contains(s[index+1])
                    ||
                char == 'C' && "DM".contains(s[index+1])
                )
        ) {
            -map[char]!!
        } else {
            map[char]!!
        }
    }
    return sum
    }
}

/**
Accepted
3999/3999 cases passed (560 ms)
Your runtime beats 5.54 % of kotlin submissions
Your memory usage beats 10.46 % of kotlin submissions (41.9 MB)
 */
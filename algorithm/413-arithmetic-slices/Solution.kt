class Solution {
    fun numberOfArithmeticSlices(A: IntArray): Int {
        var result = 0
        val len = A.size
        if(len <= 2) {
            return result
        }
        // dp[i] means aarithmetic slices ending with A[i]
        val dp = IntArray(len+1).apply {
            this[0] = 0
            this[1] = 0
        }

        for (i in 2..len-1) {
            if (A[i]-A[i-1] == A[i-1] - A[i-2]) {
                dp[i] = dp[i-1] + 1
            }
            result += dp[i]

        }
        return result

    }
}

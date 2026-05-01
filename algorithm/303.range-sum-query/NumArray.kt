class NumArray(val nums: IntArray) {
    val dp = IntArray(nums.size+1)

    init {
        for(i in 1..nums.size) {
            // sum of 0..i
            dp[i] = dp[i-1] + nums[i-1]
        }
    }
    fun sumRange(i: Int, j: Int): Int {
        return (dp[j+1]-dp[i])
    }

}


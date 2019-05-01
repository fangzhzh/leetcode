class Solution {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        val len = nums.size
        if(len == 0) {
            return 0
        }
        var i = 0
        var j = 0
        while(i < len) {
            if(nums[i] == `val`) {
                j = i+1
                while(j < len) {
                    if(nums[j] == `val`) {
                        j++
                        continue
                    }
                    val tmp = nums[i]
                    nums[i] = nums[j]
                    nums[j] = tmp
                    j++
                    i++
                }
                return i
            }
            i++
        }
        return i
    }
}

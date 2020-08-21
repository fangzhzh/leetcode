// brutal force: O(n^2)
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        if(nums.isEmpty()) {
            return intArrayOf()
        }
        // nums.sort()
        for(i in nums.indices) {
            for(j in nums.size-1 downTo i+1) {
                val sum = nums[i] + nums[j]
                if(sum == target) {
                    return intArrayOf(i, j)
                } 
            }
        }
        return intArrayOf()
    }
}

// better, O(N)
class Solution {
        if (nums.isEmpty()) {
        return intArrayOf()
    }
    val map = LinkedHashMap<Int, Int>()
    for (i in nums.indices) {
        val value = nums[i]
        if (map.containsKey(target - value)) {
            return intArrayOf(map.getOrDefault(target - value, 0), i)
        } else {
            map[value] = i
        }
    }
    return intArrayOf()
}

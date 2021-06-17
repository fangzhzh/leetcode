class Solution {
    fun longestCommonPrefix(strs: Array<String>): String {
        var result = ""
        val len = strs.size
        if(len == 0) {
            return result
        }

        var idx = 0
        while(true) {
            val char = strs[0].getOrNull(idx)
            char?.let {
                for(str in strs) {
                    if(str.getOrNull(idx) != char) {
                        return result
                    }
                }
                idx++
                result += char
            } ?: return result
        }
    }
}



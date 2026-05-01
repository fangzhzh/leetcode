class Solution {
    fun lengthOfLastWord(s: String): Int {
        var len = 0
        var newWord = false
        for(char in s) {
            if(char != ' ') {
                if(newWord == true) {
                    len = 1
                    newWord = false
                } else {
                    len ++
                }
            } else {
                newWord = true
            }

        }
        return len
    }
}

class Solution {
    fun isPalindrome(x: Int): Boolean {
        if(x<0) {
            return false
        }
        if(x == 0) {
            return true
        }
        var newX = 0
        var reminder = 0
        var tmp = x
        while(tmp > 0) {
            reminder = tmp % 10
            tmp = tmp / 10
            newX = newX * 10 + reminder
        }
        return newX == x
    }
}

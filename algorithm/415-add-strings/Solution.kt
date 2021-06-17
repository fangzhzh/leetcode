class Solution {
    fun addStrings(num1: String, num2: String): String {
        var result = ""
        var carrier = 0
        val len1 = num1.length
        val len2 = num2.length
        val len = Math.min(len1, len2)
        for (i in 0 .. len-1) {
            val sum = carrier + (num1[len1-1-i]-'0') + (num2[len2-1-i]-'0')
            result = "${sum % 10}$result"
            carrier = sum / 10
        }
        if(len < len1) {
            for(i in len..len1-1) {
                val sum = carrier + (num1[len1-1-i]-'0') 
                result = "${sum % 10}$result"
                carrier = sum / 10
            }
        } else if(len < len2) {
            for(i in len..len2-1) {
                val sum = carrier + (num2[len2-1-i]-'0')
                result = "${sum % 10}$result"
                carrier = sum / 10
            }
        } 
        if(carrier > 0){
            result = "$carrier$result"
        }
        return result
    }
}

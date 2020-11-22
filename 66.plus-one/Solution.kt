class Solution {
    fun plusOne(digits: IntArray): IntArray {
        val len = digits.size
        if(len == 0) {
            return digits
        }
        var num = ""
        var carrier = 1
        for(idx in len-1 downTo 0) {
            val sum = digits[idx]+carrier
            num += (sum % 10)
            carrier = sum / 10
        }
        if(carrier != 0) {
            num += carrier
        }

        val numStr = num.reversed()
        val newLen = num.toString().length//if(num % 10 == 0)  len+1 else len
        val result = IntArray(newLen)
        var idx = 0
        for(digit in numStr) {
            result[idx++] = digit-'0'
        }
        return result
    }
}

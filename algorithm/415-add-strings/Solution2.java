public class Solution {
    public String addStrings(String num1, String num2) {
        int m = num1.length()-1, n = num2.length()-1;
        StringBuilder b = new StringBuilder();
        int carry = 0;
        while(m>=0  || n>=0) {
            int sum = 0;
            if(m>=0) sum += num1.charAt(m)-'0';
            if(n>=0) sum += num2.charAt(n)-'0';
            sum += carry;
            b.append(sum % 10);
            carry = sum / 10;
            m--;
            n--;
        }
        if(carry > 0) {
            b.append(carry);
        }
        return b.reverse().toString();
    }
}

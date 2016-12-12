public class Solution {
    public String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        int shift=1;
        long sum=0;
        for(int i = num1.length() - 1 ; i >= 0 ; --i) {
            int mul = 1;
            for(int j = num2.length() - 1; j >= 0; --j) {
                sum += (num1.charAt(i)-'0')*shift * (num2.charAt(j)-'0') * mul;
                mul*=10;
            }
            shift*=10;
        }
        b.append(sum);
        return b.toString();
    }
}

public class Solution {
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] result = new int[m+n];
        for(int i = m-1; i >= 0; --i) {
            for(int j = n-1; j >= 0; --j) {
                int sum = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                sum += result[i+1+j];
                result[i+j] += sum / 10;
                result[i+j+1] = sum % 10;
            }
        }
        StringBuilder b = new StringBuilder();
        for(int i : result) {
            if (b.length() == 0 && i == 0) {
                continue;
            }
            b.append(i);
        }
        return b.length()==0 ? "0" : b.toString();
    }
}

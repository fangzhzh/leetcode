public class Solution {
    public String convertToTitle(int n) {
        final int base = 26;
        StringBuilder result = new StringBuilder();
        int reminder;
        while (n >= 0) {
            n--;
            reminder = n % base;
            result.append((char)(reminder+'A'));
            n = n / base;
            if (n <= 0) {
                break;
            }
        }

        return result.reverse().toString();
    }
}

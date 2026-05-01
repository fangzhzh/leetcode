public class Solution { 
    public String convertToTitle(int n) {
        if(n==0) return "";
        StringBuilder b = new StringBuilder();
        while(n>0) {
            b.append((char)((n-1)%26+'A'));
            n = (n-1)/26;
        }
        return b.reverse().toString();

    }
}

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int dif = 0;
        int m = s.length(), n = t.length();
        if( m == n) {
            for(int i = 0; i < n; ++i) {
                if(s.charAt(i) != t.charAt(i)) {
                    dif++;
                    if(dif > 1){
                        break;
                    }
                }
            }
        } else if(Math.abs(m - n) == 1) {
            String low = (m > n) ? t : s;
            String high = (m > n) ? s : t;
            for(int i = 0; i < Math.min(m,n); ++i) {
                if(low.charAt(i) != high.charAt(i+dif)) {
                    dif++;
                    i--;
                    if(dif > 1){
                        break;
                    }
                }
            }
            if(dif == 0) {
                dif++;
            }
        }
        return dif == 1;
    }
}

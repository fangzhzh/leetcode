public class Solution {
    public int numDecodings(String s) {
        if(s.length()==0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<dp.length; ++i) {
            char pre = s.charAt(i-2), cur = s.charAt(i-1);
            if(s.charAt(i-1) == '0' && (s.charAt(i-2)>'2'|| s.charAt(i-2)=='0')) {
                return 0;
            }

            if(cur == '0') {
                if(pre>'2' || pre=='0'){
                    return 0;
                } else {
                    dp[i] = dp[i-2];
                }
            } else {
                if(pre>'2' || pre=='0') {
                    dp[i] = dp[i-1];
                } else{
                    if(pre=='2' && cur>'6') {
                        dp[i] = dp[i-1];
                    }else{
                        dp[i] = dp[i-1]+dp[i-2];
                    }
                }
            }
        }
        return dp[s.length()];
    }
}

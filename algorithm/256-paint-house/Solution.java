public class Solution {
    public int minCost(int[][] costs) {
        // write your code here
        int len = costs.length;
        if(len == 0) {
            return 0;
        }
        // dp[i][0] min cost when house i use color 0
        // dp[i][1] min cost when house i use color 1
        // dp[i][2] min cost when hosue i use color 2
        int[][] dp = new int [len+1][3];
        dp[1][0] = costs[0][0];
        dp[1][1] = costs[0][1];
        dp[1][2] = costs[0][2];
        for(int i = 2; i <= len; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i-1][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i-1][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + costs[i-1][2];
        }
        return Math.min(
                dp[len][0], 
                Math.min(dp[len][1],dp[len][2])
                );
    }
}

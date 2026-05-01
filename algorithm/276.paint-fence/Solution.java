public int numWays(int n, int k) {
    if(k==1 && n>2) {
        return 0;
    }

    if(n == 1) {
        return k;
    }
    // dp[i] means number of ways to paint fence
    int[] dp = new int[n+1];

    dp[0]=1;
    dp[1]= k;
    dp[2] = dp[1]*k;
    for(int i = 3; i <= n; i++) {
        if(k-1==0) {
            return 0;
        }
        dp[i] = (dp[i-2] + dp[i-1])*(k-1);
    }
    return dp[n];
}
}

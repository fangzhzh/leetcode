// naive version
public class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n<=1) return 0;
        int[] sells = new int[n];
        int[] buys = new int[n];
        int[] cools = new int[n];
        cools[0] = 0;
        buys[0] = -prices[0];
        sells[0] = Integer.MIN_VALUE;
        for(int i = 1; i < n; ++i) {
            int price = prices[i];
            cools[i] = Math.max(cools[i-1], sells[i-1]);
            buys[i] = Math.max(buys[i-1], cools[i-1] - price);
            sells[i] = buys[i-1] + price;
        }
        return Math.max(cools[n-1], sells[n-1]);
    } 
}

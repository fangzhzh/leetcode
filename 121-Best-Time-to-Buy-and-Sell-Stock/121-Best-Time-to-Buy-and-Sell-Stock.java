class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        int result = 0;
        for(int i = 0; i < len; i++) {
            if(prices[i] < min) {
                min = prices[i];
            }
            if(prices[i] - min > result) {
                result = prices[i] - min;
            }
        }
        return result;
    }
}

// wrong version is min/max
// the test case for it: [2,4,1]
public int maxProfit(int[] prices) {
    int len = prices.length;
    if(len == 0) {
        return 0;
    }
    int max = 0;
    int min = Integer.MAX_VALUE;
    for(int i = 0; i < len; i++) {
        if(prices[i] < min) {
            min = prices[i];
            max = 0;
        }
        if(prices[i] > max) {
            max = prices[i];
        }
    }
    return max - min;
}
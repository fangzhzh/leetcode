class Solution {
    public int maxProfit(int[] prices) {
        int maxPro = 0;
        int min = Integer.MAX_VALUE;
        for( int price : prices) {
            if(price - min > maxPro) {
                maxPro = price-min;
            }
            if(price < min){
                min = price;
            }
        }
        return maxPro;
    }
}

class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int maxPro=0;
        int minPri = INT_MAX;
        for(int price : prices){
            if(price - minPri > maxPro){
                maxPro = price-minPri;
            }
            if(price < minPri){
                minPri = price;
            }
        }
        return maxPro;
    }
};
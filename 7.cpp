class Solution {
public:
    int reverse(int x) {
        bool minus = (x<0);
        string value = to_string(abs(x));
        std::reverse(value.begin(), value.end());
        string maxInt = to_string(INT_MAX);
        if((value.size() > maxInt.size())
        ||(value.size() == maxInt.size() && value.compare(maxInt) > 0)){
            return 0;
        }
        int result = atoi(value.c_str());
        return minus?result*-1:result;
    }
};

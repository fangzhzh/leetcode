#include <stdio.h>
#include<vector>
#include <iostream>
using namespace std;
class NumArray {
    vector<int>* pNums;
public:
    NumArray(vector<int> &nums) {
        pNums = &nums;
    }

    int sumRange(int i, int j) {
        int sum = 0;
        for( ; i <= j; i++ ){
            sum += (*pNums).at(i);
        }
        return sum;
    }
};

int main(){
    vector<int> nums;
    nums.push_back(-2);
    nums.push_back(0);
    nums.push_back(3);
    nums.push_back(-5);
    nums.push_back(2);
    nums.push_back(-1);
    NumArray numArray(nums);
    cout << numArray.sumRange(0, 2) << endl;
    cout << numArray.sumRange(2, 5) << endl;
    cout << numArray.sumRange(0, 5) << endl;
    cout << numArray.sumRange(0,1) << endl;
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray(nums) << endl;
// numArray.sumRange(0, 1) << endl;
// numArray.sumRange(1, 2) << endl;


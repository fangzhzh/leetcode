#include <list>
#include <iostream>
#include <numeric>
using namespace std;
class MovingAverage {
	int size;
	list<int> values;
public:
    /** Initialize your data structure here. */
    MovingAverage(int size) {
		this->size = size;
    }
    
    double next(int val) {
		values.push_back(val);
		if(values.size() > size){
			values.pop_front();
		}
        int sum = accumulate( values.begin(), values.end(), 0);
		return sum * 1.0  / values.size();
    }
};

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
int main(){
	MovingAverage aver(3);
	cout << aver.next(1) << endl;
	cout << aver.next(10) << endl;
	cout << aver.next(3) << endl;
	cout << aver.next(5) << endl;
	
}

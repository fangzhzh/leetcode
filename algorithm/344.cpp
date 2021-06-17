#include <iostream>     // std::cout
#include <string>
#include <algorithm>    // std::reverse
#include <vector>       // std::vector
using namespace std;
class Solution {
public:
    string reverseString(string s) {
		string str=s;
		reverse(str.begin(), str.end());
		return str;
    }
};

int main(){
	Solution solution;
	string s = "heelo world";
	cout << solution.reverseString(s) << endl;
	
}

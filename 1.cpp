#include <iostream>     // std::cout
#include <string>
#include <algorithm>    // std::reverse
#include <vector>       // std::vector
#include <unordered_map>
using namespace std;
#include <iostream>     // std::cout
#include <string>
#include <algorithm>    // std::reverse
#include <vector>       // std::vector
#include <unordered_map>
using namespace std;
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
		vector<int> nums1(nums);
		vector<int> result;
		sort(nums.begin(), nums.end());
		for(int i = 0; i < nums.size()-1; ++i){
			if(nums[i] > target){
				continue;
			}
			vector<int>::iterator it = find(nums.begin()+i, nums.end(), target-nums[i]);
			if( it != nums.end()){
				int index1, index2;
				for(unsigned k = 0; k < nums1.size(); ++k){
					if(nums1[k] == nums[i]){
						index1 = k;
					}else if(nums1[k] == target-nums[i]){
						if( k == index1){
							continue;
						}
						index2 = k;
					}
				}
				result.push_back(min(index1, index2));
				result.push_back(max(index1, index2));
				break;
			}


		}
		return result;
    }
};

int main(){
	vector<int> num;
	num.push_back(0);
	num.push_back(4);
	num.push_back(3);
	num.push_back(0);
	Solution solution;
	vector<int> sum = solution.twoSum(num, 0);
	cout << sum[0] << "  " << sum[1] << endl;
}

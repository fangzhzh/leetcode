/**
303. Range Sum Query - Immutable  Please login first.  My Submissions 											
Total Accepted: 38853
Total Submissions: 151973
Difficulty: Easy
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:

Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:

You may assume that the array does not change.
There are many calls to sumRange function.

*/

#include <vector>
using namespace std;
class NumArray {
vector<int> sums={0};
public:
	NumArray(vector<int> &nums) {
		int sum = 0;
		for(auto i : nums){
			sum += i;
			sums.push(sum);
		}
	}

	int sumRange(int i, int j) {
		return sums[j+1]-sums[i];		
	}
};

// Your NumArray object will be instantiated and called as such:
// NumArray numArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
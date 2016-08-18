class Solution {
public:
    int rob(vector<int>& nums) {
       		int size = nums.size();
       		if(size == 0) {
       		    return 0;
       		}
       		if(size == 1){
       			return nums[0];
       		}
       		if(size == 2){
       			return max(nums[0], nums[1]);
       		}
       		vector<int> result;
       		result.push_back(nums[0]);
       		result.push_back(max(nums[0], nums[1]));
       		for(int i = 2; i < nums.size(); ++i){
       			result.push_back(max(result[i-2]+nums[i], result[i-1]));
       		}
       		return result[size-1];
    }
};
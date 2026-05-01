public class Solution {
    public void moveZeroes(int[] nums){
        char c;
        int i,j;
        for( i = 0; i < nums.length; ++i){
            if(nums[i] == 0) break;
        }
        
        for( j = i+1; j < nums.length; ++j) {
        	if(nums[j] == 0){
        		continue;
        	}
            nums[i++] = nums[j];
         }   
        for(;i<nums.length;++i){
            if(nums[i]!=0) nums[i] = 0;
        }   
    }   
}

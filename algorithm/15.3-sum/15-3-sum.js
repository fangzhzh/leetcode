/**
 * as same as the java version, but javascript is more convenience to write
 * 
 * the basic two pointer version of three sum works, but for some rare cases 
 * like repeated element of a successful element will a Time Limit Exceed
 * results.any(result=> result[0] == cur[0] && result[1] == cur[1] && result[2] == cur[2])
 */
var threeSum = function(nums) {
    const len = nums.length;
    const results = [];
    nums.sort((a,b)=> a-b)
    console.log(nums)
    for(let k=0; k< len;k++) {
        if(k>0&&nums[k] === nums[k-1]) {
            continue
        }
        let j = len -1
		for(let i = k+1; i < len; i++) {
            if(i < j&&i>k+1 && nums[i]==nums[i-1]) {
                continue
            }
        	for(; j >i; j--) {
                if(j<len-1 && nums[j]== nums[j+1]) {
                    continue
                }
                if(nums[i] + nums[j] + nums[k] > 0) {
                    continue
                } else if(nums[i] + nums[j] + nums[k] < 0) {
                    break
                } else {
	                results.push([nums[k], nums[i], nums[j]])
                }
            }
        }
    }
    return results
};


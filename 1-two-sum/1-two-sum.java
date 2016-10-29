// brutal force 
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; ++i){
            for( int j = nums.length-1; 
                    j>i ;
                    --j){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[];

    }
}



/**
 * Hash map position
 * a major problem is that this solution will fail if nums contains duplicate value
 * because the position is not accurate
*/

public class Solution {
    public int[] twoSum(int[] nums, int target) {
		// hashMap: valut -> position
		// sort
		// find value and get position from hashMap
}


/* My solutions share a common thinking routine:
* 1 brutal solution
* 2 add helper to first brutal solution
*/
--------------------------------------------------------------------------------------------------------

/**
 * HashMap solution, resolve ontime
 *
 */ 
public class Solution {
    public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap();
		for(int i = 0; i < nums.length; ++i){
			if(map.containsKey(target - nums[i])) {
					return new int[] {
						map.get(target-nums[i]), i
					};
			} else {
				map.put(nums[i], i);
			}
		}
		return null;
    }
}


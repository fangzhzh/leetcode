import java.util.*;
public class Solution {
    public int maxSubArrayLen(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxL = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum == target) {
                maxL = Math.max(maxL, i+1);
            } else if( map.containsKey(sum - target)) {
                maxL = Math.max(maxL, i-map.get(sum-target));
            }
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxL;
    }
}

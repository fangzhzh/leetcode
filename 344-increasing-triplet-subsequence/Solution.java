public class Solution {
    public boolean increasingTriplet(int[] nums) {
		int minimum = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
		for( int num : nums) {
			if(num <= minimum) minimum = num;
			else if( num < secondMin) secondMin = num;
			else if(num > secondMin) return true;
		}
		return false;
    }
}

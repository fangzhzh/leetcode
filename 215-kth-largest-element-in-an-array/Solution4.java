public class Solution {
	public int findKthLargest(int[] nums, int k) {
        k = nums.length-k;
        int low = 0, high = nums.length-1;
        while(low < high) {
            int pivot = partition(nums, low, high);
            if(pivot < k) {
                low = pivot + 1;
            } else if( pivot > k) {
                high = pivot -1;
            } else {
                break;
            }
        }
        return nums[k];
    }
    
    int partition(int[] nums, int low, int high) {
        int left = low;
        for( int i = left; i < high; ++i) {
            if( nums[i] <= nums[high]) {
                swap(nums, i , left);
                left++;
            }
        }
        swap(nums, left, high);
        return left;
    }

    void swap(int[] nums, int left, int right) {
        final int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

}

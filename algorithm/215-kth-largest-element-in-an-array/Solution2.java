public class Solution {
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length-1, k);
    }

    int findKthLargest(int[] nums, int left, int right, int k) {
        int pivot = partition(nums, left, right);
        if(left < pivot-1) {
            findKthLargest(nums, left, pivot-1, k);
        } 
        if (pivot < right) {
            findKthLargest(nums, pivot, right, k);
        }
        return nums[nums.length-k];
    }
    int partition(int[] nums, int left, int right) {
        int pivot = nums[left + (right-left)/2];
        while(left <= right) {
            while(nums[left] < pivot) {
                left++;
            }
            while(nums[right] > pivot) {
                right--;
            }
            if(left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        return left;
    }
    void swap(int[] nums, int i , int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}

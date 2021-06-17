public class Solution {
    public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length-1);
    }
    void quickSort(int[] nums, int low, int high) {
        int pivot = partition(nums, low, high);
        if(low < pivot-1) {
            quickSort(nums, low, pivot-1);
        }
        if(pivot < high) {
            quickSort(nums, pivot, high);
        }
    }
    int partition(int[] nums, int low, int high) {
        int pivot = nums[low + (high-low) / 2];
        while(low <= high) {
            while(nums[low] < pivot) low++;
            while(nums[high] > pivot) high--;
            if(low <= high) {
                swap(nums, low, high);
                low++;
                high--;
            }
        }
        return low;
    }
    void swap(int[] nums, int low, int high) {
        int tmp = nums[low];
        nums[low] = nums[high];
        nums[high] = tmp;
    }
}

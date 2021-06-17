// [3,2,1,5,4] 5
public class Solution {
	public int findKthLargest(int[] nums, int k) {
        k = nums.length-k;
        int low = 0, high = nums.length-1;
        while(low < high) {
            final int piv = partition(nums, low, high);
            //System.out.print("aftr partition: " + piv + " ");
            //System.out.println(Arrays.toString(nums));
            if(piv < k) {
                low = piv+1;
            } else if( k < piv  ) {
                high = piv-1;
            } else {
                break;
            }
        }
        System.out.println(Arrays.toString(nums));
        return nums[k];
    }

    int partition(int[] nums, int low, int high) {
        int idx = low+(high-low)/2;
        int pivot = nums[idx];
        //System.out.println("pivot:" + pivot);
        while(low<=high) {
            while(nums[low] <= pivot) low++;
            while(nums[high] > pivot) high--;
            if(low<=high) {
                int tmp = nums[high];
                nums[high] = nums[low];
                nums[low] = tmp;
            }
        }
        int tmp = nums[high];
        nums[high] = pivot;
        nums[idx]=tmp;
        return high;
    }
}

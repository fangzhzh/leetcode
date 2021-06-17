/**
 * sort
public class Solution {
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[nums.length-k];
	}
}
 */

/**
 * prioritiyQueue
 */
public class Solution {
	public int findKthLargest(int[] nums, int k) {
		final PriorityQueue<Integer> queue = new PriorityQueue<>();
		for(int num : nums) {
			queue.offer(num);
			if(queue.size() > k) {
				queue.poll();
			}
		}
		return queue.peek();
	}
}

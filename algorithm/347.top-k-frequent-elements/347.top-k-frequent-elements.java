/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 */

// @lc code=start
class Solution {
    class Node{
        int value;
        int freq;
        Node(int value, int freq) {
            this.value = value;
            this.freq = freq;
        }
    }
    // TC O(n log k)
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>(k, (a,b)->  b.freq - a.freq );
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int key : map.keySet()) {
            Node node = new Node(key, map.get(key));
            pq.offer(node);
        }
        int[] ans = new int[k];
        // top k freq
        for(int i = k-1; i >= 0; i--) {
            ans[i] = pq.poll().value;
        }
        return ans;
    }
}
// @lc code=end


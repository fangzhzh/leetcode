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

// PQ
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> counter = new HashMap<>();
        for(int i = 0; i < n; i++ ) {
            counter.put(nums[i], counter.getOrDefault(nums[i], 0)+1);
        }

     
        // priority最小堆：是为了方便我们每次淘汰“当前最小的”
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (a, b) -> counter.get(a) - counter.get(b)
        );
        for(int key : counter.keySet()) {
            pq.offer(key);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        int[] ans = new int[k];
        int idx = 0;
        while(idx < k) {
            ans[idx++] = pq.poll();
        }
        return ans;
    }
}

// 桶排序的

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> counter = new HashMap<>();
        
        for(int num : nums) {
            counter.put(num, counter.getOrDefault(num, 0) + 1);
        }
        
        List<Integer>[] bucket = new List[n+1];
        for(int key : counter.keySet()) {
            int v = counter.get(key);
            if(bucket[v] == null) {
                bucket[v] = new ArrayList<>();
            }
            bucket[v].add(key);
        }
        
        
        // 桶排序，自然有序，特性是nums有限，那么occurency也是有限
        int[] ans = new int[k];
        int idx = 0;
        for(int i = n; i >= 0; i--){
            if(bucket[i] != null) {
                for(int num : bucket[i]) {
                    ans[idx++] = num;
                    if(idx >= k) {
                        return ans;
                    }
                }

            }
        }
        return ans;
    }
}
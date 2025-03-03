#
# @lc app=leetcode id=347 lang=python3
#
# [347] Top K Frequent Elements
#

# @lc code=start
class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        # heapq version 
        def heapqVersion() -> List[int]:
            counter = {}
            for num in nums:
                counter[num] = counter.get(num, 0) + 1
            pq = []
            for num, cnt in counter.items():
                heapq.heappush(pq, (cnt, num))
                if(len(pq) > k):
                    heapq.heappop(pq)
            return [num for cnt, num in pq]
        # self implemented priority queue version
        # the sinkDown and swimUp is hard to get(break) right, refer the below exmaple to help justify
        """
             5 
            / \
           6   7
          /
         4 
        """
        def pq() -> List[int]:
            counter = {}
            heap = [0]*k
            self.size = 0
            def offer(val: int)->None:
                if self.size < k:
                    heap[self.size] = val
                    swimUp(self.size)
                    self.size += 1
                else:
                    if(counter.get(heap[0]) > counter.get(val)):
                        return
                    heap[0] = val
                    sinkDown(0)

            def swimUp(index: int)->None:
                val = heap[index]
                while index > 0 :
                    parent = (index - 1) // 2
                    if counter[heap[parent]] <= counter[val]:
                        break
                    heap[index] = heap[parent]
                    index = parent
                heap[index] = val

            def sinkDown(index: int)->None:
                val = heap[index]
                while index * 2 + 1 < self.size:
                    left = index * 2 + 1
                    right = left + 1
                    smallest = left
                    if right < self.size and counter[heap[left]] > counter[heap[right]]:
                        smallest = right
                    if counter[heap[smallest]] >= counter[val]:
                        break
                    heap[index] = heap[smallest]
                    index = smallest
                heap[index] = val
            for num in nums:
                counter[num] = counter.get(num, 0) + 1
            for num, freq in counter.items():
                offer(num)
            return heap
        return pq()
            
            
                    
# @lc code=end


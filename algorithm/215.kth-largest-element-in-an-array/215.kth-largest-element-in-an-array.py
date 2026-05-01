#
# @lc app=leetcode id=215 lang=python3
#
# [215] Kth Largest Element in an Array
#

# @lc code=start
class Solution:
    # TC O(N log k)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        self.size = 0
        heap = [0]*k
            
        def offer(val: int) -> None:
            if self.size < k:
                heap[self.size] = val
                swimUp(self.size)
                self.size+=1
            else:
                if heap[0] > val:
                    return
                heap[0] = val
                sinkDown(0)
            pass
        def swimUp(index: int)-> None:
            item = heap[index]
            while index > 0:
                parent = (index - 1) // 2
                if heap[parent] <= item:
                    break
                heap[index] = heap[parent]
                index = parent
            heap[index] = item

        def sinkDown(index: int)->None:
            val = heap[index]
            while index * 2 + 1 < k:
                left = index * 2 + 1
                right = left + 1
                smallest = left
                if right < self.size and heap[left] > heap[right]:
                    smallest = right
                
                if heap[smallest] >= val:
                    break
                heap[index]= heap[smallest]
                index = smallest
            heap[index] = val

        ## self implemented priorityQueue
        def FixCustomPriorityQueue(size: int) -> int:
            for num in nums:
                offer(num)
            return heap[0]

        ## buildin PriorityQueue
        def buildinPQ()->int:
            pq = []
            for num in nums:
                heapq.heappush(pq, num)
                if(len(pq) > k):
                    heapq.heappop(pq)
            return pq[0]

        return buildinPQ()

        
                
# @lc code=end


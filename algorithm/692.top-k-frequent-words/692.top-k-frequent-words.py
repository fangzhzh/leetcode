#
# @lc app=leetcode id=692 lang=python3
#
# [692] Top K Frequent Words
#

# @lc code=start
class PriorityQueue:
    def __init__(self, size:int, counter: {str: int})-> None:
        self.capacity = size
        self.size = 0
        self.queue = [''] * self.capacity
        self.counter = counter
    def offer(self, word: str) -> None:
        if self.size < self.capacity:
            self.queue[self.size] = word
            self.swimUp(self.size)
            self.size += 1
        else:
            if not self.compare(self.queue[0], word):
                return
            self.queue[0] = word
            self.sinkDown(0)
    def get(self)->List[str]:
        result = self.queue[:self.size]
        result.sort(key=lambda x: (-self.counter[x], x))
        return result
    def swimUp(self, index: int)->None:
        word = self.queue[index]
        while index > 0:
            parent = (index-1) // 2
            if self.compare(self.queue[parent], word):
                break
            self.queue[index] = self.queue[parent]
            index = parent
        self.queue[index] = word
    def compare(self, word: str, word2: str)->bool:
        return self.counter[word] < self.counter[word2] or (self.counter[word] == self.counter[word2] and word > word2)
    def sinkDown(self, index: int)->None:
        word = self.queue[index]
        while  2 * index + 1 < self.size:
            left = 2 * index + 1
            right = left + 1
            smallest = left
            if right < self.size and not self.compare(self.queue[left], self.queue[right]): 
                smallest = right
            if not self.compare(self.queue[smallest], word):
                break
            self.queue[index] = self.queue[smallest]
            index = smallest
        self.queue[index] = word
class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        def pq() -> List[str]:
            # heap sort
            # min head, k size, TC O(n lg k)
            counter = {}
            stack = []
            for word in words:
                counter[word] = counter.get(word, 0) + 1
            pq = PriorityQueue(k, counter)
            for word, i in counter.items():
                # print(f"{word} to {i}")
                pq.offer(word)
                # print(pq.queue)
            return pq.get()
        return pq()
            
        
# @lc code=end


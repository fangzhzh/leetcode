#
# @lc app=leetcode id=307 lang=python3
#
# [307] Range Sum Query - Mutable
#
"""
Range Sum Query - Mutable
Category	Difficulty	Likes	Dislikes
algorithms	Medium (41.55%)	4873	259
Tags
Companies
Given an integer array nums, handle multiple queries of the following types:

Update the value of an element in nums.
Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
Implement the NumArray class:

NumArray(int[] nums) Initializes the object with the integer array nums.
void update(int index, int val) Updates the value of nums[index] to be val.
int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 

Example 1:

Input
["NumArray", "sumRange", "update", "sumRange"]
[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
Output
[null, 9, null, 8]

Explanation
NumArray numArray = new NumArray([1, 3, 5]);
numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
numArray.update(1, 2);   // nums = [1, 2, 5]
numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 

Constraints:

1 <= nums.length <= 3 * 104
-100 <= nums[i] <= 100
0 <= index < nums.length
-100 <= val <= 100
0 <= left <= right < nums.length
At most 3 * 104 calls will be made to update and sumRange.
"""

# @lc code=start
class NumArray:
    def __init__(self, nums: List[int]):
        self.nums = nums
        self.n = len(nums)
        self.maxSize = 4 * self.n
        self.tree = [0] * (4 * self.n)  # Use 4*n as safe upper bound

        self.build(0, 0, self.n-1)

    # TC O(log n)
    def build(self, node: int, start: int, end: int)->None:
        if(start == end):
            self.tree[node] = self.nums[start]
            return
        mid = start + (end - start) // 2
        left, right = 2 * node + 1, 2 * node + 2
        self.build(left, start, mid)
        self.build(right, mid+1, end)
        self.tree[node] = self.tree[left] + self.tree[right]
        
    # TC O(log n)
    def update(self, index: int, val: int) -> None:
        if index < 0 or index >= self.n:
            return
        diff = val - self.nums[index]
        self.nums[index] = val
        self.updateRange(0, 0, self.n-1, index, diff)

    def updateRange(self, node: int, start: int, end: int, index: int, diff: int) -> None:
        if index < start or index > end:
            return
        self.tree[node] += diff
        if(start != end):
            mid = start + (end - start) // 2 
            self.updateRange(2*node+1, start, mid, index, diff)
            self.updateRange(2*node+2, mid+1, end, index, diff)


    # TC O(log n)
    def sumRange(self, left: int, right: int) -> int:
        if(left < 0 or right >= self.n or left > right):
            return 0
        return self.queryRange(0, 0, self.n-1, left, right)
        
    def queryRange(self, node: int, start: int, end: int, left: int, right: int):
        if start > right or end < left:
            return 0
        if left <= start and end <= right:
            return self.tree[node]
        mid = start + (end-start)//2
        leftS = self.queryRange(2*node+1, start, mid, left, right)
        rightS = self.queryRange(2*node+2, mid+1, end, left, right)
        return leftS + rightS
        

# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)


# @lc code=end


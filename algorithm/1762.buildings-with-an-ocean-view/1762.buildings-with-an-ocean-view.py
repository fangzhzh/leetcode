
"""
1762. Buildings With an Ocean View 🔒

Description
There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.

 

Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
Example 2:

Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
Example 3:

Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
 

Constraints:

1 <= heights.length <= 105
1 <= heights[i] <= 109

"""

class Solution:
    def findBuildings(self, heights: List[int]) -> List[int]:        
        def left2Right() => List[int]:
            n = len(heights)
            stack  = [] 
            for i in range(n):
                while stack and heights[stack[-1]] <= heights[i]:
                    stack.pop()
                stack.append(i)
            return stack
        def right2Left() => List[int]:
            n = len(heights)
            stack = []
            maxHeight = 0
            while i in range(n-1, -1, -1):
                if heights[i] > maxHeight:
                    stack.append(i)
                    maxHeight = heights[i]
            return stack[::-1]
        return left2Right()





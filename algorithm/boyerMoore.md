# Boyer–Moore Majority Vote Algorithm

## Overview
The Boyer–Moore majority vote algorithm is a linear time algorithm used to find the majority element in an array. The majority element is defined as the element that appears more than n/2 times in the array, where n is the size of the array. This algorithm is efficient and operates in O(n) time complexity with O(1) space complexity.

## Key Concepts
1. **Candidate Selection**: The algorithm maintains a candidate for the majority element and a count. It iterates through the array, updating the candidate and count based on the current element.
2. **Count Adjustment**: If the count drops to zero, the current element becomes the new candidate, and the count is reset to one. If the current element matches the candidate, the count is incremented; otherwise, it is decremented.

## Algorithm Steps
1. Initialize a candidate variable and a count variable to zero.
2. Iterate through the array:
   - If the count is zero, set the current element as the candidate and set the count to one.
   - If the current element is the same as the candidate, increment the count.
   - If it is different, decrement the count.
3. After the first pass, the candidate may be the majority element. A second pass can be performed to verify if the candidate is indeed the majority element.

## Example Walkthrough
Let's consider an example array: `[3, 2, 3, 4, 3, 3, 2, 3, 3]`. We initialize the candidate to `-1` and the count to `0`.
| Iteration | num | candidate | count |
| --- | --- | --- | --- |
| 1 | 3 | 3 | 1 |
| 2 | 2 | 3 | 0 |
| 3 | 3 | 3 | 1 |
| 4 | 4 | 3 | 0 |
| 5 | 3 | 3 | 1 |
| 6 | 3 | 3 | 2 |
| 7 | 2 | 3 | 1 |
| 8 | 3 | 3 | 2 |
| 9 | 3 | 3 | 3 |

After the first pass, `candidate` is `3` and `count` is `3`. We can then perform a second pass to verify that `3` is indeed the majority element.

## Sample Implementation
Here’s a simple Java implementation of the Boyer–Moore majority vote algorithm:

```java
public class BoyerMooreMajorityVote {
    public int findMajorityElement(int[] nums) {
        int candidate = -1, count = 0;

        // First pass to find the candidate
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Optional second pass to verify the candidate
        count = 0;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }

        return count > nums.length / 2 ? candidate : -1; // Return -1 if no majority element
    }
}
```
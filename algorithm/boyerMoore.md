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

## Explanation of the Boyer–Moore Majority Vote Algorithm
The Boyer–Moore majority vote algorithm works by essentially maintaining a counter for the majority element. As we iterate through the array, we increment the counter when we encounter the majority element and decrement it when we encounter a different element. If the counter ever drops to zero, we set the current element as the new candidate and reset the counter to one.

This process effectively cancels out the minority elements, leaving us with the majority element as the candidate. The reason this works is that the majority element occurs more than n/2 times, so it will always be the last element standing after the cancellation process.

The algorithm consists of two passes through the array. The first pass finds the candidate for the majority element, and the second pass verifies that the candidate is indeed the majority element. The second pass is optional, but it provides a way to confirm that the candidate is the majority element.

## Example Walkthrough
Let's consider an example array: `[3, 2, 3, 4, 3, 3, 2, 3, 3]`. We initialize the candidate to `-1` and the count to `0`.

1. First iteration: `count` is `0`, so we set `candidate` to `3` and `count` to `1`.
2. Second iteration: `num` is `2`, which is different from `candidate`, so we decrement `count` to `0`.
3. Third iteration: `count` is `0`, so we set `candidate` to `3` and `count` to `1`.
4. Fourth iteration: `num` is `4`, which is different from `candidate`, so we decrement `count` to `0`.
5. Fifth iteration: `count` is `0`, so we set `candidate` to `3` and `count` to `1`.
6. Sixth iteration: `num` is `3`, which is the same as `candidate`, so we increment `count` to `2`.
7. Seventh iteration: `num` is `2`, which is different from `candidate`, so we decrement `count` to `1`.
8. Eighth iteration: `num` is `3`, which is the same as `candidate`, so we increment `count` to `2`.
9. Ninth iteration: `num` is `3`, which is the same as `candidate`, so we increment `count` to `3`.

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

## Summary
The Boyer–Moore majority vote algorithm is an efficient method for finding the majority element in an array. It operates in linear time and uses constant space, making it a practical choice for large datasets.
# Binary Search and Partition Loop Conditions Explained

The difference between `while(left <= right)` and `while(left < right)` is fundamental to understanding different binary search and partition implementations.

## The Key Difference

1. `while(left <= right)` - Used when searching in a closed interval `[left, right]`
2. `while(left < right)` - Used when searching in a half-open interval `[left, right)`

## Binary Search Examples

### Standard Binary Search (`left <= right`)

In the standard binary search implementation:

```java
int binary_search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
        // ...
    }
}
```

This uses `left <= right` because:
- We're searching in the closed interval `[left, right]`
- We want to include the case where `left == right` (a single element)
- The search terminates when `left > right` (empty search space)
- It's used when we want to find an exact match and return its index

### Boundary Search (`left < right`)

For lower_bound and upper_bound:

```java
int lower_bound(int[] numbers, int target) {
    int left = 0, right = nums.length;
    while(left < right) {
        // ...
    }
}
```

This uses `left < right` because:
- We're searching in the half-open interval `[left, right)`
- The search terminates when `left == right` (converged to a boundary)
- It's used when we want to find a boundary position (first element ≥ target)
- `right` starts at `nums.length` (one past the end)

## Quick Sort Partition

In the partition function:

```java
while (left <= right) {
    // ...
}
```

This uses `left <= right` because:
- We need to process all elements, including when pointers meet
- When `left == right`, we still need to decide which partition that element belongs to
- The partition is complete when the pointers cross (`left > right`)

## Summary

- Use `while(left <= right)` when:
  - You're searching in a closed interval `[left, right]`
  - You want to find an exact match
  - You need to process all elements, including when pointers meet

- Use `while(left < right)` when:
  - You're searching in a half-open interval `[left, right)`
  - You're looking for a boundary position
  - You want the algorithm to terminate when the pointers converge

The choice depends on your specific algorithm's requirements and the interval representation you're using.
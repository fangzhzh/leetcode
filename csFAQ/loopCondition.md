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


# Understanding Right Boundary Updates in Binary Search

When examining different binary search implementations, you'll notice two common patterns for updating the right boundary:

## `right = mid` vs `right = mid - 1`

The choice between these two approaches depends on the loop condition and interval representation:

### When using `right = mid - 1`:

This is typically used with the `while (left <= right)` loop condition and closed interval `[left, right]` representation:

```java
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] > target) {
        right = mid - 1;  // Exclude mid from next search
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        return mid;  // Found target
    }
}
```

In this case:
- We're working with a closed interval `[left, right]`
- If `nums[mid] > target`, we know the target must be in `[left, mid-1]`
- We exclude `mid` from the next search because we've already checked it
- This approach ensures the search space shrinks by at least one element in each iteration

### When using `right = mid`:

This is typically used with the `while (left < right)` loop condition and half-open interval `[left, right)` representation:
j
```java
while (left < right) {
    int mid = left + (right - left) / 2;
    if (nums[mid] >= target) {
        right = mid;  // Include mid in next search
    } else {
        left = mid + 1;
    }
}
```

In this case:
- We're working with a half-open interval `[left, right)`
- If `nums[mid] >= target`, the boundary we're looking for could be at `mid`
- We include `mid` in the next search because it might be the answer
- This approach is often used for finding boundaries (like lower_bound)

## Key Considerations

1. **Consistency**: Match your boundary updates with your loop condition and interval representation
2. **Convergence**: Ensure your algorithm will eventually terminate
3. **Off-by-one errors**: Be careful about inclusive vs. exclusive bounds

The choice ultimately depends on what you're trying to achieve with your binary search implementation.

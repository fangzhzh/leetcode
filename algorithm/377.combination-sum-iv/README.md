
```
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.
```


the math equation
`dp[target] = sum(dp[target-nums[i]]) where 0 <= i < nums.length, and target >= nums[i]`

Explaination:
We need find the #dp[target], let's take a look at the cases where `nums[i] < target`.

for dp[i], 
- we add `nums[i]` to the beginning of the dp[target-nums[i]]
- for other num in nums, we put the num also in the begging of a combination

They forms the combinations of nums in all posibilities.



```
   1 - 1 - 1- 0
       /
     1 - 2 - 0
   ／ 
 1 － 2 - 1 - 0
   \
    3 - 0              ==> total ways = 7
 
     1 - 1 - 0
   /
 2 - 2 - 0
 
 3 - 1 - 0
```


## keywords
- combination
- order

## Relavant question
- `add coins to a target` serial 
    + https://leetcode.com/problems/coin-change
    + https://leetcode.com/problems/coin-change-2


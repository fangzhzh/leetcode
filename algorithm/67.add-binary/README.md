Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

```
Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
```


As same as the decimal add.
```
i + j + carry
if(sum > base) {
    result += sum - 2
    carray = 1
} else {
    result += sum
}
```
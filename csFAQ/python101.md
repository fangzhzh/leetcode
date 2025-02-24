# Python's data types?
* Numeric types: int, float, complex
* Sequence types: list, tuple, range
* Text type: str
* Set types: set, frozenset
* Mapping type: dict
* Boolean type: bool
* Binary types: bytes, bytearray, memoryview

## Exmaple
### revert a string
```python
def reverse_string(s):
    return s[::-1]
```
### counting chars in a word
```python
def counting_char(s):
    d = {};
    for char in s:
        d[char] = d.get(char, 0) + 1
    return d;
```

### Fibonacci Series
```python
def fibo(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibo(n-1) + fibo(n-2)
```
### Merge Two Sorted Lists
```python
def mergeTwoLists(l1, l2):
    result = []
    i = j = 0
    while i < len(l1) and j < len(l2):
        if(l1[0] < l2[0]):
            result.append()
```        



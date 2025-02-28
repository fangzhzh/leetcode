# Python's data types?
## Numeric types: int, float, complex
## Integer.MAX and Integer.min
* Integer.MAX_VALUE: float('inf')
* Integer.MIN_VALUE: float('-inf')
## Sequence types: list, tuple, range
### Tuple
`a = (1,2,3)`
* Immutable

### Declaration of a list
In Python, there are several ways to create a list. Here are the common methods:

1. Using square brackets (most common):
```python
my_list = []  # Empty list
my_list = [1, 2, 3]  # List with elements
```

2. Using the `list()` constructor:
```python
my_list = list()  # Empty list
my_list = list((1, 2, 3))  # Convert tuple to list
my_list = list("abc")  # Convert string to list of characters: ['a', 'b', 'c']
```

3. List comprehension:
```python
my_list = [x for x in range(5)]  # Creates [0, 1, 2, 3, 4]
my_list = [x*2 for x in range(3)]  # Creates [0, 2, 4]
```

4. Multiplication for repeated elements:
```python
my_list = [0] * 5  # Creates [0, 0, 0, 0, 0]
my_list = [1, 2] * 3  # Creates [1, 2, 1, 2, 1, 2]
```

5. Using `range()` converted to list:
```python
my_list = list(range(5))  # Creates [0, 1, 2, 3, 4]
my_list = list(range(2, 5))  # Creates [2, 3, 4]
```
### two dimentional list/array
```python
# list[3, 2]
list = [[1,2] for _ in range(3)] #[[1,2], [1,2], [1,2]]

# list variable
rows, cols = 3, 2
array_2d = [[0 for _ in range(cols)] for _ in range(rows)]

```
These methods can be used based on your specific needs, such as creating empty lists, lists with initial values, or lists with computed values.
## Text type: str
## Set types: set, frozenset
```python
# Init
my_set = set()  # Note: my_set = {} creates an empty dict, not a set!

# Set from list
my_set = set([1, 2, 2, 3, 3])  # Creates {1, 2, 3}

# Set literal
my_set = {1, 2, 2, 3, 3}  # Creates {1, 2, 3}

# add element
my_set.add(1)  # Still {1, 2, 3, 4, 5}
# Remove elements
my_set.remove(1)  # Removes 1, raises KeyError if not found
my_set.discard(2)  # Removes 2, no error if not found
my_set.pop()  # Removes and returns an arbitrary element
my_set.clear()  # Removes all elements
# count
count = len(my_set)  # Returns 3

```
## Mapping type: dict
```python
# empty
cache = {}
# init
parent = {x: x for x in range(n)}
```
## Boolean type: bool
## Binary types: bytes, bytearray, memoryview

## string/list slicing notiation
**Python slicing creates new list**

* s[start:stop:step]  # Extracts elements from index `start` to `stop-1`
* Negative indices count from the end (s[-1] is the last element).

* s[-k:]
    * start: end -k
    * end: end
    * result: Last k
* s[:-k] 
    * start: 0
    * end: end -k
    * result: all except last k
* s[::-1]    
    * start: 0
    * end: end
    * step: -1
    * result: start becomes to end, and end became start, from end to start, reversed string "abc"[::-1] => "cba"


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

### iterator all neighbors of a cell in matrix
```python
direction = [[-1,0],[1,0],[0,-1],[0,1]]
for i in range(4):
    new_x = x + direction[i][0]
    new_y = y + direction[i][1]
    if 0 <= new_x < n and 0 <= new_y < m:
        # do something

```

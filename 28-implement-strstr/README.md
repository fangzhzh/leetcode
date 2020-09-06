implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

 

Constraints:

haystack and needle consist only of lowercase English characters.


## This question is excellent, and differentiate candidate very well.

First of all, it's easy to come up a solution by brutal force. Two loop and it's done.

However, when the string loop is envoleved, the boundary checking is a pain.

Why?

- emptry string?
- useless compare(too much)
- out of boundary(edge case)
- end too soon(too little)

Solution compare:

## 28-implement-strstr.js
This approach has to
- has to check the empty string
- has to complicated by a extra carry j
- has to check the boundary in two loop, it's easy to be wrong, one of my failed solution is, it will miss a case `"a", "a"`
```javascript
    for(let i = 0; i < haystack.length - needle.length; i++) {
        let j = 0;
        for(; j < needle.length; j++) {
            //...
        }
```

## Solution3.java
Let take a look at the solution, it's super smart, 
- it take care of the empty string
- it take care of the boundary checking
- it removed the usage of extra j


I'll definitely to try this in other string solution to remove an extra edge emptry string check.

```java
        for(int i = 0; ; i++) {
            for(int j = 0; ; j++) {
                if(j==needle.length()) return i;
                if(i+j==haystack.length()) return -1;
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
            }
        }
```
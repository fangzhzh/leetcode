# 字符串

字符串的问题千变万化，可能会是dp， stack，dfs，栈，回文，这里会列一些经典问题

## 字符串扫描+操作

**res = last_res + last_modify(res)**
For stack we need to store last_res and last_modify, but for some question like 1190, the last modify is always reverse, then we need only one stack.

```java
private String stringScanAndModify(String s) {
        // res = last_res + last_modify(res)
        // stack[[last_res, last_modify], [last_res, last_modify]]
        Stack<String> stack = new Stack<>();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ?) { // 进栈开始条件
                stack.push(res.toString());
                res.setLength(0);
            } else if(c == ')') { // 出栈条件
                last_modify(res);
                res = last_res + last_modify(res);
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
```

* [394.字符串解码](./394.decode-string/394.md)
    * res = last_res + mult * last_res
* [1190. 反转每对括号间的子串](./394.decode-string/394.md)
    * res = last_res + last_res.reverse()


## 可以构造的的最长XXX长度
当问最XXX的串长度的时候，题目没有要求你去构造这个最长串，目的是理解这个XXX的特点，然后直接用这个字符串的所有字符去做数学知识运算

* 409. Longest Palindrome 最长回文串

    例子“abccccdd”，回文串的定义，是字符串从头到尾的顺序和从尾到头的读起来是相同的。

    那么怎么利用这个特性呢，那字符串去构造所有可能回文串，然后求出最长的长度吗？如果真是这种解法，就需要n*(n-1)/2种可能性，是一个组合问题，时间复杂度就为O(n^2)

    我们可以重回文串这个定义出发，去parse这个字符串。
    
    当我们构建一个回文串需要怎样。如果我们有偶数个相同字符，那么这些偶数个字符必然能构成一个回文串。
    那么奇数的字符怎么处理呢。考虑`aba`这个例子，偶数个字符的最中间，仍然有一个位置给奇数个字符。

    那么我们的算法可以从定义出发，找出所有的偶数字符，这些是回文串的一部分，然后如果有单数，那么放回文串最中间。

    算法可以写为

    ```
        map => char -> cnt
        string.filter(map(c) % 2 == 0) + string.any(map(c) %2 != 0)
    ```



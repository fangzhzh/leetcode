# 字符串

字符串的问题千变万化，可能会是dp， stack，dfs，栈，回文，这里会列一些经典问题



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




## String的一些特性问题

### Palindromic 对称字符串
0. 栈
1. 通用做法需要双指针相向而行shrink，或者双指针背向而行expand
2. 通用做法可以引申出如果是需要找出所有的Palindromic，就需要dfs的shrink或者expand

#### Anagram
1. 这个通用做法就是哈希表计算
2. 如果要找出所有，也是方法1的沿伸。slicing window来keep一个哈希表，通过增减个数来确定遇到有效的Anagram

### 括号
有效括号是一类题目
* [判断有效20. Valid Parentheses](https://leetcode-cn.com/problems/valid-parentheses)
* [判断有效 32. Longest Valid Parentheses](https://leetcode-cn.com/problems/longest-valid-parentheses)
* [移除使其有效 301. Remove Invalid Parentheses](https://leetcode-cn.com/problems/remove-invalid-parentheses)
* [移除使其有效 1249. Minimum Remove to Make Valid Parentheses](https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses)

怎么判断括号是有效，思路就是栈，类似消消乐。

怎样移除使之有效，栈的方式就不好用了。这里有个模拟栈的思路，

```java
    int leftRemove = 0;
    int  rigthRemove = 0;
    int len = s.length();
    for(int i  =  0; i < len; i++) {
        char c = s.charAt(i);
        if(c == '(') {
            leftRemove++;
        }  else if(c == ')'){
            if(leftRemove != 0) {
                leftRemove--;
            } else {
                rigthRemove++;
            }
        }
    }
```
拿着计算出来的`leftRemove`和`rigthRemove`,dfs即可计算出所有valid string。这就是301题。

但是如果我们需要找出一个，最小移除的呢。我们仍然需要按照刚才的思路，不同的是，我们不需要计算有几个需要移除，我们找出移除哪个。

下边是一种贪心的算法，可以得出正确解。

    使用 *栈*，每次遇到 "("，都将它的索引压入栈中。每次遇到 ")"，都从栈中移除一个索引，用该 ")" 与栈顶的 "(" 匹配。栈的深度等于余量。需要执行以下操作：

    如果在栈为空时遇到 ")"，则删除该 ")"，防止余量为负。
    如果扫描到字符串结尾有 "("，则删除它，防止余量不为 0。

著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```java
Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indexesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }

```
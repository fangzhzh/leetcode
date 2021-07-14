# 字符串扫描+操作

字符串扫描+操作的公式

**res = last_res + last_modify(res)**

## 栈解法模板

这样的题目，我们一般需要两个栈，一个储存上次的字符串，另一个上次的操作。但是如果有的题目操作都是一样的话，我们只需要一个栈


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
* 1190.反转每对括号间的子串
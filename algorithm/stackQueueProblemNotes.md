# stack queue 栈队列错题本

## 227. Basic Calculator II

    Example 1:

    Input: s = "3+2*2"
    Output: 7
    Example 2:

    Input: s = " 3/2 "
    Output: 1
    Example 3:

    Input: s = " 3+5 / 2 "
    Output: 5

### 错误解法1 
这个解法不能找出多位数，assume只有个位数

```java
class Solution {
    public int calculate(String s) {
        // 32+3*5+24

        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int curNum = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c>='0'&&c<='9') {
                curNum = curNum*10 + c-'0';
            } else if(c=='+'||c=='-' || c== '*' || c == '/') {
                if(!ops.isEmpty()) {
                        if(ops.peek() == '*') {
                            int top = nums.pop();
                            int second = nums.pop();
                            nums.push(top*second);
                            ops.pop();
                            break;
                        } 

                        if(ops.peek() == '/') {
                            int top = nums.pop();
                            int second = nums.pop();
                            nums.push(second/top);
                            ops.pop();
                            break;
                        }
                } 
                nums.push(curNum);
                ops.push(c);
                curNum = 0;
            }
            switch(c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    int num = c-'0';
                    if(!ops.isEmpty()) {
                        if(ops.peek() == '*') {
                            num *= nums.pop();
                            nums.push(num);
                            ops.pop();
                            break;
                        } 

                        if(ops.peek() == '/') {
                            num = nums.pop() / num;
                            nums.push(num);
                            ops.pop();
                            break;
                        }
                    } 
                    nums.push(num);
                }
                
                    break;
                case '+':
                case '-':
                case '*':
                case '/':
                    ops.push(c);
                    break;
                default: 
                    break;
            }
        }
        while(!ops.isEmpty()) {
            int num = nums.pop();
            char c = ops.pop();
            if(c=='+') {
                nums.push(num + nums.pop());
            }
        }
        return nums.pop();
    }
}
```

### 错误解法2
这个解法不能正确跟踪栈的数值的数目

```java
class Solution {
    public int calculate(String s) {
        // 32+3*5+24

        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int curNum = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c>='0'&&c<='9') {
                curNum = curNum*10 + c-'0';
            } else if(c=='+'||c=='-' || c== '*' || c == '/') {
                if(!ops.isEmpty()) {
                        if(ops.peek() == '*') {
                            int top = nums.pop();
                            int second = nums.pop();
                            nums.push(top*second);
                            ops.pop();
                            break;
                        } 

                        if(ops.peek() == '/') {
                            int top = nums.pop();
                            int second = nums.pop();
                            nums.push(second/top);
                            ops.pop();
                            break;
                        }
                } 
                nums.push(curNum);
                ops.push(c);
                curNum = 0;
            }
        }
        while(!ops.isEmpty()) {
            int num = nums.pop();
            char c = ops.pop();
            if(c=='+') {
                nums.push(num + nums.pop());
            }
        }
        return nums.pop();
    }
}
```
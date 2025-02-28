# Monotonic Stack
## Algorithm Idea
1. A monotonic stack is a stack that maintains elements in either strictly increasing or strictly decreasing order
   - For next greater element: maintain decreasing order (pop when current > stack top)
   - For next smaller element: maintain increasing order (pop when current < stack top)

2. Key Operations:
   - When pushing a new element:
     * Compare with stack top
     * Pop elements that violate the order
     * Push new element
   
3. Common Patterns:
   ```python
   # Next Greater Element pattern
   stack = []  # stores indices
   for i in range(len(arr)):
       # Keep popping while current element is greater than stack top
       while stack and arr[i] > arr[stack[-1]]:
           idx = stack.pop()
           result[idx] = arr[i]  # arr[i] is the next greater element
       stack.append(i)
```
```
## 单调栈用来解决一种问题, Next Greater/Smaller Element, previous greater/smaller element

这种问题共享一个特征，在数组里**寻找左边/右边的更大/更小的下一个元素**.

以下一个更大元素为例，从图的角度看，某一个元素的next greater element就是从该点出发的下一个山顶元素，如果从该点出发是下坡，那么该点没有next greater elements.
## APIS
* 实现单调栈，实现四种不同的单调栈，分别找到每个元素左边/右边第一个比其大/小的元素
```java
int[] next_greater_element(int[] arr) {
    //[4,5,2,10,8]
    // res: [5, 10, 10, -1, 8]
    Stack<Integer> stack = new Stack<>();
    int[] result = new int[arr.length];
    for(int i = 0; i < arr.length; i++>) { // reverse order to find previous greater/smaller element
        // arr[stack.peek()] < arr[i]: next greater
        // arr[stack.peek()] > arr[i]: next smaller
        while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
            int idx = stack.pop();
            result[idx] = arr[i];
        }
        stack.push(i);
    }
    return result;
}
/**
------
i = 0
cur:4
stack: [4]
result: [-1, -1, -1, -1, -1]
----
i = 1
cur: 5
stack: [5]
result: [5, -1, -1, -1, -1]
------
i = 2
cur: 2
stack: [5, 2]
result: [5, -1, -1, -1, -1]
-----
i = 3
cur: 10
stack: [10]
result: [5, 10, 10, -1, -1]
-----
i = 4
cur:[10, 8]
stack:
result: [5, 10, 10, -1, -1]
*/
```


## Examples
```java
                  │
                  │
                  │
                 ─┤       ┌─┐
                  │       └─┘
                  │     xx    x
                  │    xx     xx
                  │   xx       x
                 ─┤ ┌─┐        ┌─┐
                  │ └─┘        └─┘x
                  │ xx            xx
                  │xx              xx
                ┌──┐────────────────┬─┬────────────────────
                └──┘  1    2    3   └─┘   5    6     7
                  │                   xx
                 ─┤                    xx┌─┐        ┌─┐
                  │                      └─┘        └─┘
                  │                        xx      xx
                  │                         xx    xx
                 ─┤                          xx┌─┐x
                  │                            └─┘
                  │

```

对图示[0,1,2,1,0,-1,-2,-2]对应的next greater element[1,2,n/a,n/a,n/a,n/a,n/a,n/a]

用代码我们怎么来实现这个解法呢，此处 我们要介绍一种做法叫单调栈。

```java
int[] nextGreaterElement(int<Integer> nums) {
    int[]res = new int[nums.length];
    // 单调栈，从高到低依次入栈，nums[i]小于栈顶元素入栈。
    // 如果nums[i]大于等于栈顶元素，则pop出所有这些元素，直到nums[i]小于栈顶元素，那么nums[i]入栈。
    // 栈永远保持单调递增
    Stack<Integer> stack = new Stack<>();
    for(int i = nums.length-1; i>=0;i--) {
        // 弹出所有小于等于当前元素的元素
        while(!stack.isEmpty() && （nums[i] - stack.peek()）>= 0) {
            stack.pop();
        }
        ans[i] = stack.isEmpty()?-1:stack.peek();
        stack.push(nums[i]); 
    }
    return res;
}
```


## 另一类问题，最长坡度
另一类跟Next Greater Element题目不同，我们不是求某个元素后边更大的元素，而是求对某个element满足某个条件的范围，

仍以此数据为例，我们想要找到的是对每个点的最长坡度(上坡)

```
                  │
                  │
                  │
                 ─┤       ┌─┐
                  │       └─┘
                  │     xx    x
                  │    xx     xx
                  │   xx       x
                 ─┤ ┌─┐        ┌─┐
                  │ └─┘        └─┘x
                  │ xx            xx
                  │xx              xx
                ┌──┐────────────────┬─┬────────────────────
                └──┘  1    2    3   └─┘   5    6     7
                  │                   xx
                 ─┤                    xx┌─┐        ┌─┐
                  │                      └─┘        └─┘
                  │                        xx      xx
                  │                         xx    xx
                 ─┤                          xx┌─┐x
                  │                            └─┘
                  │

```
对图示[0,1,2,1,0,-1,-2,-2] 每个节点对应的最长坡度[2，2，1，1，1，1，2，1]

next greater element 的栈里从下往上存储着所有的greater elemenent，我们在扫描的时候，只需要关心当前的元素能看到的下一个next greater element。

本类题目，我们要求上坡，那么就需要知道一道坡的底在哪里。寻找满足p[j] > p[i] 的最长区间。 虽然我们希望找到的是p的单增区间，但元素比较时是逆序遍历p的，所以我们构造好单调栈需要是单减的；
```
例题：
给出一个数组，元素是+1或者-1。找出和大于零的最长的天数
[1,1,-1,-1,-1,-1,1]

```

```java
class Solution {
    public int longestWPI(int[] hours) {
        //[0, 1,2,1,0,-1,-2,-1]
        int[] presum = new int[hours.length+1];
        presum[0] = 0;
        for(int i = 1; i <= hours.length; i++) {
            presum[i] = presum[i-1]+hours[i-1];
            System.out.println(String.format("presum %1d result %2d", i, presum[i]));

        }
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < presum.length; i++) {
            if(stack.isEmpty() || presum[i] < presum[stack.peek()]) {
                stack.push(i);
                System.out.println(String.format("stack pushed %1d with value%2d", i, presum[i]));

            }
        }

        int ans = 0;
        for(int i  = presum.length-1; i>=0; i--) {
            // presum[i]-presum[j] ==> sum(array, j, i)
            while(!stack.isEmpty() && presum[i] - presum[stack.peek()] > 0]) {
                ans = Math.max(ans, i - stack.pop());
            }
            System.out.println(String.format("%1d result %2d", i, ans);
        }
        return ans;

}

source: [1,1,-1,-1,-1,-1,1]

presum: [0,1,2,1,0,-1,-2,-1]

stack pushed 0 with value 0
stack pushed 5 with value-1
stack pushed 6 with value-2
7 result  1
6 result  0
5 result  0
4 result  0
3 result  3
2 result  0
1 result  0
0 result  0

```



/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 */

// @lc code=start
class MinStack {
    Stack<Integer> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();
    public MinStack() {

    }
    
    public void push(int val) {
        stack.push(val);
        list.add(val);
        Collections.sort(list);
    }
    
    public void pop() {
        int num = stack.pop();
        list.remove((Integer)num);
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return list.get(0);
    }
}
// @lc code=end

// another solution for two stacks
class MinStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    int min = Integer.MAX_VALUE;
    public MinStack() {

    }
    
    public void push(int val) {
        stack.push(val);
        min = Math.min(min, val);
        minStack.push(min);
    }
    
    public void pop() {
        int num = stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
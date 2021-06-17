/*
 * @lc app=leetcode id=225 lang=java
 *
 * [225] Implement Stack using Queues
 */

// @lc code=start
class MyStack {

  
    /** Initialize your data structure here. */
    // 1,2,3,4
    // 
    Queue<Integer> queue0 = new LinkedList<>(); // 存储栈
    Queue<Integer> queue1 = new LinkedList<>(); // 操作辅助栈
    public MyStack() {
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
        while(!queue0.isEmpty()) {
            queue1.offer(queue0.poll());
        }
        Queue<Integer> tmp = queue0;
        queue0 = queue1;
        queue1 =tmp;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue0.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue0.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue0.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
// @lc code=end


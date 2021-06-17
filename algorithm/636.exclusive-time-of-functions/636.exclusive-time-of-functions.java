/*
 * @lc app=leetcode id=636 lang=java
 *
 * [636] Exclusive Time of Functions
 */

// @lc code=start
/**
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.

 

Example 1:


Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 * 
 */

 /**
  * I can't belive I get the right answer the at the first try.
  * Follow the guildline: 
  * - Listen 
  * - Example
  * - Brute force
  * - Optimise
  * - Walk Through
  * - Implement
  * - Test
  */

/**
 * Typical stack problem.
 * Tricky part is that when memorize the last time, it's different in cases where
 * the last is start or end.
 * This is hard to reasoning, but easy to refer from the example
 */
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();
        int lastTime = 0;
        for(int i = 0; i < logs.size(); i++) {
            String tmp = logs.get(i);
            String[] process = tmp.split(":");
            int id = Integer.valueOf(process[0]);
            String status = process[1];
            int time = Integer.valueOf(process[2]);


            if(status.equals("start")) {
                if(!stack.isEmpty()) {
                    int preId = stack.peek();
                    ans[preId] += (time - lastTime);
                }
                lastTime = time;
                stack.push(id);
            } else {
                ans[id] += (time - lastTime + 1);
                lastTime = time+1;
                stack.pop();
            }
        }
        return ans;
    }
}
// @lc code=end


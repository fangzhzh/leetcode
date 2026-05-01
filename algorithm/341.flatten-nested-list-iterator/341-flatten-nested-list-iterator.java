/*
 * @lc app=leetcode lang=java
 *
 * [341] Flatten Nested List Iterator
 */

// @lc code=start
/**
 * Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
 */
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i = nestedList.size()-1; i >=0; i--) {
            var node = nestedList.get(i);
            if(!node.isInteger() && node.getList().size() == 0) {
                continue;
            }
            stack.push(node);
        }
        
    }

    @Override
    public Integer next() {
        if(!hasNext()) {
            return null;
        }
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty() && !stack.peek().isInteger()) {
            var tmp = stack.pop();
            var list = tmp.getList();
            for(int i = list.size()-1; i >=0; i--) {
                var node = list.get(i);
                if(!node.isInteger() && node.getList().size() == 0) {
                    continue;
                }
                stack.push(node);
            }
        }
        return !stack.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

 /**
  * # version one is flatten the list in the constructor and store it.
  * But it's not why they ask a question about a iterator.
  * we have to compute the iterator on the fly.
    */
// @lc code=end

/**
 * This solution is better than the first one because it removed some heavy check logic
 * `if(!node.isInteger() && node.getList().size() == 0) ` this code is to handle some edge case, 
 * but it's not neccessary. It just means the only thinking process is not general enough.
 * 
 * The main point here is isInteger, we don't need to chek empty list, emptry stack, etc.
 * The only valid point for `hasNext()` is that whetherthe first element of stack is integer.
 * True if it's so, other wise, keeps flatting.
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> stack = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i = nestedList.size()-1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            if(stack.peek().isInteger()) {
                return true;
            }
            NestedInteger node = stack.pop();
            if(!node.isInteger()) {
                for(int i = node.getList().size()-1; i >= 0; i--) {
                    NestedInteger tmp = node.getList().get(i);
                    stack.push(tmp);
                }
            }
        }
        return false;
    }
}

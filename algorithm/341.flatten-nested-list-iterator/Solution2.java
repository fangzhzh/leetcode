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
// [[1,1],2,[1,1]], [1,[[1,1],2,[1,2]]]
public class NestedIterator implements Iterator<Integer> {
    Queue<Integer> queue = new LinkedList<>();
    public NestedIterator(List<NestedInteger> nestedList) {
        Stack<NestedInteger> stack = new Stack<>();
        for(int i=0; i<nestedList.size(); ++i) {
            stack.push(nestedList.get(i));
            NestedInteger cur;
            while(!stack.isEmpty()) {
                cur=stack.pop();
                if(cur.isInteger()) {
                    queue.offer(cur.getInteger());
                } else {
                    for(int j = cur.getList().size()-1; j>=0; --j) {
                        stack.push(cur.getList().get(j));
                    }
                }
            }
        }
    }

    @Override
    public Integer next() {
        return queue.poll();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

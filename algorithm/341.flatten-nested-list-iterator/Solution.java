
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

	Stack<Integer> si = new Stack<>();
    public NestedIterator(List<NestedInteger> nestedList) {
		Stack<NestedInteger> s = new Stack<>();
		for(NestedInteger in : nestedList) {
			s.push(in);
		}
		while(!s.isEmpty()) {
			NestedInteger cur = s.pop();
			if(cur.isInteger()) si.push(cur.getInteger());
			else {
				for(NestedInteger in : cur.getList()){
					s.push(in));
				}
			}
		}
		
    }

    @Override
    public Integer next() {
       return si.pop();
    }

    @Override
    public boolean hasNext() {
		return !si.isEmpty();
        
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

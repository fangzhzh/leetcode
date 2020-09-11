
```
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:



BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

```

## A very clever and clean code

```
 while(!stack.isEmpty() || cur != null) {
    if(cur != null) {
        stack.push(cur);
        cur = cur.left;
    } else {
        cur = stack.pop();
        int value = cur.val;
        cur = cur.right;
        return value;
    }
}
return 0;
```

It's no doubt a stack problem, but it's easy to come up a while in while 

```
// while push to the far left
while(!stack.isEmpty()) {
    pop()
    visit()
    if(right != null) {
        cur = cur.right;
        // while push to the far left again
    }
}

```

this approach is edge case to edge case
- to the far left
- visit
- go to right
- to the far left
- to step visit

The better solution is generalize the solution, it doesn't care specifically whether a note is left or right. It cares whether it's null. 
- cur is null?
- no, stack.push(cur) && cur = cur.left
- yes, pop(), cur = cur.right

It's far more elegant and easy to understand, easy to mind debug.
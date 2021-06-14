# Tree
Binary Tree, Binary Search tree

## API
* `preTraveral(root).forEach(x-> visit(x))`
* `inTraveral(root).forEach(x-> visit(x))`
* `postTraveral(root).forEach(x-> visit(x))`
* `levelTraveral(root).forEach(x-> visit(x))`
## Binary Tree

### representation of a tree

![representation of a tree](./graphs/binaryTreeRepresent.drawio.svg)

* LinkedList
```mermaid

graph LR
  subgraph PostOrder ;
	right2[right] --> root2[root];
	left2[left] --> right2[right];
  end;
  subgraph InOrder;
	left --> root;
	root --> right;
  end;
  subgraph PreOrder;
	root1[root] --> left1[left];
	left1[left] --> right1[right];
  end;

```
* Array
```mermaid

graph LR
  subgraph PostOrder;
	left2[left] -.- right2[right];
	right2[right] -.- root2[root];
  end;
  subgraph InOrder;
	left -.- root;
	root -.- right;
  end;
  subgraph PreOrder;
	root1[root] -.- left1[left];
	left1[left] -.- right1[right];
  end;

```

### Tree Traveral

![traversal](./graphs/binaryTreeTraversal.drawio.svg)

```
/* binary tree travesal */
void traverse(TreeNode root) {
    // preOrder
    traverse(root.left)
    // inOrder
    traverse(root.right)
    // postOrder
}
```
For the recursive solution, the main point is to find out what need to happen to one node.


### Breadth First Search
level 

```java
void bfs(Node root) {
    Queue queue = new Queue();
    queue.offer(root);

    while(!queue.isEmpty()){
        Node node = (Node)queue.poll();

        if(node.getLeft() != null){
            queue.offer(node.getLeft());
        }

        if(node.getRight() != null){
            queue.offer(node.getRight());
        }
    }
}

```

### Depth First Search	

General signature of DFS solution

`dfs(node)`

`dfs(node, x, y, z) where x, y, z are elements helping achieve dfs result of node`


#### preOrder, inOrder, postOrder
 * [Better post order traversal](x-devonthink-item://1A56BAC0-62CC-4DC6-A20E-A0ACB1E6213E)


```java
    // preOrder is 
    void preOrderDFS(Node root) {
        if(root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node node = (Node)stack.pop();

            Node left = node.getLeft();
            Node right = node.getRight();

            if(right != null){
                stack.push(right);
            }

            if(left != null){
                stack.push(left);
            }
        }
    }

// usually we denote BST using inOrder traversal
    void inOrderDFS(Node root) {
        if(root == null)
            return;

        Stack<Node> stack = new Stack<>();
        Node p = root;

        while(!stack.isEmpty() || p != null){
            if(p != null){ // if it is not null, push to stack and go down the tree to left
                stack.push(p);
                p = p.left;
            } else { // if no left child pop stack, process the node then let p point to the right
                Node temp = (Node)stack.pop();
                visit(temp);
                p = temp.right;
            }
        }
    }

// post-Order
// post-order is widely use in mathematical expression. It is easier to write a program to parse a post-order expression. Here is an example
// in-order is also ok, but need to fiture out the priority of operation, post-order honor the operator priority already.

    iterativePostorder(node)
        s ← empty stack
        lastNodeVisited ← null
        while (not s.isEmpty() or node ≠ null)
            if (node ≠ null)
            s.push(node)
            node ← node.left
            else
            peekNode ← s.peek()
            // if right child exists and traversing node
            // from left child, then move right
            if (peekNode.right ≠ null and lastNodeVisited ≠ peekNode.right)
                node ← peekNode.right
            else
                visit(peekNode)
                lastNodeVisited ← s.pop()

    void postDFS(Node root) {
        Node node = root;
        Stack<Node> stack = new Stack<>();
        Node lastVisited = null;
        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node peekNode = stack.peek();
            // if right child exists and traversing node
            // from left child, then move right
                if(peekNode.right != null && lastVisited != peekNode.right) {
                    node = peekNode.right;
                } else {
                    visit(peekNode);
                    lastVisited = stack.pop();
                }
            }
        }


    }

```  



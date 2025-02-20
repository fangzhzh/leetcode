# Morris Traversal

二叉树的递归/非递归遍历一般解法都需要空间负载度O(h), h是二叉树的深度。

下边介绍一种空间复杂度尾O(1)的二叉树遍历算法，Morris Traversal, Morris遍历。

在递归/非递归算法中，我们用栈/递归来记录上下层的关系，而Morris另辟蹊径，用节点的null指针来临时构建层次关系。

## Morris 遍历 思想
Idea in one word "Thread". 穿针引线。

Idea in one setence "Visit, Thread, Move, Restore."

* Thread Creation: For each node, if it has a left child, the algorithm finds the rightmost node in the left subtree (the predecessor) and creates a temporary link (thread) from this node to the current node.
* Traversal: The algorithm visits the current node before moving to its left child. If there is no left child, it simply visits the current node and moves to the right child.
* Restoration: Once the left subtree is fully traversed, the algorithm removes the temporary links, restoring the original tree structure.

## Moriis 遍历 算法
设当前节点为`cur`, 初始化`cur=head`,

1. 若 `cur == null`,过程停止，否则；
2. 若 `cur` 无左子树，令 `cur = cur.right`；
3. 若 `cur` 有左子树，则找 `cur`左子树上最右的节点，计作`mostRight`
    1. 若 `mostRight.right == null`, 则令 `mostRight.right = cur`,然后`cur = cur.left`
    1. 若 `mostRight.right == cur`, 则令 `mostRight.right = null`, 然后 `cur = cur.rights`


## 例子
```
          4
        /   \
    2           6
  /   \       /   \
1       3   5       7
```



根据规则Morris Traversal的遍历如下
1. 初始状态
1. `cur`来到节点4，`cur`此时有左子树，左子树最右节点`3`的右指针指向`null`，那么让其指向`cur`，然后`cur = cur.left`（规则3.1）；
1. `cur`来到节点2，`cur`此时有左子树，左子树最右节点`1`的右指针指向`null`，那么让其指向`cur`，然后`cur = cur.left` (规则3.1)
1. `cur`来到节点1，`cur`此时没有左子树，`cur = cur.right`
1. `cur`来到节点2，`cur`有左子树，左子树最右节点`1`的右指针指向`cur`，则令`1`的右指针指向`null`，然后`cur = cur.right` (规则3.2)
1. `cur`来到节点3，`cur`没有左子树，`cur = cur.right`（规则2）
1. `cur`来到节点4，`cur`有左子树，左子树最右节点`3`的右指针指向`cur`，令`3`的右指针指向`null`，然后`cur=cur.right`(规则3.2)
1. `cur`来到节点6，`cur`有左子树，`cur`最右节点`5`的右指针指向`null`，那么让其指向`cur`，然后`cur = cur.left`（规则3.1）
1. `cur`来到节点5，`cur`没有左子树，`cur = cur.right`（规则2）
1. `cur`来到节点6，`cur`有左子树，`cur`的最右节点`5`的最右指针指向`cur`，令`5`的右指针指向`null`，然后`cur = cur.right`（规则3.2）
1. `cur`来到节点7，`cur`没有左子树，令`cur = cur.right`
1. `cur==null`，算法结束

图示

![morris Traversal ](./graphs/morrisTraversal.drawio.svg)


## 参考
```java
public class Solution {
  List<TreeNode> morisTraverse(TreeNode root) {
    TreeNode cur = root, mostRight = null;
    List<TreeNode> result = new ArrayList<>();
    while(cur != null) {
      if(root.left != null) {
        mostRight = findMostRight(cur);
        if(mostRight.right == null) {
        result.add(cur);
          mostRight.right = cur;
          cur = cur.left;
        } else {
          mostRight.right = null;
          cur = cur.right;
        }
      } else {
        result.add(cur);
        cur = cur.right;
      }
    }
    return result;
}
```

```java
public class Solution {
    public List<TreeNode> morrisPreOrder(TreeNode root) {
        TreeNode cur = root;
        List<TreeNode> result = new ArrayList<>();
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur); // Visit the current node
                cur = cur.right; // Move to the right child
            } else {
                TreeNode mostRight = findMostRight(cur);
                if (mostRight.right == null) {
                    result.add(cur); // Visit the current node
                    mostRight.right = cur; // Create a thread
                    cur = cur.left; // Move to the left child
                } else {
                    mostRight.right = null; // Remove the thread
                    cur = cur.right; // Move to the right child
                }
            }
        }
        return result;
    }
}

public class Solution {
    public List<TreeNode> morrisInOrder(TreeNode root) {
        TreeNode cur = root;
        List<TreeNode> result = new ArrayList<>();
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur); // Visit the current node
                cur = cur.right; // Move to the right child
            } else {
                TreeNode mostRight = findMostRight(cur);
                if (mostRight.right == null) {
                    mostRight.right = cur; // Create a thread
                    cur = cur.left; // Move to the left child
                } else {
                    mostRight.right = null; // Remove the thread
                    result.add(cur); // Visit the current node
                    cur = cur.right; // Move to the right child
                }
            }
        }
        return result;
    }
}
```
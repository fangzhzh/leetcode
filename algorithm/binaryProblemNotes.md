# 二叉树的题目


## 236.二叉树的最近公共祖先
```
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

示例 1：

            3
          /   \
          5    1
        /  \  / \
        6   2 0  8
           /\
           7 4

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 

输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。

```

1. 理解题目
    祖先，并且自己可以是自己的祖先。那么问题就是找**两个节点**的最近**公共祖先**。

2. 拟定方案
    1. 后序遍历
    2. 带状态
        * 可以是boolean，以当前节点为根的子树，是否包含p或者q
        * 当前节点为根的子树包含的target值，p/q如果包含这个节点，null如果不包含
    3. 判断条件
        * 返回值是boolean
            * lson && rson，两边都有一个节点，那么当前节点x就是最近公共祖先
            * (lson || rson) (p == x || q == x)
        * 返回值找到的p/q节点
            * (p==x || q == x) return x;
            * lson != null && rson != null，当前节点即是最近公共祖先
            * if(lson == null) return rson
            * if(rson == null) return lson
    
3. 错误的思路复盘
    * 首先想到的，印象中这个题目有一个题解，是一个递归函数，三个指针进去，返回一个指针，用这个指针做一些判断
    * 那么这个算法是什么，试试写写，卡壳，或者写出来答案不对，最终卡壳
    * 没弄明白，递归找的是什么，返回值是什么，
    * 正确解参考拟定方案的

4. 错误的思路复盘2
    * 也想到了用额外的数据结构来存储节点，选择了list，存储的顺序是层序遍历，子节点和父节点的关系就是 iChild/2=iParent
    * 但是两个节点到祖父这块，没有找到正确的思路 while(ip != iq) {ip = ip/2; iq = iq/2;}
    * 两个并没有同层次的关系，所以题目找到的答案不对。
    * 如果按照题解，有一个visited，while(ip != 0) {visited[ip] = true; ip = ip/2},然后 while(iq != 0) {if(visited[iq]) found; iq = ip/2;}

    此思路的错误有以下几点
    * 因为不是满二叉树，所以这个层遍历的顺序，并不能保证iChild/2=iParent
        * 因为一旦中间某层少了节点，那么child parent关系就不再是iChild/2=iParent
        * 所以错误解
    * 而且child/parent关系有更好的天然表达，`Map<TreeNode, TreeNode>`

5. 错误的Time Big O复盘
    * Time O(nLogn)
    * Space O(LogN)
    * 因为看到二叉树，一个父节点，然后去向root.left, root.right，就会想到O(logN)或者O(NlogN)
    
    此思路的错误是因为没有真正理解为什么二叉树的一些算法是O(logN)
    * 不是所有的二叉树操作都是天然O(logN)
    * BST的search是O(logN)，是因为每次递增，都剪掉一半，总共的遍历，只遍历了O(logN)这么多层
    * 而本题里，见题解，虽然对某个节点，我们依次遍历root.left, root.right, 但是所有的节点都会被遍历一遍，所以O(n)




### 题解

```java
    // Time O(N)
    // Space O(N) recursive
    // 思路1: 后序遍历，从子树遍历完到父节点带状态出来，状态是当前root节点为根的左右子树里，找到的p/q值
    private TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q) {
            return root;
        }
        if(root != null) {
            TreeNode lNode = lowestCommonAncestor(root.left, p, q);
            TreeNode rNode = lowestCommonAncestor(root.right, p, q);
            if(lNode != null && rNode != null) {
                return root;
            } else if(lNode == null) {
                return rNode;
            } else {
                return lNode;
            }
        }
        return null;
    }
```


## 662.二叉树最大宽度

```
给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。

示例 1:

输入: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
示例 2:

输入: 

          1
         /  
        3    
       / \       
      5   3     

输出: 2
解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
```

1. 错误思路复盘1
    * 直接level遍历，然后每次遍历level时，用size更新最大值
    * 这个解法是错误的，为什么，因为这个树不一定是满二叉树，所以level的size不是level的宽度

    * 如果是 index，就要计算并且记忆化  iLeftChild = iParent*2, iRightChild = iParent * 2 +1


2. 错误思路复盘2
    * 错误2基于错误1，把所有的null全部都填进去，构造一个满二叉树
    * 计算层时，left=第一个非空节点，right=最后一个非null节点，宽度就等于right-left+1
    * TLE，想象一个只有右子树的10层线性二叉树，总共10个节点，却需要构造1+2+。。。+2^10~=2048个节点，显然太多无效计算
3. 错误思路复盘3
    * 错误思路3，又是基于错误思路2，填入null节点以后，废弃节点太多，无效计算太多，那我来直接计算
    * 左边直接尽量往左走，右边尽量往右走，拿到每次走到几点的index，每层计算一下right-left+1,
    * 这个解决了部分问题，就是最左节点和最右节点能形成最优解，但是想象一个中间瘦的模型,最左节点和最右节点都不在最下层的6 7 8 0

```
          1
         / \  
        3   9 
       / \       
      5   3
         /\     
         8 9
        /\ /\
       6 7 8 9
```

### Take away
    * 更进一步，二叉树，数节点来计算个数永远不是一个好主意，到处都可能有null节点，null节点下边就不再有孩子，那么null节点下边的所有子树在计算个数时都会漏掉
    * 如果是parent，就要用map
    * 如果是 index，就要计算并且记忆化  iLeftChild = iParent*2, iRightChild = iParent * 2 +1

### 理解    
    * 更重要的的一点，我的思路，一般想个简单方案，出现纰漏，增加逻辑fix这个纰漏，
    * 再出现更高级的纰漏，改变做法，fix那个纰漏
    * 这种做法，很容易陷入到陷阱里
    * 应该
        * 理解题目，已知条件，为止条件
        * 拟定方案，说服自己，跑测试用例


### 题解


```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        Deque<NodeWithIndex> deque = new LinkedList<>();
        deque.offer(new NodeWithIndex(root, 1));
        int ans = 0;
        while(!deque.isEmpty()) {
            if(deque.peekLast().index - deque.peekFirst().index + 1 > ans) {
                ans = deque.peekLast().index - deque.peekFirst().index + 1;
            }
            int len = deque.size();
            while(len > 0) {
                NodeWithIndex node = deque.poll();
                if(node.node.left != null) {
                    deque.offer(new NodeWithIndex(node.node.left, node.index*2));
                }
                if(node.node.right != null) {
                    deque.offer(new NodeWithIndex(node.node.right, node.index*2+1));
                }
                len--;
            }
        }
        return ans;
    }
    class NodeWithIndex {
        TreeNode node;
        int index;
        NodeWithIndex(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }

    }

}
```


## 116.填充每个节点的下一个右侧节点指针

```
给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

           1
         /   \
        2     3
       / \    /\  
      4   5   6 7 

           1
         /    \
        2  -->  3
       / \     /  \  
      4 -> 5-> 6-> 7 

进阶：

你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 
例：

输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
```

1. 卡壳思路1
    * 当作递归时，能想到root.left.next = root.right,但是没办法知道root.right.next 指向哪里，特别是5-->6跨子树的时候
    ```
        if(root.right != null) {
            root.left.next = root.right;
            root.right.next = ?
        }
    ```
    * 卡壳的迷思就是困在 已知条件 ，和未知条件那里。从题目的已知条件出发，要找到跨子树root.right.next，
        * 要么要层级遍历，但是递归又做不到层级遍历
        * 要么带着一个prev在递归里，递归解法，因为是dfs，深入到第三层6以后，想要知道他的prev是5，基本上也很难做到，
        * 如果此处能够更进一步的观察题解，其实在处理root.right.next时，root.next已经被赋值，root.right.next就是root.next.left
        * 如果观察到这一点，把一路上求得的解，从未知变成已知，题目就迎刃而解。

2. 卡壳思路2
    * 第一个尝试时，在循环里，试图 
    ``` 
        Node first = queue.poll();
        NOd second = queue.poll();
        first.next = second;
        first.right = second?.left
    ```
    * 这个题解最终无法解决first.right和second.left在不同子树的情况，即second和下一个first的情况

### 题解
```java
class Solution {
    public Node connect(Node root) {
        // 理解题目: 完美二叉树，下一个右侧指针。未知：下一个右侧节点
        // 重新阐述: 找出节点右侧关系
        //      left -> right
        //      right -> left of parent's right^.left 
        //      node -> null 
        return connectPre(root);
    }

    Node connectPre(Node root) {
        if(root == null) return null;
        if(root.right != null) {
            root.left.next = root.right;
            root.right.next = (root.next != null)?root.next.left:null;
        }
        connectPre(root.left);
        connectPre(root.right);
        return root;
    }

    Node connectLevel(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node prev = null;
            int len = queue.size();
            while(len > 0) {
                Node node = queue.poll();
                if(prev != null) {
                    prev.next = node;
                    prev =  node;
                } else {
                    prev = node;
                }
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
                len--;
            }
        }
        return root;
    }


}
```    
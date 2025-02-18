# DFS Return Value 

## 定义
树/图的dfs，分决策和回溯。在设计函数的时候，求解的问题会影响我们设计的函数。

如果求的值是和递归的深入有线性关系，那么这个值就可以直接根据公式计算得出 ans = calc(root) + calc(root.children), 每个dfs的返回值也是 calc(root) + calc(root.children)
如果求的值不是和递归有线性关系，而是整个递归的某一部分，那么值就不能够有节点和其孩子直接求的。 这时calc(root) + calc(root.children)可能就变成了求值的一部分。 this.ans = calc(root) +calc(root.children). 但是函数的返回值就变为 calc(root) + select(calc(root.children)) 这里select可能是符合要求的一部分，但不能是全部，否则的话就会蜕变为第一种方案。

## 例题
很多题目需要dfs计算某个值，然后一番计算之后,跟节点和所有子节点的值某种计算就是最值。

例题112.Path Sum
```java
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        // if(targetSum - root.val < 0) return false;
        if(targetSum - root.val == 0 && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
```




但是也有类似的题目，缺需要一个global，在dfs过城中，需要不断的更新中这个值

例题:543. Diameter of Binary Tree

```java
class Solution {
    int max = 0; // globa值
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameterOfBinaryTree(root);
        return max-1;
    }
    int maxDiameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;
        int left = maxDiameterOfBinaryTree(root.left);
        int right = maxDiameterOfBinaryTree(root.right);
        // 不断更新这个值
        if(left+right+1 > max) {
            max = left+right+1;
        }
        return Math.max(left, right)+1;
    }
}
```

这两个题型的区别是

## 总结

1. 是要求的值会随着递归的深度而不断增加，比如求最大深度，所以可以直接计算得出
2. 是要求的值不一定是递归深度越大值越大，而是要在遍历的过程中记录最大值，然后返回最大值，比如最大路径和
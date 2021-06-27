# DFS

深度优先搜索是一种在回退之前尽可能深入每个分支的遍历算法。深度优先+回溯


解决一个回溯问题，实际上就是一个决策树的遍历过程

1. 路径：也就是已经做出的选择。

2. 选择列表：也就是你当前可以做的选择。

3. 结束条件：也就是到达决策树底层，无法再做选择的条件

```
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择

// backtrack就是图的traversal

void traverse(TreeNode root) {
    for (TreeNode child : root.childern)
        // 前序遍历需要的操作
        traverse(child);
        // 后序遍历需要的操作
}
```


for example, 1087. 花括号展开
 {a,b}c{d,e}f to ["acdf","acef","bcdf","bcef"]

 ![](./graphs/1087.drawio.svg)


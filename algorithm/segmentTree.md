# 线段树
线段树（Segment Tree）：一种基于分治思想的二叉树，用于在区间上进行信息统计



![segment tree](https://qcdn.itcharge.cn/images/20240511173328.png)


> 经典问题：给出数列[1 4 2 3],求给定区间的最大值 例:query(0,1) = 4 query(2,3) = 3 query(0,3) = 4

当有n个元素时，线段树可以在 O(log N)的时间复杂度内实现单点修改、区间修改、区间查询（区间求和，求区间最大值，求区间最小值）等操作。

| 数据结构 | 更新 | 查询 |
|----| ----|----|
| 数组 | O (n)| O(n)| 
| 线段树 |O(Log N) |O(log N) | 

## Reference
* [线段树知识](https://algo.itcharge.cn/07.Tree/03.Segment-Tree/01.Segment-Tree/)
* [线段树入门问题](https://github.com/ninechapter-algorithm/leetcode-linghu-templete/blob/master/%E7%AE%97%E6%B3%95%E4%B8%8E%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84/%E7%BA%BF%E6%AE%B5%E6%A0%91%E7%9F%A5%E8%AF%86%E7%82%B9%E6%80%BB%E7%BB%93.md)
* [线段树
](https://github.com/DuHouAn/Java/blob/master/docs/data_structure/11_%E7%BA%BF%E6%AE%B5%E6%A0%91.md)
* [线段树 - 多组图带你从头到尾彻底理解线段树](https://www.cnblogs.com/RioTian/p/13409694.html)
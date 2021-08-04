# leetcode
## 算法复盘
1. 理解题目
    * 未知量
    * 已知数据
    * 条件
    * 重新阐述
2. 拟定方案
3. SSH 方法
    1. 完全没有思路 SSH方法
        * Similar：找到解决过的相似题目：
        * Smaller：解决一道更简单的题目
        * High level：假定子问题已经被解决
    1. 做过的题做不出来
        * 需要进行总结和回顾算法以及数据结构
    1. 看不懂题解
        * 先从简单和中等的题目开始，有的题目可能涉及比较多子问题或者比较多数据结构，需要懂得拆分问题。
    1. 有思路但是写不对
        * 一般情况下是思路不够清晰，一些细的关键点没有想清楚，或者对于编程语言的熟悉不够
    1. 题目总结和回顾
        * 每类题目要总结出API
    1. 思路覆盘
        * 自己的思路卡在哪里    
## 🔀 排序算法
* [排序算法与其无处不在的应用](./sort.md)

## ☯️ 递归
* [递归基础与API](./recursive.md)
## 分治

## 🌀 Linked List
链表可以先提出使用额外空间的暴力解，拿到基本分，再优化到常数空间的解
* [链表基础与API](./LinkedList.md)
* [快慢指针](./LinkedListFastSlow.md)
* [移动链表节点总结](./LinkedListMoving.md)
## ✌️ Binary Tree
* [二叉树基础与API](./binaryTree.md)
* [二叉树错题本](./binaryProblemNotes.md)
## 🔎 Binary Search 
* [二分查找与API](./binarySearch.md)

## 🔟󠀽󠀽⏭️2️⃣ 位运算
* [位运算](./bits.md)
## 📚 栈，队列，Stack Queue Deque
* [栈列表API](./stackQueueAPI.md)
* [栈列表与经典题目的图解](./stackQueue.md)
### 单调栈
* [单调栈API](./monotoneStackAPI.md)
## 📏Hash/double pointers/sliding windows
* [哈希表和双指针与滑动窗口](./hashTwoPointers.md)

## 💹 动态规划
* [动态规划基础](./dynamicProgramming.md)
## 🔡 字符串
* [字符串](./string.md)
* [字符串扫描并操作](./stringScanAndModify.md)

## 数组Array
* [数组及其API][./arrayAPI.md]
## 🗺️ 图论
* [图论基础](./graph.md)
* [图论DFS错题本](./graphDFSProblemNotes.md)
## 📱 矩阵
* [矩阵及API](./matrix.md)

## 数学 Math
* [数学题目基础及API](./mathAPI.md)
## Miscs

* 递归和动态规划的一些共同点
递归相关的词汇，recursive, top down, buttom up, BFS, DFS, DFS+backtrack,DFS+(pre-order, in-order+postorder + level)

dp相关的词汇， top down, buttom up, tabulation or memoization

dp+recursive，leetcode半壁江山

* 一些符号的读法
    * a' a prime
    * () parentheses
    * [] bracket
    * {} curly bracket/braces

* int加减乘除的题目处理溢出

    处理溢出情况，通常要处理下次迭代溢出， x > MAX_VALUE || x < MIN_VALUE
    比如，7 整数反转这个题目，每次迭代就是 x *= 10， 所以 这里的条件是
    res < Integer.MIN_VALUE/10 || res > Integer.MAX_VALUE / 10

## ⚙️ 难题
* 1631.最小体力消耗路径
* 721.账户合并

## 代表问题
* [394. 字符串解码](./394.decode-string/394.md)
    * 递归，dfs

## 面经
[FB面经](./FB面经.md)

## 模板
![模板API](./codeAPI.md)
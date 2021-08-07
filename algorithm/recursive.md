# Recursive
理清父问题和子问题的关系。

看一个问题
* 能不能分解成子问题
* 如果能，递归。
* 关系是什么,
    * 父依赖子 top down, DFS+memo / stack + memo / dynamic programming
    * 子依赖父 bottom up, BFS+memo / queue / dynamic programming

tabulation or memoization
 
## 遍历方式
### DFS
#### buttom up
子依赖父
#### top down
父依赖子

### BSF LEVEL

## 参数与返回值
### 参数
一般有能够递归需要的的变量，和当前的结果

### 返回值
* 可以是要求的值，比如最大。。。的个数，那么返回值是int
* 可以是void，需要的值要更新参数列表的某个object，或者更新某个全局的变量，
    * List<Integer> ans
    * this.max = Math.max(this.max, curValue)
* 在上一种情况下，返回值也可以用做计算全局变量
    * this.max = Math.max(this.max, curValue+dfs(root.left) + dfs(root.right));

## 例题
```java
1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

 

示例 1：

输入：text1 = "abcde", text2 = "ace" 
输出：3  
解释：最长公共子序列是 "ace" ，它的长度为 3 。
示例 2：

输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
示例 3：

输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。


class Solution {
/*
* 能不能分解成子问题
* 如果能，递归。
* 关系是什么,
    * 父依赖子 top down, DFS (memo)optional / stack + memo / dynamic programming
    * 子依赖父 bottom up, BFS (memo)optional / queue
*/
    public int longestCommonSubsequence(String text1, String text2) {
        Map<Integer, Integer> cache = new HashMap<>();
        // return longestCommonSubsequenceTopDownDFSMemo(text1, 0, text2, 0, cache);
        // return longestCommonSubsequenceDP(text1, text2);
        return longestCommonSubsequenceBFS(text1, 0, text2, 0, 0);
    }

    // Time O(m^2*n^2)
    // Space O(m*n)
    // can add memo to decrease redundent calculation, but it doesn't decrease complexity
    private int longestCommonSubsequenceBFS(String s, int i, String t, int j, int level) {
        if(j >= t.length() || i >= s.length()) return level;
        char c1 = s.charAt(i);
        char c2 = t.charAt(j);
        int newLevel = 0;
        if(c1 == c2) {
            newLevel = longestCommonSubsequenceBFS(s, i+1, t, j+1, level + 1);
        } else {
            // 一个问题变成两个子问题，所以Time O(m^2 * n^2)
            newLevel = Math.max(
                longestCommonSubsequenceBFS(s, i+1, t, j, level),
                longestCommonSubsequenceBFS(s, i, t, j+1, level)
            );
        }
        return newLevel;
    }

    private int longestCommonSubsequenceBFSQueue(String s, String t) {
        // queue 也是可行的
        Queue<int[]> queue = new LinkedList<>();
        queue.push(new int[]{i*10000+j, level});

    }

    // Time O(m*n)
    // Space O(m*n)
    private int longestCommonSubsequenceDP(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int i = 1; i < s.length()+1; i++) {
            for(int j = 1; j < t.length()+1; j++) {
                if(s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(
                        dp[i-1][j],
                            dp[i][j-1]
                        );
                }
            }
        }
        return dp[s.length()][t.length()];
    }
    // Time O(m^2*n^2)
    // Space O(m*n)
    private int longestCommonSubsequenceTopDownDFSMemo(String text1, int i, String text2, int j, Map<Integer, Integer> cache) {
        if(j >= text2.length() || i >= text1.length()) return 0;
        int key = i*10000+j;
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        int cnt = 0;
        if(text1.charAt(i) == text2.charAt(j)) {
            cnt =  1 + longestCommonSubsequenceTopDownDFSMemo(text1, i+1, text2, j+1, cache);
        } else {
            // 一个问题变成两个子问题，所以Time O(m^2 * n^2)
            cnt =  Math.max(longestCommonSubsequenceTopDownDFSMemo(text1, i+1, text2, j, cache),
            Math.max(longestCommonSubsequenceTopDownDFSMemo(text1,i, text2,j+1, cache),
            longestCommonSubsequenceTopDownDFSMemo(text1,i+1, text2,j+1, cache)));
        }
        cache.put(key, cnt);
        return cnt;

    }
}
```

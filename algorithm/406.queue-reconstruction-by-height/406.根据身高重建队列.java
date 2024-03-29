/*
 * @lc app=leetcode.cn id=406 lang=java
 *
 * [406] 根据身高重建队列
 */

// @lc code=start
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // people[i] = [h, k]
        // h是高度，k是当前前面正好k个身高大于等于k的人(高的人都在前边)
        // 假设[0,i-1]已经排好，插入i的时候，如果第i个身高小于[0,i-1]，那么i的插入不会影响[0,i-1]
        // 如果i的身高高于[0, i-1]，那么就会影响排序

        // 自然而然我们考虑从高往低来处理
        // [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
        // [[7,0],[7,1],[6,1],[5,2],[5,0],[4,4]]

        // 然后看怎么生成新的队列
        // 插入 [7,0]: [7,0]
        // 插入 [7,1]: [7,0], [7, 1]
        // 插入 [6,1]: [7,0], [6,1], [7, 1] 因为6小于7，所以把[6,1]插入不影响以及排好的顺序
        // 插入 [5,0]: [5,0], [7,0], [6,1], [5,2], [7,1], [5,0]插入最前边，谁都不影响
        // 插入 [5,2]: [5,0], [7,0], [5,2], [6,1], [5,2], [7,1], 同样的，[5,2]插入不影响[7,1], 也不影响[6,1]
        // 插入 [4,4]: [5,0], [7,0], [5,2], [6,1], [4,4], [5,2], [7,1], 同样的，[5,0]插入最前边，谁都不影响
        int n = people.length;
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        List<int[]> res = new ArrayList<>();
        for(var p : people) {
            res.add(p[1], p);
        }
        return res.toArray(new int[people.length][2]);
    }
}
// @lc code=end


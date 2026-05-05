/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 *
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen
 * numbers sum to target. The same number may be chosen unlimited times.
 *
 * Example:
 *   Input: candidates = [2,3,6,7], target = 7
 *   Output: [[2,2,3],[7]]
 */

// @lc code=start

// ============================================================
// Version 1: Binary Choice DFS (pick or skip)
// ============================================================
// The key insight is the "pick or skip" pattern at each index:
//   pick: add candidates[idx], recurse with same idx (allow reuse)
//   skip: don't add, move to idx+1
//
//   list.add(candidates[idx]);
//   helper(ans, list, candidates, target - candidates[idx], idx);  // pick again
//   list.remove(list.size() - 1);
//   helper(ans, list, candidates, target, idx + 1);                // skip

class Solution1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(ans, new ArrayList<>(), candidates, target, 0);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> list,
                        int[] candidates, int target, int idx) {
        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || idx >= candidates.length) {
            return;
        }

        // Pick candidates[idx] (can reuse same index)
        list.add(candidates[idx]);
        helper(ans, list, candidates, target - candidates[idx], idx);
        list.remove(list.size() - 1);

        // Skip candidates[idx], move forward
        helper(ans, list, candidates, target, idx + 1);
    }
}

// ============================================================
// Version 2: For-loop DFS with sort + pruning (preferred)
// ============================================================
// Sort first to enable early termination (break instead of continue).
// The for-loop naturally expresses "choose from remaining candidates".
// Once candidates[i] > target, all subsequent are also too large → break.
//
// TC: O(n^target) worst case (bounded by 150 combinations per problem constraints)
// SC: O(target / min_candidate) for recursion depth

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        helper(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void helper(int[] candidates, int index, int target,
                        List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (candidates[i] > target) break;  // pruning: sorted, no need to continue
            cur.add(candidates[i]);
            helper(candidates, i, target - candidates[i], cur, res);
            cur.remove(cur.size() - 1);
        }
    }
}
// @lc code=end


/*
// @lcpr case=start
// [2,3,6,7]\n7\n
// @lcpr case=end

// @lcpr case=start
// [2,3,5]\n8\n
// @lcpr case=end

// @lcpr case=start
// [2]\n1\n
// @lcpr case=end
*/

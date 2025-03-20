/*
 * @lc app=leetcode id=134 lang=java
 *
 * [134] Gas Station
 */

// @lc code=start
// greedy 
// 1. Total gas >= 0, there could be a possible solution 
// 2. i->j, if curGas < 0, then all the stations between i and j could not be the start station, 可以反证
// 3. follow 2, if i is not the start station, reset start as i+1
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
       int totalGas = 0;
       int curGas = 0;
       int index = 0;
       for(int i = 0; i < gas.length; i++) {
        totalGas += (gas[i]-cost[i]);
        curGas += (gas[i]-cost[i]);
        if(curGas < 0) {
            curGas = 0;
            index = i+1;
        }
       }
       return totalGas >= 0 ? index : -1;
    }
}
// @lc code=end


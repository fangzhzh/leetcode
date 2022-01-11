/*
 * @lc app=leetcode.cn id=933 lang=java
 *
 * [933] 最近的请求次数
 */

// @lc code=start
class RecentCounter {
    // SC O(Q) TC O(P) P is number of ping, Q is max window of pings

    Queue<Integer> queue = new LinkedList<>();
    public RecentCounter() {
    }
    
    public int ping(int t) {
        queue.offer(t);
        while(t - queue.peek() > 3000) {
            queue.poll();
        }
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
// @lc code=end


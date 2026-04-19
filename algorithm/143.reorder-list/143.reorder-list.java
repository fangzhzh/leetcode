/*
 * @lc app=leetcode id=143 lang=java
 * @lcpr version=30403
 *
 * [143] Reorder List
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;
        ListNode sec = reverse(null, second);
        
        ListNode curHead = head;
        ListNode curSec = sec;
        while(curHead != null && curSec != null) {
            ListNode next = curHead.next;
            ListNode secNext = curSec.next;
            curHead.next = curSec;
            curSec.next = next;
            curHead = next;
            curSec = secNext;
        }
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if(cur == null) return prev;
        ListNode next = cur.next;
        cur.next = prev;
        return reverse(cur, next);
    }
}
// @lc code=end



/*
// @lcpr case=start
// [1,2,3,4]\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,4,5]\n
// @lcpr case=end

 */

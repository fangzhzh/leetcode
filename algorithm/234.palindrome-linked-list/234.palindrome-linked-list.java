/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
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

/**
 * Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true

Follow up:

Could you do it in O(n) time and O(1) space?
 */ 

/**
 * brutal force, list of int, then compare
 * 
 * Here is a trap,
 * List<Integer>, if the num is less than -128, or greater then 127,
 * it will be boxed, so that
 * list.get(i) != list.get(len-1-i) is false for -129, ... -xxx
 * 
 * but for num greater equal then -128, like -128, 0
 */ 

/**
 * O(n) space, O(n) time
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        list.clear();
        ListNode node = head;
        while(node != null) {
            list.add(node.val);
            node = node.next;
        }

        int len = list.size();

        for(int i = 0; i < len/2; i++) {
            int a = list.get(i);
            int b = list.get(len-1-i);
            if(a != b) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

/** 
 * O(1) space means no extra spaces List<> is used.
 * Then it has to be compare in place
 * 
 * We scan the linked list with two pointers, `slow` go one step at one time, 
 * `fast` two steps one time
 * When the scan finishes, slow is in middle, and fast is at the end
 * 
 * head-> ... -> slow is the first half, slow-> ... -> fast is the second half.
 * 
 * Then we need a way to compare these two lists isPalindrome.
 * Only way is to reverse the second half, 
 * head -> ... -> slow, fast -> ... -> slow
 * Then we just relax and compare one by one until two pointer meet
 * 
 * Some rare cases handles needed though, [], null when fast and slow, null when compare.
*/

class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if(fast != null && fast.next != null) {
                fast = fast.next;
            }
        }
        ListNode second = revert(slow, fast);
        ListNode forw = head, backw = second;
        while(forw != null && backw != null && forw != backw) {
            if(forw.val != backw.val) {
                return false;
            }
            forw = forw.next;
            backw = backw.next;
        }
       revert(second, slow);
        return true;
    }
    ListNode revert(ListNode head, ListNode end) {
        ListNode node = head;
        ListNode parent = null;
        while(node != null) {
            ListNode tmp = node.next;
            node.next = parent;
            parent = node;
            node = tmp;
        }

        return end;
    }
}


class Solution {
    // method1 stack Time O(n), Space O(n)
    // linked list to list, two pinter to check
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }
        for(int i = 0, j = list.size()-1; i < j; i++, j--) {
            if(list.get(i) != list.get(j)) return false;
        }
        return true;
    }


    // method2 stirng Time O(n), Space O(n), n char
    // linked list to list, two pinter to check
    public boolean isPalindrome(ListNode head) {
        StringBuilder builder = new StringBuilder();
        while(head != null) {
            builder.append(head.val);
            head = head.next;
        }
        char[] chars = builder.toString().toCharArray();
        for(int i = 0, j = chars.length-1; i < j; i++, j--) {
            if(chars[i] != chars[j]) return false;
        }
        return true;
    }

    // method 3, recursive
    // time O(n)
    // space O(n), o(1) extra space, but O(n) stack
    private ListNode front;
    public boolean isPalindrome(ListNode head) {
        front = head;
        return isPalindromeRecursive(head);

    }
    private boolean isPalindromeRecursive(ListNode cur) {
        if(cur == null) return true;
        // go to end
        if(!isPalindromeRecursive(cur.next)) {
            return false;
        }
        if(cur.val != front.val) {
            return false;
        }
        front = front.next;
        return true;
    }

    // method 4, slow and fast pointer
    // Time O(n), multiple round, but every round iteration is O(n)
    // Sapce O(1)
    public boolean isPalindrome(ListNode head) {
        // find first half end
        ListNode slow = head, fast = head;
        // this check is very tricky
        while(fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // reverse second half
        ListNode secondHead = reverseList(null, slow.next);

        // compare first half and second half
        ListNode firstPointer = head;
        ListNode secondPointer = secondHead;
        boolean isPalindrome = true;
        while(firstPointer != null && secondPointer != null) {
            if(firstPointer.val != secondPointer.val) {
                isPalindrome = false;
                break;
            }
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }
        // reverse second half back
        reverseList(null, secondHead);
        return isPalindrome;
    }
    private ListNode reverseList(ListNode parent, ListNode node) {
        if(node == null) return parent;
        ListNode next = node.next;
        node.next = parent;
        return reverseList(node, next);
    }

}
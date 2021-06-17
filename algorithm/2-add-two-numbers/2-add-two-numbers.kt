
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    l1 ?: return l2
    l2 ?: return l1;
    // 2->4->3 + 5->6->4
    // 7 -> 0 -> 8

    // 5->7->5->5 + 5->7->5
    // 0 -> 5 -> 1 -> 6
    val head = ListNode(-1)
    var carry = 0
    var cur = head
    var curL1 = l1
    var curL2 = l2
    while (curL1 != null && curL2 != null) {
        val sum = curL1.`val` + curL2.`val` + carry
        carry = if (added > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL1 = curL1.next
        curL2 = curL2.next
    }
    while (curL1 != null) {
        val sum = curL1.`val` + carry
        carry = if (added > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL1 = curL1.next
    }
    while (curL2 != null) {
        val sum = curL2.`val` + carry
        carry = if (added > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL2 = curL2.next
    }
    if (carry > 0) {
        cur.next = ListNode(carry)
    }
    return head.next
}

/*
Accepted
1563/1563 cases passed (412 ms)
Your runtime beats 20.13 % of kotlin submissions
Your memory usage beats 26.77 % of kotlin submissions (47.8 MB)
*/



// version 2, remove the ussage of /
fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
        return l2
    }
    if (l2 == null) {
        return l1
    }
    // 2->4->3 + 5->6->4
    // 7 -> 0 -> 8

    // 5->7->5->5 + 5->7->5
    // 0 -> 5 -> 1 -> 6
    val head = ListNode(-1)
    var carry = 0
    var cur = head
    var curL1 = l1
    var curL2 = l2
    while (curL1 != null && curL2 != null) {
        val sum = curL1.`val` + curL2.`val` + carry
        carry = if (sum > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL1 = curL1.next
        curL2 = curL2.next
    }
    while (curL1 != null) {
        val sum = curL1.`val` + carry
        carry = if (added > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL1 = curL1.next
    }
    while (curL2 != null) {
        val sum = curL2.`val` + carry
        carry = if (added > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL2 = curL2.next
    }
    if (carry > 0) {
        cur.next = ListNode(carry)
    }
    return head.next
}

/*
Accepted
1563/1563 cases passed (220 ms)
Your runtime beats 81.37 % of kotlin submissions
Your memory usage beats 87.15 % of kotlin submissions (38.9 MB)
*/




fun addTwoNumbers2(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
        return l2
    }
    if (l2 == null) {
        return l1
    }
    // 2->4->3 + 5->6->4
    // 7 -> 0 -> 8

    // 5->7->5->5 + 5->7->5
    // 0 -> 5 -> 1 -> 6
    val head = ListNode(-1)
    var carry = 0
    var cur = head
    var curL1 = l1
    var curL2 = l2
    while (curL1 != null || curL2 != null || carry != 0) {
        val sum = (curL1?.`val`?:0 + curL2?.`val`?:0 + carry
        carry = if (sum > 9) 1 else 0
        cur.next = ListNode(sum % 10)
        cur = cur.next!!
        curL1 = curL1?.next
        curL2 = curL2?.next
    }

    return head.next
}

/*
Accepted
1563/1563 cases passed (368 ms)
Your runtime beats 33.83 % of kotlin submissions
Your memory usage beats 21.64 % of kotlin submissions (48.7 MB)
 */
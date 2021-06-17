class Solution {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if(l1 == null) {
            return l2
        }
        if(l2 == null) {
            return l1
        }
        var node1 = l1
        var node2 = l2
        var newList: ListNode? = if(l1.`val` < l2.`val`) {
            node1 = node1!!.next
            l1
        } else {
            node2 = node2!!.next
            l2
        }
        var lastNode = newList
        while(node1 != null && node2 != null)  {
            when{
                node1.`val` < node2.`val` -> {
                    lastNode!!.next = node1
                    lastNode = node1
                    node1 = node1.next
                } 
                else -> {
                    lastNode!!.next = node2
                    lastNode = node2
                    node2 = node2.next
                }
            }
        }
        while(node1 != null) {
            lastNode!!.next = node1
            lastNode = node1
            node1 = node1.next
        }
        while(node2 != null) {
            lastNode!!.next = node2
            lastNode = node2
            node2 = node2.next
        }
        return newList
    }
}

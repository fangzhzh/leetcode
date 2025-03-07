#
# @lc app=leetcode id=21 lang=python3
#
# [21] Merge Two Sorted Lists
#

# @lc code=start
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        # rever
        def recur() -> Optional[ListNode]:
            list1, list2 = l1, l2
            if list1 is None and list2 is None:
                return None
            if list1  is None:
                return list2
            if list2  is None:
                return list1
            if list1.val < list2.val:
                list1.next = self.mergeTwoLists(list1.next, list2)
                return list1
            else:
                list2.next = self.mergeTwoLists(list1, list2.next)
                return list2
        def iter() -> Optional[ListNode]:
            list1, list2 = l1, l2
            if list1 is None and list2  is None:
                return None
            if list1  is None:
                return list2
            if list2  is None:
                return list1
            head = ListNode(0)
            cur = head
            while list1 and list2:
                if list1.val <= list2.val:
                    cur.next = list1
                    list1 = list1.next
                else:
                    cur.next = list2
                    list2 = list2.next
                cur = cur.next

            cur.next = list1 if list1 else list2
            return head.next
        return recur()
# @lc code=end


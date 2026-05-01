/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	public boolean isPalindrome(ListNode head) {
		List<Integer> l = new ArrayList<>();
		ListNode cur = head;
		while(cur != null) {
			l.add(cur.val);
			cur = cur.next;
		}
		for(int low = 0, high = l.size()-1;
		low < high;
		low++, high--
		) {
			if(l.get(low).intValue() != l.get(high).intValue()) {
//				System.out.println(low+" "+l.get(low)+" is diff with " + high+" " + l.get(high)); 
				return false;
			}
		}
		return true;
	}
}

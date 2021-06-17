#include <iostream>     // std::cout
#include <string>
#include <algorithm>    // std::reverse
#include <vector>       // std::vector
#include <unordered_map>
using namespace std;
 struct ListNode {
     int val;
     ListNode *next;
     ListNode(int x) : val(x), next(NULL) {}
 };
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
		long int i1 = extractStr(l1) ;
		long int i2 = extractStr(l2);
        long int sum = i1 + i2;
		cout << "sum = i1 " << i1 << " + i2 " << i2 << " = " << sum << endl;
        return convertToListNode(sum);
    }
    
    long int extractStr(ListNode* l){
        string s;
        ListNode *curNode = l;
        while(curNode != NULL){
            s.append(to_string(curNode->val));
            curNode = curNode->next;
        }
		reverse(s.begin(), s.end());
		cout << s.c_str() << endl;
        return atol(s.c_str());
    }
    
    ListNode* convertToListNode(long int sum){
        string s = to_string(sum);
        ListNode * head = NULL;
        ListNode * curNode;
        int i = s.size()-1;
        while(i >= 0){
            ListNode *node = new ListNode(s[i]-'0');
            if(head == NULL){
                head = node;
                curNode = head;
            } else{
                curNode->next = node;
                curNode = node;
            }
            --i;
        }
        return head;
    }
};
int main(){
	Solution solution;
	ListNode *head;
	ListNode *head1;
	// head  = solution.convertToListNode(9);
	// head1 = solution.convertToListNode(9999999991);
	head  = solution.convertToListNode(0);
	head1 = solution.convertToListNode(81);
	ListNode *head0 = solution.addTwoNumbers(head, head1);
	ListNode* curNode = head0;
	while(curNode){
		cout << curNode->val  << endl;
		curNode = curNode->next;
	}
}

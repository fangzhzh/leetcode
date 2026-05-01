/*
 * @lc app=leetcode id=721 lang=java
 *
 * [721] Accounts Merge
 */


/**
 * Accounts Merge
Category	Difficulty	Likes	Dislikes
algorithms	Medium (39.77%)	1738	332
Tags
Companies
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation: 
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30]. 
 * 
 * 
 */

/**
 * The key task here is to connect those emails, and this is a perfect use case for union find. 
 * to group these emails, each group need to have a representative, or parent.  
 * At the beginning, set each email as its own representative.
 *
 * during the following looping, update the representative
 *
 * a,b,c
 * d,e,f
 * h,a,d
 *
 * parent initialize
 * a->a
 * b->a
 * c->a
 *
 * d->d
 * e->d
 * f->d
 *
 * h->h
 * a->h
 * b->h
 *
 * parent populating
 * a->a->h
 * b->a->h
 * c->a->h
 *
 * d->d->h
 * e->d->h
 * f->d->
 *
 * h->h
 * a->h
 * b->h
 *
 *
 * unit
 * h-> a, b, c, h, d, e, f, 
 *
 *
 * email grouped
 *
 *
 * Another imporatant knowledge is the TreeSet which is the ordered set
 */
// @lc code=start
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parents = new HashMap<>();
        Map<String, String> owners = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();

        for(List<String> acc: accounts) {
            for(int i = 1; i < acc.size(); i++) {
                parents.put(acc.get(i), acc.get(i));
                owners.put(acc.get(i), acc.get(0));
            }
        }
        for(List<String> acc: accounts) {
            String p = findParent(acc.get(1), parents);
            for(int i = 2; i < acc.size(); i++) {
                parents.put(findParent(acc.get(i), parents), p);
            }
        }
        for(List<String> acc: accounts) {
            String p = findParent(acc.get(1), parents);
            if(!unions.containsKey(p)) {
                unions.put(p, new TreeSet());
            }
            for(int i = 1; i < acc.size(); i++) {
                unions.get(p).add(acc.get(i));
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for(String p : unions.keySet()) {
            List<String> email = new ArrayList(unions.get(p));
            email.add(0, owners.get(p));
            ans.add(email);
        }
        return ans;
    }

    private String findParent(String s, Map<String, String> parents) {
        return parents.get(s) == s ? s : findParent(parents.get(s), parents);
    }
}
// @lc code=end



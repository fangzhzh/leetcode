/*
 * @lc id=127 lang=java
 *
 * [127] Word Ladder
 */

 /**
  * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
  */

/**
 * ## normal bfs solution
 * - Time Limit Exceeded
 * - Typical BFS, loopping, adding and iterating
 * - so it's not friendly to large set of small str
 * 
 * ## another solutino is tow sets in leetcode
 */
// @lc code=start
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = wordList.size();
        List<List<String>> result = new ArrayList<>();
        bfs(result, new ArrayList<String>(), beginWord, endWord, wordList);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < result.size(); i++) {
            if(result.get(i).size() < min) {
                min = result.get(i).size();
            }
        }
        return min==Integer.MAX_VALUE?0:min+1;
    }

    private void bfs(List<List<String>> result, List<String> curList, 
        String beginWord, String endWord, List<String> wordList) {
            if(beginWord.equals(endWord)) {
                result.add(curList);
                return;
            }
            for(int i = 0; i < wordList.size(); i++) {
                String curWord = wordList.get(i);
                if(curWord.equals(beginWord)) {
                    continue;
                }
                if(!curList.contains(curWord)) {
                    if(oneEditAway(beginWord, curWord)) {
                        List<String> newList = new ArrayList<>(curList);
                        newList.add(curWord);
                        bfs(
                            result, newList,
                            curWord, endWord, wordList
                        );
 
                    }
                }
            }
    }
    private boolean oneEditAway(String beginWord, String endWord) {
        int diff = 0;
        for(int i = 0; i < beginWord.length(); i++) {
            if(beginWord.charAt(i) != endWord.charAt(i)) {
                diff++;
            }
        }
        return diff==1;
    }
}
// @lc code=end



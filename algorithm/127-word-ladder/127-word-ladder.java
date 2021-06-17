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


/**
 * BFS
 * put the begin in to the queue,
 * then level traversal the queue, for every word,
 * scan and put all its neighbours into a queue, until we find the end
 *      + how to find the neighbors, instead of caculating distance betwin two word, in the wordList
 *      + we literally try to every char to 'a' to 'z' and get O(26 * lengh(s)) new condidate
 *      + check whether these new word in wordList O(length(s))
 * 
 *      + loop wordList then O(n), where O(n) > O(length(beginWord))
 * 
 * But it's TLE until, until we create a wordDic set, set.contains O(1) 
 * is way better than O(lgN) List.contains.
 */

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int depth = 0;
        Set<String> visited = new HashSet<>();
        Set<String> wordDic = new HashSet<>();
        for(String word: wordList) {
            wordDic.add(word);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for(int i = 0; i < size; i++) {
                String tmp = queue.poll();
                visited.add(tmp);
                System.out.println("visit:" + tmp);
                for (int j = 0; j < tmp.length(); j++) {
                    char[] chars = tmp.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[j] = ch;
                        String word = new String(chars);
                        if (wordDic.contains(word)) {
                            if(visited.contains(word)) {
                                continue;
                            }
                            if(word.compareTo(endWord)==0) {
                                return depth+1;
                            }
                            queue.offer(word);
                            wordDic.remove(word);
                        }
                    }
                }
            }
        }
        return 0;
    }
}
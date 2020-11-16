/*
 * @lc app=leetcode id=745 lang=java
 *
 * [745] Prefix and Suffix Search
 */

 /**
  * 
  Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.

Implement the WordFilter class:

WordFilter(string[] words) Initializes the object with the words in the dictionary.
f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 

Example 1:

Input
["WordFilter", "f"]
[[["apple"]], ["a", "e"]]
Output
[null, 0]

Explanation
WordFilter wordFilter = new WordFilter(["apple"]);
wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 

Constraints:

1 <= words.length <= 15000
1 <= words[i].length <= 10
1 <= prefix.length, suffix.length <= 10
words[i], prefix and suffix consist of lower-case English letters only.
At most 15000 calls will be made to the function f.
  */
// @lc code=start

/**
 * Obvois this is a trie problem
 * 
 * The tricky part here is how do we combind forw and backw
 * 
 * The first attemp is to create two trie, forw and backw, and build them separately by 
 * word and new StringBuilder(word).reverse().toString()
 * 
 * After this solution, the max idx is difficult, forw.search() might not match back.search()
 * Then we make it a set, the complexicity lies beyond the problem itself.
 * 
 * 
 * 
 * Step back, let's review the problem again, suffix and prefix.
 * How about when building trie, we build the every suffix + string into our trie
 * 
 * suffix + "{" + word
 * 
 * The '{' is one char away from 'z', so the children becomes to chilren[27].
 * 
 * Then solution will be easy and clear.
 * 
 * Build trie for every suffix + "{" + word
 * 
 * Then search for suffix + "{" + word
 */
class WordFilter {
    class Trie{
        Trie[] children = new Trie[27];
        int max = -1;
        void add(String word, int idx) {
            Trie trie = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(trie.children[c-'a'] == null) {
                    trie.children[c-'a'] = new Trie();
                }
                trie = trie.children[c-'a'];
                trie.max = Math.max(trie.max, idx);
            }
        }
        int search(String word) {
            Trie trie = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(trie.children[c-'a'] == null) {
                    return -1;
                }
                trie = trie.children[c-'a'];
            }
            return trie.max;
        }
        
    }

    
    Trie forw = new Trie();
    public WordFilter(String[] words) {
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            for(int j = 0; j < word.length(); j++) {
                forw.add(word.substring(j)+"{" + word, i);
            }
        }
    }
    
    public int f(String prefix, String suffix) {
        return forw.search(suffix + "{"+prefix);
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */
// @lc code=end


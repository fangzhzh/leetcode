/**
 * Trie problem.
 * But it's suprisingly difficult to implement.
 * And my coding ability now deteriorates because my focus is now on speed not correct.
 *
 * When I focus on correct, I follows the path: Listen, exmple, brutal force, optimize, walkthrogh
 * But my current coding style is, it looks familiar, it's like a, b, c, let's coding right away, then 
 * - oh my, it's not working
 * - oh my, I missed this, I missed that
 * 
 */
class WordDictionary {
    WordDictionary[] children = new WordDictionary[26];
    boolean isWord = false;
    /** Initialize your data structure here. */
    public WordDictionary() {
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        WordDictionary cur = this;
        for(int i = 0;i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.children[c-'a'] == null) {
                cur.children[c-'a'] = new WordDictionary();
            }
            cur = cur.children[c-'a'];
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        Queue<WordDictionary> queue = new LinkedList();
       queue.offer(this);
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int len = queue.size();
            for(int k = 0; k < len; k++) {
                WordDictionary dic = queue.poll();
                if(c == '.') {
                    for(int j = 0; j < 26; j++) {
                        if(dic.children[j] != null) {
                            queue.offer(dic.children[j]);
                        }
                    }
                } else {
                    WordDictionary child = dic.children[c-'a'];
                    if(child != null) {
                        queue.offer(child);
                    }
                }
            }

        }
        while(!queue.isEmpty()) {
            WordDictionary dic = queue.poll();
            if(dic.isWord) {
                return true;
            }
        }
        return false;
    }

    
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

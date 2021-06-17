public class Solution {
	public List<String> findWords(char[][] board, String[] words) {
		List<String> list = new ArrayList<>();
		TrieNode trie = buildTrie(words);
        for(int i = 0; i < board.length; ++i) {
            for(int j = 0; j < board[0].length; ++j) {
                backtrack(list, trie, board, i, j);
            }
        }
		return list;
    }
    void backtrack(List<String> list, TrieNode trie, char[][] board, int i , int j) {
        char c = board[i][j];
        if(c == '#' || trie.nodes[c-'a'] == null) return;
        int idx = c-'a';
        TrieNode p = trie.nodes[idx];
        if(p.word != null) {
            list.add(p.word);
            p.word = null;
        }
        board[i][j]='#';
        if(i>0) backtrack(list, p, board, i-1, j);
        if(i<board.length-1) backtrack(list, p, board, i+1, j);
        if(j>0) backtrack(list, p, board, i, j-1);
        if(j<board[0].length-1) backtrack(list, p, board, i, j+1);
        board[i][j]=c;
    }

    class TrieNode{
        TrieNode[] nodes = new TrieNode[26];
        String word = null;
    }
    TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        TrieNode cur = root;
        for(int i = 0; i < words.length; ++i) {
            String word = words[i];
            for( int j = 0; j < word.length(); ++j) {
                int idx = word.charAt(j)-'a';
                if(cur.nodes[idx] == null) cur.nodes[idx] = new TrieNode();
                cur = cur.nodes[idx];
                if(j == word.length()-1) cur.word = word;
            }
            cur = root;
        }
        return root;
    }
}

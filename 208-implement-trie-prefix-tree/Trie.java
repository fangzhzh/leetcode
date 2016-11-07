class TrieNode {
	// Initialize your data structure here.
	TrieNode[] nodes= new TrieNode[26];
	boolean isWord;
	public TrieNode() {
		isWord = false;
	}
}
public class Trie {
	private TrieNode root;

	public Trie() {
		root= new TrieNode();
	}
	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode cur = root;
		for(int i = 0; i < word.length();++i) {
			int idx = word.charAt(i)-'a';
			if(cur.nodes[idx] == null) {
				TrieNode node = new TrieNode();
				cur.nodes[idx] = node;
			}
			cur = cur.nodes[idx];
		}
		cur.isWord = true;
	}
	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode cur = root;
		for(int i = 0; i < word.length();++i) {
			int idx = word.charAt(i)-'a';
			if(cur.nodes[idx] == null) {
				return false;
			}
			cur = cur.nodes[idx];
		}
		return cur.isWord;
	}
	
	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode cur = root;
		for(int i = 0; i < prefix.length();++i) {
			int idx = prefix.charAt(i)-'a';
			if(cur.nodes[idx] == null) return false;
			cur = cur.nodes[idx];
		}
		return true;
	}
}
// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key”);

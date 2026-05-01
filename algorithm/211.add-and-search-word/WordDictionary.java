
public class WordDictionary {
	TrieNode root = new TrieNode();
	class TrieNode{
		boolean isWord = false;
		TrieNode[] nodes = new TrieNode[26];
	}

	public void addWord(String word) {
		TrieNode cur = root;
		for(int i=0; i < word.length();++i) {
			int idx = word.charAt(i)-'a';
			if(cur.nodes[idx] == null){
				cur.nodes[idx] = new TrieNode();
			}
			cur = cur.nodes[idx];
		}
		cur.isWord = true;
	}

	public boolean search(String word) {
		TrieNode cur;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(root);
        int size;
		for(int i=0; i < word.length();++i) {
            size = queue.size();
			while(size-->0) {
				cur = queue.poll();
                char c = word.charAt(i);
                if(c=='.'){
                    for(TrieNode node : cur.nodes) {
                        if(node != null) {
                            queue.offer(node);
                        }
                    }
                } else {
                    int idx = c-'a';
                    if(cur.nodes[idx] != null) {
                        queue.offer(cur.nodes[idx]);
                    } 
                }
			}
		}
        while(!queue.isEmpty()) {
            cur = queue.poll();
            if(cur.isWord) return true;
        }
        return false;
	}
}

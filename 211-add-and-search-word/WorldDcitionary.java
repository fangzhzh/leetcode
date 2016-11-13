public class WorldDcitionary {
	TrieNode root = new TrieNode;
	class TrieNode{
		boolean isWord = false;
		TrieNode[] nodes = new TrieNode[26];
	}

	public void addWorld(String word) {
		TrieNode cur = root;
		for(int i=0; i < word.length();++i) {
			int idx = work.charAt(i)-'a';
			if(cur.nodes[idx] == null){
				TrieNode node = new TrieNode();
				cur.nodes[idx] = node;
			}
			cur = cur.nodes[idx];
		}
		cur.isWord = true;
	}

	public boolean search(String word) {
		TrieNode cur = root;
		queue.add(root);
		for(int i=0; i < word.length;++i) {
			int idx = work.charAt(i)-'a';
			Queue<TrieNode> quque = new LinkedList();
			int size = queue.size();
			boolean found = false;
			while(size>0)) {
				TrieNode cur = queue.remove();
				if(cur.nodes[idx] != null) {
					found = true;
					queue.add(cur.nodes[idx];
				}
				size--;
			}
			if(!found) return false;
		}
		return cur.isWord;
	}
}

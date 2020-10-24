/*
 * @lc app=leetcode id=133 lang=java
 *
 * [133] Clone Graph
 */

// @lc code=start
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

/**
 * Typical set, map, new
 * Don't go into indefinite loop
 */

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();

        queue.offer(node);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            if(set.contains(cur.val)) {
                continue;
            }
            set.add(cur.val);
            if(map.get(cur.val) == null) {
                map.put(cur.val, new Node(cur.val));
            }
            Node newNode = map.get(cur.val);
            newNode.neighbors = new ArrayList<>();
            for(Node neighbor: cur.neighbors) {
                if(map.get(neighbor.val) == null) {
                    map.put(neighbor.val, new Node(neighbor.val));
                }
                Node newNeighbor = map.get(neighbor.val);
                newNode.neighbors.add(newNeighbor);
                if(!set.contains(neighbor.val)) {
                    queue.offer(neighbor);
                }

            }
        }
        Node a = map.get(node.val);
        return map.get(node.val);
    }
}

/**
 * Solution 2: Recursive: 92.93 %
 * Suprisingly, the recursive version is always better in runtime
 */
class Solution {
    Map<Integer, Node> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Node newNode = map.get(node.val);
        if(newNode == null) {
            newNode = new Node(node.val, new ArrayList<>());
            map.put(node.val, newNode);
            set.add(node.val);
        }
        for(Node neighbor: node.neighbors) {
            if(!set.contains(neighbor.val)) {
                newNode.neighbors.add(cloneGraph(neighbor));
            } else {
                newNode.neighbors.add(map.get(neighbor.val));
            }
        }
        return newNode;
    }
}// @lc code=end


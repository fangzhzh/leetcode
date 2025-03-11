/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

// @lc code=start
class LRUCache {
    class Node{
        int key, val;

        Node pre, next;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    int capacity;
    int curCapacity;
    Node head, tail;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.curCapacity = 0;
        head = new Node(0, 0);
        tail = new Node(0,0);
        head.next = tail;
        head.pre = null;
        tail.next = null;
        tail.pre = head;
        map = new HashMap<>(capacity);
    }
    private void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        if(pre != null) {
            pre.next = next;
        }
        if(next != null) {
            next.pre = pre;
        }
    }
    private void addToHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }
    public int get(int key) {
        if (map.get(key) != null) {
            Node node =  map.get(key);
            removeNode(node);
            addToHead(node);
            return node.val;
            
        } else {
            return -1;
        } 
    }
    
    public void put(int key, int value) {
        if(map.get(key) != null) {
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            if( curCapacity < capacity) {
                curCapacity++;
                addToHead(node);
            } else {
                map.remove(tail.pre.key);
                removeNode(tail.pre);
                addToHead(node);
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// @lc code=end


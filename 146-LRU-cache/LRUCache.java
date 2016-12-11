import java.util.*;
public class LRUCache {
    int curCapacity;
    int capacity;
    List<Integer> list;
    Map<Integer, Integer> map;
    public LRUCache(int capacity) {
        this.curCapacity = 0;
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        list = new ArrayList<Integer>(capacity);
    }
    public int get(int key) {
        if(!map.containsKey(key)){
            return -1;
        }
        adjust(key);
        return map.get(key);
    }
    void adjust(int key) {
        final int size = curCapacity;
        if(!map.containsKey(key)) {
            if(size < capacity) {
                curCapacity++;
                list.add(key);
            } else {
                map.remove(list.get(0));
                for(int i = 0; i < size-1; i++) {
                    list.set(i, list.get(i+1));
                }
                list.set(size-1, key);
            }
        } else {
            int i = size -1;
            for(; i >= 0; i--) {
                if(list.get(i) == key) {
                    break;
                }
            }
            for(; i < size-1; i++) {
                list.set(i, list.get(i+1));
            }
            list.set(size-1, key);
        }
    }
    public void set(int key, int value) {
        adjust(key);
        map.put(key, value);
    }
}

/**
 *  * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
import java.util.*;
public class Solution {
   public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
       if(node == null ){
           return null;
       }
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        UndirectedGraphNode root = node;
        while(!queue.isEmpty()) {
            root = queue.poll();
            if(map.containsKey(root.label) && map.get(root.label).neighbors.size() >0) {
                continue;
            }
            if(!map.containsKey(root.label)){
                map.put(root.label, new UndirectedGraphNode(root.label));
            }
            UndirectedGraphNode curNew = map.get(root.label);
            for(UndirectedGraphNode cur : root.neighbors) {
                queue.offer(cur);
                if(!map.containsKey(cur.label)) {
                    map.put(cur.label, new UndirectedGraphNode(cur.label));
                }
                UndirectedGraphNode curNeighbor = map.get(cur.label);
                //if(!curNew.neighbors.contains(curNeighbor)){
                    curNew.neighbors.add(curNeighbor);
                //}
            }
        }
        return map.get(node.label);
    }
}

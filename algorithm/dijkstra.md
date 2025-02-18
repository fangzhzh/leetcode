# Dijkstra's Algorithm

## What is Dijkstra's Algorithm?
Think of Dijkstra's algorithm like a GPS finding the shortest route:
- You start at point A and want to reach point B
- At each intersection, you choose the path that minimizes total distance
- You keep track of the shortest known distance to each location

## Key Concepts

### 1. What it Solves
- Finds shortest path from a source node to all other nodes
- Works with weighted edges (distances/costs between nodes)
- Only works with positive weights (use Bellman-Ford for negative weights)

### 2. How it Works (Like a Smart GPS)
1. Start at source node (like your current location)
2. Look at all unvisited neighbors (like checking nearby intersections)
3. Pick the closest one (like choosing the nearest unvisited intersection)
4. Update distances through this node (like recalculating routes)
5. Repeat until all nodes visited

## Visual Example

```
Graph:
A ---4--- B
|         |
2         3
|         |
C ---1--- D

Start from A:

Step 1: At A(init)
Distances: A=0, B=4, C=2, D=∞
Next: Visit C (closest unvisited)

Step 2: At C(first iteration, minNode=2)
Distances: A=0, B=4, C=2, D=3 (through C)
Next: Visit D

Step 3: At D(second iteration, minNode=3)
Distances: A=0, B=4, C=2, D=3
Next: Visit B

Final: Shortest paths from A
To B: 4 (direct)
To C: 2 (direct)
To D: 3 (through C)
```

## Code Template

```java
class Solution {
    public int[] dijkstra(int[][] graph, int source, int n) {
        // distances[i] = shortest distance from source to node i
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // visited[i] = whether node i has been processed
        boolean[] visited = new boolean[n];
        
        // Process all nodes
        for (int i = 0; i < n; i++) {
            // 1. Find unvisited node with minimum distance
            int minNode = findMinDistanceNode(distances, visited);
            visited[minNode] = true;
            
            // 2. Update distances through current node
            for (int j = 0; j < n; j++) {
                if (!visited[j] && 
                    graph[minNode][j] != 0 && 
                    distances[minNode] != Integer.MAX_VALUE &&
                    distances[minNode] + graph[minNode][j] < distances[j]) {
                    
                    distances[j] = distances[minNode] + graph[minNode][j];
                }
            }
        }
        
        return distances;
    }
    
    private int findMinDistanceNode(int[] distances, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minNode = -1;
        
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < min) {
                min = distances[i];
                minNode = i;
            }
        }
        
        return minNode;
    }
}
```

## Optimized Version with Priority Queue

```java
class Solution {
    class Node {
        int id;
        int distance;
        
        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }
    
    public int[] dijkstraWithPQ(List<List<int[]>> graph, int source, int n) {
        // distances[i] = shortest distance from source to node i
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // PQ stores {node, distance} pairs
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.offer(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int node = curr.id;
            int dist = curr.distance;
            
            // Skip if we've found a better path
            if (dist > distances[node]) continue;
            
            // Check all neighbors
            for (int[] neighbor : graph.get(node)) {
                int next = neighbor[0];
                int weight = neighbor[1];
                
                // Update if we found a shorter path
                if (distances[node] + weight < distances[next]) {
                    distances[next] = distances[node] + weight;
                    pq.offer(new Node(next, distances[next]));
                }
            }
        }
        
        return distances;
    }
}
```

## Why Dijkstra's Greedy Approach Works

Dijkstra's algorithm is a greedy algorithm because at each step, it picks the node with the smallest known distance without considering future consequences. But why does this greedy choice lead to the optimal solution?

### Proof by Contradiction
Let's prove why the greedy choice always works:

1. Assume we have found the shortest path to node X (distance = d)
2. Suppose this is NOT actually the shortest path
3. Then there must exist a shorter path through some unvisited node Y
4. But this is impossible because:
   - All edges have positive weights
   - Any path through Y must have length > d (because Y wasn't chosen)
   - Therefore, no shorter path can exist

### Example
```
A ---4--- B
|         |
2         3
|         |
C ---1--- D

At node A:
1. Picks C (distance=2) because it's closest
2. Could picking B (distance=4) lead to a shorter path to C?
   - No! Because:
     * All edges are positive
     * Any path A→B→...→C must be longer than A→C
     * Since A→B alone is 4, which is > A→C (2)
```

### Why It Only Works with Positive Weights
```
A ---2--- B
    ↗   ↙ 
   -3    1
  ↙   ↖
C ---1--- D

With negative weight (-3):
1. A→B (cost=2) is chosen first
2. But A→C→B (cost=-1) is actually shorter!
3. Greedy choice failed because negative edge invalidated our assumption
```

## Common Mistakes to Avoid

1. Using with Negative Weights
   - Dijkstra doesn't work with negative weights
   - Use Bellman-Ford algorithm instead

2. Unnecessary Processing
   - Don't process a node if we've found a better path
   - Use PQ version for better performance

3. Overflow Issues
   - Be careful with Integer.MAX_VALUE
   - Check for overflow when adding distances

## Time Complexity
1. Basic Version: O(V²)
   - Good for dense graphs
   - Simple to implement

2. Priority Queue Version: O(E log V)
   - Better for sparse graphs
   - E = number of edges
   - V = number of vertices

## When to Use
1. Use Dijkstra when:
   - Finding shortest path from one node to all others
   - All weights are positive
   - Graph is weighted

2. Don't use when:
   - Graph has negative weights
   - Need all-pairs shortest paths (use Floyd-Warshall)
   - Graph is unweighted (use BFS instead)

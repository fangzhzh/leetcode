<style type="text/css">
body {
    zoom: 1.2;
    line-height: 1.5;
    padding: 2em 4em;
    font-size: 12px;
    line-height: 1.5;
    max-width: 800px;
    margin: 0 auto;
    word-wrap: break-word;
}

</style>


## Collections

|**interface**|**HashTable**|**Resizable Array** | **Balanced Tree** | Linked List | Hash Table  Linked List |
|----|----|----| ----| --- | --- | 
| Set | HashSet | 				| TreeSet*	|				| LinkedHashSet|
| List | 			| ArrayList |			| LinkedList	|	|
| Queue |			| ArrayDeque |			| LinkedList 	| 	|
| Deque|			| ArrayDequee | 		| LinkedList	|	|
| Map	| HashMap	| 			| TreeMap*(by key)	| 				| LinkedHashMap |	


[*] sorted

## Time Complexity of Data structure
| Data structure	| Access |	Search |	Insertion	|Deletion |
|---|---|---|---|---|
| Array(ArrayList) | 	O(1)	| O(N)	| O(N)	| O(N) | 
| Stack	| O(N)| 	O(N)	| O(1)	| O(1) |
| Queue	| O(N)| 	O(N)	| O(1)	| O(1) |
| Singly Linked list	| O(N)| 	O(N)	| O(1) |	O(1) |
| Doubly Linked List	| O(N)| 	O(N)	| O(1)|	O(1) |
| Hash Table| 	O(1)	| O(1)| 	O(1)	| O(1) |
| Binary Search Tree	| O(log N)	| O(log N)	| O(log N)| 	O(log N)| 
| AVL Tree	| O(log N)	| O(log N)	| O(log N)| 	O(log N) | 
| B Tree	| O(log N)	| O(log N)	|  O(log N)	| O(log N) | 
Red Black Tree| 	O(log N)	| O(log N)	| O(log N)	| O(log N)| 

## initialise List<Integer>

```
List<Integer> list=new ArrayList<>();

List<Integer> llist=new LinkedList<>();

List<Integer> stack=new Stack<>();


// size
List<Integer> list=new ArrayList<>(10);
```

## initialise List<List<Integer>>
- initialise and add
```
	List<List<Integer>> results = new ArrayList<>();
	List<Integer> result = new ArrayList<>();
	result.add(1);
	results.add(result);
```
- initialise and fill with empty
```
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            list.add(new ArrayList<>());
        }
```



## Map
* map.containsKey()
* iterate element:
	* <Map.Entry<Character, Integer> 
	+ List<Map.Entry> cached
	+ Map.Entry<Character, Integer> entry = pq.poll();
	+ entry.getValue()
	+ entry.getKey()
	+ entry.setValue
* iterate keys:
    * keySet
```java
// Using keySet()
Map<String, Integer> map = new HashMap<>();
for (String key : map.keySet()) {
    System.out.println(key + ": " + map.get(key));
}
```
* iterate values:
```java
// Using values()
Map<String, Integer> map = new HashMap<>();
for (Integer value : map.values()) {
    System.out.println(value);
}
```
* iterate entries:
```java
// Using entrySet()
Map<String, Integer> map = new HashMap<>();
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}

or 
for(String key:map.keyset()) {
    // key
    // value: map.get(key)
}
```
### HashMap
* implement Map interface
* HashTable
* Time O(1)
### LinkedHashMap
* implement Map interface
* double-linked List
* Time O(1)

LinkedHashMap is implemented as a doubly-linked list buckets in Java.

### TreeMap
* TreeMap implements the Map, NavigableMap, and SortedMap interface.
* Red-Black Tree
* Time O(log(n)) 
key sorted map

### code


```java
public static void main(String[] args)
    {
        Map<String, String> hm = new LinkedHashMap<>();
 
        hm.put("USA", "Washington");
        hm.put("United Kingdom", "London");
        hm.put("India", "New Delhi");
 
        System.out.println("LinkedHashMap : " + hm);
 
        hm = new TreeMap<>(hm);
        System.out.println("TreeMap : " + hm);
 
        hm = new HashMap<>(hm);
        System.out.println("HashMap : " + hm);
    }
```

The output 
```
LinkedHashMap : {USA=Washington, United Kingdom=London, India=New Delhi}
TreeMap : {India=New Delhi, USA=Washington, United Kingdom=London}
HashMap : {United Kingdom=London, USA=Washington, India=New Delhi}

```

add, remove, containsKey, time complexity is O(log n) where n is number of elements present in TreeMap.

## Stack

```
	Stack<String> stack = new Stack<>();
	stack.push(e);
	stack.pop()
```

## Queue
```
	Queue<TreeNode> queue = new LinkedList();
	queue.offer(e); // add
	queue.poll(); // remove
	queue.peek(); // peak the first
	

```

## Deque
* HEAD VAlue(队列)
    * offerFirst(e)
    * pollFirst
    * peekFirst()
 
* Tail Value(栈)
    * OfferLst
    * pollLast
    * peekLast()

### Priority Queue

`Priority Queue is ordered as per their natural ordering or based on a custom Comparator supplied at the time of creation.`

`The front of the priority queue contains the least element according to the specified ordering, and the rear of the priority queue contains the greatest element.` 
- **min heap**

PriorityQueue<String> queue  = new PriorityQueue<>(length);

To get a guaranteed O(n log n) time for adding n elements you may state the size of your n elements to omit extension of the container: 

`PriorityQueue(int initialCapacity)`

- **enqueing and dequeing methods (offer, poll, remove() and add) is O(log(n))**
- **retrieval methods (peek, element, and size) O(1)**
- **remove(Object) and contains(Object) O(n)**
####
`offer` -> [1,2,3,4,5] -> `poll`
* offer: add an element into the queue, the queue will become a small queue
* poll: Retrieves and removes the head of this queue, or returns null if this queue is empty.
* remove(o): Removes a single instance of the specified element from this queue, if it is present.
* peek: retrives the head of the queue without removing

#### 示例说明：
假设我们有数据：5, 3, 7, 1, 8

1. 小顶堆 中的排列（内部）：[1, 3, 7, 5, 8]
   - peek() 返回 1
   - poll() 移除并返回 1，堆变成 [3, 5, 7, 8]
2. 大顶堆 中的排列（内部）：[8, 5, 7, 1, 3]
   
   - peek() 返回 8
   - poll() 移除并返回 8，堆变成 [7, 5, 3, 1]

#### Priority Queue new comparator

PriorityQueue<ListNode> queue  = new PriorityQueue<>(lists.length, 
        ( a,  b) -> a.val - b.val);

#### PriorityQueue 比较器的简单理解方法

1. **小顶堆**（最小元素优先出队,所以留下的是最大元素）：
   ```java
   // 按照自然顺序排列（小的在前）
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> a - b);
   // 或者更简单地使用默认构造
   PriorityQueue<Integer> minHeap = new PriorityQueue<>();
   ```

2. **大顶堆**（最大元素优先出队，所以留下的是最小元素）：
   ```java
   // 按照逆序排列（大的在前）
   PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
   // 或者更简单使用默认构造器
   PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
   ```

#### 直观理解方法

想象你在**排队**，比较器决定谁站在前面：

- 如果你想让**小的数**排在前面（小顶堆）：`(a, b) -> a - b`
  - 当 a < b 时，返回负数，a 排在 b 前面
  - 当 a > b 时，返回正数，b 排在 a 前面

- 如果你想让**大的数**排在前面（大顶堆）：`(a, b) -> b - a`
  - 当 a < b 时，返回正数，b 排在 a 前面
  - 当 a > b 时，返回负数，a 排在 b 前面

#### 使用 Comparator 方法

如果觉得减法不够直观，可以使用 Comparator 的方法：

```java
// 小顶堆
PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

// 大顶堆
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
```

对于自定义对象，可以这样写：

```java
// 小顶堆 - 按频率升序
PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(node -> node.freq));

// 大顶堆 - 按频率降序
PriorityQueue<Node> maxHeap = new PriorityQueue<>(Comparator.comparing(node -> node.freq, Comparator.reverseOrder()));
// 或者
PriorityQueue<Node> maxHeap = new PriorityQueue<>(Comparator.comparing((Node node) -> node.freq).reversed());
```

## innitial an array

* `int[] result = new int[numCourses];`
	* this will create a new array and initialise it with all 0
* `int[] result = new int[]{1,2,3,4};`
	* this will create a new array of length 4 and intialise with 1,2,3,4
## copy an array
* `System.arraycopy(Object source_arr, int sourcePos, Object dest_arr, int destPos, int len)`
	* `System.arraycopy(nums, 0, original, 0, nums.length);`
* `int[] Arrays.copyOf(T[], int)`
	* `int[] copied = Arrays.copyOf(nums, nums.length);`
* loop copy an array
```java
void copy(int[] source, int[] target) {
    for (int i = 0; i < source.length; i++) {
        target[i] = source[i];
    }
}
```
## 初始化array
`Arrays.fill(dp, 1);`
## loop a array

### loop i
```
	for(int i = 0; i < numCourses; i++)  {
	
	}
```

### new for
```
	for(int c : list) {
	}
```

## initialise double array [][]

``` java
// syntax
data_type[1st dimension][2nd dimension][]..[Nth dimension] array_name = new data_type[size1][size2]….[sizeN];

int[][] multiples = new int[4][2];     // 2D integer array with 4 rows 
                                          and 2 columns
String[][] cities = new String[3][3];  // 2D String array with 3 rows 
                                          and 3 columns

int[][] wrong = new int[][]; // not OK, you must specify 1st dimension
 int[][] right = new int[2][]; // OK

```
```
	// initialise len+1 piece of len+1 array
    boolean[][] dp = new boolean[len+1][len+1];


	// initialise len+1 piece of 2 array
    boolean[][] dp = new boolean[len+1][2];
```

## size vs length


`size()` is a method specified in java.util.Collection, which is then inherited by every data structure in the standard library. 

`length` is a field on any array (arrays are objects, you just don't see the class normally), and length() is a method on java.lang.String, which is just a thin wrapper on a char[] anyway.

Perhaps by design, Strings are immutable, and all of the top-level Collection subclasses are mutable. So where you see "length" you know that's constant, and where you see "size" it isn't.

```
List nums;
int size = nums.size();
```

## sort
- Collections.sort operates on a List
- Arrays.sort operates on an array), java.util.Collections.sort() simply calls java.util.Arrays.sort() to do the heavy lifting.
- sort 2d array
	```
	double[][] array= {
	{1, 5},
	{13, 1.55},
	{12, 100.6},
	{12.1, .85} };

	java.util.Arrays.sort(array, new java.util.Comparator<double[]>() {
    	public int compare(double[] a, double[] b) {
        	return Double.compare(a[0], b[0]);
	    }
	});

	java.util.Arrays.sort(array, (a, b) -> {
        	return Double.compare(a[0], b[0]);
	    }
	});

	```

## BST & recursive

Inorder Successor in BST

```
Binary Search Tree is a node-based binary tree data structure which has the following properties:

The left subtree of a node contains only nodes with keys lesser than the node’s key.
The right subtree of a node contains only nodes with keys greater than the node’s key.
The left and right subtree each must also be a binary search tree.
```


## string
### compareTo
- public int compareTo(String anotherString)

### substring
- string substring(int beginIndex)
- string substring(int beginIndex, int endIndex) 
	+ [)

### sorting
```java
	char[] ca = s.toCharArray();
	Arrays.sort(ca);
	String strKey = String.valueOf(ca);
```
## StringBuilder	

`deleteCharAt(int index)`

## StrintBuffer VS StringBuilder
### Thread Safety:
* StringBuffer is thread-safe because its methods are synchronized, meaning only one thread can access it at a time. 
* StringBuilder is not thread-safe, as its methods are not synchronized, making it faster but potentially unsafe in multithreaded environments. 
### Performance:
* Due to synchronization overhead, StringBuffer is generally slower than StringBuilder in single-threaded scenarios. 
* StringBuilder is faster because it doesn't have the overhead of synchronization. 

## StringBuilder VS StringBuffer

| Feature | StringBuilder | StringBuffer |
|---------|--------------|--------------|
| Thread Safety | Not thread-safe (no synchronization) | Thread-safe (methods are synchronized) |
| Performance | Faster in single-threaded environments | Slower due to synchronization overhead |
| Use Case | Preferred for single-threaded scenarios | Preferred when multiple threads access the same string buffer |
| API | Same methods as StringBuffer | Same methods as StringBuilder |
| Creation | `new StringBuilder()` | `new StringBuffer()` |
| Introduced | Java 5 | Java 1.0 |

## Set
- TreeSet
	+ A  NavigableSet implementation based on a __TreeMap__. The elements are <span style="color:blue">**ordered** </span>using their natural ordering, or by a Comparator provided at set creation time, depending on which constructor is used.


```
TreeSet<String> set = new TreeSet();
set.add("treeset");
List<String> list = new ArrayList(set);
```



## Random
- The general contract of `int nextInt(int n)` is that one int value in the specified range is pseudorandomly generated and returned.
	* The method call returns the next integer number from the sequence
- The `Math.random()` method returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.

# 中文
很久以前，在繁华的数据结构之城，住着一位勤奋的程序员，名叫爱丽丝。爱丽丝的任务是为一个受欢迎的在线商店构建一个高效的数据存储系统。她选择了 **HashMap**，因为它具有O(1)的操作性能，并且她理解它的核心原理.

爱丽丝知道 **HashMap 是非同步的，并且允许空键和空值**。她选择 HashMap 而不是 Hashtable，因为 Hashtable 是同步的，不允许空键/值，而且是一个遗留类。TreeMap 和 LinkedHashMap 不符合她的需求，因为 TreeMap 保持键的排序（O(log n) 操作），而 LinkedHashMap 保持插入顺序，这对于她的应用来说是不必要的。

HashMap 的结构结合了 **数组与链表和红黑树**. 当桶中的元素数量小于 8 时，链表更有效，但是超过 8 个元素时，链表会转换为 **红黑树**，以保持 O(log n) 的性能。当计数为 6 时，会切换回链表。这种混合方法平衡了查询性能和存储效率，避免了仅使用链表或仅使用红黑树的缺点.

爱丽丝将 HashMap 的 **初始容量** 配置为 16，**负载因子** 配置为 0.75。传递 'k' 作为初始容量会将 HashMap 初始化为大于 k 的最接近的 2 的幂。这确保了高效的内存使用并最大限度地减少了碰撞。

为了有效地分配键，爱丽丝实现了一个 **哈希函数**。获取键的 32 位整数哈希码后，她应用了一个 **扰动函数**，该函数在高 16 位和低 16 位之间执行异或运算。这通过确保高位和低位都对哈希计算做出贡献来减少 **碰撞**。索引使用位与运算计算：`散列值 & 数组长度-1`，等效于 `hash % length`。位运算因其速度优于模运算而受到青睐。数组长度是 2 的幂，因此 `(length-1)` 是数组长度模数.

爱丽丝实现了 `put(key, value)` 方法。该过程包括：
*   如果数组为空，则初始化数组。
*   计算键的哈希值和存储索引。
*   如果桶为空，则创建一个新节点。
*   如果发生 **哈希冲突**，则比较键。
    *   如果键相等，则更新该值。
    *   否则，如果它是树节点，则将新节点插入到红黑树中。
    *   将新节点添加到链表中，如果列表超过 8，则将其转换为红黑树。
*   如果节点数超过阈值，则将数组的大小调整为原始大小的两倍。

随着在线商店的增长，对 HashMap 的并发访问导致数据损坏，因为 **HashMap 不是线程安全的**。并发修改可能导致调整大小期间的死锁、put 操作期间的数据丢失以及 get 操作返回 null。

为了解决并发问题，爱丽丝迁移到 **ConcurrentHashMap**。

*   在 **JDK 1.7** 中，ConcurrentHashMap 使用 **分段锁**，将数据分成多个段，每个段都有自己的锁。默认情况下有 16 个段，最多允许 16 个线程并发写入，从而减少锁争用并允许更高的并发性。
*   在 **JDK 1.8** 中，ConcurrentHashMap 结合了 **CAS（比较并交换）操作和同步块**，用节点数组、链表和红黑树替换了段。**CAS** 是一种无锁算法，它使用 CPU 原子指令来比较和更新内存值。爱丽丝使用 CAS 进行表初始化和节点更新，并使用 **synchronized** 在关键操作（如 put）期间锁定链表或红黑树的第一个节点。

爱丽丝仔细权衡了每种机制的优点和缺点。

*   CAS 提供了高性能且是非阻塞的，但会消耗 CPU 资源并且可能受到 ABA 问题的困扰。
*   Synchronized 易于实现并保证可见性，但可能导致阻塞和较低的性能。
*   分段锁提供了高并发性并减少了锁争用，但实现起来很复杂并且会消耗更多内存。

借助 ConcurrentHashMap，爱丽丝的在线商店可以处理大量并发请求而不会发生数据损坏，从而创建了一个高效且线程安全的数据存储系统。


# 英文
Once upon a time, in the bustling city of Data Structures, lived a diligent coder named Alice, tasked with building an efficient data storage system for a popular online store. Alice chose a **HashMap** for its O(1) operation performance, understanding its core principles.

Alice knew that **HashMap is not synchronized and allows null keys and values**. She opted for HashMap over Hashtable because Hashtable is synchronized, doesn't allow null keys/values, and is a legacy class. TreeMap and LinkedHashMap didn't fit her needs, as TreeMap keeps keys sorted (O(log n) operations), and LinkedHashMap maintains insertion order, unnecessary for her application.

The HashMap's structure combines **arrays with linked lists and red-black trees**. When a bucket has fewer than 8 elements, a linked list is more efficient, but exceeding 8 elements transforms the list into a **red-black tree** to maintain O(log n) performance. The switch back to a linked list happens when the count is 6. This hybrid approach balances query performance and storage efficiency, avoiding the drawbacks of using only linked lists or only red-black trees.

Alice configured the HashMap's **initial capacity** to 16 with a **load factor** of 0.75. Passing 'k' as the initial capacity initializes the HashMap to the nearest power of 2 greater than k. This ensures efficient memory use and minimizes collisions.

To distribute keys effectively, Alice implemented a **hash function**. Taking the key's 32-bit integer hash code, she applied a **扰动函数** (perturbation function), performing an XOR operation between the high and low 16 bits. This reduces **collisions** by ensuring both high and low bits contribute to the hash calculation. The index is calculated using a bitwise AND operation: `散列值 & 数组长度-1`, equivalent to `hash % length`. Bitwise operations are favored for their speed over modulo. The array length is a power of 2 so that `(length-1)` is the array length modulus.

Alice implemented the `put(key, value)` method. The process includes:
*   Initializing the array if it's empty.
*   Calculating the key's hash value and the index for storage.
*   Creating a new node if the bucket is empty.
*   In case of a **hash collision**, comparing keys.
    *   If keys are equal, updating the value.
    *   Otherwise, inserting the new node into the red-black tree if it's a tree node.
    *   Adding the new node to the linked list, converting it to a red-black tree if the list exceeds 8.
*   Resizing the array to twice its original size if the number of nodes surpasses the threshold.

As the online store grew, concurrent access to the HashMap led to data corruption, because **HashMap is not thread-safe**. Concurrent modifications could cause deadlocks during resizing, data loss during put operations, and get operations returning null.

To address concurrency, Alice migrated to **ConcurrentHashMap**.

*   In **JDK 1.7**, ConcurrentHashMap used **segmented locking**, dividing data into segments each with its own lock. With 16 default segments, up to 16 threads could write concurrently, reducing lock contention and allowing greater concurrency.
*   In **JDK 1.8**, ConcurrentHashMap combined **CAS (Compare And Swap) operations and synchronized blocks**, replacing segments with a Node array, linked lists, and red-black trees. **CAS**, a lock-free algorithm, uses CPU atomic instructions to compare and update memory values. Alice used CAS for table initialization and node updates, and **synchronized** to lock the first node of a linked list or red-black tree during critical operations like put.

Alice carefully weighed the advantages and disadvantages of each mechanism.

*   CAS offered high performance and was non-blocking but consumed CPU resources and could suffer from the ABA problem.
*   Synchronized was simple to implement and guaranteed visibility but could lead to blocking and lower performance.
*   Segmented locking offered high concurrency and reduced lock contention but was complex to implement and consumed more memory.

With ConcurrentHashMap, Alice's online store handled many concurrent requests without data corruption, creating an efficient and thread-safe data storage system.

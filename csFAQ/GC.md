## Java GC
Here's a text-based diagram visualizing the Java Garbage Collection concepts:

```
## Heap area
                                  +-----------------------+
                                  |    Java Application   |
                                  +-----------------------+
                                          | Creates Objects
                                          V
                                  +-----------------------+
                                  |       JVM Heap        |
                                  +-----------------------+
                                          |
                        +------------------+------------------+
                        |                  |                  |
                        V                  V                  |
                +-----------------+  +-----------------+  |
                | Young Generation|  | Old Generation| |
                +-----------------+  +-----------------+  |
                        |                  |                  |
            +-----------+-----------+      |      +-----------+
            |           |           |      |      |
    +---------+   +---------+   +---------+      |      +---------+
    | Eden    |-->| FromSpace|-->| ToSpace |----->       |   Old   |
    +---------+   +---------+   +---------+      |      |  Space  |
        ^             ^             ^             |      +---------+
        |             |             |             |
        +-------------+-------------+-------------+
              New Objects & Survivors (Minor GC)     |
                                                    |
                                                    V
                                       (**Major GC** Occurs Here)

## Non-Heap area
                                  +-----------------------+
                                  |    Java Application   |
                                  +-----------------------+

                                          | init
                                          V
                                  +-----------------------+
                                  |   Non-Heap Area       |
                                  +-----------------------+
                                          ^
                                          | References to Objects

# These are all GC roots
                                  +-----------------------+
                                  | **GC Roots**          |
                                  +-----------------------+
                                  /        |        \        \
                                 /         |         \         \
                        +---------+   +-----------+   +---------------+   +-----------------+
                        | Boot    |   | Static    |   | Thread Stacks |   | JNI References|
                        | Class   |   | Variables |   | (Local Vars)| |   | (Native Code) |
                        | Loader  |   |           |   |               |   |                 |
                        +---------+   +-----------+   +---------------+   +-----------------+
                                         \          /
                                          \        /
                                           +------+
                                           |      |
                                           V      V
                                   (**Reachability Scan**)


                                  +-----------------------+
                                  |   Garbage Collector   | 
                                  +-----------------------+
                                          |
                          +----------------+----------------+
                          |                |                |
                          V                V                V
                    +----------+     +----------+     +------------+
                    | **Mark** | --> | **Sweep**| --> | **Compact**|
                    +----------+     +----------+     +------------+
                          |                |                |
                          +------(Identifies Live Objects)-----+
                                         |
                                         +-----(Reclaims Dead Object Memory)-----+
                                                    |
                                                    +-----(Moves Live Objects for Contiguous Memory)
```

Garbage collection in Java is implemented as Java Garbage collector in JVM.

Objects can be 
* Live - being used and referenced
* Dead - no longer used or referenced

The core concept is **reachability**.

Garbage collectors work on the concept of Garbage Colelction Roots(GC Roots) to identify 

### GC important concepts

#### GC root
![GC roots](./images/GCRoot.drawio.svg)

The main GC roots include:
* Local variables in thread stacks(Heap)
* Static variables in classes
* Active Java threads
* JNI references
* Class loader 
   * Boot class loader is one of the GC roots, but not the only one
   * [all class loaders](./classLoader.md)
   * 所有的类加载器，都是独立的GC root
   * Bootstrap (Boot) Class Loader
   * Extension Class Loader
   * Application Class Loader
   * Custom Class Loaders


#### GC collector
Garbage collector scan objects reachability from GC roots.

![GC mark](./images/GC_mark.drawio.svg)

#### GC collection: Stop-The-World (STW)
STW is a phase where all application threads are paused to ensure memory consistency during garbage collection. It occurs in:

1. **Minor GC (Young Generation)**
   - Full STW during collection
   - Usually very brief due to small size
   - Happens frequently

2. **Major GC (Old Generation)**
   - STW duration varies by collector type:
     * Serial GC: Full STW
     * Parallel GC: Full STW but uses multiple threads
     * CMS: Minimal STW (only during initial mark and remark)
     * G1: Short STW during marking

### GC phrases（垃圾回收的基本算法步骤）
A general garbage collection phrases. And three different thinkings.

* Mark
* Sweep
* Compact

| Phrase | 1 | 2 | 3 | 4 | 5 | 6| 7|
|---|---|---|---|---|---|---|---|
|MARK | ✅ | 🟡 | ✅ | ✅ | 🟡 | ✅ | ✅ |
|SWEEP | ✅ | ⚪️ | ✅ | ✅ | ⚪️ | ✅ | ✅ |
|COMPACT | ✅ |  ✅ | ✅ |  ✅ | ✅ | ⚪️| ⚪️| 

#### cons
* MARK and SWEEP: memory friction
* MARK and COMPACT: coping is resource heavy and operational heavy.
### JVM non-heap Garbage collection
* Full GC 收集时一并回收
* 类加载器不再引用时，被回收，因为non-heap一般类源数据，常量，方法，所以在类类等卸载。

### JVM HEAP： Generational Garbage collection
* Empirical analysis has shown that most objects in java are short lived.

So the optimization is to categorizes objects by age.

![generational GC](./images/GC_generational.drawio.svg)

#### Yound generations
* **Eden space** - all new objects 
* **Survivor spaces(FromSpace & ToSpace)** - objects are moved here from Eden after one garbage collection cycle

the process goes 
1. Eden has all objects(live and dead)
2. **Minor GC occurs** - all dead are removed from Eden. All live objects are moved to S1. Eden and E2 are now emptry
3. New objects are created to Eden. some objects in Eden and S1 become dead.
4. **Minor GC occurs** - all dead objects are removed from Eden and S1. All live are move3d to S2. Eden and S1 are empty.

**Generational Garbage collection** 会在各个代中使用Mark-Sweep-Compact算法
#### Old generations
Long-lived objects are eventually moved from Young Generation to the Old Generations.

Major garbage collection event will collect garbage from old generations.


## Types of Garbage collectors in JVM
### Serial GC
### Paralevel GC
### Concurrent Mark Sweep(CMS) GC
### Concurrent Mark Sweep(CMS) GC
CMS is designed to minimize pause times by doing most of its work concurrently with the application threads. It operates in these phases:

1. **Initial Mark** (STW)
   - Brief pause to mark GC roots 标记GC Root
   - Only marks objects directly connected to roots
   
2. **Concurrent Mark**
   - Traces object graph from roots while application runs
   - Identifies all reachable objects
   
3. **Concurrent Premark**
   - Updates changes from ongoing application execution
   - Prepares for final marking phase

4. **Remark** (STW)
   - Short pause to finish marking
   - Accounts for changes during concurrent phase

5. **Concurrent Sweep**
   - Reclaims memory from garbage objects
   - Runs concurrently with application

6. **Concurrent Reset**
   - Prepares data structures for next collection

Key characteristics:
- Lower pause times than other collectors
- Higher CPU usage due to concurrent execution
- No compaction phase (can lead to fragmentation)
- Best for large heaps with response-time requirements
![GC collectors](./images/GC_collector.drawio.svg)


## 辅助记忆故事
在一个繁华的 **Java 虚拟机 (JVM)** 的世界里，无数的 **对象** 忙碌地为各种 **Java 应用程序** 服务。这些对象可以分为两类：**存活对象 (Live)**，它们正在被积极使用和引用；以及 **死亡对象 (Dead)**，它们已经不再被需要，也没有任何有效的引用指向它们.

为了保持 JVM 的高效运行，需要定期清理这些 **死亡对象**，释放它们占用的内存。这项重要的任务就落在了 **Java 垃圾收集器 (GC)** 的肩上. GC 的核心思想是 **可达性 (reachability)**.

为了判断哪些对象仍然存活，GC 会从一组被称为 **垃圾收集根 (GC Roots)** 的关键起点开始追踪对象的引用链. 这些 **GC 根** 包括：
*   **线程栈中的局部变量**.
*   **类中的静态变量**.
*   **活跃的 Java 线程** 本身.
*   用于 Java 与本地代码交互的 **JNI 引用**.
*   以及 **启动类加载器 (Boot Class Loader)**

GC 会执行一次 **可达性扫描 (reachability scan)**，从这些 **GC 根** 开始，遍历所有对象的引用关系. 任何无法通过这条引用链追溯到的对象，都被认为是 **垃圾 (garbage)**.

JVM 的内存区域，特别是存储对象的 **堆 (Heap)**，采用了 **分代垃圾收集 (Generational Garbage collection)** 的策略. 这种策略基于一个经验观察：大多数 Java 对象都具有短暂的生命周期. 因此，堆被划分为 **新生代 (Young Generation)** 和 **老年代 (Old Generation)**. **新生代** 又进一步划分为 **Eden 空间**，所有新创建的对象最初都分配在这里，以及两个 **Survivor 空间 (FromSpace & ToSpace)**，通常标记为 S1 和 S2.

当 **Eden 空间** 快满时，会触发一次 **Minor GC (Young Generation)**. 在 Minor GC 期间，所有的应用程序线程都会暂停，这个过程称为 **Stop-The-World (STW)**. 这样做是为了确保在垃圾收集过程中内存的一致性. GC 会识别 **Eden 空间** 中不再存活的对象并将它们移除. **Eden 空间** 中仍然存活的对象会被移动到其中一个 **Survivor 空间** (例如，FromSpace). 如果之前的 FromSpace 中也有存活的对象，它们也可能被移动到 ToSpace. 此时，Eden 空间和之前被清理的 Survivor 空间会变成空的. 这个 Minor GC 过程会频繁发生，但由于新生代通常较小，所以暂停时间通常很短暂.

在 **新生代** 中经历多次 Minor GC 后仍然存活的对象，最终会被晋升到 **老年代 (Old Generation)**. 对老年代的垃圾收集称为 **Major GC (Old Generation)**. Major GC 同样会引起 **STW** 暂停，但暂停的时间长度取决于所使用的具体 **GC 收集器 (GC collector)** 类型.

一般来说，垃圾收集的过程包含几个关键的 **阶段 (GC phrases)**:
*   **标记 (Mark)**：GC 从 **GC 根** 开始遍历对象图，标记所有仍然存活的对象.
*   **清除 (Sweep)**：GC 回收在标记阶段被确定为死亡的对象所占用的内存. 然而，单纯的标记和清除可能会导致 **内存碎片 (memory friction)**，即空闲内存被分割成许多小的、不连续的块，难以分配大的对象。
*   **整理/压缩 (Compact)**：为了解决内存碎片问题，GC 可能会执行 **整理 (Compact)** 操作，将存活的对象移动到一起，使得空闲内存变得连续。但是，这个过程会消耗较多的资源并且操作也比较复杂 (**coping is resource heavy and operational heavy**).

随着 JVM 的发展，出现了多种不同类型的 **垃圾收集器 (Types of Garbage collectors in JVM)**，它们针对不同的应用场景和性能需求进行了优化。一些常见的垃圾收集器包括：

*   **串行垃圾收集器 (Serial GC)**：它使用单线程执行所有的垃圾收集工作，因此在垃圾收集过程中会发生完整的 **STW** 暂停.
*   **并行垃圾收集器 (Paralevel GC)**：与串行 GC 类似，它也会导致完整的 **STW** 暂停，但它使用多个线程并行执行垃圾收集，从而可能缩短暂停时间.
*   **并发标记清除垃圾收集器 (Concurrent Mark Sweep (CMS) GC)**：CMS 旨在通过与应用程序线程并发执行大部分垃圾收集工作来最小化暂停时间. 它包含以下主要阶段：
    1.  **初始标记 (Initial Mark) (STW)**：一个短暂的暂停，用于标记 **GC 根** 以及直接与它们关联的对象.
    2.  **并发标记 (Concurrent Mark)**：GC 从初始标记的对象开始遍历对象图，标记所有可达对象，这个过程与应用程序线程并发执行.
    3.  **并发预清理 (Concurrent Premark)**：更新在并发标记阶段由于应用程序运行而发生的对象引用变化，为最终标记阶段做准备.
    4.  **重新标记 (Remark) (STW)**：一个短暂的暂停，完成标记过程，处理在并发标记期间发生的对象引用变化.
    5.  **并发清除 (Concurrent Sweep)**：GC 回收被标记为死亡的对象所占用的内存，这个过程也与应用程序线程并发执行.
    6.  **并发重置 (Concurrent Reset)**：清理内部数据结构，为下一次垃圾收集做准备.

    CMS 的主要特点是 **较低的暂停时间 (Lower pause times)**，但由于并发执行，它通常会 **消耗更多的 CPU 资源 (Higher CPU usage)**，并且 **没有压缩阶段 (No compaction phase)**，这可能导致内存碎片. CMS 适用于具有大堆内存和对响应时间有较高要求的应用程序.

总而言之，Java 垃圾收集器在 JVM 的幕后辛勤工作，通过识别并移除基于 **GC 根** 可达性判断的 **死亡对象** 来管理内存。**分代收集** 的策略和各种不同的 **GC 收集器** 使得 JVM 能够针对各种应用程序的需求优化内存管理，在吞吐量和暂停时间之间取得平衡。
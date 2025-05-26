# Java内存模型（JMM）详解

## 1. JVM内存结构

### 1.1 整体架构
- **Main Memory**: Shared memory visible to all threads
- **Working Memory**: Each thread has its own working memory (cache)
  - Threads operate on copies of variables from main memory
  - Changes must be synchronized back to main memory

缓存行状态：(MESI)
- Modified（修改）：该CPU已修改缓存行
- Exclusive（独占）：缓存行只在该CPU中
- Shared（共享）：缓存行可能在其他CPU中
- Invalid（无效）：缓存行已失效

```
+------------------+     +------------------+     +------------------+
|     Thread 1     |     |     Thread 2     |     |     Thread N     |
|  +------------+  |     |  +------------+  |     |  +------------+  |
|  | 工作内存    |  |     |  | 工作内存    |  |     |  | 工作内存    |  |
|  | (CPU缓存)   |  |     |  | (CPU缓存)   |  |     |  | (CPU缓存)   |  |
|  +------------+  |     |  +------------+  |     |  +------------+  |
+--------↕--------+     +--------↕--------+     +--------↕--------+
         ↕                      ↕                       ↕
+-----------------------------------------------------------------+
|           系统总线（Bus）                                         |
+-----------------------------------------------------------------+
                                ↑
+--------------------------------------------------+
|                    主内存（堆内存）                  |
|                    Main Memory                     |
+--------------------------------------------------+
```

### 1.2 为什么需要线程工作内存
1. 并发编程模型需求
   
   - JMM规范定义了线程如何与主内存交互
   - 工作内存为线程提供了变量操作的隔离环境
   - 保证了线程操作的原子性和可见性控制
2. 性能优化
   
   - 减少线程对主内存的直接访问
   - 符合局部性原理，提高数据访问效率

   ```java
   // 时间局部性
   for (int i = 0; i < 10000; i++) {
       counter++;  // counter会被缓存在CPU缓存中
   }
   
   // 空间局部性
   int[] array = new int[10000];
   for (int i = 0; i < array.length; i++) {
       array[i] = i;  // 连续的内存访问
   }
   ```
### 1.3 线程工作内存的硬件实现
CPU缓存作为线程工作内存的物理实现

1. **多级缓存结构**
   ```
   CPU访问速度：寄存器 > L1缓存 > L2缓存 > 主内存
   - 寄存器访问：1个时钟周期
   - L1缓存访问：~3-4个时钟周期
   - L2缓存访问：~10个时钟周期
   - 主内存访问：~100个时钟周期
   ```
2. 缓存一致性协议（MESI）
   
   - Modified（修改）：该CPU已修改缓存行
   - Exclusive（独占）：缓存行只在该CPU中
   - Shared（共享）：缓存行可能在其他CPU中
   - Invalid（无效）：缓存行已失效

### 1.3 工作内存带来的问题
This layered architecture leads to:

这种分层架构导致以下问题：

- 内存可见性问题(Memory Visibility) ：一个线程对变量的修改对其他线程不立即可见
- 内存操作重排序(Memory Ordering) ：编译器和CPU可能重排序内存操作以提高性能
- 缓存一致性问题(Cache Coherence Issues) ：不同线程的工作内存可能包含同一变量的不同值

## 2. JVM工作内存与主内存的交互操作

### 2.1 八种原子操作
```
1. lock（锁定）：作用于主内存，将变量标识为线程独占
2. unlock（解锁）：作用于主内存，将变量释放出来
3. read（读取）：作用于主内存，将变量值读到工作内存
4. load（载入）：作用于工作内存，将read的值载入变量副本
5. use（使用）：作用于工作内存，读取变量的值
6. assign（赋值）：作用于工作内存，将值赋给变量
7. store（存储）：作用于工作内存，将变量值传送到主内存
8. write（写入）：作用于主内存，将store的值写入变量
```

### 2.2 变量的原子操作过程
```java
public class MemoryOperationExample {
    private int value = 0;  // 主内存中的变量
    
    public void setValue(int v) {
        // 1. read: 从主内存读取value的值
        // 2. load: 将value载入工作内存
        // 3. assign: 将v赋值给工作内存中的value
        // 4. store: 将修改后的value传送到主内存
        // 5. write: 将value的值写入主内存
        this.value = v;
    }
    
    public int getValue() {
        // 1. read: 从主内存读取value的值
        // 2. load: 将value载入工作内存
        // 3. use: 从工作内存读取value的值
        return value;
    }
}
```
## 3 缓存一致性
### 3.1 协议 Cache Coherence Protocol（MESI）
缓存一致性协议定义了以下四种缓存状态

```
+-------+     +-------+     +-------+
| CPU1  |     | CPU2  |     | CPU3  |
| Cache |     | Cache |     | Cache |
+-------+     +-------+     +-------+
    ↓            ↓            ↓
+-----------------------------------+
|           系统总线（Bus）           |
+-----------------------------------+
    ↑
+-------+
| 主内存  |
+-------+
```

缓存状态：
- **Modified（修改）**：缓存行已被当前CPU修改，且与主内存中的数据不一致。此时，其他CPU不能读取该缓存行的数据。
- **Exclusive（独占）**：缓存行只存在于当前CPU中，且与主内存中的数据一致。此时，其他CPU可以读取该缓存行的数据，但不能修改。
- **Shared（共享）**：缓存行可能存在于多个CPU中，且与主内存中的数据一致。此时，所有CPU都可以读取该缓存行的数据，但不能修改。
- **Invalid（无效）**：缓存行已失效，不能被当前CPU使用。此时，CPU需要从主内存中重新读取数据。
### 3.2 内存屏障
见6.内存屏障

### 3.3 总线嗅探
总线嗅探是MESI协议实现的基础机制，通过监听系统总线上的数据传输，各CPU能够保持其缓存数据的一致性。




          
我需要查看当前JVM内存模型文档的第四节内容，以便全面重写并包含所有建议的部分。
        
            
toolName: view_files
            
status: success
          
            
filePath: /Users/fangzhzh/workspace/leetcode/csFAQ/JVMMemoryModel.md
          
我需要继续查看文档的后续部分，特别是关于内存屏障的详细内容，以便全面重写第四节。
        
            
toolName: view_files
            
status: success
          
            
filePath: /Users/fangzhzh/workspace/leetcode/csFAQ/JVMMemoryModel.md
          
我需要查看文档的最后部分，了解是否有关于java.util.concurrent包中的同步工具类和原子类操作的详细说明，以便在重写第四节时包含这些内容。
        
            
toolName: view_files
            
status: success
          
            
filePath: /Users/fangzhzh/workspace/leetcode/csFAQ/JVMMemoryModel.md
          
## 4. Java内存可见性
### 4.1 内存可见性问题的产生

在多核CPU架构下，每个CPU都有自己的缓存，当多个线程在不同的CPU上执行时，可能会导致数据不一致：

```java
public class VisibilityProblem {
    private int value = 0;
    
    // 线程A
    public void write() {
        value = 1;  // 写入工作内存，不一定立即刷新到主内存
    }
    
    // 线程B
    public void read() {
        // 从工作内存读取，可能读不到最新值
        System.out.println(value);
    }
}
```

### 4.2 Java内存可见性保证机制


#### 4.2.1 语言级别的同步机制

##### 1. synchronized关键字

`synchronized`关键字不仅提供了互斥访问，还保证了内存可见性：

- 线程进入synchronized块时，会清空工作内存中的共享变量，从主内存重新加载
- 线程退出synchronized块时，会将工作内存中的共享变量刷新到主内存

```java
public class SynchronizedVisibility {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;
    }
    
    public synchronized int getCounter() {
        return counter;
    }
}
```

##### 2. volatile关键字

`volatile`关键字专门用于解决内存可见性问题，它保证：

- 对volatile变量的写操作会立即刷新到主内存
- 对volatile变量的读操作会从主内存重新加载，而不使用缓存
- 禁止指令重排序优化

```java
public class VolatileVisibility {
    private volatile boolean flag = false;
    private int value = 0;
    
    public void write() {
        value = 42;  // 普通写入
        flag = true; // volatile写入，会强制刷新之前的普通写入
    }
    
    public void read() {
        if (flag) {  // volatile读取
            // 如果flag为true，则value的最新值（42）也会被读取到
            System.out.println(value);
        }
    }
}
```

##### 3. final关键字

`final`关键字在正确使用时也能保证内存可见性：

- 被final修饰的字段在构造函数结束时，会保证其他线程看到的是初始化后的值
- 前提是不发生this引用逃逸（在构造函数完成前，不能将this引用暴露给其他线程）

```java
public class FinalVisibility {
    private final int value;
    
    public FinalVisibility() {
        value = 42;
        // 构造函数结束后，其他线程看到的value必定是42
    }
    
    public int getValue() {
        return value;
    }
}
```

#### 4.2.2 API级别的同步机制

##### 1. java.util.concurrent.atomic包中的原子变量类

原子变量类通过底层的CAS（Compare-And-Swap）操作保证原子性和可见性：

```java
public class AtomicVisibility {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet(); // 原子操作，保证可见性
    }
    
    public int getCounter() {
        return counter.get(); // 保证读取到最新值
    }
}
```

##### 2. java.util.concurrent包中的显式锁（Lock接口）

显式锁提供了比synchronized更灵活的锁定机制，同时保证内存可见性：

```java
public class LockVisibility {
    private final Lock lock = new ReentrantLock();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
    
    public int getCounter() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }
}
```

##### 3. 线程安全容器

Java提供的线程安全容器类内部实现了同步机制，保证内存可见性：

```java
public class ConcurrentCollectionVisibility {
    // 线程安全的Map实现
    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    // 线程安全的List实现
    private CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    
    public void updateMap(String key, Integer value) {
        map.put(key, value); // 线程安全，保证可见性
    }
    
    public void addToList(String item) {
        list.add(item); // 线程安全，保证可见性
    }
}
```

#### 4.2.3 线程协作机制中的内存可见性

##### 1. Thread.start()和Thread.join()

Java内存模型规定：

- 主线程在调用t.start()之前的操作，对线程t可见
- 子线程的操作，在主线程调用t.join()成功返回后可见

```java
public class ThreadVisibility {
    private int value = 0;
    
    public void startAndJoin() throws InterruptedException {
        value = 10; // 在主线程中设置值
        
        Thread thread = new Thread(() -> {
            // 这里能看到value = 10
            value = 20; // 在子线程中修改值
        });
        
        thread.start();
        thread.join(); // 等待子线程完成
        
        // 这里能看到value = 20
        System.out.println(value);
    }
}
```

##### 2. 线程中断

线程中断操作与被中断线程检测中断之间也存在happens-before关系：

```java
public class InterruptVisibility {
    private int value = 0;
    
    public void interruptExample() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 循环执行任务
            }
            // 线程被中断后执行清理
            value = 100; // 这个写入对中断线程可见
        });
        
        thread.start();
        // 执行一些操作
        thread.interrupt(); // 中断线程
        thread.join(); // 等待线程结束
        
        // 这里能看到value = 100
        System.out.println(value);
    }
}
```

#### 4.2.4 JDK 9引入的VarHandle

JDK 9引入的`VarHandle`提供了比反射更轻量级的机制，可以对特定变量进行各种粒度的原子操作和内存屏障控制：

```java
public class VarHandleExample {
    private int x;
    private static final VarHandle VH;
    
    static {
        try {
            VH = MethodHandles.lookup()
                .findVarHandle(VarHandleExample.class, "x", int.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    
    public void update(int value) {
        // 使用VarHandle进行原子更新，保证可见性
        VH.setVolatile(this, value);
    }
    
    public int get() {
        // 保证读取到最新值
        return (int) VH.getVolatile(this);
    }
}
```

### 4.3 内存可见性保证机制的底层实现

这些内存可见性保证机制在底层通过以下方式实现：

1. **内存屏障**：在读写操作前后插入内存屏障指令，防止指令重排序，并强制刷新缓存
2. **缓存一致性协议**：如MESI协议，通过总线嗅探机制保证多核CPU间的缓存一致性
3. **happens-before规则**：Java内存模型定义的一组规则，保证操作之间的可见性和有序性

### 4.4 选择合适的内存可见性保证机制

| 机制 | 适用场景 | 优点 | 缺点 |
|------|---------|------|------|
| synchronized | 需要互斥访问的场景 | 简单易用，自动释放锁 | 性能较低，粒度较粗 |
| volatile | 单个变量的可见性保证，无需互斥 | 轻量级，性能好 | 不保证原子性，使用场景有限 |
| final | 不可变对象的设计 | 简单安全，性能最佳 | 只适用于初始化后不再修改的场景 |
| Atomic类 | 需要原子操作的单个变量 | 性能好，支持CAS操作 | API较为复杂 |
| Lock接口 | 需要灵活控制锁的场景 | 功能丰富，支持超时、中断等 | 需要手动释放锁，使用复杂 |
| 线程安全容器 | 并发访问集合类的场景 | 封装了并发控制，易用 | 特定操作可能需要额外同步 |
| Thread方法 | 线程协作场景 | 简单直观 | 仅适用于特定线程交互模式 |
| VarHandle | 需要细粒度内存访问控制 | 功能强大，性能好 | API复杂，JDK 9以上才支持 |

## 5. 指令重排 (Instruction Reordering)
指令重排是指编译器和处理器为了优化性能而对指令执行顺序进行调整的过程。虽然指令重排可以提高单线程性能，但在多线程环境下可能会导致内存可见性问题。

Java里通过内存屏障保证有序性，详见下一部分Memory Barriers。

#### 5.1 重排类型
1）**编译器优化的重排序**: 编译器在不改变单线程程序语义的前提下，可以重新安排语句的执行顺序。

2）**指令级并行的重排序**: 现代处理器采用了指令级并行技术（Instruction-Level Parallelism，ILP）来将多条指令重叠执行。如果不存在数据依赖性，处理器可以改变语句对应机器指令的执行顺序。

3）**内存系统的重排序**: 由于处理器使用缓存和读/写缓冲区，这使得加载和存储操作看上去可能是在乱序执行。

```mermaid
graph LR
    S[源代码] --> A[编译器优化重排]
    A --> B[指令级并行重排]
    B --> C[内存系统重排]
    C --> 最终执行指令序列
```

#### 5.2 重排相关的语义
1. **With-Thread As-If-Serial 语义**：不管怎么重排（编译器和处理器为了提高并行度而进行的重排），单线程程序的执行结果不能被改变。编译器、运行时和处理器都必须遵守 As-If-Serial 语义。
2. **Happens-Before 关系**：JMM 定义了 happens-before 关系来确保内存可见性和有序性。如果一个操作 happens-before 另一个操作，那么第一个操作的结果对第二个操作是可见的，并且第一个操作的执行顺序在第二个操作之前。

#### 5.3 Happens-Before 规则
从JDK 5开始，Java使用新的JSR-133内存模型, JSR-133使用happens-before的概念来阐述操作之间的内存可见性。

1. **程序次序规则(Program order rule)**：在一个线程内，按照代码顺序，前面的操作 happens-before 于后面的操作。
2. **监视器锁规则(Monitor lock rule)**：对一个锁的解锁 happens-before 于随后对这个锁的加锁。
3. **volatile 变量规则(Volatile variable rule)**：对一个 volatile 变量的写操作 happens-before 于后续对这个 volatile 变量的读操作。
4. **线程启动规则**：Thread.start() 方法调用 happens-before 于启动线程中的每一个动作。
5. **线程终止规则**：线程中的所有操作 happens-before 于其他线程检测到该线程已经终止。
6. **线程中断规则(Thread termination rule)**：对线程的中断操作 happens-before 于被中断线程的代码检测到中断事件的发生。
7. **对象终结规则**：一个对象的构造函数执行完毕 happens-before 于该对象的 finalize() 方法的开始。
8. **传递性**：如果 A happens-before B，且 B happens-before C，那么 A happens-before C。

## 6. JMM Memory Barriers内存屏障
以下几种情况会自动插入内存屏障：
* 1. JVM自动插入 ：当使用 volatile 、 synchronized 、 final 等关键字时，JVM会根据需要自动插入适当的内存屏障
* 2. 并发工具类 ：使用 java.util.concurrent 包中的工具类时，内部实现会使用内存屏障
* 3. 原子类操作 ： java.util.concurrent.atomic 包中的原子类操作也会使用内存屏障

### 6.1 四种屏障类型

* LoadLoad屏障：确保Load1数据的装载先于Load2及后续装载指令完成
    * inserted between two load operations, after a volatile read
    *   ```
        int x = volatile_y; // Load1 (volatile read)
        // LoadLoad barrier inserted here
        int b = z;         // Load2 (normal read)
        ```
* StoreStore屏障：确保Store1数据对其他处理器可见先于Store2及后续存储指令
    * inserted between two store operations, before a volative write
    *   ```
        a = 1;              // Store1
        // StoreStore barrier inserted here
        volatile_x = 2;     // store 2
        ```
* LoadStore屏障：确保Load1数据装载先于Store2及后续存储指令
    * inserted between a load and a store, after a volatile read
    *   ```
        int x = volatile_y; // Load (volatile read)
        // LoadStore barrier inserted here
        z = 1;             // Store (normal write)
        ```
* StoreLoad屏障：确保Store1数据对其他处理器变得可见先于Load2及后续装载指令
    * The stronger barrier
    * inserted before volatile reads and after volatile write
    *   ```
        volatile_x = 1;    // Store
        // StoreLoad barrier inserted 
        // Any subsequent loads

        // Any previous stores
        // StoreLoad barrier inserted here
        int y = volatile_x; // Load
        ```


### 6.2 屏障的使用示例
```java
public class BarrierExample {
    private int a = 0;
    private volatile int v = 0;
    
    public void write() {
        a = 1;                  // Store1
        // StoreStore屏障
        v = 1;                  // Store2
        // StoreLoad屏障
    }
    
    public void read() {
        int i = v;              // Load1
        // LoadLoad屏障
        int j = a;              // Load2
        // LoadStore屏障
    }
}
```


## Reference Escape
When `final` is used, the compiler only inserts a **StoreStore** barrier before the constructor's return statement which means the final only has correct value after constructure.

Idea that if the reference to the object (this) is shared with other threads before the constructor finishes, those threads may see an incomplete state of the object.

```java
class DatabaseService {
    private final String connectionString;
    private Connection connection;

    public DatabaseService(String connectionString) {
        this.connectionString = connectionString;

        // Start a monitoring thread (not ideal to do this in the constructor)
        new Thread(() -> {
            monitorConnection();
        }).start();
        
        // Initialize the database connection
        this.connection = DriverManager.getConnection(connectionString);
    }

    private void monitorConnection() {
        // Simulate monitoring the connection
        while (true) {
            if (connection == null) {
                System.out.println("Connection is not initialized yet!");
                break; // Exit if connection is null
            }
            // Perform monitoring logic...
        }
    }
}
```
## 7. 实际应用

### 7.1 单例模式中的内存问题
```java
public class Singleton {
    private static volatile Singleton instance;
    
    public static Singleton getInstance() {
        if (instance == null) {  // 第一次检查
            synchronized (Singleton.class) {
                if (instance == null) {  // 第二次检查
                    // 1. 分配内存空间
                    // 2. 初始化对象
                    // 3. 将引用指向内存空间
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

### 7.2 生产者-消费者模式
```java
public class ProducerConsumer {
    private volatile boolean flag = false;
    private int data;
    
    public void produce(int d) {
        while (flag) {
            // 等待消费者消费
        }
        data = d;        // 写入数据
        flag = true;     // 通知消费者
    }
    
    public void consume() {
        while (!flag) {
            // 等待生产者生产
        }
        use(data);      // 使用数据
        flag = false;    // 通知生产者
    }
}
```


## Reference
* [Java内存模型（JMM）总结](https://zhuanlan.zhihu.com/p/29881777)

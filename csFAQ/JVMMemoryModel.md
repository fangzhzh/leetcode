# Java内存模型（JMM）详解

## 1. JVM内存结构

### 1.1 整体架构
- **Main Memory**: Shared memory visible to all threads
- **Working Memory**: Each thread has its own working memory (cache)
  - Threads operate on copies of variables from main memory
  - Changes must be synchronized back to main memory

缓存行状态：
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

### 1.2 为什么需要CPU缓存
1. **性能考虑**
   ```
   CPU访问速度：寄存器 > L1缓存 > L2缓存 > 主内存
   - 寄存器访问：1个时钟周期
   - L1缓存访问：~3-4个时钟周期
   - L2缓存访问：~10个时钟周期
   - 主内存访问：~100个时钟周期
   ```

2. **局部性原理**
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

### CPU Cache带来的问题
This layered architecture leads to:

* Cache Coherence Issues: Different CPU caches may have different values
* Memory Ordering: CPU and compiler can reorder memory operations
* Memory Visibility: Updates in one CPU's cache not immediately visible to others


## 2. 内存交互操作

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

## 3. 内存可见性问题
- Without proper synchronization, changes made by one thread may not be visible to other threads
- Synchronization mechanisms:
  - **synchronized** keyword
  - **volatile** variables
  - final fields without [reference escape] of `this`
    - If the constructor initializes a final field, that value is guaranteed to be visible to any thread that accesses the object after the constructor has completed.
  - concurrent collections
### 3.1 可见性问题的产生
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

### 3.2 缓存一致性协议 Cache Coherence Protocol（MESI）
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


#### 示例代码

以下是一个简单的Java示例，展示了如何使用`volatile`关键字来确保内存可见性，这与缓存一致性协议的作用类似：
```java
public class CacheInvalidationProcess {
    private volatile int value;
    
    // CPU1上的线程
    public void writeOnCPU1() {
        value = 42;
        // 1. CPU1写入前发出占用总线信号（Bus Lock）
        // 2. CPU1将缓存行状态改为Modified
        // 3. 通过总线发出广播信号
        // 4. 其他CPU收到信号，将相应缓存行标记为Invalid
        // 5. 写入前发出解除锁总线信号（Bus Unlock）
    }
    
    // CPU2上的线程
    public void readOnCPU2() {
        // 1. CPU2发现缓存行是Invalid
        // 2. 从主内存重新读取数据
        // 3. 将新数据加载到缓存，状态改为Shared
        System.out.println(value);
    }
}
```


### 4. 指令重排 (Instruction Reordering)
指令重排是指编译器和处理器为了优化性能而对指令执行顺序进行调整的过程。虽然指令重排可以提高单线程性能，但在多线程环境下可能会导致内存可见性问题。

Java里通过内存屏障保证有序性，详见下一部分Memory Barriers。

#### 4.1 重排类型
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

#### 4.2 重排相关的语义
1. **With-Thread As-If-Serial 语义**：不管怎么重排（编译器和处理器为了提高并行度而进行的重排），单线程程序的执行结果不能被改变。编译器、运行时和处理器都必须遵守 As-If-Serial 语义。
2. **Happens-Before 关系**：JMM 定义了 happens-before 关系来确保内存可见性和有序性。如果一个操作 happens-before 另一个操作，那么第一个操作的结果对第二个操作是可见的，并且第一个操作的执行顺序在第二个操作之前。

#### 4.3 Happens-Before 规则
从JDK 5开始，Java使用新的JSR-133内存模型, JSR-133使用happens-before的概念来阐述操作之间的内存可见性。

1. **程序次序规则(Program order rule)**：在一个线程内，按照代码顺序，前面的操作 happens-before 于后面的操作。
2. **监视器锁规则(Monitor lock rule)**：对一个锁的解锁 happens-before 于随后对这个锁的加锁。
3. **volatile 变量规则(Volatile variable rule)**：对一个 volatile 变量的写操作 happens-before 于后续对这个 volatile 变量的读操作。
4. **线程启动规则**：Thread.start() 方法调用 happens-before 于启动线程中的每一个动作。
5. **线程终止规则**：线程中的所有操作 happens-before 于其他线程检测到该线程已经终止。
6. **线程中断规则(Thread termination rule)**：对线程的中断操作 happens-before 于被中断线程的代码检测到中断事件的发生。
7. **对象终结规则**：一个对象的构造函数执行完毕 happens-before 于该对象的 finalize() 方法的开始。
8. **传递性**：如果 A happens-before B，且 B happens-before C，那么 A happens-before C。

## 4. JMM Memory Barriers内存屏障
以下几种情况会自动插入内存屏障：
* 1. JVM自动插入 ：当使用 volatile 、 synchronized 、 final 等关键字时，JVM会根据需要自动插入适当的内存屏障
* 2. 并发工具类 ：使用 java.util.concurrent 包中的工具类时，内部实现会使用内存屏障
* 3. 原子类操作 ： java.util.concurrent.atomic 包中的原子类操作也会使用内存屏障

### 4.1 四种屏障类型

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


### 4.2 屏障的使用示例
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


### Reference Escape
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
## 5. 实际应用

### 5.1 单例模式中的内存问题
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

### 5.2 生产者-消费者模式
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


# Reference
* [Java内存模型（JMM）总结](https://zhuanlan.zhihu.com/p/29881777)

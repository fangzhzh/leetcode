# Java内存模型（JMM）详解
## 1. JVM内存架构基础
### 1.1 整体架构
在Java虚拟机中，内存被组织为两个主要部分：

- 主内存（Main Memory） ：所有线程共享的内存区域，存储所有变量的主副本
- 工作内存（Working Memory） ：每个线程私有的内存区域，存储线程操作的变量副本
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
### 1.2 线程工作内存的概念与实现 
#### 1.2.1 为什么需要线程工作内存
1. 并发编程模型需求
   
   - JMM规范定义了线程如何与主内存交互
   - 工作内存为线程提供了变量操作的隔离环境
   - 保证了线程操作的原子性和可见性控制
2. 性能优化
   
   - 减少线程对主内存的直接访问
   - 符合局部性原理，提高数据访问效率
   ```
   // 时间局部性示例
   for (int i = 0; i < 10000; i++) {
       counter++;  // counter会被缓存在工作内存中
   }
   
   // 空间局部性示例
   int[] array = new int[10000];
   for (int i = 0; i < array.length; i++) {
       array[i] = i;  // 连续的内存访问
   }
   ``` 
####   1.2.2 线程工作内存的硬件实现
CPU缓存是线程工作内存的物理实现：

1. 多级缓存结构
   
   ```
   CPU访问速度：寄存器 > L1缓存 > L2缓存 > 主内存
   - 寄存器访问：1个时钟周期
   - L1缓存访问：~3-4个时钟周期
   - L2缓存访问：~10个时钟周期
   - 主内存访问：~100个时钟周期
   ```
2. 缓存行（Cache Line）
   
   - CPU缓存的基本单位，通常为64字节
   - 一次读取操作会加载整个缓存行
   - 伪共享问题：多线程修改同一缓存行中的不同变量

### 1.3 JVM工作内存与主内存的交互操作 
#### 1.3.1 八种原子操作
JMM定义了8种原子操作，用于工作内存与主内存之间的交互：

1. lock（锁定） ：作用于主内存，将变量标识为线程独占
2. unlock（解锁） ：作用于主内存，将变量释放出来
3. read（读取） ：作用于主内存，将变量值读到工作内存
4. load（载入） ：作用于工作内存，将read的值载入变量副本
5. use（使用） ：作用于工作内存，读取变量的值
6. assign（赋值） ：作用于工作内存，将值赋给变量
7. store（存储） ：作用于工作内存，将变量值传送到主内存
8. write（写入） ：作用于主内存，将store的值写入变量 1.3.2 变量操作的实际过程

```java
public class MemoryOperationExample {
    private int value = 0;  // 主内存中的变量
    
    public void setValue(int v) {
        // 1. read: 从主内存读取value的值
        // 2. load: 将value载入工作内存
        // 3. assign: 将v赋值给工作内存中的value
        // 4. store: 将修改后的value传送到主内存
        // 5. write: 将value的值写入主内存
        this.value = v;
    }
    
    public int getValue() {
        // 1. read: 从主内存读取value的值
        // 2. load: 将value载入工作内存
        // 3. use: 从工作内存读取value的值
        return value;
    }
}
```

## 2. 并发编程中的三大核心问题
多线程环境下，工作内存与主内存的分离架构导致三大核心问题：

### 2.1 内存可见性问题（Memory Visibility）
问题定义 ：一个线程对共享变量的修改对其他线程不立即可见。

产生原因 ：

- 线程操作的是工作内存中的变量副本，而非主内存中的变量
- 线程对变量的修改可能滞留在工作内存中，未及时刷新到主内存
- 其他线程可能读取到过期的主内存数据
问题示例 ：

```java
public class VisibilityProblem {
    private int value = 0;
    
    // 线程A
    public void write() {
        value = 1;  // 写入工作内存，不一定立即刷新
        到主内存
    }
    
    // 线程B
    public void read() {
        // 从工作内存读取，可能读不到最新值
        System.out.println(value);
    }
}
```
解决机制 ：

- volatile关键字
- synchronized关键字
- final关键字（特定场景）
- java.util.concurrent包中的工具类
- 显式锁（Lock接口）
- 原子变量类

### 2.2 指令重排序问题（Instruction Reordering）
问题定义 ：编译器和处理器为了优化性能，可能会对指令执行顺序进行重新排序。

产生原因 ：

1. 编译器优化 ：编译器可能改变代码中语句的执行顺序
2. 处理器指令并行执行 ：现代CPU采用了指令级并行技术
3. 内存系统重排序 ：缓存、写缓冲区等导致的加载和存储操作乱序执行
问题示例 ：

```java
public class ReorderingProblem {
    private int a = 0;
    private boolean flag = false;
    
    // 线程A
    public void write() {
        a = 1;       // 语句1
        flag = true; // 语句2 - 可能被重排到语句1
        之前执行
    }
    
    // 线程B
    public void read() {
        if (flag) {  // 如果flag为true
            // 此时a可能为0，因为语句1和语句2可能被
            重排序
            System.out.println(a);
        }
    }
}
```
解决机制 ：

- volatile关键字（禁止重排序）
- synchronized关键字
- 内存屏障
- happens-before规则

### 2.3 原子性问题（Atomicity）
问题定义 ：一个操作或多个操作要么全部执行并且不会被任何因素打断，要么都不执行。

产生原因 ：

- Java中的非原子操作可能被线程调度机制打断
- 看似简单的操作实际包含多个步骤
问题示例 ：

```java
public class AtomicityProblem {
    private int counter = 0;
    
    public void increment() {
        // counter++ 实际包含三个操作：读取、递增、
        写入
        // 这三个操作可能被其他线程打断
        counter++;
    }
    
    public int getCounter() {
        return counter;
    }
}
```
解决机制 ：

- synchronized关键字
- 显式锁（Lock接口）
- 原子变量类（如AtomicInteger）
- CAS（Compare-And-Swap）操作

## 3. 问题与解决机制的映射关系
### 3.1 按问题索引解决机制 
#### 3.1.1 内存可见性问题的解决机制 

##### 语言级别机制
1. volatile关键字

机制原理 ：

- 对volatile变量的写操作会立即刷新到主内存
- 对volatile变量的读操作会从主内存重新加载，而不使用缓存
- 禁止指令重排序优化
适用场景 ：

- 单个变量的可见性保证
- 状态标志
- 双重检查锁定（DCL）单例模式
代码示例 ：

```java
public class VolatileVisibility {
    private volatile boolean flag = false;
    private int value = 0;
    
    public void write() {
        value = 42;  // 普通写入
        flag = true; // volatile写入，会强制刷新
        之前的普通写入
    }
    
    public void read() {
        if (flag) {  // volatile读取
            // 如果flag为true，则value的最新值
            （42）也会被读取到
            System.out.println(value);
        }
    }
}
```
底层实现 ：

- 在volatile变量写操作前插入StoreStore屏障
- 在volatile变量写操作后插入StoreLoad屏障
- 在volatile变量读操作前插入LoadLoad屏障
- 在volatile变量读操作后插入LoadStore屏障


2. synchronized关键字

机制原理 ：

- 线程进入synchronized块时，会清空工作内存中的共享变量，从主内存重新加载
- 线程退出synchronized块时，会将工作内存中的共享变量刷新到主内存
- 保证同一时刻只有一个线程执行同步块
适用场景 ：

- 需要互斥访问的场景
- 需要保证原子性、可见性和有序性的场景
代码示例 ：

```java
public class SynchronizedVisibility {
    private int counter = 0;
    
    public synchronized void increment() {
        counter++;
    }
    
    public synchronized int getCounter() {
        return counter;
    }
}
```
底层实现 ：

- 基于监视器（Monitor）实现
- 通过monitorenter和monitorexit指令控制同步块
- JVM会在同步块前后插入内存屏障

3. final关键字

机制原理 ：

- 被final修饰的字段在构造函数结束时，会保证其他线程看到的是初始化后的值
- 前提是不发生this引用逃逸（在构造函数完成前，不能将this引用暴露给其他线程）
适用场景 ：

- 不可变对象的设计
- 初始化后不再修改的场景
代码示例 ：

```java
public class FinalVisibility {
    private final int value;
    
    public FinalVisibility() {
        value = 42;
        // 构造函数结束后，其他线程看到的value必定是
        42
    }
    
    public int getValue() {
        return value;
    }
}
```
底层实现 ：

- 在构造函数退出前插入StoreStore屏障
- 防止final字段的初始化与构造函数之外的操作重排序 
##### API级别机制

1. java.util.concurrent.atomic包中的原子变量类

机制原理 ：

- 通过底层的CAS（Compare-And-Swap）操作保证原子性和可见性
- 内部使用volatile变量保证可见性
适用场景 ：

- 需要原子操作的单个变量
- 高并发计数器
- 无锁算法实现
代码示例 ：

```
public class AtomicVisibility {
    private AtomicInteger counter = new 
    AtomicInteger(0);
    
    public void increment() {
        counter.incrementAndGet(); // 原子操作，
        保证可见性
    }
    
    public int getCounter() {
        return counter.get(); // 保证读取到最新值
    }
}
```
底层实现 ：

- 基于CPU的原子指令（如x86的CMPXCHG指令）
- 内部使用volatile变量保证可见性

2. java.util.concurrent包中的显式锁（Lock接口）

机制原理 ：

- 提供比synchronized更灵活的锁定机制
- 同时保证内存可见性、原子性和有序性
适用场景 ：

- 需要灵活控制锁的场景（超时、可中断、条件变量等）
- 读写锁分离
- 锁降级
代码示例 ：

```java
public class LockVisibility {
    private final Lock lock = new ReentrantLock
    ();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }
    
    public int getCounter() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }
}
```
底层实现 ：

- 基于AQS（AbstractQueuedSynchronizer）框架
- 内部使用volatile变量和CAS操作保证可见性和原子性

3. 线程安全容器

机制原理 ：

- 内部实现了同步机制，保证内存可见性和线程安全
- 不同容器采用不同的同步策略
适用场景 ：

- 并发访问集合类的场景
- 生产者-消费者模式
代码示例 ：

```java
public class ConcurrentCollectionVisibility {
    // 线程安全的Map实现
    private ConcurrentHashMap<String, Integer> 
    map = new ConcurrentHashMap<>();
    // 线程安全的List实现
    private CopyOnWriteArrayList<String> list 
    = new CopyOnWriteArrayList<>();
    
    public void updateMap(String key, Integer 
    value) {
        map.put(key, value); // 线程安全，保证可
        见性
    }
    
    public void addToList(String item) {
        list.add(item); // 线程安全，保证可见性
    }
}
```
底层实现 ：

- ConcurrentHashMap：分段锁或CAS+synchronized
- CopyOnWriteArrayList：写时复制策略
- BlockingQueue：内部锁或条件变量 

##### 线程操作机制
1. Thread.start()和Thread.join()

机制原理 ：

- Java内存模型规定了线程启动和终止的happens-before关系
- 主线程在调用t.start()之前的操作，对线程t可见
- 子线程的操作，在主线程调用t.join()成功返回后可见
适用场景 ：

- 线程间的协作
- 主线程等待子线程完成
代码示例 ：

```java
public class ThreadVisibility {
    private int value = 0;
    
    public void startAndJoin() throws 
    InterruptedException {
        value = 10; // 在主线程中设置值
        
        Thread thread = new Thread(() -> {
            // 这里能看到value = 10
            value = 20; // 在子线程中修改值
        });
        
        thread.start();
        thread.join(); // 等待子线程完成
        
        // 这里能看到value = 20
        System.out.println(value);
    }
}
```
底层实现 ：

- 基于happens-before规则
- JVM保证线程启动和终止的内存可见性
2. 线程中断

机制原理 ：

- 线程中断操作与被中断线程检测中断之间存在happens-before关系
适用场景 ：

- 线程协作
- 取消长时间运行的任务
代码示例 ：

```java
public class InterruptVisibility {
    private int value = 0;
    
    public void interruptExample() throws 
    InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().
            isInterrupted()) {
                // 循环执行任务
            }
            // 线程被中断后执行清理
            value = 100; // 这个写入对中断线程可见
        });
        
        thread.start();
        // 执行一些操作
        thread.interrupt(); // 中断线程
        thread.join(); // 等待线程结束
        
        // 这里能看到value = 100
        System.out.println(value);
    }
}
```
底层实现 ：

- 基于happens-before规则
- JVM保证中断操作的内存可见性 
##### JDK 9+新特性
VarHandle

机制原理 ：

- JDK 9引入的 VarHandle 提供了比反射更轻量级的机制
- 可以对特定变量进行各种粒度的原子操作和内存屏障控制
适用场景 ：

- 需要细粒度内存访问控制的场景
- 性能敏感的底层代码
代码示例 ：

```
public class VarHandleExample {
    private int x;
    private static final VarHandle VH;
    
    static {
        try {
            VH = MethodHandles.lookup()
                .findVarHandle
                (VarHandleExample.class, "x", 
                int.class);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
    
    public void update(int value) {
        // 使用VarHandle进行原子更新，保证可见性
        VH.setVolatile(this, value);
    }
    
    public int get() {
        // 保证读取到最新值
        return (int) VH.getVolatile(this);
    }
}
```
底层实现 ：

- 直接映射到JVM内存访问操作
- 提供不同级别的内存屏障控制 
#### 3.1.2 指令重排序问题的解决机制 内存屏障（Memory Barriers）
机制原理 ：

- 内存屏障是一种CPU指令，用于控制特定条件下的重排序和内存可见性
- Java中的volatile、synchronized等关键字会根据需要自动插入内存屏障
四种屏障类型 ：

1. LoadLoad屏障 ：确保Load1数据的装载先于Load2及后续装载指令完成
   
   ```
   int x = volatileVar; // Load1 (volatile读取)
   // LoadLoad屏障
   int y = normalVar;   // Load2 (普通读取)
   ```
2. StoreStore屏障 ：确保Store1数据对其他处理器可见先于Store2及后续存储指令
   
   ```
   normalVar = 1;      // Store1 (普通写入)
   // StoreStore屏障
   volatileVar = 2;    // Store2 (volatile写入)
   ```
3. LoadStore屏障 ：确保Load1数据装载先于Store2及后续存储指令
   
   ```
   int x = volatileVar; // Load (volatile读取)
   // LoadStore屏障
   normalVar = 1;      // Store (普通写入)
   ```
4. StoreLoad屏障 ：确保Store1数据对其他处理器变得可见先于Load2及后续装载指令
   
   ```
   volatileVar = 1;    // Store (volatile写入)
   // StoreLoad屏障
   int x = volatileVar; // Load (volatile读取)
   ```
适用场景 ：

- volatile变量的读写
- synchronized的进入和退出
- final字段的初始化
- 并发工具类的实现
代码示例 ：

```
public class BarrierExample {
    private int a = 0;
    private volatile int v = 0;
    
    public void write() {
        a = 1;                  // Store1
        // StoreStore屏障（由JVM自动插入）
        v = 1;                  // Store2
        // StoreLoad屏障（由JVM自动插入）
    }
    
    public void read() {
        int i = v;              // Load1
        // LoadLoad屏障（由JVM自动插入）
        int j = a;              // Load2
        // LoadStore屏障（由JVM自动插入）
    }
}
```
底层实现 ：

- 不同处理器架构有不同的内存屏障指令
- x86/x64：较强的内存模型，某些屏障可能被优化掉
- ARM/POWER：较弱的内存模型，需要更多屏障 happens-before规则
机制原理 ：

- JMM定义了happens-before关系来确保内存可见性和有序性
- 如果操作A happens-before 操作B，则A的结果对B可见，且A的执行顺序在B之前
八大规则 ：

1. 程序次序规则 ：在单线程内，按照程序代码顺序执行
2. 监视器锁规则 ：对一个锁的解锁happens-before于后续对这个锁的加锁
3. volatile变量规则 ：对volatile变量的写happens-before于后续对该变量的读
4. 线程启动规则 ：Thread.start()的调用happens-before于线程中的任何操作
5. 线程终止规则 ：线程中的所有操作happens-before于其他线程检测到该线程已终止
6. 线程中断规则 ：对线程interrupt()方法的调用happens-before于被中断线程检测到中断
7. 对象终结规则 ：一个对象的构造函数执行结束happens-before于finalize()方法的开始
8. 传递性规则 ：如果A happens-before B，且B happens-before C，则A happens-before C
适用场景 ：

- 理解并发代码的内存可见性保证
- 设计线程安全的类和算法
代码示例 ：

```
public class HappenBeforeExample {
    private int value = 0;
    private volatile boolean flag = false;
    
    public void write() {
        value = 42;    // 写入普通变量
        flag = true;   // 写入volatile变量
        （volatile写规则）
    }
    
    public void read() {
        if (flag) {    // 读取volatile变量
        （volatile读规则）
            // 由于happens-before关系，这里能看到
            value = 42
            System.out.println(value);
        }
    }
}
```
底层实现 ：

- JVM通过内存屏障实现happens-before规则
- 编译器和运行时系统遵循这些规则进行优化 3.1.3 
#### 3.1.3 原子性问题的解决机制 
##### synchronized关键字
机制原理 ：

- 保证同一时刻只有一个线程执行同步块
- 自动获取锁和释放锁
适用场景 ：

- 需要互斥访问共享资源的场景
- 简单的同步需求
代码示例 ：

```
public class SynchronizedAtomicity {
    private int counter = 0;
    
    public synchronized void increment() {
        // 整个方法是原子的，不会被其他线程打断
        counter++;
    }
    
    public synchronized int getCounter() {
        return counter;
    }
}
```
底层实现 ：

- 基于对象监视器（Monitor）
- 锁升级机制（偏向锁、轻量级锁、重量级锁） 

##### Lock接口及其实现
机制原理 ：

- 提供比synchronized更灵活的锁定机制
- 显式的锁获取和释放
适用场景 ：

- 需要超时、可中断、条件变量等高级特性的场景
- 读写锁分离
代码示例 ：

```
public class LockAtomicity {
    private final Lock lock = new ReentrantLock
    ();
    private int counter = 0;
    
    public void increment() {
        lock.lock();
        try {
            // 这个操作是原子的，不会被其他线程打断
            counter++;
        } finally {
            lock.unlock();
        }
    }
    
    public int getCounter() {
        lock.lock();
        try {
            return counter;
        } finally {
            lock.unlock();
        }
    }
}
```
底层实现 ：

- 基于AQS（AbstractQueuedSynchronizer）框架
- 支持公平锁和非公平锁模式

##### 原子变量类
机制原理：

- 利用CAS（Compare-And-Swap）操作实现无锁算法
- 直接操作内存，避免线程上下文切换开销
适用场景：

- 计数器、标志位等简单共享变量
- 性能敏感的高并发场景
代码示例：

```java
public class AtomicCounter {
    private AtomicInteger counter = new AtomicInteger(0);
    
    public void increment() {
        // 原子操作，线程安全
        counter.incrementAndGet();
    }
    
    public int getCounter() {
        return counter.get();
    }
    
    // 复合操作示例
    public void updateIfGreaterThan(int expected) {
        // 自旋CAS实现复合原子操作
        int current;
        do {
            current = counter.get();
            if (current <= expected) {
                return;
            }
        } while (!counter.compareAndSet(current, current + 1));
    }
}
```
底层实现：

- 基于处理器的原子指令（如x86的CMPXCHG）
- 使用Unsafe类直接访问内存

##### 并发容器
机制原理：

- 内部使用细粒度锁或无锁算法保证线程安全
- 针对特定场景优化的数据结构
适用场景：

- 高并发读写的集合操作
- 需要线程安全且性能较好的容器
代码示例：

```java
public class ConcurrentMapExample {
    private ConcurrentHashMap<String, Integer> map = 
        new ConcurrentHashMap<>();
    
    public void update(String key) {
        // 原子更新操作
        map.compute(key, (k, v) -> v == null ? 1 : v + 1);
    }
    
    public Integer get(String key) {
        return map.get(key);
    }
}
```
底层实现：

- ConcurrentHashMap：分段锁或CAS操作
- CopyOnWriteArrayList：写时复制策略
- ConcurrentLinkedQueue：无锁算法

#### 3.1.4 综合比较与选择指南

| 同步机制 | 可见性 | 有序性 | 原子性 | 性能特性 | 适用场景 |
|---------|-------|-------|-------|---------|----------|
| **volatile** | ✓ | ✓ | ✗ | 读写都有内存屏障，但无锁开销 | 状态标志、单例DCL |
| **synchronized** | ✓ | ✓ | ✓ | JDK 6后优化显著，偏向锁/轻量级锁 | 复合操作、简单同步 |
| **final** | ✓ | ✓ | - | 仅初始化阶段保证，无运行时开销 | 不可变对象设计 |
| **Atomic类** | ✓ | ✓ | ✓ | 无锁但高竞争下性能下降 | 计数器、标志位 |
| **Lock接口** | ✓ | ✓ | ✓ | 比synchronized灵活但略高开销 | 需要高级特性的同步 |
| **并发容器** | ✓ | ✓ | ✓ | 针对特定场景优化 | 高并发集合操作 |
| **VarHandle** | ✓ | ✓ | ✓ | 细粒度内存访问控制 | 底层代码、JDK 9+ |

### 3.2 实际应用案例

#### 3.2.1 单例模式的内存语义

```java
public class Singleton {
    // volatile防止初始化过程中的指令重排序
    private static volatile Singleton instance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (instance == null) {  // 第一次检查
            synchronized (Singleton.class) {
                if (instance == null) {  // 第二次检查
                    // 以下操作可能重排序：
                    // 1. 分配内存
                    // 2. 初始化对象
                    // 3. 引用指向内存
                    // volatile防止2和3重排序
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

内存语义分析：

- volatile保证instance的写操作对其他线程立即可见
- volatile防止构造过程中的指令重排序，确保对象完全初始化后才被引用
- synchronized保证同一时刻只有一个线程执行实例化代码
- 双重检查避免每次获取实例都加锁

#### 3.2.2 生产者-消费者模式

```java
public class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 10;
    private final Object lock = new Object();
    private volatile boolean running = true;
    
    public void produce() throws InterruptedException {
        int value = 0;
        while (running) {
            synchronized (lock) {
                while (queue.size() == MAX_SIZE) {
                    // 队列满，等待消费者消费
                    lock.wait();
                }
                queue.add(value);
                System.out.println("Produced: " + value);
                value++;
                // 通知消费者
                lock.notifyAll();
            }
            Thread.sleep(100); // 模拟生产耗时
        }
    }
    
    public void consume() throws InterruptedException {
        while (running) {
            synchronized (lock) {
                while (queue.isEmpty()) {
                    // 队列空，等待生产者生产
                    lock.wait();
                }
                int value = queue.poll();
                System.out.println("Consumed: " + value);
                // 通知生产者
                lock.notifyAll();
            }
            Thread.sleep(200); // 模拟消费耗时
        }
    }
    
    public void stop() {
        running = false; // volatile保证可见性
    }
}
```

内存语义分析：

- synchronized保证queue的操作原子性和可见性
- wait/notify机制基于监视器锁实现线程协作
- volatile变量running保证停止信号对所有线程立即可见
- happens-before关系确保生产者的写入对消费者可见

### 4. 底层原理
### 4.1 协议 Cache Coherence Protocol（MESI）
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

### 5. 参考资料

1. Java语言规范（Java Language Specification）- 第17章 线程和锁
2. Java虚拟机规范（Java Virtual Machine Specification）
3. 《Java并发编程实战》（Java Concurrency in Practice）- Brian Goetz等
5. Doug Lea的并发编程文章：http://gee.cs.oswego.edu/dl/cpj/jmm.html
6. JSR-133 (Java Memory Model)规范文档
7. JDK源码中的java.util.concurrent包实现
